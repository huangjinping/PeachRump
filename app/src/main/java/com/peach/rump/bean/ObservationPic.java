package com.peach.rump.bean;

import java.util.List;

/**
 * author Created by harrishuang on 2017/6/21.
 * email : huangjinping@hdfex.com
 */

public class ObservationPic {

    private String title;
    private List<Pic> picList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Pic> getPicList() {
        return picList;
    }

    public void setPicList(List<Pic> picList) {
        this.picList = picList;
    }

    @Override
    public String toString() {
        return "ObservationPic{" +
                "title='" + title + '\'' +
                ", picList=" + picList +
                '}';
    }
}
