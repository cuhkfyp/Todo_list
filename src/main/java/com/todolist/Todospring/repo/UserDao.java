package com.todolist.Todospring.repo;


import com.todolist.Todospring.entity.Todolist;
import com.todolist.Todospring.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserDao extends JpaRepository<User,Long>,UserDaoCustom {
    //JpaRepository<User,Long>

    /*
    *
    * @Query("SELECT ld FROM ListDetail ld WHERE ld.listid.idd = :id order by ld.createdAt asc")
    List<ListDetail> findListDetailsByTodolistId(@Param("id") int id);
    *
    *
    * from User where userName=:uName and enabled=true
    * */

    @Query("SELECT Us FROM User Us WHERE Us.userName=:userName")
    User findByUserName(@Param("userName") String userName);

    //void save(User theUser);

    //User findById(int id);
    //Optional<Object> findById(long id);

    /*@Query("select Us from User Us where Us.id=:id")
    @EntityGraph(attributePaths = { "todolists" })
    User refresh(@Param("id") long id);*/


}
