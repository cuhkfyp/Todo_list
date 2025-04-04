package com.todolist.Todospring.service;

import com.todolist.Todospring.entity.ListDetail;
import com.todolist.Todospring.entity.Todolist;

import java.util.Optional;

public interface ListDetailService {
    ListDetail addnewdetailtolist(String uuid, String detail, Todolist tl);

    ListDetail updatedetailtolist(ListDetail ld);

    Optional<ListDetail> finddetailbyId(String id);

    void deletebyId(String id);
}
