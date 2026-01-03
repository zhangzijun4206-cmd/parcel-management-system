package com.at.zijun.dto;

import java.util.List;

public class PageResult<T> {
    private final List<T> list;
    private final int total;
    private final int page;
    private final int size;

    public PageResult(List<T> list, int total, int page, int size) {
        this.list = list;
        this.total = total;
        this.page = page;
        this.size = size;
    }

    public List<T> getList() { return list; }
    public int getTotal() { return total; }
    public int getPage() { return page; }
    public int getSize() { return size; }

    public int getTotalPages() {
        return (int) Math.ceil(total * 1.0 / size);
    }

    public boolean isHasPrev() { return page > 1; }
    public boolean isHasNext() { return page < getTotalPages(); }
}