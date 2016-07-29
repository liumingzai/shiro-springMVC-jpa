package com.gome.splunk.service.impl;

import com.gome.splunk.common.page.Page;
import com.gome.splunk.common.page.PageList;
import com.gome.splunk.dao.ResourceDao;
import com.gome.splunk.dao.ResourceRoleDao;
import com.gome.splunk.entity.ResourceEntity;
import com.gome.splunk.entity.RoleEntity;
import com.gome.splunk.repository.ResourceRepository;
import com.gome.splunk.service.ResourceService;
import com.gome.splunk.vo.ResourceRoleVo;
import com.gome.splunk.vo.ResourceVo;
import com.gome.splunk.vo.RoleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liubing-ds3 on 2016/5/13.
 */

@Service("ResourceService")
@Transactional(value = "jpaTransactionManager")
public class ResourceServiceImpl implements ResourceService {


    @Autowired
    private ResourceRepository resourceRepository;

    @Resource
    private ResourceDao resourceDao;


    @Resource
    private ResourceRoleDao resourceRoleDao;

    public List<ResourceEntity> findAll() {
        List<ResourceEntity> resourceEntityList = resourceDao.findAll();
        return resourceEntityList;
    }

    public List<ResourceVo> findParentResources() {
        String hql = "select new " + ResourceVo.class.getName() +
                "(" +
                "p.id,"+
                "p.rsname "+
                " ) from ResourceEntity as p " +
                "where 1=1 " +
                " and p.level = 0 ";

        List<Object> params = new ArrayList<Object>();

        List<ResourceVo> lists =  resourceDao.findList( ResourceVo.class, hql, params);
        return lists;
    }

    public List<ResourceRoleVo> findResourceByRoleId(Integer rid) {
        String hql = "select new " + ResourceRoleVo.class.getName() +
                "(" +
                "p.id,"+
                "rs.id, "+
                "r.id "+
                " ) from ResourceRoleEntity as p " +
                "left join p.resourceByResourceId as rs " +
                "left join p.roleByRoleId as r " +
                "where 1=1 and rs.level=0 " ;
        List<Object> params = new ArrayList<Object>();
        if(rid!=null && rid.intValue()!=0){
            hql += " and r.id = ? ";
            params.add(rid);
        }
        return resourceRoleDao.findList(ResourceRoleVo.class,hql,params);
    }

    public List<ResourceRoleVo> findChildResourceByRoleId(Integer rid) {
        String hql = "select new " + ResourceRoleVo.class.getName() +
                "(" +
                "p.id,"+
                "rs.id, "+
                "r.id "+
                " ) from ResourceRoleEntity as p " +
                "left join p.resourceByResourceId as rs " +
                "left join p.roleByRoleId as r " +
                "where 1=1 and rs.level=1 " ;
        List<Object> params = new ArrayList<Object>();
        if(rid!=null && rid.intValue()!=0){
            hql += " and r.id = ? ";
            params.add(rid);
        }
        return resourceRoleDao.findList(ResourceRoleVo.class,hql,params);
    }

    public List<ResourceVo> getChirlds() {
        String hql = "select new " + ResourceVo.class.getName() +
                "(" +
                "p.id,"+
                "p.rsname, "+
                "p.level, "+
                "p.parentid "+
                " ) from ResourceEntity as p " +
                "where 1=1 " +
                " and p.level = 1 and p.parentid !=-1 ";

        List<Object> params = new ArrayList<Object>();
        List<ResourceVo> lists =  resourceDao.findList( ResourceVo.class, hql, params);
        return lists;
    }

    public ResourceEntity findResourceById(Integer resourceId) {
        ResourceEntity resourceEntity = resourceRepository.findOne(resourceId);
        return resourceEntity;
    }

    public void addResource(ResourceEntity resourceEntity) {
        resourceRepository.save(resourceEntity);
    }

    public ResourceEntity findRoleById(Integer id) {
        ResourceEntity resourceEntity = resourceRepository.findOne(id);
        return resourceEntity;
    }

    public PageList<ResourceVo> selectPageList(Page page, String rsname) {
        String hql = "select new " + ResourceVo.class.getName() +
                "(" +
                "p.id,"+
                "p.rsname,"+
                "p.parentid,"+
                "p.url," +
                "p.level," +
                "p.update_time" +
                " ) from ResourceEntity as p " +
                "where 1=1 ";

        List<Object> params = new ArrayList<Object>();

        if (StringUtils.isNotBlank(rsname)) {
            hql += " and p.rsname = ? ";
            params.add(rsname);
        }
        hql += " order by p.update_time desc";
        return resourceDao.findPageList(page, ResourceVo.class, hql, params);
    }




    public Integer addResource(ResourceVo resourceVo) throws Exception {
        ResourceEntity entity = new ResourceEntity();
        entity.setRsname(resourceVo.getRsname());
        entity.setUrl(resourceVo.getUrl());
        entity.setLevel(resourceVo.getLevel());
        if(resourceVo.getLevel()==0){
            entity.setParentid(-1);
        }else{
            entity.setParentid(resourceVo.getParentid());
        }
        entity.setCreate_time(new Date(System.currentTimeMillis()));
        entity.setUpdate_time(new Date(System.currentTimeMillis()));
        resourceDao.save(entity);
        return entity.getId();
    }

    public boolean updateResource(ResourceVo resourceVo) throws Exception {
        ResourceEntity entity  = this.resourceDao.findListByProperty("rsid",resourceVo.getId()).get(0);
        entity.setRsname(resourceVo.getRsname());
        entity.setUrl(resourceVo.getUrl());
        entity.setLevel(resourceVo.getLevel());
        if(resourceVo.getLevel()==0){
            entity.setParentid(-1);
        }else{
            entity.setParentid(resourceVo.getParentid());
        }
        entity.setUpdate_time(new Date(System.currentTimeMillis()));
        resourceDao.update(entity);
        return true;
    }


    public boolean remove(Integer id) {
        try{
            this.resourceRoleDao.deleteResultsByPropertyInValues("rsid",id);
            this.resourceDao.deleteResultsByPropertyInValues("rsid",id);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;

    }
}
