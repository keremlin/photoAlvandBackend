package com.ara.photoalvand.services;

import java.util.ArrayList;

import com.ara.photoalvand.models.category;
import com.ara.photoalvand.repository.categoryRepository;
import com.ara.photoalvand.viewModels.VMcategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class categoryImp implements Icategory {
    @Autowired
    private categoryRepository repo;
    @Autowired
    private search searchService;

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
    public VMcategory findWithRandomImage(int item)
    {
        try{
            return new VMcategory(
                repo.findCategoryById(item).get(),
                searchService.byCategory(item).get(
                    util.RandomNumberBetween(0, searchService.numberOfCategoryItems(item))
                    ).getPhysicalPath()
                );
        }catch(Exception ex){
            System.out.println(ex.getMessage());return null;}
        
    };
    @Override
    public category findById(int item){
        try{
          return repo.findCategoryById(item).get();  
        }catch(Exception e){return null;}
    }
    @Autowired
    private config config;
    @Override
    public Iterable<VMcategory> getRandomeCateory(int number){
        var list=new ArrayList<VMcategory>();
        for(int item=0;item<number; item++){
           list.add(
               findWithRandomImage(
                   config.getAllCategoriesId()[util.RandomNumberBetween(0,config.getAllCategoriesId().length-1)]
                   )
                   );
        }
        return list;
    }
}
