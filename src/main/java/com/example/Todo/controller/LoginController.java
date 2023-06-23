package com.example.Todo.controller;

import com.example.Todo.Entity.User;
import com.example.Todo.service.IServiceLogin;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/todo")
public class LoginController {

    @Autowired
    private IServiceLogin serviceLogin;

    @Autowired
    HttpSession _httpsession;

    @GetMapping("/login/{type}")
    public ModelAndView getLogin (@PathVariable("type") String type){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("LoginForm");
        modelAndView.addObject("user",new User());
        modelAndView.addObject("type",type);
        return modelAndView;
    }

    @GetMapping("/loout")
    public String getLogOut(){
        _httpsession.removeAttribute("isLogged");
        return "redirect:/todo";
    }

    @PostMapping("/login/{type}")
    public String postLogin(@PathVariable("type") String type,@ModelAttribute User user){
        if(type.equals("login")){
            if(serviceLogin.login(user)){
                _httpsession.setAttribute("isLogged",true);
            }
        }else{
            if(serviceLogin.register(user)){
                _httpsession.setAttribute("isLogged",true);
            }
        }
        return "redirect:/todo";
    }
}
