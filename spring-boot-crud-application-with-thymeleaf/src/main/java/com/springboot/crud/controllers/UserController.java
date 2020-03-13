package com.springboot.crud.controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.crud.entities.User;
import com.springboot.crud.repositories.UserRepository;

import java.io.IOException;

@Controller
public class UserController {
    
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        for(int i=1;i<=30;i++) {
            String name = "Tri-DepTrai-" + i;
            User a = new User(name, "dangtri2911@gmail.com");
            userRepository.save(a);
        }
        this.userRepository = userRepository;
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }
    @GetMapping("/")
    public String Index(Model model) {
        model.addAttribute("users",userRepository.findAll(PageRequest.of(0,5)));
        return "index";
    }
    @GetMapping("/page/lastPage")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int num = (int) userRepository.count();
        int pageable = (int) Math.ceil(num/5);
        if(num%5 != 0 )
            resp.sendRedirect("/page/" + (pageable+1));
        else
            resp.sendRedirect("/page/" + (pageable));

    }
    @GetMapping("/page/{pageNumber}")
    public void redirect(@PathVariable int pageNumber,HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int num = (int) userRepository.count();
        int pageable = (int) Math.ceil(num/5);
        if(num%5 == 0)
            resp.sendRedirect("/page/" + (pageable) + "/" + pageNumber);
        else
            resp.sendRedirect("/page/" + (pageable+1) + "/" + pageNumber);
    }
    @GetMapping("/page/{pageAble}/{pageNumber}")
    public String Pagination(@PathVariable int pageAble,@PathVariable int pageNumber,Model model,HttpServletRequest req, HttpServletResponse resp) throws IOException {
        model.addAttribute("users",userRepository.findAll(PageRequest.of(pageNumber-1,5)));
        return "index";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }
    
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "update-user";
    }
    
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }
        
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }
    
    @GetMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") long id, Model model,HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        int pageNumber = (int) Math.ceil(id/5);
        int num = (int) userRepository.count();
        int pageable = (int) Math.ceil(num/5);

            resp.sendRedirect("/page/" + (pageable+1) + "/" + (pageNumber+1));
    }
    @GetMapping("/showAll")
    public String showAll(Model model)
    {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }
}