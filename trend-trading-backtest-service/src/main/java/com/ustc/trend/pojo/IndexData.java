package com.ustc.trend.pojo;

public class IndexData implements Comparable<IndexData> {
    String date;
    float closePoint;

    public IndexData(String date, float closePoint) {
        this.date = date;
        this.closePoint = closePoint;
    }

    public IndexData() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getClosePoint() {
        return closePoint;
    }

    public void setClosePoint(float closePoint) {
        this.closePoint = closePoint;
    }


    @Override
    public int compareTo(IndexData o) {
        if (this.getDate().compareTo(o.getDate()) > 0)
            return 1;
        if (this.getDate().compareTo(o.getDate()) < 0)
            return -1;
        return 0;
    }
}
