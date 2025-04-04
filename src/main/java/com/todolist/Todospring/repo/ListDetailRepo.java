package com.todolist.Todospring.repo;

import com.todolist.Todospring.entity.ListDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListDetailRepo extends JpaRepository<ListDetail,String> {
}
