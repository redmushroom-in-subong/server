package com.rms.drifeserver.controller;


import com.rms.drifeserver.model.User;
import com.rms.drifeserver.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService=userService;
    }
    @GetMapping("/user")
    public String user(Model model) {
       List<User> users= userService.findAllUsers();
       model.addAttribute("users",users);
        return "user";
    }
    @GetMapping("/user/signup")
    public String signupPage(Model model) {
        model.addAttribute("preResult",null);
        return "signup";
    }
    @PostMapping("/user/signup")
    public String signup(Model model,User user) {
        Integer result=userService.saveUser(user);
        model.addAttribute("preResult",result);
        return "signup";
    }
}
