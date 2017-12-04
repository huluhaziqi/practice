package com.lin.test.rpc.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lin.test.dao.model.UpmsOrganization;
import com.lin.test.dao.model.UpmsOrganizationExample;
import com.lin.test.dao.mapper.UpmsOrganizationMapper;
import com.lin.test.base.BaseServiceMock;

import java.util.List;
/**
* UpmsOrganizationService
* Create by linxiaobin on 2017-12-01.
*/

public class UpmsOrganizationServiceMock extends BaseServiceMock<UpmsOrganizationMapper,UpmsOrganization,UpmsOrganizationExample>
implements UpmsOrganizationService{
    private static Logger logger = LoggerFactory.getLogger(UpmsOrganizationServiceMock.class);

}