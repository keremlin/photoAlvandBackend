package com.ara.photoalvand.repository;

import java.util.List;

import com.ara.photoalvand.models.category;
import com.ara.photoalvand.models.file;

import org.springframework.data.repository.CrudRepository;

public interface fileRepository extends CrudRepository<file,Integer>
{
    public List<file> findAll();
    public List<file> findFileByisReviewed(boolean isReviewed);
    public file findById(int id);
    public List<file> findFileBycategories(category category);
    public int countByCategories(category category);
    
}
