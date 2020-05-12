package com.nustti.springboot05.dao;

import com.nustti.springboot05.bean.User;
import com.nustti.springboot05.bean.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoRepositoty<T extends UserBean> extends JpaRepository<User,Integer> {

}
