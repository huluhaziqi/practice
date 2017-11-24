package com.lin.test.base;

public interface BaseService<Record, Example> {
    int countByExample(Example example);

    Record selectByPrimaryKey(Example example);

    void initMapper();
}
