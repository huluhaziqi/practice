package com.lin.test.rpc.service.impl;


import com.lin.test.dao.model.UpmsPermission;
import com.lin.test.rpc.api.UpmsApiService;
import net.sf.json.util.JSONUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:META-INF/spring/applicationContext.xml",
        "classpath:META-INF/spring/applicationContext-jdbc.xml",
        "classpath:META-INF/spring/applicationContext-listener.xml",
        "classpath:META-INF/spring/applicationContext-dubbo-provider.xml"
})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UpmsApiServiceImplTest {

    @Autowired
    UpmsApiService upmsApiService;


    @Test
    public void testSelectUpmsPermissionByUpmsUserId(){
        Integer userId = 1;
        List<UpmsPermission> list = upmsApiService.selectUpmsPermissionByUpmsUserId(userId);
        for(UpmsPermission upmsPermission : list) {
            System.out.println(JSONUtils.valueToString(upmsPermission));
        }
    }
}
