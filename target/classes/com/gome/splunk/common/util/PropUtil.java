package com.gome.splunk.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Properties;

/**
 * Created by liubing-ds3 on 2016/6/15.
 */
public class PropUtil implements ApplicationContextAware {

    public static org.springframework.context.ApplicationContext context = null;

    public static void init(org.springframework.context.ApplicationContext context) {
        PropUtil.context = context;
        // 配置文件properties
        Properties config = (Properties) context.getBean("config");
        //用户名
        AppContext.username = config.getProperty("username");
        //密码
        AppContext.password = config.getProperty("password");
    }


    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        this.context = context;
    }

    public synchronized static Object getBean(String beanName) {
        return context.getBean(beanName);
    }


}
