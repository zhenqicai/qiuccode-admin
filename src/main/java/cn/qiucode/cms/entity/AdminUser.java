package cn.qiucode.cms.entity;

import java.io.Serializable;
import java.util.Set;

/**
 * @program: cms
 * @description: 用户实体类
 * @author: 上官江北
 * @create: 2021-07-25 22:46
 */
public class AdminUser implements Serializable {

    private static final long serialVersionUID = 6476614928897122932L;

    private Long id;
    private String username;//后台管理员名称
    private String password;//后台管理员密码
    private String salt;    //盐值

    private String email; //用户邮箱
    private Integer status; // 状态 0锁定 1有效

    private String avatar; //用户头像

    /**
     * 以下数据库不关联
     */
    private String roleId;  //角色 ID

    private String roleName;  //角色

    private Set<String> stringPermissions;

    private Set<String> roles;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<String> getStringPermissions() {
        return stringPermissions;
    }

    public void setStringPermissions(Set<String> stringPermissions) {
        this.stringPermissions = stringPermissions;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
