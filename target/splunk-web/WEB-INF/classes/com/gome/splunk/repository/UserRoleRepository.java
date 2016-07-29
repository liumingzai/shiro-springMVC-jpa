package com.gome.splunk.repository;

import com.gome.splunk.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by liubing-ds3 on 2016/5/13.
 */
public interface UserRoleRepository extends JpaRepository<UserRoleEntity,Integer>
{

}
