package cn.qiucode.cms.service.impl;

import cn.qiucode.cms.dao.RoleDao;
import cn.qiucode.cms.entity.Role;
import cn.qiucode.cms.service.RoleService;
import cn.qiucode.cms.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: cms
 * @description: 角色Service实现类
 * @author: 上官江北
 * @create: 2021-08-21 15:49
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Page<Role> rolePage(Role role, long pageNow, long pageSize){
        long startLimit = (pageNow-1)*pageSize;
        Page<Role> page =  new Page<>();
        long totalCount = roleDao.countRole(role);
        List<Role> roleList = roleDao.findRoleList(role,startLimit,pageSize);

        long totalPage=(totalCount % pageSize == 0) ? (totalCount/pageSize) : (totalCount/pageSize)+1;
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);
        page.setCurrentPage(pageNow);
        page.setData(roleList);

        return page;
    }

}
