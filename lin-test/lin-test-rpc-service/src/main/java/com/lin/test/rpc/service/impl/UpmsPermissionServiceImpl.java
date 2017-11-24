package com.lin.test.rpc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lin.test.dao.model.UpmsPermission;
import com.lin.test.dao.model.UpmsPermissionExample;
import com.lin.test.dao.mapper.UpmsPermissionMapper;
import com.lin.test.rpc.api.UpmsPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import com.lin.test.annotation.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
/**
* UpmsPermissionServiceImpl
* Create by linxiaobin on 2017-11-24.
*/

@Service
@Transactional
@BaseService
public class UpmsPermissionServiceImpl extends BaseServiceImpl<UpmsPermissionMapper, UpmsPermission, UpmsPermissionExample> implements UpmsPermissionService {

    private static Logger logger = LoggerFactory.getLogger(UpmsPermissionServiceImpl.class);

    @Autowired
    UpmsPermissionMapper upmsPermissionMapper;

}