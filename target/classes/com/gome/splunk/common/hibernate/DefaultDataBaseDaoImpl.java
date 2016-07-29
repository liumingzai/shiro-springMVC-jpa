package com.gome.splunk.common.hibernate;

import com.gome.splunk.common.jpa.JpaBaseDaoImpl;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * 默认数据库base dao
 *
 * @param <T>
 * @param <PK>
 */
@Repository
public abstract class DefaultDataBaseDaoImpl<T, PK extends Serializable> extends JpaBaseDaoImpl<T, PK> {


    //    如果继承了JPA的 dao 基础类，则需要实现此方法，用于注入EntityManager
    @PersistenceContext
    @Override
    protected void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
/*

public abstract class DefaultDataBaseDaoImpl<T, PK extends Serializable> extends HibernateBaseDaoImpl<T, PK> {

    //    如果继承了Hibernate 的dao基础类，则需要实现此方法，用于注入HibernateSessionFactory
    @Override
    @Resource
    protected void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}*/
