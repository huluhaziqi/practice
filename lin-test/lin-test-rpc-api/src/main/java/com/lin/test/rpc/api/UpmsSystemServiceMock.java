package com.lin.test.rpc.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lin.test.model.UpmsSystem;
import com.lin.test.model.UpmsSystemExample;
import com.lin.test.mapper.UpmsSystemMapper;
import com.lin.test.base.BaseServiceMock;

import java.util.List;
/**
* UpmsSystemService
* Create by linxiaobin on 2017-11-24.
*/

public class UpmsSystemServiceMock extends BaseServiceMock<UpmsSystemMapper,UpmsSystem,UpmsSystemExample>
implements UpmsSystemService{
    private static Logger logger = LoggerFactory.getLogger(UpmsSystemServiceMock.class);

}