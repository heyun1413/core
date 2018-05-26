package me.ron.core.bean;

import java.io.Serializable;
import java.util.List;


public class Pager<Data> implements Serializable {

    private static final int DEFAULT_PAGER_SIZE = 10;

    private List<Data> data;
    private long count;
    private int pagerSize = DEFAULT_PAGER_SIZE;
    private int curPage;

    public Pager(int curPage, long count, List<Data> data) {
        this.curPage = curPage;
        this.count = count;
        this.data = data;
    }



    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getPagerSize() {
        return pagerSize;
    }

    public void setPagerSize(int pagerSize) {
        this.pagerSize = pagerSize;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    @Override
    public String toString() {
        return "Pager{" +
                "data=" + data +
                ", count=" + count +
                ", pagerSize=" + pagerSize +
                ", curPage=" + curPage +
                '}';
    }
}
