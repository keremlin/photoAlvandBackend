package com.ara.photoalvand.services;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.ara.photoalvand.repository.categoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class config {
    public config(){
        System.out.println("--------------------------Configuration is Loaded : true ");
    }
    
    @Autowired
    private categoryRepository repoCategory;
    private ArrayList<Integer> list;

    public Integer[] getAllCategoriesId() {
        if(list==null)
            list=new ArrayList<>();
        if (list.isEmpty())
            repoCategory.findAll().stream().forEach(item -> {
                list.add(item.getId());
            }); 
        return list.toArray(new Integer[list.size()]);
    }
}
