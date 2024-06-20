package com.itda.C_TeamProject.user.service;

import com.itda.C_TeamProject.user.UserRepository;
import com.itda.C_TeamProject.user.data.User;
import com.itda.C_TeamProject.user.data.UserPersonalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserUpdateInfoService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserPersonalDTO updatePersonal(User userInfo) {
        User user = userRepository.findByUsername(userInfo.getUsername());

        if (user != null) {
            String userName = user.getUsername();
            String password = user.getPassword();
            String email = user.getEmail();
            String phoneNumber = user.getPhoneNumber();
            user.setUsername(userName);
            user.setPassword(password);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);

            User userUpdate = userRepository.save(user);

            return userUpdate.toPersonalDTO();
        }
        return null;
    }
}
