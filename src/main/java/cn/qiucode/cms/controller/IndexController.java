package cn.qiucode.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: qiucode-admin
 * @description: 首页Controller类
 * @author: 上官江北
 * @create: 2020-11-14 20:40
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping
    public String index(){
        return "index";
    }
}
