package com.example.Todo.repository;

import com.example.Todo.Entity.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryImage extends CrudRepository<Image,Integer> {
}
