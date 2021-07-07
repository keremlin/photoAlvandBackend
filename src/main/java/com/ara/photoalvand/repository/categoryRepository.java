package com.ara.photoalvand.repository;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.ara.photoalvand.models.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
@Transactional
public interface categoryRepository extends JpaRepository<category,Long> {
    public Optional<category> findCategoryById(int id);
    public List<category> findAll();
    
    @Transactional
    @Query( "FROM category c WHERE c.id in (:ids)")
     public List<category> findCategoryByIds(@Param("ids") List<Integer> ids);
}
