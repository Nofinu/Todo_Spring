package com.example.Todo.service.Impl;

import com.example.Todo.Entity.Todo;
import com.example.Todo.repository.RepositoryTodo;
import com.example.Todo.service.IServiceTodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceTodo implements IServiceTodo {

    @Autowired
    private RepositoryTodo _repositoryTodo;

    @Override
    public boolean create(Todo t) {
        _repositoryTodo.save(t);
        return true;

    }
    @Override
    public boolean update(Todo t) {
        _repositoryTodo.save(t);
        return true;
    }


    @Override
    public boolean delete(int id) {
        Optional<Todo> todo = _repositoryTodo.findById(id);
        if(todo.isPresent()){
            _repositoryTodo.delete(todo.get());
            return true;

        }
        return false;
    }

    @Override
    public Todo findById(int id) {
        return _repositoryTodo.findById(id).get();
    }

    @Override
    public List<Todo> findAll() {
        return (List<Todo>) _repositoryTodo.findAll();
    }
}
