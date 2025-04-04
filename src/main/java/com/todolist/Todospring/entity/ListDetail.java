package com.todolist.Todospring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="listdetail")
public class ListDetail {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="detail")
    private String detail;

    /*@Column(name="item_order")
    private int order;*/
    @CreationTimestamp // Automatically populates this field on insert
    @Column(name = "created_at", updatable = false) // Prevent updates
    private LocalDateTime createdAt;

    @ManyToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE,
               CascadeType.DETACH, CascadeType.REFRESH},
                fetch = FetchType.LAZY)
    @JoinColumn(name="listid")
    @JsonBackReference
    private Todolist listid;

    @Column(name="state")
    private boolean itemstate;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

 /*   public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }*/

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /* public int getListid() {
        return listid;
    }

    public void setListid(int listid) {
        this.listid = listid;
    }*/

    public Todolist getListid() {
        return listid;
    }

    public void setListid(Todolist listid) {
        this.listid = listid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getItemstate() {
        return itemstate;
    }

    public void setItemstate(boolean itemstate) {
        this.itemstate = itemstate;
    }

    /*@Override
    public String toString() {
        return "ListDetail{" +
                "id=" + id +
                ", detail='" + detail + '\'' +
                ", order=" + order +
                '}';
    }*/

    @Override
    public String toString() {
        return "ListDetail{" +
                "id='" + id + '\'' +
                ", detail='" + detail + '\'' +
                ", createdAt=" + createdAt +
                ", listid=" + listid +
                ", itemstate=" + itemstate +
                '}';
    }

    /*public ListDetail(String detail, int order, int listid) {
        this.detail = detail;
        this.order = order;
        this.listid = listid;
    }*/

    public ListDetail() {
    }

    public ListDetail(String uuid,String detail, Todolist listid) {
        this.id=uuid;
        this.detail = detail;
        this.listid = listid;
    }

    public ListDetail(String id, String detail) {
        this.id = id;
        this.detail = detail;
    }

    public ListDetail(String detail) {
        this.detail = detail;
    }




}
