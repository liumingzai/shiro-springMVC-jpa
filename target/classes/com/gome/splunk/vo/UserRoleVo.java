package com.gome.splunk.vo;


/**
 * Created by liubing-ds3 on 2016/5/17.
 */
public class UserRoleVo {

    private Integer id;
    private Integer uid;
    private Integer rid;

    public UserRoleVo() {

    }

    public UserRoleVo(Integer id, Integer uid, Integer rid) {
        this.id = id;
        this.uid = uid;
        this.rid = rid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}
