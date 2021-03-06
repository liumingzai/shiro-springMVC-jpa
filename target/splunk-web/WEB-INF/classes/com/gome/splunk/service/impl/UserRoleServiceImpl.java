package com.gome.splunk.service.impl;

import com.gome.splunk.dao.UserRoleDao;
import com.gome.splunk.entity.RoleEntity;
import com.gome.splunk.entity.UserEntity;
import com.gome.splunk.entity.UserRoleEntity;
import com.gome.splunk.repository.UserRoleRepository;
import com.gome.splunk.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by liubing-ds3 on 2016/5/13.
 */

@Service("UserRoleService")
@Transactional(value = "jpaTransactionManager")
public class UserRoleServiceImpl implements UserRoleService {


    @Autowired
    private UserRoleRepository userRoleRepository;

    @Resource
    private UserRoleDao userRoleDao;


    public void addUserRole(UserRoleEntity userRoleEntity) {
        userRoleRepository.save(userRoleEntity);
    }

    public boolean add(String[] roles, String uId) {
        userRoleDao.deleteResultsByProperty("uId",uId);
        if (null != roles)
        {
            for ( int i = 0; i < roles.length; i++ )
            {
                UserRoleEntity entity = new UserRoleEntity();
                RoleEntity roleEntity = new RoleEntity();
                roleEntity.setId(Integer.parseInt( roles[i]));
                entity.setRoleByRoleId(roleEntity);
                UserEntity userEntity = new UserEntity();
                userEntity.setId(Integer.parseInt(uId));
                entity.setUserByUserId(userEntity);
                this.userRoleDao.save( entity);
            }
        }
        this.userRoleDao.flush();
        return true;
    }


    /**
     * 获取用户有权限的functionCode
     */
    public Set<String> getUserPerms(int uid) {

        String hql = "select rp.perm from UserRoleEntity rp where rp.adminRoleByRoleId.roleId IN (" +
                "select ru.adminRoleByRoleId.roleId from AdminUserRoleBindEntity as ru where ru.adminUserByUserId.userId = ?) ";

        List<String> list = this.userRoleDao.findList(String.class, hql, uid);

        if (list == null || list.size() == 0) {
            return null;
        }
        Set<String> set = new HashSet<String>(list);
        return set;
    }

    public void removeDatas() throws Exception {
        this.userRoleDao.deleteAll();
    }

    public List<UserRoleEntity> findAll() {
        return userRoleDao.findAll();
    }

}
