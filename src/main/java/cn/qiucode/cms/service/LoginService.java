package cn.qiucode.cms.service;

import java.util.Map;

/**
 * @program: cms
 * @description: 登录相关Service接口
 * @author: 上官江北
 * @create: 2021-07-29 21:50
 */
public interface LoginService {

    /**
     * 根据用户名、密码进行登录
     * @param userName  用户名
     * @param password  密码
     * @return    Map<String,Object>
     */
    public Map<String,Object> login(String userName, String password);

    /**
     * 退出操作
     */
    public void logout();

}
