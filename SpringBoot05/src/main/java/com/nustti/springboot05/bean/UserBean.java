package com.nustti.springboot05.bean;

import org.hibernate.annotations.GenericGenerator;
/**
 * 存放实体的公共属性，不会映射到数据库里
 */
import javax.persistence.*;
import java.util.Date;
@MappedSuperclass
public class UserBean {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)//设置id自增长
    private Integer id;

}
