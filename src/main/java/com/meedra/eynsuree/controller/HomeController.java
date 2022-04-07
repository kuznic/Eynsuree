package com.meedra.eynsuree.controller;

import com.meedra.eynsuree.implementation.*;
import com.meedra.eynsuree.model.InsuredItem;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;


@Controller
public class HomeController {

    @Autowired
    private ProductsCategoryService productsCategoryService;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private InsuredItemService insuredItemService;

    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;

    @Autowired
    private CustomerService customerService;


    @GetMapping("/")
    public String home(Authentication a, Model model) throws NotFoundException, NoSuchFieldException {

        model.addAttribute("username", jpaUserDetailsService.fetchUser(a.getName()).getFirstName());

        if (customerService.fetchCustomer(a.getName()).isEmpty()){
            return "notinsuredyet";
        }

        var insuredItems = insuredItemService.fetchInsuredItems(a.getName());



        model.addAttribute("insuredItemDetails", insuredItems
                .stream()
                .map(InsuredItem::getInsuredItem)
                .collect(Collectors.toList()));



        model.addAttribute("dashboard", insuredItems);


        //model.addAttribute("dashboard",insuredItemService.fetchInsuredItems(a.getName()));
        return "main";
    }


    @GetMapping("/login")
    public String login() {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/";
    }


    @GetMapping("/error")
    public String error(){
        return "errorpage";
    }


    @GetMapping("/home")
    public String home(){
        return "home";
    }






}
