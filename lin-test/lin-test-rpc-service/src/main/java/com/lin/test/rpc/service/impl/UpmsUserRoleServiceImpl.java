package com.lin.test.rpc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lin.test.dao.model.UpmsUserRole;
import com.lin.test.dao.model.UpmsUserRoleExample;
import com.lin.test.dao.mapper.UpmsUserRoleMapper;
import com.lin.test.rpc.api.UpmsUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import com.lin.test.annotation.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;
import com.lin.test.base.BaseServiceImpl;


import java.util.List;
/**
* UpmsUserRoleServiceImpl
* Create by linxiaobin on 2017-12-01.
*/

@Service
@Transactional
@BaseService
public class UpmsUserRoleServiceImpl extends BaseServiceImpl<UpmsUserRoleMapper, UpmsUserRole, UpmsUserRoleExample> implements UpmsUserRoleService {

    private static Logger logger = LoggerFactory.getLogger(UpmsUserRoleServiceImpl.class);

    @Autowired
    UpmsUserRoleMapper upmsUserRoleMapper;

}