package com.lin.test.rpc.service.impl;

import com.lin.test.annotation.BaseService;
import com.lin.test.base.BaseServiceImpl;
import com.lin.test.dao.mapper.UpmsOrganizationMapper;
import com.lin.test.dao.model.UpmsOrganization;
import com.lin.test.dao.model.UpmsOrganizationExample;
import com.lin.test.rpc.api.UpmsOrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
* UpmsOrganizationServiceImpl
* Create by linxiaobin on 2017-11-24.
*/

@Service
@Transactional
@BaseService
public class UpmsOrganizationServiceImpl extends BaseServiceImpl<UpmsOrganizationMapper, UpmsOrganization, UpmsOrganizationExample> implements UpmsOrganizationService {

    private static Logger logger = LoggerFactory.getLogger(UpmsOrganizationServiceImpl.class);

    @Autowired
    UpmsOrganizationMapper upmsOrganizationMapper;

}