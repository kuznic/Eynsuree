package com.meedra.eynsuree.controller;


import com.meedra.eynsuree.dto.UserRegistrationDto;
import com.meedra.eynsuree.exception.EmailExistsException;
import com.meedra.eynsuree.exception.PasswordMismatchException;
import com.meedra.eynsuree.implementation.JpaUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class RegistrationController {

    private final JpaUserDetailsService jpaUserDetailsService;



    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(JpaUserDetailsService jpaUserDetailsService, BCryptPasswordEncoder passwordEncoder) {
        this.jpaUserDetailsService = jpaUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }




    @GetMapping(value = "/register")
    public ModelAndView showRegistrationForm() {
        return new ModelAndView("registration", "userRegistrationDto",
                new UserRegistrationDto());
    }


    @PostMapping(value = "/register")
    public ModelAndView registerUserAccount(@ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto,
                                            BindingResult result, ModelMap model) throws PasswordMismatchException {

        String error;


        //check for password mismatch
        if (!(userRegistrationDto.getPassword().equals(userRegistrationDto.getRePassword()))) {
            var passwordMismatch = new PasswordMismatchException("Passwords do not match");
            error = passwordMismatch.getMessage();
            model.addAttribute("passwordError", error);

            return new ModelAndView("registration", model);
        }

        //check if user email already exists
        var pendingUser =  jpaUserDetailsService.checkUser(userRegistrationDto.getEmail());
        if (pendingUser.isPresent()) {
            var emailExistsException = new EmailExistsException("Email already Exists");
            error = emailExistsException.getMessage();
            model.addAttribute("emailError", error);

            return new ModelAndView("registration", model);


        }

        //encrypt password
        userRegistrationDto.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));


        //create new user
        jpaUserDetailsService.registerNewUser(userRegistrationDto);
        //return new ModelAndView("redirect:/login");
        return new ModelAndView("successful-registration");
    }
}
