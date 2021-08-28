package cn.qiucode.cms.entity;

import java.io.Serializable;

/**
 * @program: cms
 * @description: 角色菜单关联类
 * @author: 上官江北
 * @create: 2021-08-23 21:40
 */
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = -1326201288824663295L;

    private Long roleId; //角色ID

    private Long menuId; //菜单ID

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
