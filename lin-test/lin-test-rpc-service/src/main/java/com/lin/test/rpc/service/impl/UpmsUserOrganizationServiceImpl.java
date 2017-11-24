package com.lin.test.rpc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lin.test.dao.model.UpmsUserOrganization;
import com.lin.test.dao.model.UpmsUserOrganizationExample;
import com.lin.test.dao.mapper.UpmsUserOrganizationMapper;
import com.lin.test.rpc.api.UpmsUserOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import com.lin.test.annotation.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
/**
* UpmsUserOrganizationServiceImpl
* Create by linxiaobin on 2017-11-24.
*/

@Service
@Transactional
@BaseService
public class UpmsUserOrganizationServiceImpl extends BaseServiceImpl<UpmsUserOrganizationMapper, UpmsUserOrganization, UpmsUserOrganizationExample> implements UpmsUserOrganizationService {

    private static Logger logger = LoggerFactory.getLogger(UpmsUserOrganizationServiceImpl.class);

    @Autowired
    UpmsUserOrganizationMapper upmsUserOrganizationMapper;

}