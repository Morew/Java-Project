package com.nustti.producer;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Repository;

import javax.jms.Destination;


/**
 * 往消息服务推送邮件信息
 */
@Repository
public class RegisterMailboxProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void send(Destination destination, String msg){
        jmsMessagingTemplate.convertAndSend(destination,msg);
    }
}
