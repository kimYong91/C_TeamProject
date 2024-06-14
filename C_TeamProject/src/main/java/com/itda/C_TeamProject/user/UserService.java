package com.itda.C_TeamProject.user;

import org.apache.el.lang.ELArithmetic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserInfoById(String user_Id) {
        User userInfo = userRepository.findById(user_Id).orElse(null);
        return userInfo;
    }

    public UserDTO getUserInfoDTOById(String user_Id) {
        User userInfo = getUserInfoById(user_Id);
        UserDTO dto = userInfo.toDTO();
        return dto;
    }


    // 회원 가입
    @Transactional
    public User createUser(User user) {
        User userInfoById = getUserInfoById(user.getUser_Id());
        User createUser = new User(user.getUser_Id(), user.getPassword(), user.getEmail(), user.getPhoneNumber(), user.getUserAge(), user.getUserGender(), user.getUserWeight(), user.getUserHeight(), user.getBasalMetabolism());
        if (userInfoById == null) {
            User saved = userRepository.save(createUser);
            return saved;
        } else {
            return null;
        }

    }

    // 성별, 나이, 키, 몸무게, 기초대사량의 정보 업데이트
    @Transactional
    public UserDTO updateUserDTO(String user_Id, UserDTO userDTO) {
        User user = getUserInfoById(user_Id);
        user.setUserAge(userDTO.getUserAge());
        user.setUserGender(userDTO.getUserGender());
        user.setUserWeight(userDTO.getUserWeight());
        user.setUserHeight(userDTO.getUserHeight());
        user.setBasalMetabolism(userDTO.getBasalMetabolism());
        User savedUser = userRepository.save(user);
        UserDTO dto = savedUser.toDTO();
        return dto;
    }
}
