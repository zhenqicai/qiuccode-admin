package cn.qiucode.cms.service.impl;

import cn.qiucode.cms.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: cms
 * @description: 登录Service实现类
 * @author: 上官江北
 * @create: 2021-07-29 21:51
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Override
    public Map<String, Object> login(String userName, String password) {
        Map<String,Object> result=new HashMap<>();
        if(StringUtils.isBlank(userName)){
            result.put("message","用户名为空");
            result.put("success",false);
            return result;
        }
        String msg="";
        // 1、获取Subject实例对象
        Subject currentUser = SecurityUtils.getSubject();
        // 2、将用户名和密码封装到UsernamePasswordToken


        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try{
            currentUser.login(token);// 传到MyAuthorizingRealm类中的方法进行认证
            Session session = currentUser.getSession();
            session.setAttribute("userName", userName);
            result.put("message","登录成功！");
            result.put("success",true);
            return result;
        }catch (UnknownAccountException e){
            e.printStackTrace();
            msg="账号不存在!";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            msg="用户验证失败!";
        }
        result.put("message",msg);
        result.put("success",false);
        return result;
    }

    @GetMapping("index/{username}")
    @ResponseBody
    public Map<String, Object> index( @PathVariable String username) {
        // 更新登录时间
        //userService.updateLoginTime(username);
        // 获取首页数据
        Map<String, Object> result = new HashMap<>();//loginLogService.retrieveIndexPageData(username);
        Map<String, Object> data = new HashMap<>();
        data.put("totalVisitCount", 27);
        data.put("todayVisitCount", 345);
        data.put("todayIp", "127.0.0.1");
        // 获取近期系统访问记录
        data.put("lastSevenVisitCount", 124);
        data.put("lastSevenUserVisitCount", 136);

        result.put("code",200);
        result.put("data",data);
        return result;
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
