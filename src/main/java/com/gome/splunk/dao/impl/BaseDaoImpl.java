package com.gome.splunk.dao.impl;

import com.gome.splunk.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T,PK> {


    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Resource(name = "jdbcTemplate")
    protected JdbcTemplate jdbcTemplate;

    protected Class<T> clazz;

    protected BaseDaoImpl() {
        Class c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type)
                    .getActualTypeArguments();
            this.clazz = (Class<T>) parameterizedType[0];
        }
    }

    protected Session getCurrentSession(){
        return this.sessionFactory.getCurrentSession();
    }


    public Serializable save(T model)  throws Exception {
        return getCurrentSession().save(model);
    }

   
    public void saveOrUpdate(T model) throws Exception {
        getCurrentSession().saveOrUpdate(model);
    }

   
    public T getById(PK id) throws Exception {
        return (T) getCurrentSession().get(clazz,id);
    }

   
    public List<T> getByIds(PK[] ids) throws Exception {
        if (ids == null || ids.length == 0) {
            return Collections.EMPTY_LIST;
        }
        return getCurrentSession().createQuery(
                "FROM " + clazz.getSimpleName() + " WHERE "+sessionFactory.getClassMetadata(this.clazz).getIdentifierPropertyName()+" IN(:ids)")
                .setParameterList("ids", ids).list();
    }

   
    public List<T> getByProperty(Map<String, Object> param) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("FROM " + clazz.getSimpleName());
        Query query = getQuery(sb,param);
        return query.list();
    }

   
    public List<T> getAll() throws Exception {
        return getCurrentSession().createQuery("from " + clazz.getSimpleName()).list();
    }

   
    public void update(T model) throws Exception {
        getCurrentSession().update(model);
    }

   
    public int update(String hql, Object... field) throws Exception {
        Query query = getCurrentSession().createQuery(hql);
        if(field.length != 0){
            for(int i=0 ;i < field.length; i++){
                query.setParameter(i, field[i]);
            }
        }
        return query.executeUpdate();
    }

   
    public int getCount(Map<String, Object> param)  throws Exception{
        StringBuffer sb = new StringBuffer();
        sb.append("select count(*) from " + clazz.getSimpleName());
        int totalCount = ((Long)getQuery(sb,param).list().get(0)).intValue();
        return totalCount;
    }


   
    public void delete(PK id) throws Exception{
        Object object = getCurrentSession().get(clazz, id);
        getCurrentSession().delete(object);
    }

    private Query getQuery(StringBuffer sb, Map<String,Object> param){
        Query query = null;
        if(param != null && param.size() != 0){
            sb.append(" WHERE 1=1 ");
            List<String> list = new ArrayList<String>(param.keySet());
            for(int i=0;i<list.size();i++){
                if (list.get(i) == null || param.get(list.get(i)) == null) {
                    continue;
                }
                sb.append(" AND ");
                sb.append(list.get(i));
                sb.append("=");
                sb.append(param.get(list.get(i)));
            }
            query = getCurrentSession().createQuery(sb.toString());
        }else{
            query = getCurrentSession().createQuery(sb.toString());
        }
        return query;
    }

}
