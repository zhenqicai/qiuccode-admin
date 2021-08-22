package cn.qiucode.cms.controller;

import cn.qiucode.cms.entity.Role;
import cn.qiucode.cms.service.RoleService;
import cn.qiucode.cms.utils.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: cms
 * @description: 角色Controller
 * @author: 上官江北
 * @create: 2021-08-21 15:25
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /*@GetMapping
    public Map<String,Object> getAllRoles(Role role) {
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);

        Map<String,Object> data = new HashMap<>();

        roleService.rolePage()
        data.put("rows",userPage.getData());
        data.put("total",userPage.getTotalCount());

        result.put("data",data);
        return result;
    } */

    @GetMapping("list")
    //@RequiresPermissions("role:view")
    public Map<String,Object> roleList(Role role, HttpServletRequest request) {

        long pageNum = request.getParameter("pageNum") == null ? 0L : Long.parseLong(request.getParameter("pageNum"));
        long pageSize = request.getParameter("pageSize") == null ? 10L : Long.parseLong(request.getParameter("pageSize"));

        Map<String,Object> result = new HashMap<>();
        result.put("code",200);

        Map<String,Object> data = new HashMap<>();

        Page<Role> rolePage = roleService.rolePage(role,pageNum,pageSize);
        data.put("rows",rolePage.getData());
        data.put("total",rolePage.getTotalCount());

        result.put("data",data);
        return result;
    }
}
