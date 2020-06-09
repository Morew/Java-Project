package com.nustti.mapper;

import com.nustti.entity.UserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BaseDao {

    @InsertProvider(type=BaseProvider.class,method = "save")
    public void save(@Param("oj") Object oj, String table);

    @Select("select * from mb_user where phone = #{phone} and password = #{password}")
    public UserEntity getUserPhoneAndPwd(@Param("phone") String phone,@Param("password") String password);

    @Select("select * from mb_user where email = #{email} and password = #{password}")
    public UserEntity getUserEmailAndPwd(@Param("email") String email,@Param("password") String password);

    @Select("select * from mb_user where id = #{id}")
    public UserEntity getUserInfo(@Param("id") Long id);

    @Select("select * from mb_user where username = #{username}")
    public UserEntity getUserByName(@Param("username") String username);
    //public UserEntity getUserId(@Param("phone") String phone,@Param("password") String password);


}
