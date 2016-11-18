package com.izzi.integra.console.security.service;

import com.izzi.integra.console.dao.entity.CatUser;
import com.izzi.integra.console.dao.repository.CatUserRepository;
import com.izzi.integra.console.security.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import com.izzi.security.repository.UserRepository;

/**
 * Created by stephan on 20.03.16.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private CatUserRepository catUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final CatUser catUser = catUserRepository.findByUsername(username);

        if (catUser == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(catUser);
        }
    }


}
