package com.meedra.eynsuree.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Slf4j
@Controller
public class RedirectController {


    @GetMapping(value = "/return")
    public String handleRedirect(Model model){
        model.getAttribute("id");

        //model.getAttribute("id");
        //model.getAttribute("status");



        return "main";
    }



}
