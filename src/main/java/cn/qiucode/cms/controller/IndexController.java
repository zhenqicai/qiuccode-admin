package cn.qiucode.cms.controller;

import cn.qiucode.cms.entity.AdminUser;
import cn.qiucode.cms.service.AdminUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping("/")
    public String index(){
        return "redirect:/login";
    }

    @RequestMapping("index")
    public String toIndex(Model model){
        AdminUser currentUser = (AdminUser) SecurityUtils.getSubject().getPrincipal();
        AdminUser principal = adminUserService.findByName(currentUser.getUsername());
        principal.setPassword("It's a secret");
        principal.setSalt("It's a secret");
        model.addAttribute("user", principal);
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

    @GetMapping("/qiu/views/system/user/add")
    //@RequiresPermissions("user:add")
    public String systemUserAdd() {
        return "/qiu/views/system/user/userAdd";
    }


    @GetMapping("/qiu/views/system/user")
    //@RequiresPermissions("user:view")
    public String systemUser() {
        return "/qiu/views/system/user/user";
    }

    @GetMapping("/qiu/views/system/user/detail/{username}")
    //@RequiresPermissions("user:view")
    public String systemUserDetail(@PathVariable String username, Model model) {
        AdminUser adminUser = adminUserService.findByName(username);
        model.addAttribute("user",adminUser);
        return "/qiu/views/system/user/userDetail";
    }

    @GetMapping("/qiu/views/system/role")
    //@RequiresPermissions("role:view")
    public String systemRole() {
        return "/qiu/views/system/role/role";
    }

    @GetMapping("/qiu/views/system/menu")
    @RequiresPermissions("menu:view")
    public String systemMenu() {
        return "/qiu/views/system/menu/menu";
    }




}
