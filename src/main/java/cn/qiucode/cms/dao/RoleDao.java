package cn.qiucode.cms.dao;

import cn.qiucode.cms.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: cms
 * @description: 角色DAO
 * @author: 上官江北
 * @create: 2021-08-21 15:28
 */
@Mapper
public interface RoleDao {

    /**
     * 获取角色总数   用以分页
     * @param role   角色参数对象
     * @return       角色总数
     */
    Long countRole(@Param("role") Role role);

    /**
     * 获取角色列表 以分页形式
     * @param role         角色参数对象
     * @param pageNow     当前页
     * @param pageSize    当页显示条数
     * @return            查询出当页角色数据集
     */
    public List<Role> findRoleList(@Param("role") Role role, @Param("pageNow") long pageNow, @Param("pageSize") long pageSize);

    /**
     * 根据ID更新角色
     * @param role  角色参数对象
     * @return  更新角色是否成功
     */
    public boolean updateById(Role role);
}
