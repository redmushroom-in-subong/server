package com.rms.drifeserver.controller;


import com.rms.drifeserver.model.User;
import com.rms.drifeserver.service.UserService;
import org.mockito.internal.matchers.Null;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    ~~~ @RequestBody와 완전히 일치하는 생성자가 있어야 하는듯
     */
    @PostMapping("/user/signupJson")
    @ResponseBody
    public Map<String,Object> signupJson(Model model,@RequestBody User user) {
        Map<String,Object> ret=new HashMap<String,Object>();
        String temp=user.getLocGu();
        System.out.println("temp = " + temp);
        if(temp==null){
            ret.put("result","fail");
            ret.put("user",-1);
            return ret;
        }else{
            Optional<User> result=userService.saveUser(user);
            ret.put("result","success");
            ret.put("user",result);
            return ret;
        }
    }
    @PostMapping("/user/signup")
    public String signup(Model model,User user) {
        Optional<User> result=userService.saveUser(user);
        model.addAttribute("preResult",result);
        return "signup";
    }
}
