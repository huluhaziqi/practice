package com.lin.test.client.shiro.chapter5.base64;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.BlowfishCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Assert;
import org.junit.Test;

import java.security.Key;


public class Base64Test {

    @Test
    public void testBase64() {
        String str = "hello";
        String base64Encoded = Base64.encodeToString(str.getBytes());
        System.out.println(base64Encoded);
        String result = Base64.decodeToString(base64Encoded);
        System.out.println(result);
        Assert.assertEquals(result, str);

        String base64 = Hex.encodeToString(str.getBytes());
        System.out.println(base64);
        String str2 = new String(Hex.decode(base64.getBytes()));
        System.out.println(str2);
        Assert.assertEquals(str, str2);
    }

    @Test
    public void testMD5() {
        String str = "liu";
        String salt = "f50842a02fdb259281f83e7412f060a7";
        String md5 = new Md5Hash(str, salt).toString();
        System.out.println(md5);

        String password = "123";
        String md52 = new Md5Hash(password,salt).toString();
        System.out.println(md52);
        //7dfe54ea69b2d07a597952e49374a1aebf3c10689444a83f0a084761c8a1c626
    }

    @Test
    public void testSha() {
        String str = "hello";
        String salt = "123";
        String sha = new Sha256Hash(str, salt).toString();
        System.out.println(sha);
    }

    @Test
    public void testHashSha() {
        DefaultHashService service = new DefaultHashService();

        //b1039379b0c8a52df0369891d1aaf97c7089ac336df0bfbd6d12bac3848fd097
        service.setHashAlgorithmName("SHA-256");
        //私钥
        service.setPrivateSalt(new SimpleByteSource("123"));
        //生成公钥
        service.setGeneratePublicSalt(true);
        //公盐生成器
        service.setRandomNumberGenerator(new SecureRandomNumberGenerator());
        //hash值迭代次数
        service.setHashIterations(2);

        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("SHA-256").setSource(ByteSource.Util.bytes("hello"))
                .setSalt(new SimpleByteSource("123")).setIterations(2).build();
        String hex = service.computeHash(request).toHex();
        System.out.println(hex);
    }

    @Test
    public void testAES() {
        AesCipherService service = new AesCipherService();
        Key key = service.generateNewKey(128);
        String text = "hello";
        String encrypt = service.encrypt(text.getBytes(), key.getEncoded()).toHex();
        System.out.println(encrypt);
        String text2 = new String(service.decrypt(Hex.decode(encrypt),key.getEncoded()).getBytes());
        System.out.println(text2);
        Assert.assertEquals(text,text2);
    }

    @Test
    public void testBlowfishCiperService(){
        BlowfishCipherService service = new BlowfishCipherService();
        service.setKeySize(128);
        //生成key
        Key key = service.generateNewKey();
        String text = "hello";
        //加密
        String encrptText = service.encrypt(text.getBytes(),key.getEncoded()).toHex();
        System.out.println(encrptText);
        String text2 = new String(service.decrypt(Hex.decode(encrptText),key.getEncoded()).getBytes());
        System.out.println(text2);
        //解密
    }
}
