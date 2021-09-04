package cn.qiucode.cms.controller;

import cn.qiucode.cms.service.AdminUserService;
import cn.qiucode.cms.service.LoginService;
import cn.qiucode.cms.utils.MD5util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: cms
 * @description: 登录Controller类
 * @author: 上官江北
 * @create: 2021-07-29 21:56
 */
@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin(Map<String, Object> map, HttpServletRequest request){
        loginService.logout();
        return "qiu/views/login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(String username, String password,String key){
        Map<String,Object> res=new HashMap<String,Object>();
        Map<String,Object> loginResult=loginService.login(username, password);
        boolean flag=(boolean)loginResult.get("success");
        if(flag){
            res.put("code",200);
            res.put("msg","登录成功！");
            res.put("url","/index");
        }else{
            res.put("code",-1);
            res.put("msg","账号密码不正确,登录失败！");
        }

        return res;
    }

    @RequestMapping("/logout")
    public String logOut(HttpSession session) {
        loginService.logout();
        return "qiu/views/login";
    }
}
