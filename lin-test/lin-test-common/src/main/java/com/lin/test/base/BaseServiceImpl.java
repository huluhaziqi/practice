package com.lin.test.base;

import com.lin.test.common.util.SpringContextUtil;

import java.lang.reflect.ParameterizedType;

public abstract class BaseServiceImpl<Mapper,Record,Example> implements BaseService<Record,Example> {

    private Mapper mapper;
    @Override
    public int countByExample(Example example) {
        return 0;
    }

    @Override
    public Record selectByPrimaryKey(Example example) {
        return null;
    }

    @Override
    public void initMapper() {
        mapper = SpringContextUtil.getBean(getMapperClass());
    }

    public Class<Mapper> getMapperClass(){
        return (Class<Mapper>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
