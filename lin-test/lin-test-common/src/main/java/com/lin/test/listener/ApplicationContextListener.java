package com.lin.test.listener;

import com.lin.test.annotation.BaseService;
import com.lin.test.base.BaseInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;
import java.util.Map;

public class ApplicationContextListener implements ApplicationListener<ContextRefreshedEvent> {

    private static Logger logger = LoggerFactory.getLogger(ApplicationContextListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //root application 要不执行两次
        if (null == event.getApplicationContext().getParent()) {
            Map<String, Object> services = event.getApplicationContext().getBeansWithAnnotation(BaseService.class);
            for (Object service : services.values()) {
                logger.debug(" {} >>>>initMapper", service.getClass().getName());
                try {
                    Method method = service.getClass().getMethod("initMapper");
                    method.invoke(service);
                } catch (Exception e) {
                    logger.error("BaseService 出错！");

                }
            }

            Map<String, BaseInterface> baseInterfaces = event.getApplicationContext().getBeansOfType(BaseInterface.class);

            for (BaseInterface baseInterface : baseInterfaces.values()) {
                logger.debug("{} >>>>> init");
                try {
                    Method method = baseInterface.getClass().getMethod("init");
                    method.invoke(baseInterface);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("baseInterface 出错！");
                }
            }
        }
    }
}
