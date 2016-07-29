package com.gome.splunk.common.jpa;

import com.gome.splunk.common.page.Page;
import com.gome.splunk.common.page.PageList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 功能：JPA dao 基础类，一个jpa的dao类继承此抽象类后，需要实现 setEntityManager 方法，注入数据源
 *  Created by liubing-ds3 on 2016/5/17..
 */
@Repository
public abstract class JpaBaseDaoImpl<T, PK extends Serializable> implements JpaBaseDao<T, PK> {

    private Class<T> entityClass;
    protected EntityManager entityManager;

    /**
     * 需要注意具体的数据源
     *
     * @param entityManager
     */
    protected abstract void setEntityManager(EntityManager entityManager);

    protected JpaBaseDaoImpl() {
        Class c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type)
                    .getActualTypeArguments();
            this.entityClass = (Class<T>) parameterizedType[0];
        }
    }

    private String getIdPropertyName() {
        String idProperty = entityManager.getMetamodel().entity(entityClass).getId(entityManager.getMetamodel().entity(entityClass).getIdType().getJavaType()).getName();
        return idProperty;
    }


    public T getById(PK id) {
        return entityManager.find(entityClass, id);
    }

    public T loadById(PK id) {
        return entityManager.getReference(entityClass, id);
    }

    public <T> T getPropertyById(PK id, String propertyName, Class<T> clazz) {
        Object obj = entityManager.createQuery("select " + propertyName + " from " + entityClass.getName() + " where " + getIdPropertyName() + " = :id").setParameter("id", id).getSingleResult();
        return obj == null ? null : (T) obj;
    }

    public void save(T model) {
        entityManager.persist(model);
    }

    public void saveOrUpdate(T model) {
        entityManager.merge(model);
    }

    public void delete(T model) {
        entityManager.remove(model);
    }

    public void deleteById(PK... id) {
        if (id != null) {
            for (PK pk : id) {
                if (pk != null) {
                    entityManager.remove(entityManager.getReference(entityClass, pk));
                }
            }
        }
    }

    public boolean deleteAll() {
        String hql = "delete " + this.entityClass.getName();
        this.execute(hql);
        return true;
    }

    public void execute(String hql, List<Object> params) {
        this.execute(hql, params != null ? params.toArray() : null);
    }

    public void execute(String hql, Object... params) {

        Query query = entityManager.createQuery(hql);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params);
        }
        query.executeUpdate();
    }

    public void incr(PK id, String propertyName) {
        entityManager.createQuery("update " + entityClass.getName() + " set " + propertyName + "=" + propertyName + "+1 where " + getIdPropertyName() + " =:id")
                .setParameter("id", id).executeUpdate();
    }

    public void incr(PK id, String propertyName, Integer n) {
        entityManager.createQuery("update " + entityClass.getName() + " set " + propertyName + "=" + propertyName + "+:n where " + getIdPropertyName() + " =:id")
                .setParameter("n", n).setParameter("id", id).executeUpdate();
    }

    public void update(T model) {
        entityManager.merge(model);
    }

    public void updateProperty(PK id, String propertyName, Object status) {
        entityManager.createQuery("update " + entityClass.getName() + " set " + propertyName + "=:v where " + getIdPropertyName() + " =:id")
                .setParameter("v", status).setParameter("id", id).executeUpdate();
    }

    public void updateProperties(PK id, String[] propertyNames, Object[] values) {
        String hql = "update " + entityClass.getName() + " set ";
        for (int i = 0; i < propertyNames.length; i++) {
            hql += propertyNames[i] + "= :v" + i;
            if (i + 1 < propertyNames.length) {
                hql += ",";
            }
        }
        hql += " where " + getIdPropertyName() + " =:id";
        Query query = entityManager.createQuery(hql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter("v" + i, values[i]);
        }
        query.setParameter("id", id).executeUpdate();
    }

    public List<T> findAll() {
        return entityManager.createQuery("from " + entityClass.getName()).getResultList();
    }

    public List<T> findListByProperty(String propertyName, Object value) {
        return entityManager.createQuery("from " + entityClass.getName() + " where " + propertyName + "=:v").setParameter("v", value).getResultList();

    }

    public <T> List<T> findListByProperty(Class<T> resultClass, String getProperName, String propertyName, Object value) {
        return entityManager.createQuery("select " + getProperName + " from " + entityClass.getName() + " where " + propertyName + "=:v").setParameter("v", value).getResultList();
    }

    public <T> List<T> findList(Class<T> clazz, String hql, List<Object> params) {
        return findList(clazz, hql, params != null ? params.toArray() : null);
    }

    public <T> List<T> findList(Class<T> clazz, String hql, Object... params) {

        Query query = entityManager.createQuery(hql);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }
        List resultList = query.getResultList();
        return resultList;
    }

    public <T> List<T> findListLimit(Class<T> clazz, String hql, int limit, List<Object> params) {

        return findListLimit(clazz, hql, limit, params != null ? params.toArray() : null);
    }

    public <T> List<T> findListLimit(Class<T> clazz, String hql, int limit, Object... params) {

        Query query = entityManager.createQuery(hql);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }
        query.setFirstResult(0);
        query.setMaxResults(limit);

        List resultList = query.getResultList();
        return resultList;
    }

    public <T> PageList<T> findPageList(Page page, Class<T> clazz, String hql, Object... params) {
        Query query = entityManager.createQuery(hql);
        if (page instanceof Page.Offset) {
            query.setFirstResult(((Page.Offset) page).getStart()).setMaxResults(
                    ((Page.Offset) page).getLimit());
        } else {
            query.setFirstResult((page.getPage() - 1) * page.getPageSize())
                    .setMaxResults(page.getPageSize());
        }
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i + 1, params[i]);
            }
        }
        page.setTotal(this.getTotalCount(hql, params));
        return new PageList(page, query.getResultList());
    }

    public <T> PageList<T> findPageList(Page page, Class<T> clazz, String hql, List<Object> params) {
        return findPageList(page, clazz, hql, params.toArray());
    }

    
    public T getUniqueResultByProperty(String propertyName, Object value) {
        return this.getUniqueResult("from " + this.entityClass.getName()
                + " where " + propertyName + "=?", value);
    }


    public void deleteResultsByProperty(String propertyName, Object value) {
        Query query = entityManager.createQuery("delete from " + entityClass.getName() + " where " + propertyName + " = ?");
        query.setParameter(1, value);
        query.executeUpdate();
    }

    public void deleteResultsByPropertyInValues(String propertyName, Object... value) {
        String s = StringUtils.leftPad("?", value.length * 2 - 1, "?,");
        Query query = entityManager.createQuery("delete from " + entityClass.getName() + " where " + propertyName + " in (" + s + ") ");
        for (int i = 0; i < value.length; i++) {
            query.setParameter(i + 1, value[i]);
        }
        query.executeUpdate();
    }

    public T getUniqueResult(String hql, Object... params) {
        return getUniqueResult(this.entityClass, hql, params);
    }

    public <T> T getUniqueResult(Class<T> clazz, String hql, List<Object> params) {
        Query q = entityManager.createQuery(hql);
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                q.setParameter(i + 1, params.get(i));
            }
        }
        q.setFirstResult(0);
        q.setMaxResults(1);
        List list = q.getResultList();
        if (list == null || list.isEmpty() || list.get(0) == null) {
            return null;
        }
        return (T) list.get(0);
    }

    public <T> T getUniqueResult(Class<T> clazz, String hql, Object... params) {
        return this.getUniqueResult(clazz, hql,
                params == null ? null : Arrays.asList(params));
    }

    public PageList<T> findByPage(Page page, String hql, List<Object> params) {
        Query q = entityManager.createQuery(hql);
        if (page instanceof Page.Offset) {
            q.setFirstResult(((Page.Offset) page).getStart()).setMaxResults(
                    ((Page.Offset) page).getLimit());
        } else {
            q.setFirstResult((page.getPage() - 1) * page.getPageSize())
                    .setMaxResults(page.getPageSize());
        }

        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                q.setParameter(i + 1, params.get(i));
            }
        }
        page.setTotal(this.getTotalCount(hql, params));
        return new PageList(page, q.getResultList());
    }


    public PageList<T> findByPage(Page page, String hql, Object... params) {
        return this.findByPage(page, hql, params == null ? null : Arrays.asList(params));
    }

    public PageList<T> findByPage(Page page) {
        return this.findByPage(page, "from " + this.entityClass.getName(),
                Collections.EMPTY_LIST);
    }

    public void flush() {
        entityManager.flush();
    }

    public void clear() {
        entityManager.clear();
    }

    public void evict(Object o) {
        entityManager.detach(o);
    }

    public boolean isExist(String propertyName, Object value) {
        String hql = "select count(*) from " + entityClass.getName()
                + " where " + propertyName + "=?";
        Query q = entityManager.createQuery(hql);
        q.setParameter(1, value);
        return (Long) q
                .setParameter(1, value).getSingleResult() > 0;
    }

    public long getTotalCount() {
        String hql = "select count(*) from " + entityClass.getName();
        return Long.valueOf(entityManager.createQuery(hql).getSingleResult()
                .toString());
    }

    public long getTotalCount(String hql, List<Object> params) {
        Query q = entityManager.createQuery(prepareCountHql(hql));
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                q.setParameter(i + 1, params.get(i));
            }
        }
        Object singleResult = q.getSingleResult();
        if (singleResult instanceof Object[]) {
            return Long.valueOf(((Object[]) singleResult)[0].toString()).longValue();
        }
        return Long.valueOf(singleResult.toString()).longValue();
    }

    protected String prepareCountHql(String hql) {
        String fromHql = hql;
        fromHql = " from " + StringUtils.substringAfter(fromHql, "from ");
        fromHql = StringUtils.substringBefore(fromHql, "order by");
//取出查询的字段
        String selectWhat = StringUtils.substringBetween(hql, "select", "from");
//        如果是new ClassName (x.x.x)格式的处理
        if (selectWhat != null && selectWhat.contains("new ") && selectWhat.contains("(") && selectWhat.contains(")")) {
            selectWhat = StringUtils.substringBetween(selectWhat, "(", ")");
        }
//        第一列查询总行数
        String countHql = "select count(*)" + (selectWhat == null ? "" : ", " + selectWhat + " ") + fromHql;
        return countHql;
    }

    public long getTotalCount(String hql, Object... params) {
        return this.getTotalCount(hql,
                params == null ? null : Arrays.asList(params));
    }

    public void beginTransaction() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
    }

    public void commitTransaction() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.commit();
    }

    public void rollbackTransaction() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.rollback();
    }


}
