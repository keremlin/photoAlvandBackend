package com.ara.photoalvand.services;

import java.util.List;

import com.ara.photoalvand.models.category;
import com.ara.photoalvand.repository.categoryRepository;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class categoryImp implements Icategory {
    @Autowired
    private categoryRepository repo;

    @Override
    public boolean create(category item){
        try{ repo.save(item);return true;}
        catch(Exception e){return false;}
    };
    @Override
    public boolean delete(int id){
        try{
            var item=this.findById(id);
            if(item!=null){
                repo.delete(item);
                return true;
            }
            else return false;
        }catch(Exception e){return false;}
    };
    @Override
    public Iterable<category> list(){
        try{var items= repo.findAll();return items;}
        catch(Exception e){return null;}
    };
    @Override
    public category find(String item)
    {
        return null;
    };
    @Override
    public category findById(int item){
        try{
          return repo.findCategoryById(item);  
        }catch(Exception e){return null;}
    }
}
