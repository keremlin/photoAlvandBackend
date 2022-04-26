package com.ara.photoalvand.services;

import java.util.List;

import com.ara.photoalvand.models.category;
import com.ara.photoalvand.viewModels.VMcategory;

public interface Category {

    public category create(category item);

    public category delete(int id);

    public Iterable<category> list();

    public category find(String item);

    public category findById(int item);

    public VMcategory findWithRandomImage(int item);

    public Iterable<VMcategory> getRandomCategory(int number);

    public List<VMcategory> getAllCategories();
}
