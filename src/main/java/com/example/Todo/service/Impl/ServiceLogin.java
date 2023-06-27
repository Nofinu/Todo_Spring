package com.example.Todo.service.Impl;

import com.example.Todo.Entity.User;
import com.example.Todo.repository.RepositoryTodo;
import com.example.Todo.repository.RepositoryUser;
import com.example.Todo.service.IServiceLogin;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceLogin implements IServiceLogin {

    @Autowired
    private RepositoryUser repositoryUser;

    @Override
    public boolean register(User u) {
        if(repositoryUser.findByUsername(u.getUsername()) == null){
            u.setPassword(BCrypt.hashpw(u.getPassword(),BCrypt.gensalt(10)));
            repositoryUser.save(u);
            return true;
        }
        return false;
    }

    @Override
    public boolean login(User u) {
        if(repositoryUser.findByUsername(u.getUsername()) != null){
            User userFind = (User) repositoryUser.findByUsername(u.getUsername());
            return BCrypt.checkpw(u.getPassword(), userFind.getPassword());
        }
        return false;
    }
}
