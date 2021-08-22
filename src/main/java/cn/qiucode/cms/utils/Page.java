package cn.qiucode.cms.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @program: cms
 * @description: 分页工具类
 * @author: 上官江北
 * @create: 2021-08-14 10:22
 */
public class Page<T> implements Serializable {


    //总的页数
    private long totalPage;
    //当前页
    private long currentPage;
    //总数量
    private long totalCount;

    //查询存储结果集
    private List<T> data;

    public Page() {
    }

    public Page(List<T> data, long totalPage) {
        this.data = data;
        this.totalPage = totalPage;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
