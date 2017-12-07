package com.lin.test.client.shiro.chapter3.permission;

import org.apache.shiro.authz.Permission;
import org.springframework.util.StringUtils;

public class BitPermission implements Permission {

    private String resourceIdentity;
    private int permissionBit;
    private String instanceId;

    public BitPermission(String permissionString) {
        String[] arr = permissionString.split("\\+");
        if (arr.length > 1) {
            resourceIdentity = arr[1];
        }
        if (StringUtils.isEmpty(resourceIdentity)) {
            resourceIdentity = "*";
        }
        if (arr.length > 2) {
            permissionBit = Integer.valueOf(arr[2]);
        }
        if (arr.length > 3) {
            instanceId = arr[3];
        }
        if (StringUtils.isEmpty(instanceId)) {
            instanceId = "*";
        }
    }

    @Override
    public boolean implies(Permission p) {
        if (!(p instanceof BitPermission)) {
            return false;
        }

        BitPermission bitPermission = (BitPermission) p;
        if (!(("*").equals(resourceIdentity) || this.resourceIdentity.equals(bitPermission.resourceIdentity))) {
            return false;
        }

        if (!(permissionBit == 0 || ((permissionBit & bitPermission.permissionBit) != 0))) {
            return false;
        }

        if (!(("*").equals(instanceId) || this.instanceId.equals(bitPermission.instanceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BitPermission{" +
                "resourceIdentity='" + resourceIdentity + '\'' +
                ", permissionBit=" + permissionBit +
                ", instanceId='" + instanceId + '\'' +
                '}';
    }
}
