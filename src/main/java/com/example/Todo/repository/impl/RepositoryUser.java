package com.example.Todo.repository.impl;

import com.example.Todo.Entity.User;
import com.example.Todo.repository.IRepositoryUser;
import com.example.Todo.utils.ServiceHibernate;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryUser extends IRepositoryUser<User> {

    public RepositoryUser(ServiceHibernate serviceHibernate) {
        super(serviceHibernate);
    }

    @Override
    public User findByUserName(String username) {
        Query<User> userQuery = session.createQuery("from User where username = :name", User.class);
        userQuery.setParameter("name",username);
        return userQuery.uniqueResult();
    }
}
