package com.gome.splunk.entity;

import javax.persistence.*;

/**
 * Created by liubing-ds3 on 2016/5/17.
 */
@Entity
@Table(name="bo_resource_role")
public class ResourceRoleEntity {

    private Integer id;

    private ResourceEntity resourceByResourceId;

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
    @JoinColumn(name = "rsid", referencedColumnName = "rsid")
    public ResourceEntity getResourceByResourceId() {
        return resourceByResourceId;
    }

    public void setResourceByResourceId(ResourceEntity resourceByResourceId) {
        this.resourceByResourceId = resourceByResourceId;
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
