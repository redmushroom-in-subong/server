package com.rms.drifeserver.HomeController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainTestController {
    @GetMapping("/")
    public String hello(){
        return "home";
    }
}
