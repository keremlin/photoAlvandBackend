package com.ara.photoalvand.repository;
import java.util.List;

import com.ara.photoalvand.models.category;
import org.springframework.data.repository.CrudRepository;

public interface categoryRepository extends CrudRepository<category,Long> {
    public category findCategoryById(int id);
    public List<category> findAll();
     
}
