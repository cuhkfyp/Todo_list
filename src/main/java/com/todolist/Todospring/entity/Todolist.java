package com.todolist.Todospring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="todolist")
public class Todolist {

    @Column(name="title")
    private String title;

    @Column(name="icon_name")
    private String iconname;//icon_name

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int idd;

    @ManyToOne(cascade = {CascadeType.PERSIST ,CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE})
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User user_id;


    @OneToMany(mappedBy = "listid",
               fetch = FetchType.LAZY,
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    @JsonManagedReference
    private List<ListDetail> listDetails;

    public Todolist(String title, String iconname){
        this.title = title;
        this.iconname = iconname;
    }

    public Todolist(String title, String iconname, User user_id) {
        System.out.println("the received  constructor data");
        System.out.println("===============================");
        System.out.println(title);
        System.out.println(iconname);
        System.out.println(user_id);

        System.out.println("===============================");


        this.title = title;
        this.iconname = iconname;
        this.user_id = user_id;
    }

    public Todolist() {
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

    public int getIdd() {
        return idd;
    }

    public void setIdd(int idd) {
        this.idd = idd;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    /*@Override
    public String toString() {
            *//*return "Todolist{" +
                    "title='" + title + '\'' +
                    ", iconname='" + iconname + '\'' +
                    '}';*//*
        return "Todolist{" +
                "title='" + title + '\'' +
                ", iconname='" + iconname + '\'' +
                '}';
    }*/

   /* @Override
    public String toString() {
        return "Todolist{" +
                "title='" + title + '\'' +
                ", iconname='" + iconname + '\'' +
                ", idd=" + idd +
                '}';
    }*/

    @Override
    public String toString() {
        return "Todolist{" +
                "title='" + title + '\'' +
                ", iconname='" + iconname + '\'' +
                ", idd=" + idd +
                ", user_id=" + user_id +
                '}';
    }

    public List<ListDetail> getListDetails() {
        return listDetails;
    }

    public void setListDetails(List<ListDetail> listDetails) {
        this.listDetails = listDetails;
    }

    public void add(ListDetail templistDetail){
        if (listDetails == null) {
            listDetails = new ArrayList<>();
        }
        listDetails.add(templistDetail);

        templistDetail.setListid(this);

    }

}
