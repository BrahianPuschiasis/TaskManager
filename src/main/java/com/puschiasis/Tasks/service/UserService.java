package com.puschiasis.Tasks.service;

import com.puschiasis.Tasks.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    List<User> findByUserName(String userName);
    Optional<User> findById(String id);


}
