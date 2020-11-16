package cn.qiucode.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: qiucode-admin
 * @description: 登录Controller类
 * @author: 上官江北
 * @create: 2020-11-16 21:38
 */
@Controller
@RequestMapping
public class LoginController {

    @RequestMapping("/")
    public String loginPage(){
        return "login";
    }
}
