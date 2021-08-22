package cn.qiucode.cms.dao;

import cn.qiucode.cms.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * @param password   用户密码
     * @param username   用户名
     * @return     AdminUser
     */
    public AdminUser findOne(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查找用户
     * @param username  用户名
     * @return        返回查找到的用户对象
     */
    public AdminUser findByName(String username);

    /**
     * 获取用户总数   以便分页使用
     * @param user   用户对象参数
     * @return       用户总数
     */
    public long getUserCount(@Param("adminUser") AdminUser user);

    /**
     * 获取用户列表 以分页形式
     * @param user         用户参数对象
     * @param pageNow     当前页
     * @param pageSize    当页显示条数
     * @return            查询出当页用户数据集
     */
    public List<AdminUser> findUserList(@Param("adminUser") AdminUser user,@Param("pageNow") long pageNow, @Param("pageSize") long pageSize);

    /**
     * 创建用户
     * @param user  需创建的用户对象
     * @return      是否创建成功
     */
    public boolean saveAdminUser(AdminUser user);
}
