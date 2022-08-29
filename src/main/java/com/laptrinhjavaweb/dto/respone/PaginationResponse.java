package com.laptrinhjavaweb.dto.respone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaginationResponse<T> implements Serializable {

    private List<T> listResult = new ArrayList<>();
    private Integer page = 1;
    private Integer totalPages;
    private Integer totalPageItems = 4;
    private Integer totalItems;
    private String tableId = "tableList";


    public List<T> getListResult() {
        return listResult;
    }

    public void setListResult(List<T> listResult) {
        this.listResult = listResult;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalPageItems() {
        return totalPageItems;
    }

    public void setTotalPageItems(Integer totalPageItems) {
        this.totalPageItems = totalPageItems;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
}
