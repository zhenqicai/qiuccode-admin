package cn.qiucode.cms.dao;

import cn.qiucode.cms.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @program: cms
 * @description:
 * @author: 上官江北
 * @create: 2021-07-25 22:52
 */
@Mapper
public interface AdminUserDao {

    /**
     * 根据用户名密码查找对应的用户
     *
     * @param password
     * @param username
     * @return
     */
    public AdminUser findOne(@Param("username") String username, @Param("password") String password);

    public AdminUser findByName(String username);
}
