package com.lin.test.client.shiro.chapter6.realm.credentials;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.concurrent.atomic.AtomicInteger;

public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Ehcache ehcache;

    public RetryLimitHashedCredentialsMatcher() {
        CacheManager cacheManager = CacheManager.newInstance(
                CacheManager.class.getClassLoader().getResource("ehcache.xml"));
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = token.getPrincipal().toString();
        Element element = ehcache.get(username);
        if(element == null){
            element = new Element(username,new AtomicInteger(0));
            ehcache.put(element);
        }
        AtomicInteger atomicInteger = (AtomicInteger) element.getObjectValue();
        if(atomicInteger.incrementAndGet() > 5){
            throw new ExcessiveAttemptsException();
        }
        boolean isMatch = super.doCredentialsMatch(token,info);
        if(isMatch){
            ehcache.remove(username);
        }
        return isMatch;
    }
}
