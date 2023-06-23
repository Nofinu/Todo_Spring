package com.example.Todo.service.Impl;

import com.example.Todo.Entity.User;
import com.example.Todo.repository.IRepositoryUser;
import com.example.Todo.service.IServiceLogin;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceLogin implements IServiceLogin {

    @Autowired
    private IRepositoryUser repositoryUser;

    @Override
    public boolean register(User u) {
        if(repositoryUser.findByUserName(u.getUsername()) == null){
            u.setPassword(BCrypt.hashpw(u.getPassword(),BCrypt.gensalt(10)));
            if(repositoryUser.create(u)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean login(User u) {
        if(repositoryUser.findByUserName(u.getUsername()) != null){
            User userFind = (User) repositoryUser.findByUserName(u.getUsername());
            return BCrypt.checkpw(u.getPassword(), userFind.getPassword());
        }
        return false;
    }
}
