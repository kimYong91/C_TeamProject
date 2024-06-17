package com.itda.C_TeamProject.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUserInfoById(String user_Id) {
        User userInfo = userRepository.findById(user_Id).orElse(null);
        return userInfo;
    }

    public UserHealthDTO getUserInfoDTOById(String user_Id) {
        User userInfo = getUserInfoById(user_Id);
        UserHealthDTO dto = userInfo.toDTO();
        return dto;
    }


    // 회원 가입
    @Transactional
    public User createUser(User user) {
        User userInfoById = getUserInfoById(user.getUsername());
        User createUser = new User(user.getUsername(), user.getPassword(), user.getEmail(), user.getPhoneNumber(),
                user.getUserAge(), user.getUserGender(), user.getUserWeight(), user.getUserHeight(), user.getBasalMetabolism());
        if (userInfoById == null) {
            createUser.setPassword(passwordEncoder.encode(user.getPassword()));
            User saved = userRepository.save(createUser);
            return saved;
        } else {
            return null;
        }

    }

    // 성별, 나이, 키, 몸무게, 기초대사량의 정보 업데이트
    @Transactional
    public UserHealthDTO updateUserHealthDTO(String username, UserHealthDTO userDTO) {
        User user = getUserInfoById(username);
        user.setUserAge(userDTO.getUserAge());
        user.setUserGender(userDTO.getUserGender());
        user.setUserWeight(userDTO.getUserWeight());
        user.setUserHeight(userDTO.getUserHeight());
        user.setBasalMetabolism(userDTO.getBasalMetabolism());
        User savedUser = userRepository.save(user);
        UserHealthDTO dto = savedUser.toDTO();
        return dto;
    }

    @Transactional
    public UserPersonalDTO updateUserPersonalDTO(String username, UserPersonalDTO userPersonalDTO) {
        User user = getUserInfoById(username);
        user.setPassword(userPersonalDTO.getPassword());
        user.setEmail(userPersonalDTO.getEmail());
        user.setPhoneNumber(userPersonalDTO.getPhoneNumber());
        User savedUserPersonal = userRepository.save(user);
        UserPersonalDTO dto = savedUserPersonal.toPersonalDTO();
        return dto;
    }
}
