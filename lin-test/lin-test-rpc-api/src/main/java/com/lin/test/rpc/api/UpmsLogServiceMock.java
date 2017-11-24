package com.lin.test.rpc.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lin.test.dao.model.UpmsLog;
import com.lin.test.dao.model.UpmsLogExample;
import com.lin.test.dao.mapper.UpmsLogMapper;
import com.lin.test.base.BaseServiceMock;

import java.util.List;
/**
* UpmsLogService
* Create by linxiaobin on 2017-11-24.
*/

public class UpmsLogServiceMock extends BaseServiceMock<UpmsLogMapper,UpmsLog,UpmsLogExample>
implements UpmsLogService{
    private static Logger logger = LoggerFactory.getLogger(UpmsLogServiceMock.class);

}