package cn.qiucode.cms.service;

import cn.qiucode.cms.entity.Role;
import cn.qiucode.cms.utils.Page;

/**
 * @program: cms
 * @description: 角色Service
 * @author: 上官江北
 * @create: 2021-08-21 15:45
 */
public interface RoleService {

    /**
     * 分页获取角色列表数据
     * @param role        角色对象  查询条件
     * @param pageNow     当前页
     * @param pageSize    每页显示条数
     * @return  角色分页结果集
     */
    public Page<Role> rolePage(Role role, long pageNow, long pageSize);

}