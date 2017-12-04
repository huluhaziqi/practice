package com.lin.test.rpc.mapper;

import com.lin.test.dao.model.UpmsPermission;
import com.lin.test.dao.model.UpmsRole;

import java.util.List;

public interface UpmsApiMapper {

    List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);


    List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId);
}
