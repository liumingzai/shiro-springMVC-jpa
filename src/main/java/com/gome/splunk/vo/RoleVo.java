package com.gome.splunk.vo;

import java.util.Date;

/**
 * Created by liubing-ds3 on 2016/5/17.
 */
public class RoleVo {

    private Integer id;
    private String name;
    private String type;
    private String type_name;
    private String description;
    private Integer update_user;
    private Date update_time;
    private Integer create_user;
    private Date create_time;

    public RoleVo() {
    }

    public RoleVo(Integer id) {
        this.id = id;
    }

    public RoleVo(Integer id, String name, String type, String type_name,String description, Date update_time) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.type_name = type_name;
        this.description = description;
        this.update_time = update_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(Integer update_user) {
        this.update_user = update_user;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getCreate_user() {
        return create_user;
    }

    public void setCreate_user(Integer create_user) {
        this.create_user = create_user;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}
