package cn.qiucode.cms.entity;

import java.io.Serializable;

/**
 * @program: cms
 * @description: 用户角色关联 Entity
 * @author: 上官江北
 * @create: 2021-08-26 23:09
 */
public class AdminUserRole implements Serializable {

    private static final long serialVersionUID = -8391608276497411046L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
