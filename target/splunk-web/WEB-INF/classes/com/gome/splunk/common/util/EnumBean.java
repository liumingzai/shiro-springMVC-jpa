package com.gome.splunk.common.util;


import com.gome.splunk.common.constance.EnumInterface;

import java.io.Serializable;

/**
 * 功能：
 * 作者： yangyan
 * 时间： 2015/4/8 .
 */
public class EnumBean implements EnumInterface {

    Serializable id;

    String name;

    String desc;

    String originName;

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
