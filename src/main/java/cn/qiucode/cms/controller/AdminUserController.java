package cn.qiucode.cms.controller;

import cn.qiucode.cms.entity.AdminUser;
import cn.qiucode.cms.service.AdminUserService;
import cn.qiucode.cms.utils.MD5util;
import cn.qiucode.cms.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: cms
 * @description: 用户管理Controller
 * @author: 上官江北
 * @create: 2021-08-14 09:56
 */
@RestController
@RequestMapping("user")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping
    @RequiresPermissions("user:add")
    public Map<String,Object> addUser( AdminUser user) {
        adminUserService.createAdminUser(user);
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        return result;
    }

    @GetMapping("list")
    @RequiresPermissions("user:view")
    public Map<String,Object> userList(AdminUser user, HttpServletRequest request){
        long pageNum = request.getParameter("pageNum") == null ? 0L : Long.parseLong(request.getParameter("pageNum"));
        long pageSize = request.getParameter("pageSize") == null ? 10L : Long.parseLong(request.getParameter("pageSize"));

        Map<String,Object> result = new HashMap<>();

        Map<String,Object> data = new HashMap<>();

        Page<AdminUser> userPage = adminUserService.findUserList(user,pageNum,pageSize);

        result.put("code", 200);

        data.put("rows",userPage.getData());
        data.put("total",userPage.getTotalCount());

        result.put("data",data);

        return result;
    }

    @GetMapping("delete/{userIds}")
    @RequiresPermissions("user:delete")
    public Map<String,Object> deleteUsers( @PathVariable String userIds) {
        adminUserService.deleteUsers(StringUtils.split(userIds, ","));
        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        return result;
    }

    @PostMapping("update")
    @RequiresPermissions("user:update")
    public Map<String,Object> updateUser(AdminUser user) {
        Map<String,Object> result = new HashMap<>();
        /*if (user.getId() == null) {
            throw new Exception("用户ID为空");
        } */
        adminUserService.updateUser(user);
        result.put("code", 200);
        return result;
    }

    @PostMapping("password/reset/{usernames}")
    @RequiresPermissions("user:password:reset")
    public Map<String,Object> resetPassword(@PathVariable String usernames) {
        adminUserService.resetPassword(StringUtils.split(usernames, ","));
        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        return result;
    }

    @PostMapping("password/update")
    public Map<String,Object> updatePassword( String oldPassword, String newPassword) {
        Map<String,Object> result = new HashMap<>();
        AdminUser user = (AdminUser)SecurityUtils.getSubject().getPrincipal();
        if (!StringUtils.equals(user.getPassword(), MD5util.encrypt(oldPassword,user.getSalt()))) {
            result.put("message", "原密码不正确");
            result.put("code", 400);
            return result;
        }
        adminUserService.updatePassword(user.getUsername(), newPassword);
        result.put("code", 200);
        return result;
    }


}
