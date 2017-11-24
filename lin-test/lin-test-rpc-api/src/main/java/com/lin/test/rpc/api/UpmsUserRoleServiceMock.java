package com.lin.test.rpc.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lin.test.model.UpmsUserRole;
import com.lin.test.model.UpmsUserRoleExample;
import com.lin.test.mapper.UpmsUserRoleMapper;
import com.lin.test.base.BaseServiceMock;

import java.util.List;
/**
* UpmsUserRoleService
* Create by linxiaobin on 2017-11-24.
*/

public class UpmsUserRoleServiceMock extends BaseServiceMock<UpmsUserRoleMapper,UpmsUserRole,UpmsUserRoleExample>
implements UpmsUserRoleService{
    private static Logger logger = LoggerFactory.getLogger(UpmsUserRoleServiceMock.class);

}