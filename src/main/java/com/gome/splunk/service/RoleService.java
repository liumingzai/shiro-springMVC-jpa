package com.gome.splunk.service;


import com.gome.splunk.common.page.Page;
import com.gome.splunk.common.page.PageList;
import com.gome.splunk.entity.RoleEntity;
import com.gome.splunk.vo.RoleVo;
import com.gome.splunk.vo.UserRoleVo;

import java.util.List;

/**
 * Created by liubing-ds3 on 2016/5/13.
 */

public interface RoleService {

   void addRole(RoleEntity role);

   RoleEntity findRoleById(Integer roleId);

   List<UserRoleVo> findRoleByUserId(Integer uid);

   List<RoleEntity> findAll();
   /**
    * 删除
    *
    * @param id
    * @return
    */
   boolean remove(Integer id);

   /**
    * 分页查询
    * @param page
    * @param name
    * @return
    */
   PageList<RoleVo> selectPageList(Page page, String name);


   /**
    * 添加用户
    * @param roleVo
    * @return
    * @throws Exception
    */
   Integer addRole(RoleVo roleVo) throws Exception;

   /**
    * 修改用户
    * @param roleVo
    * @return
    * @throws Exception
    */
   boolean updateRole(RoleVo roleVo) throws Exception;
}
