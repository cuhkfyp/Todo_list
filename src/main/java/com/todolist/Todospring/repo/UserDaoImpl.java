package com.todolist.Todospring.repo;

import com.todolist.Todospring.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class UserDaoImpl implements UserDaoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void refresh(User user) {
        entityManager.refresh(user); // Forces reload from DB
    }
}
