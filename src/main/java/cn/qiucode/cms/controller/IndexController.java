package cn.qiucode.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: qiucode-admin
 * @description: 首页Controller类
 * @author: 上官江北
 * @create: 2020-11-14 20:40
 */
@Controller
@RequestMapping
public class IndexController {

    @RequestMapping("/")
    //@ResponseBody
    public String index(){
        //return "admin/login";
        //return "views/login";
        return "redirect:/login";
    }

    @RequestMapping("index")
    public String toIndex(){
        return "index";
    }

    @GetMapping("index/{username}")
    @ResponseBody
    public Map<String, Object> index(@PathVariable String username) {
        // 更新登录时间
        //userService.updateLoginTime(username);
        // 获取首页数据
        Map<String, Object> data = new HashMap<>(8);
        // 获取系统访问记录
        data.put("totalVisitCount", 127);
        data.put("todayVisitCount", 234);
        data.put("todayIp", "127.0.0.1");
        // 获取近期系统访问记录
        data.put("lastSevenVisitCount", 235);
        data.put("lastSevenUserVisitCount", 137);
        //Map<String, Object> data = loginLogService.retrieveIndexPageData(username);
        Map<String, Object> result = new HashMap<>();
        result.put("code",200);
        result.put("data",data);
        return result;
    }

    @RequestMapping("/qiu/views/layout")
    public String layout(){

        return "qiu/views/layout";
    }

    @RequestMapping("/qiu/views/index")
    public String pageIndex() {
        return "/qiu/views/index";
    }



}
