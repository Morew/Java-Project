package com.nustti.springboot05.service.impl;

import com.nustti.springboot05.bean.User;
import com.nustti.springboot05.dao.DaoRepositoty;
import com.nustti.springboot05.dao.UserRepository;
import com.nustti.springboot05.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public void save(User user) {

        userRepository.save(user);
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public User find(int id) {
        Optional<User> byId = userRepository.findById(id);
        return byId.get();
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }


}
