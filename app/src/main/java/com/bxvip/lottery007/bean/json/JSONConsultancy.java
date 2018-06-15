package com.bxvip.lottery007.bean.json;

import java.util.List;

public class JSONConsultancy {

    private List<DataList> dataList;
    private int pageSize;
    private int pageNo;
    private int totalPage;

    public void setDataList(List<DataList> dataList) {
        this.dataList = dataList;
    }
    
    public List<DataList> getDataList() {
        return dataList;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getPageSize() {
        return pageSize;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }
}