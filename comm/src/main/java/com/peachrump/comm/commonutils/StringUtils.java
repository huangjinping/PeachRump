package com.peachrump.comm.commonutils;

import android.text.TextUtils;

/**
 * author Created by harrishuang on 2017/6/22.
 * email : huangjinping@hdfex.com
 */

public class StringUtils {

    public  static String replaceAll(String input, String tag) {
        if (TextUtils.isEmpty(input)) {
            return tag;
        }
        StringBuffer buffer=new StringBuffer();
        for (int i = 0; i <input.length() ; i++) {
            buffer.append(tag);
        }
        return buffer.toString();
    }
}
