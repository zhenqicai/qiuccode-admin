package cn.qiucode.cms.dao;

import cn.qiucode.cms.entity.AdminUserRole;
import cn.qiucode.cms.entity.RoleMenu;
import com.google.common.collect.Lists;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: cms
 * @description: 用户角色关联DAO
 * @author: 上官江北
 * @create: 2021-08-25 22:16
 */
@Mapper
public interface AdminUserRoleDao {

    /**
     * 批量删除用户角色关联
     * @param roleIds  待删除的角色ID集合
     * @return  被删除的条数
     */
    public int batchDeleteByRoleIds(@Param("roleIds") List<String> roleIds);

    /**
     * 批量删除用户角色关联
     * @param userIds  待删除的用户ID集合
     * @return 被删除的条数
     */
    public int batchDeleteByUserIds(@Param("userIds") List<String> userIds);

    /**
     * 根据角色ID集合查找出用户角色数据
     * @param roleIds  角色ID
     * @return  查找到的用户角色关联数据
     */
    public List<AdminUserRole> selectList(@Param("roleIds") List<String> roleIds);

    /**
     * 根据角色ID集合查找出用户角色数据
     * @param roleId  角色ID
     * @return  查找到的用户角色关联数据
     */
    public default List<AdminUserRole> selectByRoleIdList(Long roleId){
        List<String> roleIdList = Lists.newArrayList(String.valueOf(roleId));
        return this.selectList(roleIdList);
    }

    /**
     * 批量新增用户角色关联表数据
     * @param adminUserRoles  需批量新增的数据
     * @return  是否批量新增成功
     */
    public boolean batchSave(List<AdminUserRole> adminUserRoles);
}
