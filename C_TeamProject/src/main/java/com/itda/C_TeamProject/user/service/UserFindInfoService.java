package com.itda.C_TeamProject.user.service;

import com.itda.C_TeamProject.user.UserRepository;
import com.itda.C_TeamProject.user.data.User;
import com.itda.C_TeamProject.user.data.UserFindPasswordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

@Service
public class UserFindInfoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public String getUserFindPassword(UserFindPasswordDTO userFindPasswordDTO) {
        User userInfo = userRepository.findByUsername(userFindPasswordDTO.getUsername());
        if (userInfo != null) {
            if (userInfo.getEmail().equals(userFindPasswordDTO.getEmail()) &&
                    userInfo.getPhoneNumber().equals(userFindPasswordDTO.getPhoneNumber()) &&
                    userInfo.getDateOfBirth().equals(userFindPasswordDTO.getDateOfBirth())
            ) {
                // 임시 비밀번호 생성
                String newPassword = generateTemporaryPassword();
                // 비밀번호 암호화하여 저장
                String encryptedPassword = passwordEncoder.encode(newPassword);
                userInfo.setPassword(encryptedPassword);
                userRepository.save(userInfo);
                return newPassword;
            }
        }
        return null;
    }
    private String generateTemporaryPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=<>?";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }

}
