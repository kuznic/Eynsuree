package com.meedra.eynsuree.controller;//package com.meedra.eynsure.controller;
//
//import com.meedra.eynsure.dto.UserRegistrationDto;
//import com.meedra.eynsure.service.UserService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.naming.Binding;
//
//
//@Slf4j
//@Controller
//public class RegistrationController {
//
//    @Autowired
//    private UserService userService;
//
//
//    @GetMapping(value="/registration")
//    public ModelAndView showRegistrationForm(ModelAndView modelAndView) {
//
//        modelAndView.addObject("userRegistrationDto",new UserRegistrationDto());
//        modelAndView.setViewName("registration");
//        return modelAndView;
//    }
//
//
//    @PostMapping(value="/registration")
//    public String registerUserAccount(@ModelAttribute UserRegistrationDto userRegistrationDto,
//                                      BindingResult binding, Model model) {
//
//
//        userService.save(userRegistrationDto);
//        return "redirect:/registration?success";
//    }
//}