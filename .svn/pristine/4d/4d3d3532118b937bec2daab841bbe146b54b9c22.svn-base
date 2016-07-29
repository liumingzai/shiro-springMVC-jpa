package com.gome.splunk.common.hibernate;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Hibernate dao 基础类，一个Hibernate的dao类继承此抽象类后，需要实现 setSessionFactory 方法，注入数据源
 *
 * @param <T>  对象类型
 * @param <PK> 对象主键类型
 */
@Repository
public abstract class HibernateBaseDaoImpl<T, PK extends Serializable> implements
        HibernateDao<T, PK> {


    protected SessionFactory sessionFactory;

    /**
     * 注入对应的数据源
     *
     * @param sessionFactory
     */
    protected abstract void setSessionFactory(SessionFactory sessionFactory);

    private Class<T> entityClass;

    protected HibernateBaseDaoImpl() {

        Class c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type)
                    .getActualTypeArguments();
            this.entityClass = (Class<T>) parameterizedType[0];
        }
    }


    public Session getCurrentSession() {
        Session s = sessionFactory.getCurrentSession();


        return s;
//        if (ManagedSessionContext.hasBind(sessionFactory)) {
//
//        } else {
//            Session s = openSession();
//            ManagedSessionContext.bind(s);
//            return s;
//        }
    }

    public Session openSession() {
        return sessionFactory.openSession();
    }

    public void beginTransaction() {
        getCurrentSession().beginTransaction();
    }

    public void commitTransaction() {
        getCurrentSession().getTransaction().commit();
    }

    public void rollbackTransaction() {
        getCurrentSession().getTransaction().rollback();
    }

    public void closeSession(Session session) {
        session.close();
    }

    public T getById(PK id) {
        return (T) getCurrentSession().get(entityClass, id);
    }

    public T loadById(PK id) {
        return (T) getCurrentSession().load(entityClass, id);
    }

    public <T1> T1 getPropertyById(PK id, String propertyName, Class<T1> clazz) {
        return getUniqueResult(clazz, "select " + propertyName + " from " + entityClass.getName() + " where " + sessionFactory.getClassMetadata(this.entityClass).getIdentifierPropertyName() + "=?", id);
    }

    public void save(T model) {
        getCurrentSession().save(model);
    }

    public void saveOrUpdate(T model) {
        getCurrentSession().saveOrUpdate(model);
    }

    public List<T> findListByProperty(String propertyName, Object value) {
        Query query = getCurrentSession().createQuery("from " + entityClass.getName() + " where " + propertyName + "=?");
        query.setParameter(0, value);
        return query.list();
    }

    public <T> List<T> findListByProperty(Class<T> resultClass, String getPropertyName, String propertyName, Object value) {
        Query query = getCurrentSession().createQuery("select " + getPropertyName + " from " + entityClass.getName() + " where " + propertyName + "=?");
        query.setParameter(0, value);
        return query.list();
    }

    public void delete(T model) {
        getCurrentSession().delete(model);
    }

    public void deleteById(PK... id) {
        for (int i = 0; i < id.length; i++) {
            execute("delete from " + entityClass.getName() + " where " + sessionFactory.getClassMetadata(this.entityClass).getIdentifierPropertyName() + "=?", id[i]);
        }
    }

    public void update(T model) {
        getCurrentSession().update(model);
    }

    public List<T> findAll() {
        return getCurrentSession().createCriteria(entityClass.getName()).list();
    }

    public void deleteResultsByProperty(String propertyName, Object value) {
        Query query = getCurrentSession().createQuery("delete from " + entityClass.getName() + " where " + propertyName + "=?");
        query.setParameter(0, value);
        query.executeUpdate();
    }

    public void deleteResultsByPropertyInValues(String propertyName, Object... value) {
        String s = StringUtils.leftPad("?", value.length * 2 - 1, "?,");
        Query query = getCurrentSession().createQuery("delete from " + entityClass.getName() + " where " + propertyName + " in (" + s + ") ");
        for (int i = 0; i < value.length; i++) {
            query.setParameter(i, value[i]);
        }
        query.executeUpdate();
    }

    /*public PageList<T> findByPage(Page page, String hql, List<Object> params) {
        Query q = getCurrentSession().createQuery(hql);
        if (page instanceof Page.Offset) {
            q.setFirstResult(((Page.Offset) page).getStart()).setMaxResults(
                    ((Page.Offset) page).getLimit());
        } else {
            q.setFirstResult((page.getPage() - 1) * page.getPageSize())
                    .setMaxResults(page.getPageSize());
        }

        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                q.setParameter(i, params.get(i));
            }
        }
        page.setTotal(this.getTotalCount(hql, params));
        return new PageList<T>(page, q.list());
    }

    public PageList<T> findByPage(Page page) {
        return this.findByPage(page, "from " + this.entityClass.getName(),
                Collections.EMPTY_LIST);
    }

    public PageList<T> findByPage(Page page, String hql, Object... params) {
        return this.findByPage(page, hql, Arrays.asList(params));
    }*/

    public void clear() {
        getCurrentSession().clear();
    }

    public void flush() {
        getCurrentSession().flush();
    }

    public void evict(Object o) {
        getCurrentSession().evict(o);
    }

    public boolean isExist(String propertyName, Object value) {
        String hql = "select count(*) from " + entityClass.getName()
                + " where " + propertyName + "=?";
        return (Long) getCurrentSession().createQuery(hql)
                .setParameter(0, value).uniqueResult() > 0;
    }

    public long getTotalCount() {
        String hql = "select count(*) from " + entityClass.getName();
        return Long.valueOf(getCurrentSession().createQuery(hql).uniqueResult()
                .toString());
    }

    public long getTotalCount(String hql, List<Object> params) {
        Query q = getCurrentSession().createQuery(prepareCountHql(hql));
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                Object o = params.get(i);
                if(o instanceof Collection)
                    q.setParameterList("idList", (Collection) o);
                else if(o instanceof Object[])
                    q.setParameterList("idList", (Object[]) o);
                else
                    q.setParameter(i, o);
            }
        }
        Object singleResult = q.uniqueResult();
        if (singleResult instanceof Object[]) {
            return Long.valueOf(((Object[]) singleResult)[0].toString()).longValue();
        }
        return Long.valueOf(singleResult.toString()).longValue();
    }

    public long getTotalCount(String hql, Object... params) {
        return this.getTotalCount(hql,
                params == null ? null : Arrays.asList(params));
    }



    protected String prepareCountHql(String hql) {
        String fromHql = hql;
        fromHql = " from " + StringUtils.substringAfter(fromHql, "from ");
        fromHql = StringUtils.substringBefore(fromHql, "order by");
        String selectWhat = StringUtils.substringBetween(hql, "select", "from");
        if (selectWhat != null && selectWhat.contains("new ") && selectWhat.contains("(") && selectWhat.contains(")")) {
            selectWhat = StringUtils.substringBetween(selectWhat, "(", ")");
        }
        String countHql = "select count(*)" + (selectWhat == null ? "" : ", " + selectWhat + " ") + fromHql;
        return countHql;
    }

   /* protected String getCountSql(String originalHql,
                                 SessionFactory sessionFactory) {

        SessionFactoryImplementor sessionFactoryImplementor = (SessionFactoryImplementor) sessionFactory;

        HQLQueryPlan hqlQueryPlan = sessionFactoryImplementor
                .getQueryPlanCache().getHQLQueryPlan(originalHql, false,
                        Collections.emptyMap());

        String[] sqls = hqlQueryPlan.getSqlStrings();

        String countSql = "select count(*) from (" + sqls[0] + ") count";

        return countSql;

    }*/

    /*public SessionFactoryImplementor getSessionFactoryImplementor() {

        return (SessionFactoryImplementor) sessionFactory;

    }*/

    /*public QueryPlanCache getQueryPlanCache() {

        return getSessionFactoryImplementor().getQueryPlanCache();

    }*/

    /*public HQLQueryPlan getHqlQueryPlan(String hql) {

        return getQueryPlanCache().getHQLQueryPlan(hql, false,
                Collections.emptyMap());

    }*/

    /*protected String prepareCountSql(String sql) {
        return getCountSql(sql, sessionFactory);

    }*/

    public <T> List<T> findList(Class<T> clazz, String hql, List<Object> params) {
        Query q = getCurrentSession().createQuery(hql);
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                Object o = params.get(i);
                if(o instanceof Collection)
                    q.setParameterList("idList", (Collection) o);
                else if(o instanceof Object[])
                    q.setParameterList("idList", (Object[]) o);
                else
                    q.setParameter(i, o);
            }
        }
        return q.list();
    }

    public <T> List<T> findListLimit(Class<T> clazz, String hql, int limit,
                                     List<Object> params) {
        Query q = getCurrentSession().createQuery(hql);
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                Object o = params.get(i);
                if(o instanceof Collection)
                    q.setParameterList("idList", (Collection) o);
                else if(o instanceof Object[])
                    q.setParameterList("idList", (Object[]) o);
                else
                    q.setParameter(i, o);
            }
        }
        q.setFirstResult(0);
        q.setMaxResults(limit);
        return q.list();
    }

    public T getUniqueResult(String hql, Object... params) {
        return getUniqueResult(this.entityClass, hql, params);
    }

    public <T> T getUniqueResult(Class<T> clazz, String hql, List<Object> params) {
        Query q = getCurrentSession().createQuery(hql);
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                Object o = params.get(i);
                if(o instanceof Collection)
                    q.setParameterList("idList", (Collection) o);
                else if(o instanceof Object[])
                    q.setParameterList("idList", (Object[]) o);
                else
                    q.setParameter(i, o);
            }
        }
        q.setFirstResult(0);
        q.setMaxResults(1);
        List list = q.list();
        if (list == null || list.isEmpty() || list.get(0) == null) {
            return null;
        }
        return (T) list.get(0);
    }

    public <T> List<T> findList(Class<T> clazz, String hql, Object... params) {
        return this.findList(clazz, hql,
                params == null ? null : Arrays.asList(params));
    }

    /**
     * 用给定的HQL和参数查询前几条给定类型的数据列表
     * @param clazz
     * @param hql
     * @param limit
     * @param params
     * @return
     */
    public <T> List<T> findListLimit(Class<T> clazz, String hql, int limit,
                                     Object... params) {
        return this.findListLimit(clazz, hql, limit, params == null ? null
                : Arrays.asList(params));
    }

   /* public <T> PageList<T> findPageList(Page page, Class<T> clazz, String hql,
                                        Object... params) {
        if (params != null && params.length == 1 && params[0] instanceof List) {
            return findPageList(page, clazz, hql, params[0]);
        }
        return findPageList(page, clazz, hql, Arrays.asList(params));
    }*/

/*    public <T> PageList<T> findPageList(Page page, Class<T> clazz, String hql,
                                        List<Object> params) {
        Query q = getCurrentSession().createQuery(hql);
        if (page instanceof Page.Offset) {
            q.setFirstResult(((Page.Offset) page).getStart()).setMaxResults(
                    ((Page.Offset) page).getLimit());
        } else {
            q.setFirstResult((page.getPage() - 1) * page.getPageSize())
                    .setMaxResults(page.getPageSize());
        }
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                Object o = params.get(i);
                if(o instanceof Collection)
                    q.setParameterList("idList", (Collection) o);
                else if(o instanceof Object[])
                    q.setParameterList("idList", (Object[]) o);
                else
                    q.setParameter(i, o);
            }
        }
        page.setTotal(this.getTotalCount(hql, params));
        return new PageList(page, q.list());

    }*/

    public T getUniqueResultByProperty(String propertyName, Object value) {
        return this.getUniqueResult("from " + this.entityClass.getName()
                + " where " + propertyName + "=?", value);
    }

    public <T> T getUniqueResult(Class<T> clazz, String hql, Object... params) {
        return this.getUniqueResult(clazz, hql,
                params == null ? null : Arrays.asList(params));
    }
 
    public void execute(String hql, List<Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                Object o = params.get(i);
                if(o instanceof Collection)
                    q.setParameterList("idList", (Collection) o);
                else if(o instanceof Object[])
                    q.setParameterList("idList", (Object[]) o);
                else
                    q.setParameter(i, o);
            }
        }
        q.executeUpdate();
    }

    public void execute(String hql, Object... params) {
        this.execute(hql, params == null ? null : Arrays.asList(params));
    }

    public void updateProperty(PK id, String propertyName, Object status) {
        String hql = "update " + this.entityClass.getName() + " set " + propertyName + " = ? where " + sessionFactory.getClassMetadata(this.entityClass).getIdentifierPropertyName() + "= ?";
        this.execute(hql, status, id);
    }

    public void incr(PK id, String propertyName) {
        String hql = "update " + this.entityClass.getName() + " set " + propertyName + " = " + propertyName + "+1 where " + sessionFactory.getClassMetadata(this.entityClass).getIdentifierPropertyName() + "= ?";
        this.execute(hql, id);
    }

    public void incr(PK id, String propertyName, Integer n) {
        String hql = "update " + this.entityClass.getName() + " set " + propertyName + " = " + propertyName + "+1 where " + sessionFactory.getClassMetadata(this.entityClass).getIdentifierPropertyName() + "= ?";
        this.execute(hql, id, n);
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
        Query query = getCurrentSession().createQuery(hql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter("v" + i, values[i]);
        }
        query.setParameter("id", id).executeUpdate();
    }

    public String getIdPropertyName() {
        return sessionFactory.getClassMetadata(this.entityClass).getIdentifierPropertyName();
    }

    public boolean deleteAll() {
        String hql = "delete " + this.entityClass.getName();
        this.execute(hql);
        return true;
    }

    public SQLQuery getSqlQuery(String sqlQuery) {
        return this.getCurrentSession().createSQLQuery(sqlQuery);
    }

    /**
     * 当model与数据库中的字段类型不一致的时候调用，不能用getSqlQueryList
     *
     * @param sqlQuery 查询的sql语句，如 select id from user where id = ?
     * @param objects  where查询条件的参数 ,如 new Object[]{id}
     * @return
     */
    public SQLQuery getSqlQuery(String sqlQuery, Object[] objects) {
        SQLQuery q = this.getSqlQuery(sqlQuery);
        for (int i = 0, len = objects.length; i < len; i++) {
            Object o = objects[i];
            if(o instanceof Collection)
                q.setParameterList("idList", (Collection) o);
            else if(o instanceof Object[])
                q.setParameterList("idList", (Object[]) o);
            else
                q.setParameter(i, o);

        }
        return q;
    }

    /**
     * 当model与数据库中的字段类型不一致的时候调用，不能用getSqlQueryList
     *
     * @param sqlQuery 查询的sql语句，如 select id from user where id = ?
     * @param objects  where查询条件的参数 ,如 new Object[]{id}
     * @param <O>
     * @return
     */
    public <O> List<O> getSqlQueryForList(String sqlQuery, Object[] objects) {
        return this.getSqlQuery(sqlQuery, objects).list();
    }


    /**
     * 当需要用到的类型与数据库类型不一致时，需要强转时调用
     *
     * @param sqlQuery 查询的sql语句，如 select id from user where id = ?
     * @param objects  where查询条件的参数 ,如 new Object[]{id}
     * @param oClass
     * @param <O>
     * @return
     */
    public <O> List<O> getSqlQueryForList(String sqlQuery, Object[] objects, Class<O> oClass) {
        return (List<O>) this.getSqlQuery(sqlQuery, objects).list();
    }

    public <O> List<O> getSqlQueryList(String sqlQuery, Class<O> oClass) {
        return this.getSqlQuery(sqlQuery).setResultTransformer(Transformers.aliasToBean(oClass)).list();
    }

    public <O> List<O> getSqlQueryList(String sqlQuery, Object[] objects, Class<O> oClass) {
        SQLQuery query = this.getSqlQuery(sqlQuery);
        for (int i = 0, len = objects.length; i < len; i++) {
            query.setParameter(i, objects[i]);

        }
        return query.setResultTransformer(Transformers.aliasToBean(oClass)).list();
    }

    //不推荐使用
    public <O> List<O> getSqlQueryList(String sqlQuery, Object[] objects, String[] paramNameArr, Class[] paramTypeArr, Class<O> oClass) {
        SQLQuery query = this.getSqlQuery(sqlQuery);
        for (int i = 0, len = objects.length; i < len; i++) {
            if(objects[i] instanceof Map){
                Map map = (Map)objects[i];
                Set<String> keySet = map.keySet();
                for (String string : keySet) {
                    Object obj = map.get(string);
                    //这里考虑传入的参数是什么类型，不同类型使用的方法不同
                    if(obj instanceof Collection<?>){
                        query.setParameterList(string, (Collection<?>)obj);
                    }else if(obj instanceof Object[]){
                        query.setParameterList(string, (Object[])obj);
                    }else{
                        query.setParameter(string, obj);
                    }
                }
            }else{
                query.setParameter(i, objects[i]);
            }

        }

        org.hibernate.type.Type type = null;

        for (int i = 0, len = paramNameArr.length; i < len; i++) {

            if (paramTypeArr[i] == String.class) {
                type = StandardBasicTypes.STRING;
            } else if (paramTypeArr[i] == Integer.class) {
                type = StandardBasicTypes.INTEGER;
            } else if (paramTypeArr[i] == Date.class) {
                type = StandardBasicTypes.DATE;
            } else if (paramTypeArr[i] == Long.class) {
                type = StandardBasicTypes.LONG;
            } else if (paramTypeArr[i] == Double.class) {
                type = StandardBasicTypes.DOUBLE;
            } else if (paramTypeArr[i] == Float.class) {
                type = StandardBasicTypes.FLOAT;
            }

            query.addScalar(paramNameArr[i], type);
        }

        return query.setResultTransformer(Transformers.aliasToBean(oClass)).list();
    }

    //推荐使用
    public <O> List<O> getSqlQueryList(String sqlQuery, Map<String, Object> conditionMap, String[] paramNameArr, Class[] paramTypeArr, Class<O> oClass) {
        SQLQuery query = this.getSqlQuery(sqlQuery);

        Set<String> keySet = conditionMap.keySet();
        for (String string : keySet) {
            Object obj = conditionMap.get(string);
            //这里考虑传入的参数是什么类型，不同类型使用的方法不同
            if(obj instanceof Collection<?>){
                query.setParameterList(string, (Collection<?>)obj);
            }else if(obj instanceof Object[]){
                query.setParameterList(string, (Object[])obj);
            }else{
                query.setParameter(string, obj);
            }
        }

        org.hibernate.type.Type type = null;

        for (int i = 0, len = paramNameArr.length; i < len; i++) {

            if (paramTypeArr[i] == String.class) {
                type = StandardBasicTypes.STRING;
            } else if (paramTypeArr[i] == Integer.class) {
                type = StandardBasicTypes.INTEGER;
            } else if (paramTypeArr[i] == Date.class) {
                type = StandardBasicTypes.DATE;
            } else if (paramTypeArr[i] == Long.class) {
                type = StandardBasicTypes.LONG;
            } else if (paramTypeArr[i] == Double.class) {
                type = StandardBasicTypes.DOUBLE;
            } else if (paramTypeArr[i] == Float.class) {
                type = StandardBasicTypes.FLOAT;
            }

            query.addScalar(paramNameArr[i], type);
        }

        return query.setResultTransformer(Transformers.aliasToBean(oClass)).list();
    }





    public <O> O getSqlQueryT(String sqlQuery, Object[] objects, Class<O> oClass) {
        return getSqlQueryForList(sqlQuery, objects, oClass).get(0);
    }


    public <O> O getSqlQueryForT(String sqlQuery, Object[] objects, String parameterName, org.hibernate.type.Type types) {
        List<O> list = getSqlQuery(sqlQuery, objects).addScalar(parameterName, types).list();

        if (list == null || list.size() == 0) {
            return null;
        }
        return (O) list.get(0);
    }
}
