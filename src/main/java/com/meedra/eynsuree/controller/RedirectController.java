package com.meedra.eynsuree.controller;


import com.meedra.eynsuree.dto.RedirectDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Slf4j
@Controller
public class RedirectController {


    @GetMapping(value = "/return")
    public String handleRedirect(Model model){
        model.addAttribute("id");
        model.addAttribute("status");
        model.addAttribute("externalReference");



        return "index2";
    }



    @GetMapping(value = "/process/redirect")
    public String processRedirectReturn(@ModelAttribute String id, Model model){

        log.info(id);

        model.getAttribute("id");

        return  "redirect:" + "/";
    }



}
