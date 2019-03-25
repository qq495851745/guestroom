package com.bateng.guestroom.entity;

import java.util.List;

/**
 * 自定义分页对象
 * @param <T>
 */
public class PageVo<T> {
    private  int pageNum=1;//当前页码
    private  int numPerPage=10;//每页显示数量
    private  long totalCount;//总数量
    private  int totalPages;//总页数
    private  List<T> contents;//数据




    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }


    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getContents() {
        return contents;
    }

    public void setContents(List<T> contents) {
        this.contents = contents;
    }
}
