package com.lin.test.rpc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lin.test.dao.model.UpmsRole;
import com.lin.test.dao.model.UpmsRoleExample;
import com.lin.test.dao.mapper.UpmsRoleMapper;
import com.lin.test.rpc.api.UpmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import com.lin.test.annotation.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;
import com.lin.test.base.BaseServiceImpl;


import java.util.List;
/**
* UpmsRoleServiceImpl
* Create by linxiaobin on 2017-12-01.
*/

@Service
@Transactional
@BaseService
public class UpmsRoleServiceImpl extends BaseServiceImpl<UpmsRoleMapper, UpmsRole, UpmsRoleExample> implements UpmsRoleService {

    private static Logger logger = LoggerFactory.getLogger(UpmsRoleServiceImpl.class);

    @Autowired
    UpmsRoleMapper upmsRoleMapper;

}