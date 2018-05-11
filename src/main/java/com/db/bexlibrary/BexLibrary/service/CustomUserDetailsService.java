package com.db.bexlibrary.BexLibrary.service;

import com.db.bexlibrary.BexLibrary.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        com.db.bexlibrary.BexLibrary.entities.User user = userRepo.findUserByEmail(email);

        if (user != null) {
            if (user.isAdmin())
                return new User(user.getEmail(), "{noop}" + user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
            return new User(user.getEmail(), "{noop}" + user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));

        } else throw new UsernameNotFoundException("No such user");
    }

}