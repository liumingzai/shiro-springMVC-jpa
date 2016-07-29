package com.gome.splunk.dao.impl;

import com.gome.splunk.common.hibernate.DefaultDataBaseDaoImpl;
import com.gome.splunk.dao.ResourceDao;
import com.gome.splunk.entity.ResourceEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by liubing-ds3 on 2016/6/1.
 */
@Repository
public class ResourceDaoImpl extends DefaultDataBaseDaoImpl<ResourceEntity, String> implements ResourceDao {


}
