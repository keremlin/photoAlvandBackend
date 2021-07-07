package com.ara.photoalvand.services;

import com.ara.photoalvand.models.category;

public interface Icategory {
    public boolean create(category item);
    public boolean delete(int id);
    public Iterable<category> list();
    public category find(String item);
    public category findById(int item);
}
