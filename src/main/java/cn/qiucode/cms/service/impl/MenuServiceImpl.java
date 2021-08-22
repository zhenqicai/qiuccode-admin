package cn.qiucode.cms.service.impl;

import cn.qiucode.cms.dao.MenuDao;
import cn.qiucode.cms.entity.Menu;
import cn.qiucode.cms.entity.dto.MenuTree;
import cn.qiucode.cms.service.MenuService;
import cn.qiucode.cms.utils.TreeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        return null;
    }

    @Override
    public void createMenu(Menu menu) {

    }

    @Override
    public void updateMenu(Menu menu) {

    }

    @Override
    public void deleteMenus(String menuIds) {

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
}
