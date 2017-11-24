package com.lin.test.rpc.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lin.test.model.UpmsUser;
import com.lin.test.model.UpmsUserExample;
import com.lin.test.mapper.UpmsUserMapper;
import com.lin.test.base.BaseServiceMock;

import java.util.List;
/**
* UpmsUserService
* Create by linxiaobin on 2017-11-24.
*/

public class UpmsUserServiceMock extends BaseServiceMock<UpmsUserMapper,UpmsUser,UpmsUserExample>
implements UpmsUserService{
    private static Logger logger = LoggerFactory.getLogger(UpmsUserServiceMock.class);

}