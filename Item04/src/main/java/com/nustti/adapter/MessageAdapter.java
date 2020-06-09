package com.nustti.adapter;

import com.alibaba.fastjson.JSONObject;

/**
 * 将所有信息交给适配器转发
 */
public interface MessageAdapter {

    //接收信息
    void distribute(JSONObject obj);
}
