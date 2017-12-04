package com.lin.test.rpc.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lin.test.dao.model.UpmsPermission;
import com.lin.test.dao.model.UpmsPermissionExample;
import com.lin.test.dao.mapper.UpmsPermissionMapper;
import com.lin.test.base.BaseServiceMock;

import java.util.List;
/**
* UpmsPermissionService
* Create by linxiaobin on 2017-12-01.
*/

public class UpmsPermissionServiceMock extends BaseServiceMock<UpmsPermissionMapper,UpmsPermission,UpmsPermissionExample>
implements UpmsPermissionService{
    private static Logger logger = LoggerFactory.getLogger(UpmsPermissionServiceMock.class);

}