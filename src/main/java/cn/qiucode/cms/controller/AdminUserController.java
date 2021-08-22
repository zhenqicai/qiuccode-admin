package cn.qiucode.cms.controller;

import cn.qiucode.cms.entity.AdminUser;
import cn.qiucode.cms.service.AdminUserService;
import cn.qiucode.cms.utils.Page;
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
    //@RequiresPermissions("user:add")
    //@ControllerEndpoint(operation = "新增用户", exceptionMessage = "新增用户失败")
    public Map<String,Object> addUser( AdminUser user) {
        adminUserService.createAdminUser(user);
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        return result;
    }

    @GetMapping("list")
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


}
