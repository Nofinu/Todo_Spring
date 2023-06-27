package com.example.Todo.repository;

import com.example.Todo.Entity.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryTodo extends CrudRepository<Todo,Integer> {
}
