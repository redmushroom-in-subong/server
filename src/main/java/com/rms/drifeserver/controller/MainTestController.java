package com.rms.drifeserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller()
public class MainTestController {
    @GetMapping("/")
    public String hello(){
        return "home";
    }


}
