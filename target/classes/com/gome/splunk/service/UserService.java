package com.gome.splunk.service;


import com.gome.splunk.common.page.Page;
import com.gome.splunk.common.page.PageList;
import com.gome.splunk.entity.UserEntity;
import com.gome.splunk.vo.UserVo;

import java.util.List;
import java.util.Set;

/**
 * Created by liubing-ds3 on 2016/5/13.
 */

public interface UserService {

   void addUser(UserEntity user);

   Set<UserEntity> findByName(String username);

   /**
    * 按名称查找
    * @param username
    * @return
    */
   List<UserEntity> findByUsername(String username);

   /**
    * and条件查询
    * @param username
    * @param nickname
    * @return
    */
   List<UserEntity> findByAndMethod(String username,String nickname);


   UserEntity findUserById(Integer userId);

   List<UserEntity> findAll();

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
    * @param nickname
    * @return
    */
   PageList<UserVo> selectPageList(Page page, String nickname);

   /**
    * 添加用户
    * @param userVo
    * @return
    * @throws Exception
    */
   Integer addUser(UserVo userVo) throws Exception;

   /**
    * 修改用户
    * @param userVo
    * @return
    * @throws Exception
    */
   boolean updateUser(UserVo userVo) throws Exception;

   /**
    * 清表
    */
   void removeDatas() throws Exception;


   void saveDatas(List<UserEntity> info) throws Exception;
}
