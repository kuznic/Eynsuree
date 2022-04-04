package com.meedra.eynsuree.controller;

import com.meedra.eynsuree.implementation.InsuredItemService;
import com.meedra.eynsuree.implementation.JpaUserDetailsService;
import com.meedra.eynsuree.implementation.ProductsCategoryService;
import com.meedra.eynsuree.implementation.ProductsService;
import com.meedra.eynsuree.model.InsuredItem;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.stream.Collectors;


@Controller
public class PaymentController {

    @Autowired
    private ProductsCategoryService productsCategoryService;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private InsuredItemService insuredItemService;

    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;


    @GetMapping("/payment")
    public String payment(Authentication a, Model model) throws NotFoundException, NoSuchFieldException {

        model.addAttribute("username", jpaUserDetailsService.fetchUser(a.getName()).getFirstName());

        var insuredItems = insuredItemService.fetchInsuredItems(a.getName());

        model.addAttribute("insuredItemDetails", insuredItems
                .stream()
                .map(InsuredItem::getInsuredItem)
                .collect(Collectors.toList()));



        model.addAttribute("dashboard", insuredItems);


        //model.addAttribute("dashboard",insuredItemService.fetchInsuredItems(a.getName()));
        return "main";


    }



}
