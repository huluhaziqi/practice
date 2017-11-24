package com.lin.test.rpc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lin.test.dao.model.UpmsUserPermission;
import com.lin.test.dao.model.UpmsUserPermissionExample;
import com.lin.test.dao.mapper.UpmsUserPermissionMapper;
import com.lin.test.rpc.api.UpmsUserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import com.lin.test.annotation.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
/**
* UpmsUserPermissionServiceImpl
* Create by linxiaobin on 2017-11-24.
*/

@Service
@Transactional
@BaseService
public class UpmsUserPermissionServiceImpl extends BaseServiceImpl<UpmsUserPermissionMapper, UpmsUserPermission, UpmsUserPermissionExample> implements UpmsUserPermissionService {

    private static Logger logger = LoggerFactory.getLogger(UpmsUserPermissionServiceImpl.class);

    @Autowired
    UpmsUserPermissionMapper upmsUserPermissionMapper;

}