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

    /*
    @yts
    request body 받아와서 회원가입 예제(postman 참고) @RequestBody 만 붙여주면됨
    --> request body가 User 랑 다른 형식이면 400에러를 던저준다
     */
    @PostMapping("/user/signupJson")
    public String signupJson(Model model,@RequestBody User user) {
        Integer result=userService.saveUser(user);
        model.addAttribute("preResult",result);
        return "signup";
    }
    @PostMapping("/user/signup")
    public String signup(Model model,User user) {
        Integer result=userService.saveUser(user);
        model.addAttribute("preResult",result);
        return "signup";
    }
}
