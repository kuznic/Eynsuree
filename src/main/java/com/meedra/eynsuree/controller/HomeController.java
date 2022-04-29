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




    //renders the login page or home page depending on user interaction
    @GetMapping("/login")
    public String login() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "redirect:/";
    }




    //Displays the main page after logging in successfully
    @GetMapping("/")
    public String home(Authentication a, Model model) throws NotFoundException, NoSuchFieldException {

        var fullName = jpaUserDetailsService.fetchUser(a.getName()).getFirstName() + " " +
                jpaUserDetailsService.fetchUser(a.getName()).getLastName();

        model.addAttribute("username", fullName);


        //This checks that user has just registered and presents a page with no insurance
        if (customerService.fetchCustomer(a.getName()).isEmpty()){
            return "notinsuredyet";
        }

        //retrieves the insurance details of a user
        var insuredItems = insuredItemService.fetchInsuredItems(a.getName());



        model.addAttribute("insuredItemDetails", insuredItems
                .stream()
                .map(InsuredItem::getInsuredItem)
                .collect(Collectors.toList()));



        model.addAttribute("dashboard", insuredItems);

        return "main";
    }





    @GetMapping("/error")
    public String error(){
        return "errorpage";
    }


    //renders the home page
    @GetMapping("/home")
    public String home(){
        return "home";
    }






}
