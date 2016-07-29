package com.gome.splunk.dao.impl;

import com.gome.splunk.common.hibernate.DefaultDataBaseDaoImpl;
import com.gome.splunk.dao.UserRoleDao;
import com.gome.splunk.entity.UserRoleEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by liubing-ds3 on 2016/6/1.
 */
@Repository
public class UserRoleDaoImpl extends DefaultDataBaseDaoImpl<UserRoleEntity, String> implements UserRoleDao {


}
