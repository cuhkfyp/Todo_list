package com.todolist.Todospring.repo;


import com.todolist.Todospring.entity.Role;
import com.todolist.Todospring.entity.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role,Long> {

    // @Query("SELECT ld FROM ListDetail ld WHERE ld.listid.idd = :id order by ld.createdAt asc")   @Param("id") int id
    //from Role where name=:roleName

    //select rn from role where name

    @Query("select rl from Role rl where rl.name=:roleName")
    public Role findRoleByName(@Param("roleName") String roleName);

}