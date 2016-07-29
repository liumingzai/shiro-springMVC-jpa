package com.gome.splunk.dao.impl;

import com.gome.splunk.common.hibernate.DefaultDataBaseDaoImpl;
import com.gome.splunk.dao.UserDao;
import com.gome.splunk.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by liubing-ds3 on 2016/6/1.
 */
@Repository
public class UserDaoImpl  extends DefaultDataBaseDaoImpl<UserEntity, String> implements UserDao {


}
