package com.meedra.eynsuree.controller;//package com.meedra.eynsure.controller;
//
//
//import com.meedra.eynsure.dto.UserRegistrationDto;
//import com.meedra.eynsure.model.User;
//import com.meedra.eynsure.service.UserService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.HttpClientErrorException;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
//
//@RestController
//@Slf4j
//@RequiredArgsConstructor
//@RequestMapping("/api/v1")
//public class TheseestRegistrationController {
//
//    @Autowired
//    UserService userService;
//
//
//    @PostMapping(value = "/test_registration",
//            consumes = APPLICATION_JSON_VALUE,
//            produces = APPLICATION_JSON_VALUE)
//    public User createUser(
//            @RequestBody UserRegistrationDto user) throws HttpClientErrorException.BadRequest {
//
//        return userService.save(user);
//
//
//
//    }
//
//}