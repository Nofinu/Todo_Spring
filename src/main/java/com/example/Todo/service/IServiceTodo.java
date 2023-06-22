package com.example.Todo.service;

import com.example.Todo.Entity.Todo;

import java.util.Date;
import java.util.List;

public interface IServiceTodo {
    boolean create(Todo t);
    boolean update(Todo t);
    boolean delete(int id);

    Todo findById(int id);

    List<Todo> findAll();
}
