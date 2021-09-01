package cn.qiucode.cms.service;

import cn.qiucode.cms.entity.Role;
import cn.qiucode.cms.utils.Page;

import java.util.List;

/**
 * @program: cms
 * @description: 角色Service
 * @author: 上官江北
 * @create: 2021-08-21 15:45
 */
public interface RoleService {

    /**
     * 分页获取角色列表数据
     * @param role        角色对象  查询条件
     * @param pageNow     当前页
     * @param pageSize    每页显示条数
     * @return  角色分页结果集
     */
    public Page<Role> rolePage(Role role, long pageNow, long pageSize);


    /**
     * 新增角色
     * @param role 待新增的角色
     */
    public void createRole(Role role);


    /**
     * 修改角色
     * @param role 待修改的角色
     */
    public void updateRole(Role role);

    /**
     * 删除角色
     * @param roleIds 待删除角色的 id
     */
    public void deleteRoles(String roleIds);

    /**
     * 通过用户名查找用户角色
     * @param username 用户名
     * @return 用户角色集合
     */
    public List<Role> findUserRole(String username);
}
