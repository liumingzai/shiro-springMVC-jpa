package com.gome.splunk.repository;

import com.gome.splunk.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * Created by liubing-ds3 on 2016/5/13.
 */
public interface UserRepository extends JpaRepository<UserEntity,Integer>
{

    List<UserEntity> findByUsername(String username);

    List<UserEntity> findByUsernameAndNickname(String username,String nickname);
    /**
     * 通过原生SQL进行查询
     */
    @Query(value = "select * from bo_user where name=? ", nativeQuery = true)
    Set<UserEntity> findSql(String username);

    List<UserEntity> findAll();
}
