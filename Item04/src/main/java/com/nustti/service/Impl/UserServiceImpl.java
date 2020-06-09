package com.nustti.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.nustti.entity.UserEntity;
import com.nustti.mapper.BaseDao;
import com.nustti.producer.MQInterfaceType;
import com.nustti.producer.RegisterMailboxProducer;
import com.nustti.service.BaseRedisService;
import com.nustti.service.UserService;
import com.nustti.utils.*;
import lombok.extern.slf4j.Slf4j;

import org.apache.activemq.command.ActiveMQQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.jms.Destination;
import java.util.Map;


@Service
@Slf4j
public class UserServiceImpl  implements UserService {
    @Autowired
    private BaseDao baseDao;

   @Value("${spring.messages.queue}")
    private String MESSAGES_QUEUE;

    @Autowired
    private RegisterMailboxProducer registerMailboxProducer;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private BaseRedisService baseRedisService;
    BaseApiUtils baseApiUtils = new BaseApiUtils();

    @Override
    public void register(UserEntity userEntity) {
        String username = userEntity.getUsername();
        if(baseDao.getUserByName(username) != null){
            log.debug("用户名已经存在，请重试");
        }
        userEntity.setCreated(DataUtils.getTimeStamp());
        userEntity.setUpdated(DataUtils.getTimeStamp());
        //String phone = userEntity.getPhone();
        String password = userEntity.getPassword();
        userEntity.setPassword(md5PassSalt("atgigu@",password));
        baseDao.save(userEntity,"mb_user");
        //注册成功之后
        Destination activeMQQueue = new ActiveMQQueue(MESSAGES_QUEUE);
        //组装报文格式
        String message = mailMessage(userEntity.getEmail(), userEntity.getUsername());
        log.info("###regist() 注册发送邮件报文mailMessage:{}",message);
        registerMailboxProducer.send(activeMQQueue,message);
    }


    public String md5PassSalt(String account, String password) {

        String s = MD5Utils.MD5(account + password);
        return s;
    }


    private String mailMessage(String email,String username){
        JSONObject root= new JSONObject();
        JSONObject header= new JSONObject();
     header.put("interfaceType",MQInterfaceType.SMS_MAIL);
        JSONObject content= new JSONObject();
        content.put("mail",email);
        content.put("username",username);
        root.put("header",header);
        root.put("content",content);
        return root.toJSONString();
    }



    @Override
    public Map<String, Object> login(String account,String password) {
        //1.在数据库中查找数据
        //2.生成对应token
        //3.key为自定义令牌，用户userId作为value,存放在redis中

        /**
         * 1.通过邮箱登录
         *
         */
        Long id = null;
        String newPassword = md5PassSalt("atgigu@",password);
        System.out.println(account);
        System.out.println(newPassword);
        if(account.indexOf('@') == -1){//不为邮箱，没有‘@’符号
            System.out.println("111");
            UserEntity userPhoneAndPwd = baseDao.getUserPhoneAndPwd(account, newPassword);
            id = userPhoneAndPwd.getId();
            System.out.println(id);
            if(userPhoneAndPwd == null){
                return baseApiUtils.setResultError("账号或者密码错误！");
            }
        }
        else if(account.indexOf('@') > 0 ) {
            System.out.println("122");
            System.out.println(newPassword);
            UserEntity userEmailAndPwd = baseDao.getUserEmailAndPwd(account, "0FD09F1D1054AFC7265F07F3541F96FE");
            System.out.println(userEmailAndPwd);
            id = userEmailAndPwd.getId();
           if(userEmailAndPwd == null) {
                return baseApiUtils.setResultError("账号或者密码错误！");
            }
        }
        String token = tokenUtils.getToken();
        baseRedisService.set(token,id+"", Constants.TERMVALIDITY);
        return baseApiUtils.setResultSuccesssData(token);
    }

    /**
     * 使用token查找用户信息
     * @param token
     * @return
     */
    @Override
    public Map<String, Object> getUser(String token) {
        String userId = baseRedisService.get(token);
        if(StringUtils.isEmpty(userId)){
            return baseApiUtils.setResultError("用户已经过期");
        }
        Long newUserId1 = Long.parseLong(userId);
        UserEntity userInfo = baseDao.getUserInfo(newUserId1);
        userInfo.setPassword(null);
        return  baseApiUtils.setResultSuccesssData(userInfo);
    }


}
