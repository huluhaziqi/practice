package com.lin.test.rpc.service.impl;

import com.lin.test.annotation.BaseService;
import com.lin.test.base.BaseServiceImpl;
import com.lin.test.mapper.UpmsPermissionMapper;
import com.lin.test.model.UpmsPermission;
import com.lin.test.model.UpmsPermissionExample;
import com.lin.test.rpc.api.UpmsPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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