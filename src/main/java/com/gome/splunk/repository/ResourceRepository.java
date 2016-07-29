package com.gome.splunk.repository;

import com.gome.splunk.entity.ResourceEntity;
import com.gome.splunk.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * Created by liubing-ds3 on 2016/5/13.
 */
public interface ResourceRepository extends JpaRepository<ResourceEntity,Integer>
{

}
