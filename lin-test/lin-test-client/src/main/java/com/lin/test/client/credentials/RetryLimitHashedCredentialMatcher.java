package com.lin.test.client.credentials;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.concurrent.atomic.AtomicInteger;

public class RetryLimitHashedCredentialMatcher extends HashedCredentialsMatcher {

    private Ehcache ehcache;

    public RetryLimitHashedCredentialMatcher() {
        CacheManager cacheManager = CacheManager.newInstance(
                CacheManager.class.getClassLoader().getResource("ehcache.xml"));
        ehcache = cacheManager.getEhcache("passwordCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        Element element = ehcache.get(username);
        if (element == null) {
            element = new Element(username, new AtomicInteger(0));
            ehcache.put(element);
        }
        AtomicInteger atomicInteger = (AtomicInteger) element.getObjectValue();
        if (atomicInteger.incrementAndGet() > 5) {
            throw new ExcessiveAttemptsException("超过五次");
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            ehcache.remove(username);
        }
        return matches;
    }
}
