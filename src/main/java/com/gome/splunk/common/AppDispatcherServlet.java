package com.gome.splunk.common;

import com.gome.splunk.common.util.PropUtil;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Created by liubing-ds3 on 2016/6/15.
 */
public class AppDispatcherServlet extends DispatcherServlet {


    @Override
    protected void onRefresh(ApplicationContext context) {
        super.onRefresh(context);
        //初始化配置
        PropUtil.init(context);
    }
}
