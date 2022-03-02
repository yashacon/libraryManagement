package com.example.libraryManagement.Security;

import com.example.libraryManagement.Models.MyUserDetails;
import com.example.libraryManagement.Models.User;
import com.example.libraryManagement.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(username);
        user.orElseThrow(()->new UsernameNotFoundException("Not Found: "+username));
        return user.map(MyUserDetails::new).get();
    }
}
