package com.meedra.eynsuree.implementation;


import com.meedra.eynsuree.model.User;
import com.meedra.eynsuree.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) {
        Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException("Problem during authentication!");
        User u = userRepository
                .findUserByEmail(username)
                .orElseThrow(s);
        return new CustomUserDetails(u);
    }


    public User fetchUser(String email){
        Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException("User not found!");
        User u = userRepository
                .findUserByEmail(email)
                .orElseThrow(s);

        return u;



    }
}