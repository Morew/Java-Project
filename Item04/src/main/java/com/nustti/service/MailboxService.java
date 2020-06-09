package com.nustti.service;

import com.alibaba.fastjson.JSONObject;
import com.nustti.adapter.MessageAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailboxService implements MessageAdapter {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void distribute(JSONObject obj) {
        String mail = obj.getString("mail");
        System.out.println(mail);
        String username = obj.getString("username");
        log.info("###消费者收到消息...mail:{},username:{}",mail,username);
        //发送邮件
        //开始发送邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kuangchuny@sina.com");
        message.setTo(mail);//自己给自己发送邮件
        message.setSubject("注册成功提醒");
        message.setText("尊敬的" + username +"用户"+"," +"恭喜您注册成功！谢谢您的关注");
        log.info("###发送短信邮箱 mail:{}",mail);
        mailSender.send(message);
    }
}
