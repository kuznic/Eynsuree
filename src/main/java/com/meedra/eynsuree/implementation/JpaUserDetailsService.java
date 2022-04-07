package com.meedra.eynsuree.implementation;


import com.meedra.eynsuree.dto.UserRegistrationDto;
import com.meedra.eynsuree.model.Authority;
import com.meedra.eynsuree.model.User;
import com.meedra.eynsuree.repository.AuthorityRepository;
import com.meedra.eynsuree.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

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

    public Optional<User> checkUser(String email){
        return userRepository.findUserByEmail(email);
    }


    public void registerNewUser(UserRegistrationDto userRegistrationDto){

        var user = User.builder()
                .email(userRegistrationDto.getEmail())
                .password(userRegistrationDto.getPassword())
                .firstName(userRegistrationDto.getFirstName())
                .lastName(userRegistrationDto.getLastName())
                .build();

        var authority = new Authority();

        authority.setName("READ");
        user = userRepository.save(user);

        authority.setUsers(user);
        authorityRepository.save(authority);

        //return user;
    }
}