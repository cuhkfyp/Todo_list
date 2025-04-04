package com.todolist.Todospring.service;

import com.todolist.Todospring.entity.ListDetail;
import com.todolist.Todospring.entity.Todolist;
import com.todolist.Todospring.entity.User;

import java.util.List;

public interface TodoListService {
    List<Todolist> getAllTodoList();

    Todolist addorUpdateTodolist(String title, String iconname, User user_id);

    Todolist getTodolistById(int id);

    List<ListDetail> getListAllDetailById(int id);

    void deletedbyid(int id);
}
