package com.itda.C_TeamProject.user.service;

import com.itda.C_TeamProject.user.UserRepository;
import com.itda.C_TeamProject.user.data.User;
import com.itda.C_TeamProject.user.data.UserHealthDTO;
import com.itda.C_TeamProject.user.data.UserPersonalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserUpdateInfoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 성별, 키, 몸무게의 정보 업데이트
    @Transactional
    public UserHealthDTO updateUserHealthDTO(String username, UserHealthDTO userDTO) {
        User user = userService.getUserInfoByUserName(username);


        user.setUserGender(userDTO.getUserGender());
        user.setUserWeight(userDTO.getUserWeight());
        user.setUserHeight(userDTO.getUserHeight());


        User savedUser = userRepository.save(user);
        UserHealthDTO dto = savedUser.toDTO();
        return dto;
    }

    // 아이디, 비밀번호, 생년월일, 이메일, 핸드폰번호의 개인정보 업데이트
    @Transactional
    public UserPersonalDTO updateUserPersonalDTO(String username, UserPersonalDTO userPersonalDTO) {
        User user = userService.getUserInfoByUserName(username);

        if (userPersonalDTO.getPassword() != null && !userPersonalDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userPersonalDTO.getPassword()));
        }
        if (userPersonalDTO.getEmail() != null && !userPersonalDTO.getEmail().isEmpty()) {
            user.setEmail(userPersonalDTO.getEmail());
        }
        if (userPersonalDTO.getPhoneNumber() != null && !userPersonalDTO.getPhoneNumber().isEmpty()) {
            user.setPhoneNumber(userPersonalDTO.getPhoneNumber());
        }
        if (userPersonalDTO.getDateOfBirth() != null && !userPersonalDTO.getDateOfBirth().isEmpty()) {
            user.setDateOfBirth(userPersonalDTO.getDateOfBirth());
        }
        User savedUserPersonal = userRepository.save(user);
        UserPersonalDTO dto = savedUserPersonal.toPersonalDTO();
        return dto;
    }
}
