package cn.qiucode.cms.dao;

import cn.qiucode.cms.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
    public List<Menu> findUserPermissions(String username);

    /**
     * 查找用户菜单集合
     *
     * @param username 用户名
     * @return 用户菜单集合
     */
    public List<Menu> findUserMenus(String username);

    /**
     * 获取所有菜单数据
     * @param menu  参数对象
     * @return  菜单数据集
     */
    public List<Menu> selectList(Menu menu);

    /**
     * 根据菜单/按钮ID集合获取菜单集合数据
     * @param menuIds  菜单/按钮集合
     * @return  查找到的菜单/按钮集合数据
     */
    public List<Menu> selectByMenuIdsList(@Param("menuIds") List<String> menuIds);

    /**
     * 新增菜单/按钮
     * @param menu  需添加的菜单/按钮
     * @return   是否新增成功
     */
    public boolean insertMenu(Menu menu);

    /**
     * 根据菜单/按钮 ID更新
     * @param menu  需更新的菜单/按钮参数
     * @return  是否更新成功
     */
    public boolean updateById(Menu menu);

    /**
     * 批量删除菜单
     * @param menuIds  菜单ID集合
     * @return   是否删除成功
     */
    public boolean deleteBatchIds(@Param("menuIds") List<String> menuIds);

}
