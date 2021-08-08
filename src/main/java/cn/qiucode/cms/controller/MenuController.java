package cn.qiucode.cms.controller;

import cn.qiucode.cms.entity.AdminUser;
import cn.qiucode.cms.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: cms
 * @description: 菜单Controller
 * @author: 上官江北
 * @create: 2021-08-07 18:37
 */
@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("{username}")
    public Map<String,Object> getUserMenus(@PathVariable String username) throws Exception {

        AdminUser currUser = (AdminUser)SecurityUtils.getSubject().getPrincipal();

        if (!StringUtils.equalsIgnoreCase(username, currUser.getUsername())) {
            throw new Exception("您无权获取别人的菜单");
        }
        Map<String,Object> result = new HashMap<>();
        result.put("data",menuService.findUserMenus(username));
        return result;
    }
}
