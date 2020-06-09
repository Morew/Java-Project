package com.nustti.utils;

import com.alibaba.fastjson.JSONObject;
import com.nustti.adapter.MessageAdapter;
import com.nustti.producer.MQInterfaceType;
import com.nustti.service.MailboxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 消费消息
 */
@Slf4j
@Component
public class ConsumerDistribute {

    @Autowired
    private MailboxService mailboxService;

    @Async
    @JmsListener(destination = "messages_queue")
    public void distribute(String json){
        log.info("###消息服务###收到消息，消息内容  json:{}",json);
        if(StringUtils.isEmpty(json)){
            return;
        }
        JSONObject jsonObject = new JSONObject().parseObject(json);
        //拿到请求头
        JSONObject header = jsonObject.getJSONObject("header");
        //取接口类型
        String interfaceType = header.getString("interfaceType");
        MessageAdapter messageAdapter = null;
        switch (interfaceType){
            case MQInterfaceType.SMS_MAIL:
                messageAdapter=mailboxService;
                break;
            default:
                break;
        }
        JSONObject content = jsonObject.getJSONObject("content");
        messageAdapter.distribute(content);
    }
}

