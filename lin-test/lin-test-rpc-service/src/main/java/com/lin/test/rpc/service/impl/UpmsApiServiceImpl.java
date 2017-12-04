package com.lin.test.rpc.service.impl;

import com.lin.test.dao.mapper.UpmsLogMapper;
import com.lin.test.dao.mapper.UpmsRolePermissionMapper;
import com.lin.test.dao.mapper.UpmsUserMapper;
import com.lin.test.dao.mapper.UpmsUserPermissionMapper;
import com.lin.test.dao.model.*;
import com.lin.test.rpc.api.UpmsApiService;
import com.lin.test.rpc.mapper.UpmsApiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UpmsApiServiceImpl implements UpmsApiService {


    private static Logger logger = LoggerFactory.getLogger(UpmsApiServiceImpl.class);

    @Autowired
    UpmsApiMapper upmsApiMapper;

    @Autowired
    UpmsUserMapper userMapper;

    @Autowired
    UpmsRolePermissionMapper upmsRolePermissionMapper;

    @Autowired
    UpmsUserPermissionMapper upmsUserPermissionMapper;

    @Autowired
    UpmsLogMapper upmsLogMapper;

    @Override
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId) {
        UpmsUser upmsUser = userMapper.selectByPrimaryKey(upmsUserId);
        if (null == upmsUser || 1 == upmsUser.getLocked()) {
            logger.info("selectUpmsPermissionByUpmsUserId : upmsUserId = {}", upmsUserId);
            return null;
        }
        return upmsApiMapper.selectUpmsPermissionByUpmsUserId(upmsUserId);
    }

    @Override
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserIdByCache(Integer upmsUserId) {
        return null;
    }

    @Override
    public List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId) {
        UpmsUser upmsUser = userMapper.selectByPrimaryKey(upmsUserId);
        if (null == upmsUser || 1 == upmsUser.getLocked()) {
            logger.info("selectUpmsRoleByUpmsUserId : upmsUserId {}", upmsUserId);
            return null;
        }
        return upmsApiMapper.selectUpmsRoleByUpmsUserId(upmsUserId);
    }

    @Override
    public List<UpmsRole> selectUpmsRoleByUpmsUserIdByCache(Integer upmsUserId) {
        return null;
    }

    @Override
    public List<UpmsRolePermission> selectUpmsRolePermissionByUpmsRoleId(Integer upmsRoleId) {
        UpmsRolePermissionExample upmsRolePermissionExample = new UpmsRolePermissionExample();
        upmsRolePermissionExample.createCriteria().andRoleIdEqualTo(upmsRoleId);
        List<UpmsRolePermission> result = upmsRolePermissionMapper.selectByExample(upmsRolePermissionExample);
        return result;
    }

    @Override
    public List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId) {
        UpmsUserPermissionExample upmsUserPermissionExample = new UpmsUserPermissionExample();
        upmsUserPermissionExample.createCriteria().andUserIdEqualTo(upmsUserId);
        List<UpmsUserPermission> result = upmsUserPermissionMapper.selectByExample(upmsUserPermissionExample);
        return result;
    }

    @Override
    public UpmsUser selectUpmsUserByUsername(String username) {
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        upmsUserExample.createCriteria().andUsernameEqualTo(username);
        List<UpmsUser> upmsUsers = userMapper.selectByExample(upmsUserExample);
        if (null != upmsUsers && upmsUsers.size() > 0) {
            return upmsUsers.get(0);
        }
        return null;
    }

    @Override
    public int insertUpmsLogSelective(UpmsLog upmsLog) {
        return upmsLogMapper.insertSelective(upmsLog);
    }

    @Override
    public List<UpmsSystem> selectUpmsSystemByExample(UpmsSystemExample upmsSystemExample) {
        return null;
    }

    @Override
    public List<UpmsOrganization> selectUpmsOrganizationByExample(UpmsOrganizationExample upmsOrganizationExample) {
        return null;
    }
}
