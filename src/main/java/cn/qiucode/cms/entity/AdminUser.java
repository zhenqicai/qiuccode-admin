package cn.qiucode.cms.entity;

import java.io.Serializable;

/**
 * @program: cms
 * @description: 用户实体类
 * @author: 上官江北
 * @create: 2021-07-25 22:46
 */
public class AdminUser implements Serializable {

    private static final long serialVersionUID = 6476614928897122932L;

    private Integer id;
    private String username;//后台管理员名称
    private String password;//后台管理员密码
    private String salt;    //盐值

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
