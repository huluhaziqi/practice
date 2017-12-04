package com.lin.test.rpc.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lin.test.dao.model.UpmsUserPermission;
import com.lin.test.dao.model.UpmsUserPermissionExample;
import com.lin.test.dao.mapper.UpmsUserPermissionMapper;
import com.lin.test.base.BaseServiceMock;

import java.util.List;
/**
* UpmsUserPermissionService
* Create by linxiaobin on 2017-12-01.
*/

public class UpmsUserPermissionServiceMock extends BaseServiceMock<UpmsUserPermissionMapper,UpmsUserPermission,UpmsUserPermissionExample>
implements UpmsUserPermissionService{
    private static Logger logger = LoggerFactory.getLogger(UpmsUserPermissionServiceMock.class);

}