package com.todolist.Todospring.repo;


import com.todolist.Todospring.entity.User;

public interface UserDaoCustom {
    void refresh(User user);
}