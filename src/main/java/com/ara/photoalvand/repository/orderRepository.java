package com.ara.photoalvand.repository;

import java.util.List;
import java.util.Optional;

import com.ara.photoalvand.models.User;
import com.ara.photoalvand.models.orderCase;

import org.springframework.data.repository.CrudRepository;

public interface orderRepository extends CrudRepository<orderCase,Integer> {
    public Optional<List<orderCase>> findByOwner(User owner);
    public Optional<List<orderCase>> findByOwnerAndIsFinished(User owner,boolean isFinished);
    public Optional<orderCase> findTopByOwnerAndIsFinished(User owner,boolean isFinished);

}
