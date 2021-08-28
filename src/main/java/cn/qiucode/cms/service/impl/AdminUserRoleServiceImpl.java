package cn.qiucode.cms.service.impl;

import cn.qiucode.cms.dao.AdminUserRoleDao;
import cn.qiucode.cms.entity.AdminUserRole;
import cn.qiucode.cms.service.AdminUserRoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: cms
 * @description: 用户角色关联Service实现类
 * @author: 上官江北
 * @create: 2021-08-25 22:20
 */
@Service("adminUserRoleService")
public class AdminUserRoleServiceImpl implements AdminUserRoleService {

    @Autowired
    private AdminUserRoleDao adminUserRoleDao;

    @Override
    public void deleteUserRolesByRoleId(List<String> roleIds) {
        adminUserRoleDao.batchDeleteByRoleIds(roleIds);
    }

    @Override
    public Set<Long> findUserIdByRoleId(Long roleId) {
        List<AdminUserRole> userRoles = adminUserRoleDao.selectByRoleIdList(roleId);
        if (CollectionUtils.isNotEmpty(userRoles)) {
            return userRoles.stream().map(AdminUserRole::getUserId).collect(Collectors.toSet());
        }
        return null;
    }

    @Override
    public Set<Long> findUserIdByRoleIds(List<String> roleIds) {
        List<AdminUserRole> userRoles = adminUserRoleDao.selectList(roleIds);
        if (CollectionUtils.isNotEmpty(userRoles)) {
            return userRoles.stream().map(AdminUserRole::getUserId).collect(Collectors.toSet());
        }
        return null;
    }
}
