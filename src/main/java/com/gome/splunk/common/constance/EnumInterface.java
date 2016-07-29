package com.gome.splunk.common.constance;

import java.io.Serializable;

/**
 * 功能：枚举类型操作接口，如果想使用valueOf方法传入id返回枚举类型的值，那就必须在getId方法中返回name()值，也就是变量名
 * 作者： liubing-ds3
 * 时间： 2015/6/8 .
 */
public interface EnumInterface<idType extends Serializable> {
    /**
     * 返回数据库中保存的id
     * @return
     */
    idType getId();

    /**
     * 返回前段显示的名称
     * @return
     */
    String getName();

    /**
     * 返回显示的描述信息
     * @return
     */
    String getDesc();
}
