package com.example.Todo.repository;


import com.example.Todo.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUser extends CrudRepository<User,Integer> {

    public User findByUsername (String username);
}
