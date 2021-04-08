package com.android.android_repo.service;

import com.android.android_repo.entity.IMEI;
import com.android.android_repo.entity.User;
import com.android.android_repo.repository.IMEIRepository;
import com.android.android_repo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Qualifier("userService")
public class CustomIMEIDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUserName(username);
        //IMEI imei = imeiRepository.findByValue(username);
        if(user != null){
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
        }

        return null;

    }

}
