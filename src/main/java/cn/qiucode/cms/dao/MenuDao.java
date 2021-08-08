package cn.qiucode.cms.dao;

import cn.qiucode.cms.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: cms
 * @description: 菜单DAO
 * @author: 上官江北
 * @create: 2021-08-07 18:20
 */
@Mapper
public interface MenuDao {

    /**
     * 查找用户权限集
     *
     * @param username 用户名
     * @return 用户权限集合
     */
    List<Menu> findUserPermissions(String username);

    /**
     * 查找用户菜单集合
     *
     * @param username 用户名
     * @return 用户菜单集合
     */
    List<Menu> findUserMenus(String username);

}
