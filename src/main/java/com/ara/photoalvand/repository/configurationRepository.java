package com.ara.photoalvand.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import com.ara.photoalvand.models.configuration;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
@Transactional
public interface configurationRepository extends CrudRepository<configuration,Integer> {
    Optional<configuration> findById(int id);
    Optional<configuration> findByConfigKey(String configKey);
    Iterable<configuration> findAll();
        
}
