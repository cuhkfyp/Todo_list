package com.todolist.Todospring.service;


import com.todolist.Todospring.entity.User;
import com.todolist.Todospring.user.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    public User findByUserName(String userName);

    void save(WebUser webUser);

    public Optional<User> findById(int id);

    void refresh(User user);

}
