package com.lin.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> tClass){
        return context.getBean(beanName,tClass);
    }

    public static boolean containsBean(String beanName){
        return context.containsBean(beanName);
    }

    public static boolean isSingleton(String beanName){
        return context.isSingleton(beanName);
    }
    public static Class getType(String beanName){
        return context.getType(beanName);
    }

    public static <T> T getBeanByType(Class<T> tClass){
        return context.getBean(tClass);
    }

    public static <T> T getBean(Class<T> tClass){
        T t = null;
        Map<String,T> map = context.getBeansOfType(tClass);
        for(Map.Entry<String,T> entry : map.entrySet()){
            t = entry.getValue();
        }
        return t;
    }
}
