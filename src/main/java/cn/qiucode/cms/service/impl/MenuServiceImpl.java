package cn.qiucode.cms.service.impl;

import cn.qiucode.cms.dao.MenuDao;
import cn.qiucode.cms.entity.Menu;
import cn.qiucode.cms.entity.dto.MenuTree;
import cn.qiucode.cms.event.UserAuthenticationUpdatedEventPublisher;
import cn.qiucode.cms.service.MenuService;
import cn.qiucode.cms.service.RoleMenuService;
import cn.qiucode.cms.utils.TreeUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @program: cms
 * @description: 菜单业务实现类
 * @author: 上官江北
 * @create: 2021-08-07 18:27
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private UserAuthenticationUpdatedEventPublisher publisher;

    @Override
    public List<Menu> findUserPermissions(String username) {
        return menuDao.findUserPermissions(username);
    }

    @Override
    public MenuTree<Menu> findUserMenus(String username) {
        List<Menu> menus = menuDao.findUserMenus(username);
        List<MenuTree<Menu>> trees = convertMenus(menus);
        return TreeUtil.buildMenuTree(trees);
    }

    @Override
    public MenuTree<Menu> findMenus(Menu menu) {
        List<Menu> menus = menuDao.selectList(menu);
        List<MenuTree<Menu>> trees = convertMenus(menus);
        return TreeUtil.buildMenuTree(trees);
    }

    @Override
    public List<Menu> findMenuList(Menu menu) {
        return menuDao.selectList(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createMenu(Menu menu) {
        menu.setCreateTime(new Date());
        setMenu(menu);
        menuDao.insertMenu(menu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMenu(Menu menu) {
        menu.setUpdateTime(new Date());
        setMenu(menu);
        menuDao.updateById(menu);

        Set<Long> userIds = roleMenuService.findUserIdByMenuIds(Lists.newArrayList(String.valueOf(menu.getMenuId())));
        if(userIds != null && !userIds.isEmpty()){
            publisher.publishEvent(userIds);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMenus(String menuIds) {
        List<String> menuIdList = Arrays.asList(menuIds.split(","));
        delete(menuIdList);
        Set<Long> userIds = roleMenuService.findUserIdByMenuIds(menuIdList);
        if(userIds != null && !userIds.isEmpty()){
            publisher.publishEvent(userIds);
        }
    }

    private List<MenuTree<Menu>> convertMenus(List<Menu> menus) {
        List<MenuTree<Menu>> trees = new ArrayList<>();
        menus.forEach(menu -> {
            MenuTree<Menu> tree = new MenuTree<>();
            tree.setId(String.valueOf(menu.getMenuId()));
            tree.setParentId(String.valueOf(menu.getParentId()));
            tree.setTitle(menu.getMenuName());
            tree.setIcon(menu.getIcon());
            tree.setHref(menu.getUrl());
            tree.setData(menu);
            trees.add(tree);
        });
        return trees;
    }
    private void setMenu(Menu menu) {
        if (menu.getParentId() == null) {
            menu.setParentId(0L);
        }
        if ("1".equals(menu.getType())) {
            menu.setUrl(null);
            menu.setIcon(null);
        }
    }

    private void delete(List<String> menuIds) {
        List<String> list = new ArrayList<>(menuIds);
        menuDao.deleteBatchIds(menuIds);

        List<Menu> menus = menuDao.selectByMenuIdsList(menuIds);
        if(menus != null && !menus.isEmpty()){
            List<String> menuIdList = new ArrayList<>();
            menus.forEach(m -> menuIdList.add(String.valueOf(m.getMenuId())));
            list.addAll(menuIdList);
            roleMenuService.deleteRoleMenusByMenuId(list);
            delete(menuIdList);
        } else {
            roleMenuService.deleteRoleMenusByMenuId(list);
        }
    }
}
