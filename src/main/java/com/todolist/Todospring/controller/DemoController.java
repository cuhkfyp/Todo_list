package com.todolist.Todospring.controller;

import com.todolist.Todospring.entity.ListDetail;
import com.todolist.Todospring.entity.Todolist;
import com.todolist.Todospring.entity.User;
import com.todolist.Todospring.service.TodoListServiceImpl;
import com.todolist.Todospring.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Enumeration;
import java.util.List;

@Controller
@CrossOrigin
public class DemoController {


    /*class Todolist {
        String title;
        String iconname;
        UUID idd;


        Todolist(String title,String iconname,UUID idd){
            this.title = title;
            this.iconname = iconname;
            this.idd = idd;

        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIconname() {
            return iconname;
        }

        public void setIconname(String iconname) {
            this.iconname = iconname;
        }

        public UUID getIdd() {
            return idd;
        }

        public void setIdd(UUID idd) {
            this.idd = idd;
        }

        @Override
        public String toString() {
            return "Todolist{" +
                    "title='" + title + '\'' +
                    ", iconname='" + iconname + '\'' +
                    '}';

    }*/

    @Autowired
    private TodoListServiceImpl todoListServiceImpl;

    @Autowired
    private UserServiceImpl userService;


    /*@Autowired
    private ArrayList<Todolist> toLi = new ArrayList<>();*/


    @GetMapping("/")
    public String basePage(Model model, Authentication authentication, HttpSession session) {
        String[] icons = {
                "bi-alarm", "bi-award", "bi-bell", "bi-calendar", "bi-camera",
                "bi-check-circle", "bi-cloud", "bi-emoji-smile", "bi-gear",
                "bi-archive","bi-arrow-down-up","bi-arrow-repeat","bi-arrows-move",
                "bi-award","bi-badge-4k","bi-badge-ad-fill","bi-bag-check-fill","bi-battery-charging",
                "bi-bell-slash-fill","bi-bicycle","bi-bookmarks","bi-bootstrap-reboot","bi-border-all",
                "bi-border-style","bi-box","bi-bricks","bi-bullseye","bi-calendar2-week","bi-camera-video",
                "bi-camera"
        };
        model.addAttribute("icons", icons);

        //System.out.println(authentication);

        System.out.println("session single");



        Enumeration<String> attributeNames = session.getAttributeNames();
        System.out.println("===== Session Attributes =====");
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            Object attributeValue = session.getAttribute(attributeName);
            System.out.println(attributeName + " = " + attributeValue);
        }
        System.out.println("=============================");


        System.out.println("testing filter todolist for user");

        System.out.println("==========================================");
        User findeduser =  userService.findByUserName(authentication.getName());


        userService.refresh(findeduser);

        System.out.println(findeduser.getTodolists());

        System.out.println("==========================================");

        //model.addAttribute("updatelist", todoListServiceImpl.getAllTodoList());

        model.addAttribute("updatelist", findeduser.getTodolists());


        return "home";
    }


    @PostMapping("/")
    public String testreturndata(HttpServletRequest request, Authentication authentication, Model model, HttpSession session, RedirectAttributes redirectAttributes){
        System.out.println("session receivedpost");

        var userlogin = new User();

        userlogin = (User) session.getAttribute("user");

        String theName = request.getParameter("inputname");

        if(theName != null && !theName.trim().isEmpty()) {
            theName = theName.toUpperCase();
        }else {
            theName = "false";
        }

        redirectAttributes.addFlashAttribute("message",theName);


        String iconname = request.getParameter("selectedIcon");


        redirectAttributes.addFlashAttribute("itemdetail", todoListServiceImpl.addorUpdateTodolist(theName,iconname,userlogin));

        System.out.println("controller line 196");


        return "redirect:/modifylist";
        //return "home";
    }

    @GetMapping("/modifylist")
    public String successPage(HttpServletRequest request,Authentication authentication, Model model,HttpSession session) {

        String[] icons = {
                "bi-alarm", "bi-award", "bi-bell", "bi-calendar", "bi-camera",
                "bi-check-circle", "bi-cloud", "bi-emoji-smile", "bi-gear",
                "bi-archive","bi-arrow-down-up","bi-arrow-repeat","bi-arrows-move",
                "bi-award","bi-badge-4k","bi-badge-ad-fill","bi-bag-check-fill","bi-battery-charging",
                "bi-bell-slash-fill","bi-bicycle","bi-bookmarks","bi-bootstrap-reboot","bi-border-all",
                "bi-border-style","bi-box","bi-bricks","bi-bullseye","bi-calendar2-week","bi-camera-video",
                "bi-camera"
        };
        model.addAttribute("icons", icons);

        User finduser = userService.findByUserName(authentication.getName());

        userService.refresh(finduser);

        model.addAttribute("updatelist", finduser.getTodolists());

        System.out.println("controller line 200");

        return "home"; // Load successPage.html
    }

    @GetMapping("/icons")
    public String showIcons(Model model) {
             String[] icons = {
                   "bi-alarm", "bi-award", "bi-bell", "bi-calendar", "bi-camera",
                   "bi-check-circle", "bi-cloud", "bi-emoji-smile", "bi-gear"
                };
            model.addAttribute("icons", icons);
            return "icon";
    }





    @GetMapping("/detail/{idd}")
    public String getItemDetails(@PathVariable("idd") int idd, Model model) {

        int searchName = idd;

        //System.out.println(("6789"));

      /*  Optional<Todolist> foundItem = toLi.stream()
                .filter(item ->  Integer.toString(item.getIdd())==searchName)
                .findFirst();*/

        String itemdetail;

        Todolist foundItem = todoListServiceImpl.getTodolistById(idd);
                /*todoListService.getTodolistById(idd);*/

        //System.out.println(("000000"));

        List<ListDetail>  currentlistdetail = null;
        //System.out.println("the found item is "+foundItem);
        if (foundItem !=null) {
            itemdetail = String.valueOf(foundItem.toString());
            //System.out.println("PATH 1 PATH 1 PATH 1 PATH 1 PATH 1");
            model.addAttribute("itemdetail", foundItem);
            currentlistdetail = todoListServiceImpl.getListAllDetailById(idd);
            foundItem.setListDetails(currentlistdetail);
        } else {
            itemdetail = "No item founded";
            //System.out.println("PATH 2 PATH 2 PATH 2 PATH 2 PATH 2");
            model.addAttribute("itemdetail", new Todolist("No item found","No icon"));
        }

        //List<ListDetail>  currentlistdetail =  todoListService.getListAllDetailById(idd);

        //foundItem.setListDetails(currentlistdetail);

        model.addAttribute("todolistdetail",currentlistdetail);
        System.out.println(currentlistdetail);
        return "fragments/itemdetail :: itemdetaill";


    }


}
