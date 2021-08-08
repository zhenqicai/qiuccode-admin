package cn.qiucode.cms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: cms
 * @description: 角色实体类
 * @author: 上官江北
 * @create: 2021-08-07 13:29
 */
public class Role implements Serializable {

    private static final long serialVersionUID = 6982457062549754222L;

    private Long roleId; //角色ID

    private String roleName; //角色名称

    private String remark; //角色描述

    private Date createTime; //创建时间

    private Date updateTime; //修改时间

    private String menuIds; //数据库额外字段 角色对应的菜单（按钮） ID

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }
}
