package com.example.gateway.utils;

import com.example.gateway.exceptions.ValidationException;
import com.example.gateway.model.Users;
import com.example.gateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AppUtil {

    @Autowired
    private UserRepository userRepository;


    public Users getLoggedInUser() throws ValidationException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepository.findByEmail(((UserDetails)principal).getUsername())
                .orElseThrow(() -> new ValidationException("Error getting logged in user"));
    }


    public boolean validEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

}
