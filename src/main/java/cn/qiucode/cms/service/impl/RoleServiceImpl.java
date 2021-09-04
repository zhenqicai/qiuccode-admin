package cn.qiucode.cms.service.impl;

import cn.qiucode.cms.dao.RoleDao;
import cn.qiucode.cms.entity.Role;
import cn.qiucode.cms.entity.RoleMenu;
import cn.qiucode.cms.event.UserAuthenticationUpdatedEventPublisher;
import cn.qiucode.cms.service.AdminUserRoleService;
import cn.qiucode.cms.service.RoleMenuService;
import cn.qiucode.cms.service.RoleService;
import cn.qiucode.cms.utils.Page;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @program: cms
 * @description: 角色Service实现类
 * @author: 上官江北
 * @create: 2021-08-21 15:49
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private AdminUserRoleService adminUserRoleService;

    @Autowired
    private UserAuthenticationUpdatedEventPublisher publisher;

    @Override
    public Page<Role> rolePage(Role role, long pageNow, long pageSize){
        long startLimit = (pageNow-1)*pageSize;
        Page<Role> page =  new Page<>();
        long totalCount = roleDao.countRole(role);
        List<Role> roleList = roleDao.findRoleList(role,startLimit,pageSize);

        long totalPage=(totalCount % pageSize == 0) ? (totalCount/pageSize) : (totalCount/pageSize)+1;
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);
        page.setCurrentPage(pageNow);
        page.setData(roleList);

        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRole(Role role) {
        role.setCreateTime(new Date());
        roleDao.saveRole(role);
        saveRoleMenus(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(Role role) {
        role.setUpdateTime(new Date());
        roleDao.updateById(role);
        List<String> roleIdList = Lists.newArrayList(String.valueOf(role.getRoleId()));
        roleMenuService.deleteRoleMenusByRoleId(roleIdList);
        saveRoleMenus(role);
        Set<Long> userIds = adminUserRoleService.findUserIdByRoleId(role.getRoleId());
        if (CollectionUtils.isNotEmpty(userIds)) {
            publisher.publishEvent(userIds);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoles(String roleIds) {
        List<String> list = Arrays.asList(roleIds.split(","));
        roleDao.deleteByBatch(list);
        roleMenuService.deleteRoleMenusByRoleId(list);
        adminUserRoleService.deleteUserRolesByRoleId(list);

        Set<Long> userIds = adminUserRoleService.findUserIdByRoleIds(list);
        if (CollectionUtils.isNotEmpty(userIds)) {
            publisher.publishEvent(userIds);
        }
    }

    @Override
    public List<Role> findUserRole(String username) {
        return roleDao.findUserRole(username);
    }

    @Override
    public List<Role> findRoles(Role role) {
        return roleDao.findRoles(role);
    }

    /**
     * 在新增角色时，对应的在角色菜单关联表批量新增数据
     * @param role   新增的角色数据
     */
    private void saveRoleMenus(Role role) {
        if (StringUtils.isNotBlank(role.getMenuIds())) {
            String[] menuIds = role.getMenuIds().split(",");
            List<RoleMenu> roleMenus = Lists.newArrayList();
            Arrays.stream(menuIds).forEach(menuId -> {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(Long.valueOf(menuId));
                roleMenu.setRoleId(role.getRoleId());
                roleMenus.add(roleMenu);
            });
            roleMenuService.batchSave(roleMenus);
        }
    }
}
