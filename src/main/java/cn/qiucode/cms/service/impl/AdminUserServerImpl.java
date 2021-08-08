package cn.qiucode.cms.service.impl;

import cn.qiucode.cms.dao.AdminUserDao;
import cn.qiucode.cms.entity.AdminUser;
import cn.qiucode.cms.service.AdminUserService;
import cn.qiucode.cms.utils.MD5util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: cms
 * @description: 用户业务处理相关类
 * @author: 上官江北
 * @create: 2021-07-25 22:50
 */
@Service("adminUserService")
public class AdminUserServerImpl implements AdminUserService {

    @Autowired
    private AdminUserDao adminUserDao;



    @Override
    public AdminUser findOne(String username, String password) {
        password= MD5util.md5(password);
        return adminUserDao.findOne(username,password);
    }
    @Override
    public AdminUser findByName(String username){
        return adminUserDao.findByName(username);
    }
}
