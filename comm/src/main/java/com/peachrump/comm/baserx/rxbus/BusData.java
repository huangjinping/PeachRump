package com.peachrump.comm.baserx.rxbus;

/**
 * author Created by harrishuang on 2017/6/21.
 * email : huangjinping@hdfex.com
 */

public class BusData {

    String id;
    String status;
    public BusData() {}
    public BusData(String id, String status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
