package cn.qiucode.cms.service;

import java.util.Map;

/**
 * @program: cms
 * @description: 登录相关Service接口
 * @author: 上官江北
 * @create: 2021-07-29 21:50
 */
public interface LoginService {

    public Map<String,Object> login(String userName, String password);

    public void logout();

}
