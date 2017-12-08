package com.lin.test.client.shiro;

import org.apache.shiro.realm.jdbc.JdbcRealm;

public class JdbcSaltReaml extends JdbcRealm {
    public JdbcSaltReaml() {
        setSaltStyle(SaltStyle.COLUMN);
    }
}
