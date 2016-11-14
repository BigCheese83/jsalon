package ru.bigcheese.jsalon.dao.paging;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {

    private List<T> data;
    private int totalPages;
    private long totalElements;

    public Page() {}

    public Page(List<T> data, long totalElements, int pageSize) {
        this.data = data;
        this.totalElements = totalElements;
        this.totalPages = calcTotalPages(totalElements, pageSize);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    private int calcTotalPages(long total, int pageSize) {
        if (total == 0 || pageSize == 0) return 0;
        return (int)(total + pageSize - 1)/pageSize;
    }

    @Override
    public String toString() {
        return "Page{" +
                "data=" + data +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                '}';
    }
}
