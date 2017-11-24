package com.lin.test.rpc.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lin.test.model.UpmsRolePermission;
import com.lin.test.model.UpmsRolePermissionExample;
import com.lin.test.mapper.UpmsRolePermissionMapper;
import com.lin.test.base.BaseServiceMock;

import java.util.List;
/**
* UpmsRolePermissionService
* Create by linxiaobin on 2017-11-24.
*/

public class UpmsRolePermissionServiceMock extends BaseServiceMock<UpmsRolePermissionMapper,UpmsRolePermission,UpmsRolePermissionExample>
implements UpmsRolePermissionService{
    private static Logger logger = LoggerFactory.getLogger(UpmsRolePermissionServiceMock.class);

}