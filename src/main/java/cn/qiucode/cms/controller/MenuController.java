package cn.qiucode.cms.controller;

import cn.qiucode.cms.entity.AdminUser;
import cn.qiucode.cms.entity.Menu;
import cn.qiucode.cms.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("tree")
    public Map<String,Object> getMenuTree(Menu menu) {
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("data",menuService.findMenus(menu).getChilds());
        return result;
    }

    @PostMapping
    @RequiresPermissions("menu:add")
    public  Map<String,Object>  addMenu(Menu menu) {
        menuService.createMenu(menu);
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        return result;
    }

    @GetMapping("delete/{menuIds}")
    @RequiresPermissions("menu:delete")
    public  Map<String,Object>  deleteMenus(@PathVariable String menuIds) {
        menuService.deleteMenus(menuIds);
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        return result;
    }

    @PostMapping("update")
    @RequiresPermissions("menu:update")
    public Map<String,Object> updateMenu(Menu menu) {
        menuService.updateMenu(menu);
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        return result;
    }


}
