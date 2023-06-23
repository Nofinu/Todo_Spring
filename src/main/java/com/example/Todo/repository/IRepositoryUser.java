package com.example.Todo.repository;

import com.example.Todo.utils.ServiceHibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

public abstract class IRepositoryUser<T> {
    protected Session session;

    @Autowired
    protected ServiceHibernate _serviceHibernate;

    public IRepositoryUser(ServiceHibernate serviceHibernate){
        this._serviceHibernate = serviceHibernate;
        this.session = _serviceHibernate.getSession();
    }

    public boolean create(T o) {
        boolean result = false;
        try{
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
            result = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }
        return result;
    }

    public void end(){
        session.close();
    }

    public abstract T findByUserName(String username);


}
