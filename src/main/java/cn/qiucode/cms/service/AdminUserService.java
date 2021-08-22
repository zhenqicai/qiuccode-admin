package cn.qiucode.cms.service;

import cn.qiucode.cms.entity.AdminUser;
import cn.qiucode.cms.utils.Page;

import java.util.Map;

/**
 * @program: cms
 * @description:  用户相关类
 * @author: 上官江北
 * @create: 2021-07-25 22:44
 */
public interface AdminUserService {

    /**
     * 根据用户名密码查找对应的用户
     * @param password   用户密码
     * @param username   用户名
     * @return      用户
     */
    public AdminUser findOne(String username, String password);


    /**
     * 根据用户名查找用户
     * @param username  用户名
     * @return        返回查找到的用户对象
     */
    public AdminUser findByName(String username);

    /**
     * 分页获取用户列表数据
     * @param user        用户对象  查询条件
     * @param pageNow     当前页
     * @param pageSize    每页显示条数
     * @return  用户分页结果集
     */
    public Page<AdminUser> findUserList(AdminUser user,long pageNow, long pageSize);

    /**
     * 创建用户
     * @param user 需创建的用户对象参数
     */
    public void  createAdminUser(AdminUser user);
}
