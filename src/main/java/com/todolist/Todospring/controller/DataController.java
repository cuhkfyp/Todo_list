package com.todolist.Todospring.controller;

import com.todolist.Todospring.entity.ListDetail;
import com.todolist.Todospring.service.ListDetailServiceImpl;
import com.todolist.Todospring.service.TodoListServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
public class DataController {

/*    @RequestMapping("/handlemodaldata")
    public String handata(){
        return "hello-world!";
    }*/

    @Autowired
    private ListDetailServiceImpl listDetailServiceImpl;

    @Autowired
    private TodoListServiceImpl todoListServiceImpl;

    @PostMapping("/{idd}")
    public ResponseEntity<?> updatelist(@PathVariable("idd") int idd, Model model, HttpServletRequest request, @RequestBody ListDetail listDetail) throws IOException {
        System.out.println("it work!");


        // Print request parameters
        request.getParameterMap().forEach((key, values) -> {
            System.out.println("Parameter: " + key + " -> " + String.join(", ", values));
        });

        ListDetail ld = null;


        try {
            ld =  listDetailServiceImpl.addnewdetailtolist(listDetail.getId(), listDetail.getDetail(), todoListServiceImpl.getTodolistById(idd));

            //System.out.println("testing get detail");

            //System.out.println(todoListService.getListAllDetailById(idd));
            return new ResponseEntity<>(ld,HttpStatus.CREATED);
            //return todoListService.getListAllDetailById(idd);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Read request body (useful for JSON payloads)



    }

    @PutMapping("/{idd}")
    public ResponseEntity<?> updatedetail(@PathVariable("idd") int idd, Model model, HttpServletRequest request, @RequestBody ListDetail listDetail) throws IOException {
        System.out.println("received updated data");



        try {
            //ListDetail ld = ;
            Optional<ListDetail> findedld = listDetailServiceImpl.finddetailbyId(listDetail.getId());

            ListDetail ld =  findedld.get();

            ld.setDetail(listDetail.getDetail());

            ld.setItemstate(listDetail.getItemstate());

            ListDetail updateresult = listDetailServiceImpl.updatedetailtolist(ld);

            //System.out.println(todoListService.getListAllDetailById(idd));
            return new ResponseEntity<>(updateresult,HttpStatus.OK);
            //return todoListService.getListAllDetailById(idd);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }



    @DeleteMapping("/detail/{dtailid}")
    public ResponseEntity<?> deletedetail(@PathVariable("dtailid") String detailid) throws IOException {

        System.out.println("received delete detail request");
        try{

            listDetailServiceImpl.deletebyId(detailid);
            return new ResponseEntity<>(detailid,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/todolist/{id}")
    public ResponseEntity<?> deletetodolist(@PathVariable("id") int id, Authentication authentication) throws IOException {

        System.out.println("received delete list request");
        try {
            System.out.println("the received id is" + id);
            todoListServiceImpl.deletedbyid(id);
            System.out.println("deleted success");
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}


////response entity  reference section 16 248  https://github.com/navinreddy20/spring6-course/blob/main/16%20Project%20using%20Spring%20Boot%20MVC/16.10%20Update%20and%20Delete%20Product/src/main/java/com/telusko/springecom/controller/ProductController.java