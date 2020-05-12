package com.nustti.springboot05.service;

import com.nustti.springboot05.bean.User;

import java.util.List;

public interface UserService {
    /**
     * 保存用户对象
     * @param user
     */
    void save(User user);

    /**
     * 获取所有用户对象
     * @return
     */
    List<User> getUserList();

    /**
     * 查找一个用户信息
     * @param id
     * @return
     */
    User find(int id);
    /**
     * 删除用户对象
     * @param user
     */
    void delete(User user);

}
