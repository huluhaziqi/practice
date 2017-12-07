package com.lin.test.client.shiro.chapter5.base64;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Assert;
import org.junit.Test;


public class Base64Test {

    @Test
    public void testBase64(){
        String str = "hello";
        String base64Encoded= Base64.encodeToString(str.getBytes());
        System.out.println(base64Encoded);
        String result = Base64.decodeToString(base64Encoded);
        System.out.println(result);
        Assert.assertEquals(result,str);

        String base64 = Hex.encodeToString(str.getBytes());
        System.out.println(base64);
        String str2 = new String(Hex.decode(base64.getBytes()));
        System.out.println(str2);
        Assert.assertEquals(str,str2);
    }

    @Test
    public void testMD5(){
        String str = "hello";
        String salt="123";
        String md5 = new Md5Hash(str,salt).toString();
        System.out.println(md5);
        //7dfe54ea69b2d07a597952e49374a1aebf3c10689444a83f0a084761c8a1c626
    }

    @Test
    public void testSha(){
        String str = "hello";
        String salt="123";
        String sha = new Sha256Hash(str,salt).toString();
        System.out.println(sha);
    }
}
