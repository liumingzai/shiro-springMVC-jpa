package com.gome.splunk.common.page;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Created by liubing-ds3 on 2016/5/17.
 */
public class PageList<T> {


    public PageList(Page page, List<T> list) {
        this.page = page;
        this.list = list;
    }

    private Page page;
    private List<T> list;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("page", page)
                .append("list", list.toString())
                .toString();
    }
}
