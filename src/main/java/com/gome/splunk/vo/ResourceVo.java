package com.gome.splunk.vo;

import java.util.Date;

/**
 * Created by liubing-ds3 on 2016/5/17.
 */
public class ResourceVo {

    private Integer id;
    private String rsname;
    private String permission;
    private Integer parentid;
    private String url;
    private Integer level;
    private String description;
    private Integer update_user;
    private Date update_time;
    private Integer create_user;
    private Date create_time;

    public ResourceVo() {
    }

    public ResourceVo(Integer id) {
        this.id = id;
    }


    public ResourceVo(Integer id, String rsname, Integer parentid, String url, Integer level, Date update_time) {
        this.id = id;
        this.rsname = rsname;
        this.parentid = parentid;
        this.url = url;
        this.level = level;
        this.update_time = update_time;
    }

    public ResourceVo(Integer id, String rsname) {
        this.id = id;
        this.rsname = rsname;
    }

    public ResourceVo(Integer id, String rsname,Integer level, Integer parentid) {
        this.id = id;
        this.rsname = rsname;
        this.level = level;
        this.parentid = parentid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRsname() {
        return rsname;
    }

    public void setRsname(String rsname) {
        this.rsname = rsname;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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
}

