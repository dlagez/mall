package com.roczhang.mall.dao;

import com.roczhang.mall.mbg.model.UmsPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * 后台用户与角色管理自定义Dao
 */
@Repository
public interface UmsAdminRoleRelationDao {
    /**
     * 获取用户所有权限
     * @param adminId
     * @return
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}
