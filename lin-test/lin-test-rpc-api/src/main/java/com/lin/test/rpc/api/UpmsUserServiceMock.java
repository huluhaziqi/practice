package com.lin.test.rpc.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lin.test.dao.model.UpmsUser;
import com.lin.test.dao.model.UpmsUserExample;
import com.lin.test.dao.mapper.UpmsUserMapper;
import com.lin.test.base.BaseServiceMock;

import java.util.List;
/**
* UpmsUserService
* Create by linxiaobin on 2017-12-01.
*/

public class UpmsUserServiceMock extends BaseServiceMock<UpmsUserMapper,UpmsUser,UpmsUserExample>
implements UpmsUserService{
    private static Logger logger = LoggerFactory.getLogger(UpmsUserServiceMock.class);

}