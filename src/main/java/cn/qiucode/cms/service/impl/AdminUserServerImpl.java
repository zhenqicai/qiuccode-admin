package cn.qiucode.cms.service.impl;

import cn.qiucode.cms.dao.AdminUserDao;
import cn.qiucode.cms.entity.AdminUser;
import cn.qiucode.cms.entity.AdminUserRole;
import cn.qiucode.cms.entity.Menu;
import cn.qiucode.cms.entity.Role;
import cn.qiucode.cms.event.UserAuthenticationUpdatedEventPublisher;
import cn.qiucode.cms.service.AdminUserRoleService;
import cn.qiucode.cms.service.AdminUserService;
import cn.qiucode.cms.service.MenuService;
import cn.qiucode.cms.service.RoleService;
import cn.qiucode.cms.utils.MD5util;
import cn.qiucode.cms.utils.Page;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: cms
 * @description: 用户业务处理相关类
 * @author: 上官江北
 * @create: 2021-07-25 22:50
 */
@Service("adminUserService")
public class AdminUserServerImpl implements AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;

    @Autowired
    private AdminUserRoleService adminUserRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserAuthenticationUpdatedEventPublisher publisher;



    @Override
    public AdminUser findOne(String username, String password) {
        password = MD5util.md5(password);
        return adminUserDao.findOne(username,password);
    }
    @Override
    public AdminUser findByName(String username){
        return adminUserDao.findByName(username);
    }

    @Override
    public Page<AdminUser> findUserList(AdminUser user, long pageNow, long pageSize){
        long startLimit = (pageNow-1)*pageSize;
        Page<AdminUser> page =  new Page<>();
        long totalCount = adminUserDao.getUserCount(user);
        List<AdminUser> userList = adminUserDao.findUserList(user,startLimit,pageSize);

        long totalPage=(totalCount % pageSize == 0) ? (totalCount/pageSize) : (totalCount/pageSize)+1;
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);
        page.setCurrentPage(pageNow);
        page.setData(userList);

        return page;
    }

    @Override
    @Transactional
    public void  createAdminUser(AdminUser user){
        //随机生成 7 位字符串作为密码盐
        String salt = MD5util.getRandomString(7);
        user.setPassword(MD5util.encrypt("123456",salt));
        user.setSalt(salt);
        adminUserDao.saveAdminUser(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUsers(String[] userIds) {
        List<String> list = Arrays.asList(userIds);
        // 删除用户
        adminUserDao.deleteBatchIds(list);
        // 删除关联角色
        adminUserRoleService.deleteUserRolesByUserId(list);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(AdminUser user) {
        // 更新用户
        user.setPassword(null);
        user.setUsername(null);
        adminUserDao.updateById(user);

        String[] userId = {String.valueOf(user.getId())};
        adminUserRoleService.deleteUserRolesByUserId(Arrays.asList(userId));
        String[] roles = StringUtils.splitByWholeSeparatorPreserveAllTokens(user.getRoleId(), ",");
        setUserRoles(user, roles);

        publisher.publishEvent(Sets.newHashSet(user.getId()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(String[] usernames) {
        List<AdminUser> list = new ArrayList<>();
        Arrays.stream(usernames).forEach(username -> {
            AdminUser user = new AdminUser();
            String salt = MD5util.getRandomString(7);
            user.setPassword(MD5util.encrypt("123456",salt));
            user.setSalt(salt);
            list.add(user);
        });
        adminUserDao.batchUpdate(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(String username, String password) {
        AdminUser user = new AdminUser();
        String salt = MD5util.getRandomString(7);
        user.setPassword(MD5util.encrypt(password,salt));
        user.setSalt(salt);
        adminUserDao.updatePasswordByUserName(user);
    }

    @Override
    public void doGetUserAuthorizationInfo(AdminUser user) {
        // 获取用户角色集
        List<Role> roleList = roleService.findUserRole(user.getUsername());
        Set<String> roleSet = roleList.stream().map(Role::getRoleName).collect(Collectors.toSet());
        user.setRoles(roleSet);

        // 获取用户权限集
        List<Menu> permissionList = menuService.findUserPermissions(user.getUsername());
        Set<String> permissionSet = permissionList.stream().map(Menu::getPerms).collect(Collectors.toSet());
        user.setStringPermissions(permissionSet);
    }

    private void setUserRoles(AdminUser user, String[] roles) {
        List<AdminUserRole> userRoles = new ArrayList<>();
        Arrays.stream(roles).forEach(roleId -> {
            AdminUserRole userRole = new AdminUserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(Long.valueOf(roleId));
            userRoles.add(userRole);
        });
        adminUserRoleService.saveBatch(userRoles);
    }
}
