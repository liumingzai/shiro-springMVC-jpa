package com.gome.splunk.common.hibernate;

import com.gome.splunk.common.jpa.JpaBaseDao;
import org.hibernate.Session;

import java.io.Serializable;

/**
 * 功能：
 * 作者： yangyan
 * 时间： 2014/12/21 .
 */
public interface HibernateDao<T, PK extends Serializable> extends JpaBaseDao<T, PK> {

    /**
     * 获取当前的Session
     *
     * @return
     */
     Session getCurrentSession();

    /**
     * 开启新的session
     *
     * @return
     */
     Session openSession();


    /**
     * 释放sessin
     *
     * @param session
     */
     void closeSession(Session session);
}
