package ru.bigcheese.jsalon.dao.paging;

import java.io.Serializable;

public class PageRequest implements Serializable {

    private int offset;
    private int pageSize;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageRequest{" +
                "offset=" + offset +
                ", pageSize=" + pageSize +
                '}';
    }
}
