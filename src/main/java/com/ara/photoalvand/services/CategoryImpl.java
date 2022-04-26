package com.ara.photoalvand.services;

import java.util.ArrayList;
import java.util.List;

import com.ara.photoalvand.exception.RecordNotFoundException;
import com.ara.photoalvand.exception.RecordPersistanceException;
import com.ara.photoalvand.models.category;
import com.ara.photoalvand.repository.categoryRepository;
import com.ara.photoalvand.viewModels.VMcategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryImpl implements Category {

    @Autowired
    private categoryRepository repo;
    
    @Autowired
    private SearchImpl searchService;

    @Override
    public category create(category item) {
        try {
            return repo.save(item);
        } catch (Exception e) {
            throw new RecordPersistanceException();
        }
    }

    @Override
    public category delete(int id) {
        try {
            var item = this.findById(id);
            repo.delete(item);
            return item;
        } catch (Exception e) {
            throw new RecordPersistanceException("category could not deleted : " + id);
        }
    }

    @Override
    public Iterable<category> list() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            throw new RecordPersistanceException(e.getMessage());
        }
    }
   
    @Override
    public category find(String item) {
        return null;
    };

    @Override
    public VMcategory findWithRandomImage(int item) {
        try {
            return new VMcategory(
                    repo.findCategoryById(item).get(),
                    searchService.byCategory(item).get(
                            util.RandomNumberBetween(0, searchService.numberOfCategoryItems(item))).getPhysicalPath());
        } catch (Exception ex) {
            throw new RecordNotFoundException(ex.getMessage());
        }
    }

    @Override
    public category findById(int item) {
        try {
            return repo.findCategoryById(item).get();
        } catch (Exception e) {
            throw new RecordNotFoundException("The item is not exist : " + item);
        }
    }

    @Autowired
    private config config;

    @Override
    public Iterable<VMcategory> getRandomCategory(int number) {
        var list = new ArrayList<VMcategory>();
        for (int item = 0; item < number; item++) {
            list.add(
                    findWithRandomImage(
                            config.getAllCategoriesId()[util.RandomNumberBetween(0,
                                    config.getAllCategoriesId().length - 1)]));
        }
        return list;
    }

    @Override
    public List<VMcategory> getAllCategories() {
        List<VMcategory> list = new ArrayList<>();
        repo.findAll().forEach((item) -> {
            list.add(findWithRandomImage(item.getId()));
        });
        return list;
    }
}
