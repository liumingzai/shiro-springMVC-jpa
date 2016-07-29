package com.gome.splunk.common.jpa;

import com.gome.splunk.common.page.Page;
import com.gome.splunk.common.page.PageList;
import org.hibernate.SQLQuery;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * hibernate 数据操作通实现接口
 *
 * @param <T>  对象类型
 * @param <PK> 对象的主键类型
 */
public interface JpaBaseDao<T, PK extends Serializable> {

    /**
     * 用主键查询
     *
     * @param id
     * @return
     */
    T getById(PK id);

    /**
     * load by id
     *
     * @param id
     * @return
     */
    T loadById(PK id);

    /**
     * 根据主键查询某个属性
     *
     * @param id
     * @param propertyName
     * @param clazz
     * @return
     */
    <T> T getPropertyById(PK id, String propertyName, Class<T> clazz);


    /**
     * 保存
     *
     * @param model
     */
    void save(T model);

    /**
     * 添加或更新
     *
     * @param model
     */
    void saveOrUpdate(T model);

    /**
     * 删除
     *
     * @param model
     */
    void delete(T model);

    /**
     * 删除
     *
     * @param id
     */
    void deleteById(PK... id);

    /**
     * 删除所有数据
     */
    boolean deleteAll();

    /**
     * 执行hql，不返回结果，如果需要返回结果，请直接使用session
     *
     * @param hql
     * @param params
     */
    void execute(String hql, List<Object> params);

    /**
     * 执行hql，不返回结果，如果需要返回结果，请直接使用session
     *
     * @param hql
     * @param params
     */
    void execute(String hql, Object... params);

    /**
     * 将某个数字类型的属性值更新+1
     *
     * @param id           主键
     * @param propertyName 自增+1的属性名
     */
    void incr(PK id, String propertyName);

    /**
     * 将某个数值类型的属性值更新 +N
     *
     * @param id           主键
     * @param propertyName 自增+n的属性名
     * @param n            增量
     */
    void incr(PK id, String propertyName, Integer n);

    /**
     * 更新到数据库
     *
     * @param model
     */
    void update(T model);

    /**
     * 使用主键，更新一个属性
     *
     * @param id
     * @param propertyName
     * @param status
     */
    void updateProperty(PK id, String propertyName, Object status);

    /**
     * 使用主键，更新多个属性
     *
     * @param id
     * @param propertyNames
     * @param values
     */
    void updateProperties(PK id, String[] propertyNames, Object[] values);

    /**
     * 查询所有
     *
     * @return
     */
    List<T> findAll();

    /**
     * 根据某个属性值查询
     *
     * @param propertyName
     * @param value
     * @return
     */
    List<T> findListByProperty(String propertyName, Object value);


    /**
     * 根据某个属性值查询某个属性值列表
     *
     * @param resultClass   返回的列表的泛型
     * @param getProperName 需要返回的字段
     * @param propertyName  作为筛选条件的属性名
     * @param value         筛选条件的值
     * @return
     */
    <T> List<T> findListByProperty(Class<T> resultClass, String getProperName, String propertyName, Object value);


    /**
     * 用给定的HQL和参数查询给定类型的数据列表
     *
     * @param <T>
     * @param clazz
     * @param hql
     * @param params
     * @return
     */
    <T> List<T> findList(Class<T> clazz, String hql, List<Object> params);

    /**
     * 用给定的HQL和参数查询给定类型的数据列表
     *
     * @param <T>
     * @param clazz
     * @param hql
     * @param params
     * @return
     */
    <T> List<T> findList(Class<T> clazz, String hql, Object... params);

    /**
     * 用给定的HQL和参数查询前几条给定类型的数据列表
     *
     * @param clazz
     * @param hql
     * @param limit
     * @param params
     * @return
     */
    <T> List<T> findListLimit(Class<T> clazz, String hql, int limit,
                              List<Object> params);

    /**
     * 用给定的HQL和参数查询前几条给定类型的数据列表
     *
     * @param clazz
     * @param hql
     * @param limit
     * @param params
     * @return
     */
    <T> List<T> findListLimit(Class<T> clazz, String hql, int limit,
                              Object... params);

    /**
     * 用给定的HQL和参数查询分页给定类型的数据列表
     *
     * @param page   分页对象
     * @param clazz  返回对象的类型
     * @param hql    HQL
     * @param params HQL 参数
     * @return
     */
    <T> PageList<T> findPageList(Page page, Class<T> clazz, String hql,
                                 Object... params);

    /**
     * 用给定的HQL和参数查询分页给定类型的数据列表
     *
     * @param page
     * @param clazz
     * @param hql
     * @param params
     * @return
     */
    <T> PageList<T> findPageList(Page page, Class<T> clazz, String hql,
                                 List<Object> params);


    /**
     * 根据唯一的属性值返回单条数据
     *
     * @param propertyName
     * @param value
     * @return
     */
    T getUniqueResultByProperty(String propertyName, Object value);

    /**
     * 删除匹配此属性的所有记录
     *
     * @param propertyName
     * @param value
     */
    void deleteResultsByProperty(String propertyName, Object value);

    /**
     * 删除属性值在给定列表中的所有记录
     *
     * @param propertyName
     * @param value
     */
    void deleteResultsByPropertyInValues(String propertyName, Object... value);

    /**
     * 返回单条数据
     *
     * @param hql
     * @param params
     * @return
     */
    T getUniqueResult(String hql, Object... params);

    /**
     * 用给定的HQL和参数查询给定类型的唯一一条数据
     *
     * @param <T>
     * @param clazz
     * @param hql
     * @param params
     * @return
     */
    <T> T getUniqueResult(Class<T> clazz, String hql, List<Object> params);

    /**
     * 用给定的HQL和参数查询给定类型的唯一一条数据
     *
     * @param <T>
     * @param clazz
     * @param hql
     * @param params
     * @return
     */
    <T> T getUniqueResult(Class<T> clazz, String hql, Object... params);

    /**
     * 使用给定的HQL分页查询
     *
     * @param page
     * @param hql
     * @param params
     * @return
     */
    PageList<T> findByPage(Page page, String hql, List<Object> params);

    /**
     * 使用给定的HQL分页查询
     *
     * @param page
     * @param hql
     * @param params
     * @return
     */
    PageList<T> findByPage(Page page, String hql, Object... params);

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    PageList<T> findByPage(Page page);

    /**
     * 刷新当前session的操作
     */
    void flush();

    /**
     * 清除当前session的缓存和状态
     */
    void clear();

    /**
     * 将持久化对象脱离session
     *
     * @param o
     */
    void evict(Object o);

    /**
     * 查询指定属性值的记录是否存在
     *
     * @param propertyName
     * @param value
     * @return
     */
    boolean isExist(String propertyName, Object value);

    /**
     * 查询总条数
     *
     * @return
     */
    long getTotalCount();

    /**
     * 查询给定HQL和参数查询结果的总条数
     *
     * @param hql
     * @param params
     * @return
     */
    long getTotalCount(String hql, List<Object> params);

    /**
     * 查询给定HQL和参数查询结果的总条数
     *
     * @param hql
     * @param params
     * @return
     */
    long getTotalCount(String hql, Object... params);


    /**
     * 开始事务
     */
    void beginTransaction();

    /**
     * 提交事务
     */
    void commitTransaction();

    /**
     * 回退事务
     */
    void rollbackTransaction();

}
