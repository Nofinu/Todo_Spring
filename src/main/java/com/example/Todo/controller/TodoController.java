package com.example.Todo.controller;

import com.example.Todo.Entity.Todo;
import com.example.Todo.service.Impl.ServiceTodo;
import jakarta.servlet.http.HttpSession;
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

    @Autowired
    HttpSession _httpsession;

    private String select = "all";

    @GetMapping("")
    public ModelAndView homePage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("HomeTodo");
        modelAndView.addObject("isLogged",_httpsession.getAttribute("isLogged"));
        List<Todo> todos = serviceTodo.findAll();
        if(!todos.isEmpty()){
            if (select.equals("todo")){
                todos = todos.stream().filter(t-> !t.isComplete()).toList();
            }else if(select.equals("do")){
                todos = todos.stream().filter(Todo::isComplete).toList();
            } else if (select.equals("urgent")){
                todos = todos.stream().filter(t-> t.getDueDate().before(new Date())).toList();
            }
            modelAndView.addObject("todo",todos);
        }
        return modelAndView;
    }

    @PostMapping("")
    public String select(@RequestParam("type")String type){
        select = type;
        return "redirect:/todo";
    }



    @GetMapping("/form")
    public ModelAndView getFormTodo (){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Error");
        if((boolean) _httpsession.getAttribute("isLogged")){
            modelAndView.setViewName("FormTodo");
            modelAndView.addObject("todo",new Todo());
        }
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
