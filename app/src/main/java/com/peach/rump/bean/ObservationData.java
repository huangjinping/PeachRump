package com.peach.rump.bean;

import com.peachrump.comm.basebean.BaseBean;

/**
 * author Created by harrishuang on 2017/6/21.
 * email : huangjinping@hdfex.com
 */

public class ObservationData extends BaseBean {


    private String title;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ObservationData{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
