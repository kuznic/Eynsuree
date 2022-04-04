package com.meedra.eynsuree.service;


import com.meedra.eynsuree.dto.UserRegistrationDto;
import com.meedra.eynsuree.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
    List<User> getAll();
}
