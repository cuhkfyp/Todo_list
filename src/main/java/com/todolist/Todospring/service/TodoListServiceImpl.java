package com.todolist.Todospring.service;


import com.todolist.Todospring.entity.ListDetail;
import com.todolist.Todospring.entity.Todolist;
import com.todolist.Todospring.entity.User;
import com.todolist.Todospring.repo.TodoListRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoListServiceImpl implements TodoListService {

    @Autowired
    private TodoListRepo todoListRepo;

    @Override
    public List<Todolist> getAllTodoList(){
        return todoListRepo.findAll();
    }

    @Autowired
    private UserServiceImpl userService;

    @Override
    @Transactional
    public Todolist addorUpdateTodolist(String title, String iconname, User user_Id){

        System.out.println("the received server impli");
        System.out.println("===============================");
        System.out.println(title);
        System.out.println(iconname);
        System.out.println(user_Id);

        System.out.println("===============================");

        //User managedUser = userDao.findById((int)user_Id.getId());

        User managedUser = userService.findById((int)user_Id.getId()).orElseThrow(() -> new RuntimeException("User not found"));

        return todoListRepo.save(new Todolist(title,iconname,managedUser));
    }

    @Override
    public Todolist getTodolistById(int id) {

        Optional<Todolist> result = todoListRepo.findById(id);

        Todolist theTodolist = null;

        if (result.isPresent()){
            theTodolist = result.get();
        }
        return theTodolist;
        /*return todoListRepo.findById(id).orElse(new Todolist());*/
    }

    @Override
    public List<ListDetail> getListAllDetailById(int id){
        return todoListRepo.findListDetailsByTodolistId(id);
    }

    @Override
    @Transactional
    public void deletedbyid(int id) {
        System.out.println("the todolist delete id is "+id);

        //Todolist findtodolist  = todoListRepo.findById(id).orElseThrow();

        //findtodolist.setUser_id(new User());

        todoListRepo.deleteById(id);

        //todoListRepo.flush();
        //todoListRepo.delete(todoListRepo.findById(id).orElseThrow());
        System.out.println("service impl deleted success");
    }

/*    public ArrayList<ListDetail> getListDetailsByTodolistId(int id) {
        List<ListDetail> listDetails = todoListRepo.findListDetailsByTodolistId(id);
        return new ArrayList<>(listDetails);
    }*/

}
