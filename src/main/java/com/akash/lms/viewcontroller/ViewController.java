package com.akash.lms.viewcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
//@RequestMapping(value = "/lib/view")
public class ViewController {

    @GetMapping(value = "/home")
    public String homePage(Model model){
        model.addAttribute("theData", LocalDateTime.now());
        return "homepage";
    }
}
