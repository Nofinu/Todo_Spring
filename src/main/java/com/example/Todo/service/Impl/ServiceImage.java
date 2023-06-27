package com.example.Todo.service.Impl;

import com.example.Todo.Entity.Image;
import com.example.Todo.Entity.Todo;
import com.example.Todo.repository.RepositoryImage;
import com.example.Todo.service.IServiceImage;
import com.example.Todo.service.IServiceTodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceImage implements IServiceImage {

    @Autowired
    private RepositoryImage repositoryImage;
    @Override
    public Image create(String imagestr, Todo todo) {
        Image image = new Image();
        image.setUri(imagestr);
        image.setTodo(todo);
        return  repositoryImage.save(image);
    }
}
