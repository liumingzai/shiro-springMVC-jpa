package com.gome.splunk.common.page;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 分页对象，包含页码，每页条数，总条数 User: liubing-ds3 Date: 16-05-18 Time: 上午11:59 To change this
 * template use File | Settings | File Templates.
 */
public class Page {

    private int page = 1;
    private int pageSize = 50;
    private long total = 0;

    //默认需要分页true
    private boolean isPage = true;

    public boolean isPage() {
        return isPage;
    }

    public void setIsPage(boolean isPage) {
        this.isPage = isPage;
    }

    public int getOffset() {
        return (page - 1) * pageSize;
    }

    public int getTotalPage() {
        return (int) ((total + pageSize) / pageSize);
    }

    public Page() {
    }

    public Page(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getStartIndex() {
        return getOffset();
    }

    public int getEndIndex() {
        return getStartIndex() + pageSize;
    }

    /**
     * 另一种形式
     */
    public static class Offset extends Page {

        private int start = 0;
        private int limit = 50;

        public Offset() {
        }

        public Offset(int start, int limit) {
            super(1, limit);
            this.start = start;
            this.limit = limit;
            if (start < limit) {
                setPage(1);
            } else {
                setPage((start + limit * 2 - 1) / limit);
            }

        }

        public int getLimit() {
            return limit;
        }

        public int getStart() {
            return start;
        }

        public void setLimit(int limit) {
            this.limit = limit;
            if (start < limit) {
                setPage(1);
            } else {
                setPage((start + limit * 2 - 1) / limit);
            }
        }

        public void setStart(int start) {
            this.start = start;
            if (start < limit) {
                setPage(1);
            } else {
                setPage((start + limit * 2 - 1) / limit);
            }
        }

    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("page", page)
                .append("pageSize", pageSize)
                .append("total", total)
                .append("startIndex:" + this.getStartIndex())
                .append("endIndex:" + this.getEndIndex())
                .toString();
    }
}
