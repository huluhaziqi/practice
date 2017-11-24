package com.lin.test.plugin;

import com.lin.test.common.util.AESUtil;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private String[] propertyNames = {
            "master.jdbc.password","slave.jdbc.password","generator.jdbc.password","master.redis.password"
    };

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        for(String p : propertyNames){
            if(p.equals(propertyName)){
                return AESUtil.AESDecode2(p,"AES");
            }
        }
        return super.convertProperty(propertyName, propertyValue);
    }
}
