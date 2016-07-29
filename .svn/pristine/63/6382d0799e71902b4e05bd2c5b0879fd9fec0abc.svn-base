package com.gome.splunk.entity;

import javax.persistence.*;

/**
 * Created by liubing-ds3 on 2016/5/17.
 */

@Entity
@Table(name="bo_user_role")
public class UserRoleEntity {

    private Integer id;

    private UserEntity userByUserId;

    private RoleEntity roleByRoleId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "rid", referencedColumnName = "rid")
    public RoleEntity getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(RoleEntity roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }
}

