package com.example.Todo.controller;

import com.example.Todo.Entity.Todo;
import com.example.Todo.service.Impl.ServiceTodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private ServiceTodo serviceTodo;

    @GetMapping("")
    public ModelAndView homePage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("HomeTodo");
        List<Todo> todos = serviceTodo.findAll();
        if(!todos.isEmpty()){
            modelAndView.addObject("todo",todos);
        }
        return modelAndView;
    }



    @GetMapping("/form")
    public ModelAndView getFormTodo (){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("FormTodo");
        modelAndView.addObject("todo",new Todo());

        return modelAndView;
    }

    @PostMapping("/form")
    public String postFromTodo (@ModelAttribute Todo todo){
        String result = "FormTodo";
        if(todo.getId() == 0){
            if(serviceTodo.create(todo)){
                result = "redirect:/todo";
            }
        }else{
            if(serviceTodo.findAll().contains(todo)){
                List<Todo> todos = serviceTodo.findAll();
                Todo todoFind = todos.get(todos.indexOf(todo));
                todoFind.setTitle(todo.getTitle());
                todoFind.setContent(todo.getContent());
                todoFind.setDueDate(todo.getDueDate());
                if(serviceTodo.update(todoFind)){
                    result = "redirect:/todo";
                }
            }
        }
        return result;
    }

    @GetMapping("/status/{id}")
    public String changeStatus(@PathVariable("id") Integer id){
        Todo todo = serviceTodo.findById(id);
        todo.changeStatus();
        serviceTodo.update(todo);
        return "redirect:/todo";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editTodo(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Error");
        if(serviceTodo.findById(id) != null){
            modelAndView.setViewName("FormTodo");
            modelAndView.addObject("todo",serviceTodo.findById(id));
        }
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable("id") Integer id){
        serviceTodo.delete(id);
        return "redirect:/todo";
    }
}