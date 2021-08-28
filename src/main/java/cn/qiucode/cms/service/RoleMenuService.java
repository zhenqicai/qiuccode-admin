package cn.qiucode.cms.service;

import cn.qiucode.cms.entity.RoleMenu;

import java.util.List;
import java.util.Set;

/**
 * @program: cms
 * @description: 角色菜单关联Service接口
 * @author: 上官江北
 * @create: 2021-08-23 22:00
 */
public interface RoleMenuService {

    /**
     * 批量新增角色菜单关联数据
     * @param roleMenu  需要批量新增的角色菜单数据集
     */
    public void batchSave(List<RoleMenu> roleMenu);

    /**
     * 通过角色ID删除
     * @param roleIds 角色ID
     */
    public void deleteRoleMenusByRoleId(List<String> roleIds);


    /**
     * 通过菜单ID集合查找关联的用户ID集合
     * @param menuIds 菜单ID集合
     * @return 用户ID集合
     */
    public Set<Long> findUserIdByMenuIds(List<String> menuIds);


    /**
     * 通过菜单（按钮）ID删除
     *
     * @param menuIds 菜单（按钮）ID
     */
    public void deleteRoleMenusByMenuId(List<String> menuIds);


}
