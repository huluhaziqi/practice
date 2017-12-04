package com.lin.test.rpc.api;

import com.lin.test.dao.model.*;

import java.util.List;

/**
 * @author linxiaobin
 * @date 2017年12月04日11:53:02
 */
public interface UpmsApiService {

    /**
     * 根据用户的id获取拥有的权限（用户和角色权限合集）
     *
     * @param upmsUserId
     * @return
     */
    List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);

    /**
     * 根据用户id获取拥有的权限，通过cache
     *
     * @param upmsUserId
     * @return
     */
    List<UpmsPermission> selectUpmsPermissionByUpmsUserIdByCache(Integer upmsUserId);


    /**
     * 根据用户id获取所属的角色
     *
     * @param upmsUserId
     * @return
     */
    List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId);

    /**
     * 根据用户id获取所属的角色
     *
     * @param upmsUserId
     * @return
     */
    List<UpmsRole> selectUpmsRoleByUpmsUserIdByCache(Integer upmsUserId);


    /**
     * 根据角色id获取所拥有的权限
     *
     * @param upmsRoleId
     * @return
     */
    List<UpmsRolePermission> selectUpmsRolePermissionByUpmsRoleId(Integer upmsRoleId);


    /**
     * 根据用户id获取所拥有的权限
     *
     * @param upmsUserId
     * @return
     */
    List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId);


    /**
     * 根据username获取upmsUser
     *
     * @param username
     * @return
     */

    UpmsUser selectUpmsUserByUsername(String username);

    /**
     * 写入操作日志
     *
     * @param upmsLog
     * @return
     */
    int insertUpmsLogSelective(UpmsLog upmsLog);

    /**
     * 根据条件获取系统数据
     *
     * @param upmsSystemExample
     * @return
     */
    List<UpmsSystem> selectUpmsSystemByExample(UpmsSystemExample upmsSystemExample);

    /**
     * 根据条件获取组织信息
     * @param upmsOrganizationExample
     * @return
     */
    List<UpmsOrganization> selectUpmsOrganizationByExample(UpmsOrganizationExample upmsOrganizationExample);
}
