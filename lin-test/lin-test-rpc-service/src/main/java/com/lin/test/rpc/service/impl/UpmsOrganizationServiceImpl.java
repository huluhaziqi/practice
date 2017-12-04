package com.lin.test.rpc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lin.test.dao.model.UpmsOrganization;
import com.lin.test.dao.model.UpmsOrganizationExample;
import com.lin.test.dao.mapper.UpmsOrganizationMapper;
import com.lin.test.rpc.api.UpmsOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import com.lin.test.annotation.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;
import com.lin.test.base.BaseServiceImpl;


import java.util.List;
/**
* UpmsOrganizationServiceImpl
* Create by linxiaobin on 2017-12-01.
*/

@Service
@Transactional
@BaseService
public class UpmsOrganizationServiceImpl extends BaseServiceImpl<UpmsOrganizationMapper, UpmsOrganization, UpmsOrganizationExample> implements UpmsOrganizationService {

    private static Logger logger = LoggerFactory.getLogger(UpmsOrganizationServiceImpl.class);

    @Autowired
    UpmsOrganizationMapper upmsOrganizationMapper;

}