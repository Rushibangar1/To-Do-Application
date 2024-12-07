package com.cdac.todo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cdac.todo.Entity.Todo;

import java.util.Optional;

@Repository
public interface TodoRepository extends PagingAndSortingRepository<Todo, Long>{

    void save(Todo todo);

    void deleteById(long id);

    Object findAll();
    
    Optional<Todo> findById(long id);
}
