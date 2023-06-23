package com.example.Todo.repository.impl;

import com.example.Todo.Entity.Todo;
import com.example.Todo.repository.IRepositoryTodo;
import com.example.Todo.utils.ServiceHibernate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepository extends IRepositoryTodo<Todo> {

    public TodoRepository(ServiceHibernate serviceHibernate) {
        super(serviceHibernate);
    }

    @Override
    public Todo findById(int id) {
        return session.get(Todo.class,id);
    }

    @Override
    public List<Todo> findAll() {
        return session.createQuery("from Todo ", Todo.class).list();
    }
}
