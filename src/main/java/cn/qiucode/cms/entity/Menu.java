package cn.qiucode.cms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: cms
 * @description: 菜单实体类
 * @author: 上官江北
 * @create: 2021-08-07 11:33
 */
public class Menu implements Serializable {

    private static final long serialVersionUID = 5261057308366733979L;

    private Long menuId; //菜单ID

    private Long parentId; // 上级菜单ID

    private String menuName;  //菜单名称

    private String url; // 菜单URL

    private String perms ; //菜单权限标识

    private String icon ; //菜单图标

    private String type; //菜单类型  0 菜单  1 按钮

    private Long sort ; //菜单排序

    private Date createTime; //菜单创建时间

    private Date updateTime; //菜单修改时间


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
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
}
