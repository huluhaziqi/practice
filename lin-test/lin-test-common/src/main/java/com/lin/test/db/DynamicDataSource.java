package com.lin.test.db;

import com.lin.test.enums.DataSourceEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);

    private static final ThreadLocal<String> t = new ThreadLocal<>();
    @Override
    protected Object determineCurrentLookupKey() {

        return getDataSource();
    }

    public static void setDatasource(String datasource){
        t.set(datasource);
    }


    public static String getDataSource(){
        String datasource = t.get();
        if(null == datasource){
            datasource = DataSourceEnum.MASTER.getDefault();
        }
        return datasource;
    }

    public static void clearDataSource(){
        t.remove();
    }


}
