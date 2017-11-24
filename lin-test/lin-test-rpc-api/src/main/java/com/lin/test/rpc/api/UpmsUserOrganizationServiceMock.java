package com.lin.test.rpc.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lin.test.dao.model.UpmsUserOrganization;
import com.lin.test.dao.model.UpmsUserOrganizationExample;
import com.lin.test.dao.mapper.UpmsUserOrganizationMapper;
import com.lin.test.base.BaseServiceMock;

import java.util.List;
/**
* UpmsUserOrganizationService
* Create by linxiaobin on 2017-11-24.
*/

public class UpmsUserOrganizationServiceMock extends BaseServiceMock<UpmsUserOrganizationMapper,UpmsUserOrganization,UpmsUserOrganizationExample>
implements UpmsUserOrganizationService{
    private static Logger logger = LoggerFactory.getLogger(UpmsUserOrganizationServiceMock.class);

}