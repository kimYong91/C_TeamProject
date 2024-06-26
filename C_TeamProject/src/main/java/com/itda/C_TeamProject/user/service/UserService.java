package com.itda.C_TeamProject.user.service;

import com.itda.C_TeamProject.user.data.User;
import com.itda.C_TeamProject.user.data.UserHealthDTO;
import com.itda.C_TeamProject.user.data.UserPersonalDTO;
import com.itda.C_TeamProject.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 유저 모든 정보 가져오기
    public User getUserInfoByUserName(String userName) {
        User userInfo = userRepository.findById(userName).orElse(null);
        return userInfo;
    }

    // 유저 건강 정보 가져오기
    public UserHealthDTO getUserInfoDTOById(String userName) {
        User userInfo = getUserInfoByUserName(userName);
        UserHealthDTO dto = userInfo.toDTO();
        return dto;
    }

    // 회원 가입
    @Transactional
    public User createUser(User user) {
        User userInfoById = getUserInfoByUserName(user.getUsername());
        if (userInfoById == null) {
            user.setUserAge(User.calculateAge(user.getDateOfBirth()));
            user.setBasalMetabolism(User.calculateBMR(user.getUserHeight(), user.getUserAge(), user.getUserWeight(), user.getUserGender()));
            user.setJoinDate(LocalDateTime.now());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User saved = userRepository.save(user);
            return saved;
        } else {
            return null;
        }
    }

    @Transactional
    public Boolean deleteUser(String userName) {
        User userInfoById = getUserInfoByUserName(userName);
        if (userInfoById != null) {
            userRepository.delete(userInfoById);
            return true;
        } else {
            return false;
        }
    }


}
