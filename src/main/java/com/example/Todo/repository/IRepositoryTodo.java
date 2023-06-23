package com.example.Todo.repository;

import com.example.Todo.utils.ServiceHibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class IRepositoryTodo<T> {
    protected Session session;

    @Autowired
    protected ServiceHibernate _serviceHibernate;

    public IRepositoryTodo(ServiceHibernate serviceHibernate){
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

    public boolean update(T o) {
        boolean result = false;
        try{
            session.beginTransaction();
            session.update(o);
            session.getTransaction().commit();
            result = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }
        return result;
    }

    public boolean delete(T o) {
        boolean result = false;
        try{
            session.beginTransaction();
            session.delete(o);
            session.getTransaction().commit();
            result = true;
        }catch (Exception e){
            session.getTransaction().rollback();
        }
        return result;
    }

    public void end(){
        session.close();
    }

    public abstract T findById(int id);

    public abstract List<T> findAll();
}
