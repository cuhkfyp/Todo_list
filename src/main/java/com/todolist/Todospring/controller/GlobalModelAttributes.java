package com.todolist.Todospring.controller;

import com.todolist.Todospring.entity.User;
import com.todolist.Todospring.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {


    /*@Autowired
    private UserServiceImpl userService;

    @ModelAttribute
    public void addAttributes(Model model,Authentication authentication) {
        String[] icons = {
                "bi-alarm", "bi-award", "bi-bell", "bi-calendar", "bi-camera",
                "bi-check-circle", "bi-cloud", "bi-emoji-smile", "bi-gear",
                "bi-archive","bi-arrow-down-up","bi-arrow-repeat","bi-arrows-move",
                "bi-award","bi-badge-4k","bi-badge-ad-fill","bi-bag-check-fill","bi-battery-charging",
                "bi-bell-slash-fill","bi-bicycle","bi-bookmarks","bi-bootstrap-reboot","bi-border-all",
                "bi-border-style","bi-box","bi-bricks","bi-bullseye","bi-calendar2-week","bi-camera-video",
                "bi-camera"
        };


        if (authentication!=null) {
            User findeduser = userService.findByUserName(authentication.getName());

            userService.refresh(findeduser);

            model.addAttribute("updatelist", findeduser.getTodolists());
        }

        model.addAttribute("icons", icons);


    }*/
}
