package cn.qiucode.cms.service.impl;

import cn.qiucode.cms.dao.AdminUserDao;
import cn.qiucode.cms.entity.AdminUser;
import cn.qiucode.cms.service.AdminUserService;
import cn.qiucode.cms.utils.MD5util;
import cn.qiucode.cms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        password = MD5util.md5(password);
        return adminUserDao.findOne(username,password);
    }
    @Override
    public AdminUser findByName(String username){
        return adminUserDao.findByName(username);
    }

    @Override
    public Page<AdminUser> findUserList(AdminUser user, long pageNow, long pageSize){
        long startLimit = (pageNow-1)*pageSize;
        Page<AdminUser> page =  new Page<>();
        long totalCount = adminUserDao.getUserCount(user);
        List<AdminUser> userList = adminUserDao.findUserList(user,startLimit,pageSize);

        long totalPage=(totalCount % pageSize == 0) ? (totalCount/pageSize) : (totalCount/pageSize)+1;
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);
        page.setCurrentPage(pageNow);
        page.setData(userList);

        return page;
    }

    @Override
    @Transactional
    public void  createAdminUser(AdminUser user){
        //随机生成 7 位字符串作为密码盐
        String salt = MD5util.getRandomString(7);
        user.setPassword(MD5util.encrypt("123456",salt));
        user.setSalt(salt);
        adminUserDao.saveAdminUser(user);
    }
}
