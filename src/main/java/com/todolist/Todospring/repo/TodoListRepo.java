package com.todolist.Todospring.repo;

import com.todolist.Todospring.entity.ListDetail;
import com.todolist.Todospring.entity.Todolist;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoListRepo extends JpaRepository<Todolist,Integer> {

    //@Query("select * from ListDetail where listid.idd = :id")
    //@Query("SELECT ld FROM ListDetail ld WHERE ld.listid.idd = :id")

    /// version one
    @Query("SELECT ld FROM ListDetail ld WHERE ld.listid.idd = :id order by ld.createdAt asc")
    List<ListDetail> findListDetailsByTodolistId(@Param("id") int id);

    //DELETE FROM table_name WHERE condition;


    @Modifying
    @Transactional
    @Query("Delete from Todolist tl where tl.id=:id")
    void deleteById(@Param("id") int id);
    //@Query("SELECT t FROM Todolist t JOIN FETCH t.listDetails WHERE t.idd = :id")
    //Optional<Todolist> findByIdWithListDetails(@Param("id") int id);

}



