package com.lin.test.rpc.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lin.test.model.UpmsRole;
import com.lin.test.model.UpmsRoleExample;
import com.lin.test.mapper.UpmsRoleMapper;
import com.lin.test.base.BaseServiceMock;

import java.util.List;
/**
* UpmsRoleService
* Create by linxiaobin on 2017-11-24.
*/

public class UpmsRoleServiceMock extends BaseServiceMock<UpmsRoleMapper,UpmsRole,UpmsRoleExample>
implements UpmsRoleService{
    private static Logger logger = LoggerFactory.getLogger(UpmsRoleServiceMock.class);

}