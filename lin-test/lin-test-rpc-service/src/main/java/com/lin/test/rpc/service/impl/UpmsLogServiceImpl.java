package com.lin.test.rpc.service.impl;

import com.lin.test.annotation.BaseService;
import com.lin.test.base.BaseServiceImpl;
import com.lin.test.dao.mapper.UpmsLogMapper;
import com.lin.test.dao.model.UpmsLog;
import com.lin.test.dao.model.UpmsLogExample;
import com.lin.test.rpc.api.UpmsLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsLogServiceImpl
* Create by linxiaobin on 2017-11-24.
*/

@Service
@Transactional
@BaseService
public class UpmsLogServiceImpl extends BaseServiceImpl<UpmsLogMapper, UpmsLog, UpmsLogExample> implements UpmsLogService {

    private static Logger logger = LoggerFactory.getLogger(UpmsLogServiceImpl.class);

    @Autowired
    UpmsLogMapper upmsLogMapper;

}