package com.lin.test.rpc.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lin.test.model.UpmsOrganization;
import com.lin.test.model.UpmsOrganizationExample;
import com.lin.test.mapper.UpmsOrganizationMapper;
import com.lin.test.base.BaseServiceMock;

import java.util.List;
/**
* UpmsOrganizationService
* Create by linxiaobin on 2017-11-24.
*/

public class UpmsOrganizationServiceMock extends BaseServiceMock<UpmsOrganizationMapper,UpmsOrganization,UpmsOrganizationExample>
implements UpmsOrganizationService{
    private static Logger logger = LoggerFactory.getLogger(UpmsOrganizationServiceMock.class);

}