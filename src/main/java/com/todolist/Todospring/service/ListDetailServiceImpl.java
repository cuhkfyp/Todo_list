package com.todolist.Todospring.service;


import com.todolist.Todospring.entity.ListDetail;
import com.todolist.Todospring.entity.Todolist;
import com.todolist.Todospring.repo.ListDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ListDetailServiceImpl implements ListDetailService {
    @Autowired
    private ListDetailRepo listDetailRepo;

/*    public ListDetail additemtocurrentlist(String detail) {

    }*/

    @Override
    public ListDetail addnewdetailtolist(String uuid, String detail, Todolist tl){


        ListDetail ld = new ListDetail();

        ld.setId(uuid);
        ld.setDetail(detail);
        ld.setListid(tl);

        return listDetailRepo.save(ld);
    }

    @Override
    public ListDetail updatedetailtolist(ListDetail ld) {
        return listDetailRepo.save(ld);
    }

    @Override
    public Optional<ListDetail> finddetailbyId(String id) {
        return listDetailRepo.findById(id);
    }

    @Override
    @Transactional
    public void deletebyId(String id) {

        listDetailRepo.deleteById(id);

    }

}
