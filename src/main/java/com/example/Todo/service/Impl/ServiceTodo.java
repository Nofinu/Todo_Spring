package com.example.Todo.service.Impl;

import com.example.Todo.Entity.Todo;
import com.example.Todo.repository.impl.TodoRepository;
import com.example.Todo.service.IServiceTodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ServiceTodo implements IServiceTodo {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public boolean create(Todo t) {
        return todoRepository.create(t);
    }
    @Override
    public boolean update(Todo t) {
        return todoRepository.update(t);
    }


    @Override
    public boolean delete(int id) {
        Todo todo = todoRepository.findById(id);
        if(todo != null){
            return  todoRepository.delete(todo);
        }
        return false;
    }

    @Override
    public Todo findById(int id) {
        return todoRepository.findById(id);
    }

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }
}
