package com.gome.splunk.vo;

import java.util.Date;

/**
 * Created by liubing-ds3 on 2016/5/17.
 */
public class ResourceRoleVo {

    private Integer id;
    private Integer rsid;
    private Integer rid;

    public ResourceRoleVo(Integer id, Integer rsid, Integer rid) {
        this.id = id;
        this.rsid = rsid;
        this.rid = rid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRsid() {
        return rsid;
    }

    public void setRsid(Integer rsid) {
        this.rsid = rsid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}

