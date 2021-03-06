package com.gome.splunk.service;


import com.gome.splunk.common.page.Page;
import com.gome.splunk.common.page.PageList;
import com.gome.splunk.entity.ResourceEntity;
import com.gome.splunk.vo.ResourceRoleVo;
import com.gome.splunk.vo.ResourceVo;

import java.util.List;

/**
 * Created by liubing-ds3 on 2016/5/13.
 */

public interface ResourceService {

   List<ResourceEntity> findAll();

   List<ResourceVo> getChirlds();

   List<ResourceVo> findParentResources();

   List<ResourceRoleVo> findResourceByRoleId(Integer rid);


   List<ResourceRoleVo> findChildResourceByRoleId(Integer rid);

   ResourceEntity findResourceById(Integer resourceId);
   /**
    * 删除
    * @param id
    * @return
    */
   boolean remove(Integer id);


   /**
    * 分页查询
    * @param page
    * @param rsname
    * @return
    */
   PageList<ResourceVo> selectPageList(Page page, String rsname);

   /**
    * 添加资源
    * @param resourceVo
    * @return
    * @throws Exception
    */
   Integer addResource(ResourceVo resourceVo) throws Exception;

   /**
    * 修改资源
    * @param resourceVo
    * @return
    * @throws Exception
    */
   boolean updateResource(ResourceVo resourceVo) throws Exception;
}
