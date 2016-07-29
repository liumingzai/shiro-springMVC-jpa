package com.gome.splunk.dao;


import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface BaseDao<T, PK> {

    /**
     * 保存实例
     * @param model 实体对象
     * @return 主键
     */
    public Serializable save(T model) throws Exception;


    /**
     * 保存实例
     * @param model 实体对象
     */
    public void saveOrUpdate(T model) throws Exception;

    /**
     * 通过ID得到当前的实例
     * @param id 实体ID
     * @return 实体对象
     */
    public T getById(PK id) throws Exception;

    /**
     * 通过数组id得到数组列表
     * @param ids 数组ID
     * @return 实体list
     */
    public List<T> getByIds(PK[] ids) throws Exception;

    /**
     * 通过属性得到结果
     * @param param 参数Map
     * @return 实体
     */
    public List<T> getByProperty(Map<String, Object> param) throws Exception;

    /**
     *
     * @return
     */
    public List<T> getAll() throws Exception;

    /**
     * 更新实体类
     * @param model 实体对象
     */
    public void update(T model) throws Exception;

    /**
     * 更新实体
     * @param hql hql语句
     * @param field 更新字段
     * @return 修改的数量
     */
    public int update(String hql, Object... field) throws Exception;

    /**
     * 统计数量
     * @param param 参数
     * @return
     */
    public int getCount(Map<String, Object> param) throws Exception;

    /**
     * 通过ID删除一个实体
     * @param id 实体ID
     */
    public void delete(PK id) throws Exception;




}
