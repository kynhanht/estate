package com.laptrinhjavaweb.dto.request;

import java.io.Serializable;

public class PaginationRequest implements Serializable {

    private Integer page;

    private Integer totalPageItems = 4;

    private String sortColumnName;

    private String sortDirection;

    private String tableId = "tableList";


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPageItems() {
        return totalPageItems;
    }

    public void setTotalPageItems(Integer totalPageItems) {
        this.totalPageItems = totalPageItems;
    }

    public String getSortColumnName() {
        return sortColumnName;
    }

    public void setSortColumnName(String sortColumnName) {
        this.sortColumnName = sortColumnName;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
}
