package com.ara.photoalvand.services;

import java.util.List;

import com.ara.photoalvand.models.category;
import com.ara.photoalvand.viewModels.VMcategory;

public interface Icategory {
    public boolean create(category item);

    public boolean delete(int id);

    public Iterable<category> list();

    public category find(String item);

    public category findById(int item);

    public VMcategory findWithRandomImage(int item);

    public Iterable<VMcategory> getRandomeCateory(int number);

    public List<category> getAllCategories();
}
