package com.bsoft.support;

import java.util.List;
import java.util.Map;

/**
 * Created by wjtc8 on 2017/5/12.
 */
public class Pager {

    private long rowCount;

    private int pageSize;

    public Pager() {
    }

    public Pager(long rowCount, int pageSize, List<Map<String, Object>> result) {
        this.rowCount = rowCount;
        this.pageSize = pageSize;
        this.result = result;
    }

    private List<Map<String, Object>> result;

    public long getRowCount() {
        return rowCount;
    }

    public void setRowCount(long rowCount) {
        this.rowCount = rowCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Map<String, Object>> getResult() {
        return result;
    }

    public void setResult(List<Map<String, Object>> result) {
        this.result = result;
    }


}
