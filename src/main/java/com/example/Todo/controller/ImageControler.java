package com.example.Todo.controller;

import com.example.Todo.Entity.Image;
import com.example.Todo.Entity.Todo;
import com.example.Todo.service.IServiceImage;
import com.example.Todo.service.IServiceTodo;
import com.example.Todo.service.Impl.ServiceImage;
import com.example.Todo.service.Impl.ServiceTodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/todo")
public class ImageControler {
    private String location = "src\\main\\resources\\static\\upload-dir";

    @Autowired
    private IServiceTodo serviceTodo;

    @Autowired
    private IServiceImage serviceImage;

    @GetMapping("/formupload/{id}")
    public ModelAndView getFormUpload(@PathVariable("id")Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("FormUpload");
        return modelAndView;
    }

    @PostMapping("/formupload/{id}")
    public String postFormUpload(@PathVariable("id")Integer id,@RequestParam("image") MultipartFile image) throws IOException {
      if(serviceTodo.findById(id)!= null){
          Todo todo =serviceTodo.findById(id);

          Path destinationFile = Paths.get(location).resolve(Paths.get(image.getOriginalFilename())).toAbsolutePath();
          InputStream stream = image.getInputStream();
          Files.copy(stream, destinationFile, StandardCopyOption.REPLACE_EXISTING);


          todo.addImage(serviceImage.create(image.getOriginalFilename(),todo));
      }
      return "redirect:/todo";
    }
}
