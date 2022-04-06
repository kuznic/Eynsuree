package com.meedra.eynsuree.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


    @ControllerAdvice
    @Slf4j
    public class ErrorControllerAdvice {


        @ExceptionHandler(IllegalStateException.class)  //handle this exception
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public String storageException(final IllegalStateException throwable, final Model model) {
            log.error("Exception during execution of application", throwable);
            model.addAttribute("errorMessage", "Failed to store file"); //custom message to render in HTML
            return "error";  //the html page in resources/templates folder
        }

    }

