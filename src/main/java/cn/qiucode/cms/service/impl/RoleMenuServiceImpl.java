package cn.qiucode.cms.service.impl;

import cn.qiucode.cms.dao.RoleMenuDao;
import cn.qiucode.cms.entity.RoleMenu;
import cn.qiucode.cms.service.AdminUserRoleService;
import cn.qiucode.cms.service.RoleMenuService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: cms
 * @description: 角色菜单关联Service实现类
 * @author: 上官江北
 * @create: 2021-08-23 22:03
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Autowired
    private AdminUserRoleService adminUserRoleService;

    @Override
    public void batchSave(List<RoleMenu> roleMenu) {
        roleMenuDao.batchInsert(roleMenu);
    }

    @Override
    public void deleteRoleMenusByRoleId(List<String> roleIds) {
        roleMenuDao.batchDeleteByRoleIds(roleIds);
    }

    @Override
    public Set<Long> findUserIdByMenuIds(List<String> menuIds) {
        List<RoleMenu> roleMenus = roleMenuDao.selectList(menuIds);
        if (CollectionUtils.isNotEmpty(roleMenus)) {
            List<String> roleIds = roleMenus.stream().map(RoleMenu::getRoleId)
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            return adminUserRoleService.findUserIdByRoleIds(roleIds);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoleMenusByMenuId(List<String> menuIds) {
        roleMenuDao.batchDeleteByMenuIds(menuIds);
    }
}
