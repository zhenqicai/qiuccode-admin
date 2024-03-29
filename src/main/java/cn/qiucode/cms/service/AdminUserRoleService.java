package cn.qiucode.cms.service;

import cn.qiucode.cms.entity.AdminUserRole;

import java.util.List;
import java.util.Set;

/**
 * @program: cms
 * @description: 用户角色关联Service
 * @author: 上官江北
 * @create: 2021-08-25 22:18
 */
public interface AdminUserRoleService {

    /**
     * 通过角色ID删除
     * @param roleIds 角色ID
     */
    public void deleteUserRolesByRoleId(List<String> roleIds);

    /**
     * 通过用户ID删除
     * @param userIds 用户ID
     */
    public void deleteUserRolesByUserId(List<String> userIds);

    /**
     * 通过角色ID查找关联的用户ID
     * @param roleId 角色ID
     * @return 用户ID集合
     */
    public Set<Long> findUserIdByRoleId(Long roleId);
    /**
     * 通过角色ID集合查找关联的用户ID
     * @param roleIds 角色ID集合
     * @return 用户ID集合
     */
    public Set<Long> findUserIdByRoleIds(List<String> roleIds);

    /**
     * 批量用户角色关联数据
     * @param adminUserRoles  用户角色关联集合
     */
    public void saveBatch(List<AdminUserRole> adminUserRoles);
}
