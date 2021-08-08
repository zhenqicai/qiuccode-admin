package cn.qiucode.cms.service;

import cn.qiucode.cms.entity.AdminUser;

/**
 * @program: cms
 * @description:  用户相关类
 * @author: 上官江北
 * @create: 2021-07-25 22:44
 */
public interface AdminUserService {

    public AdminUser findOne(String username, String password);

    public AdminUser findByName(String username);
}
