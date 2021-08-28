package cn.qiucode.cms.dao;

import cn.qiucode.cms.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: cms
 * @description: 角色菜单关联DAO接口
 * @author: 上官江北
 * @create: 2021-08-23 21:51
 */
@Mapper
public interface RoleMenuDao {

    /**
     * 批量新增角色菜单关联表数据
     * @param roleMenu  需批量新增的数据
     * @return  是否批量新增成功
     */
    public boolean batchInsert(List<RoleMenu> roleMenu);

    /**
     * 批量删除角色菜单关联
     * @param roleIds  待删除的角色ID集合
     * @return  被删除的条数
     */
    public int batchDeleteByRoleIds(@Param("roleIds") List<String> roleIds);

    /**
     * 批量删除角色菜单关联
     * @param menuIds  待删除的菜单ID集合
     * @return  被删除的条数
     */
    public int batchDeleteByMenuIds(@Param("menuIds") List<String> menuIds);

    /**
     * 根据菜单ID 找出角色菜单关联数据
     * @param menuIds  菜单ID集合
     * @return   角色菜单关联集合数据
     */
    public List<RoleMenu> selectList(@Param("menuIds") List<String> menuIds);
}
