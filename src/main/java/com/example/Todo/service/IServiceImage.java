package com.example.Todo.service;

import com.example.Todo.Entity.Image;
import com.example.Todo.Entity.Todo;

public interface IServiceImage {
    Image create(String imagestr,Todo todo);
}
