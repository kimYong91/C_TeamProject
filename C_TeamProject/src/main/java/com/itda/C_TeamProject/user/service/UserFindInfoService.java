package com.itda.C_TeamProject.user.service;

import com.itda.C_TeamProject.user.UserRepository;
import com.itda.C_TeamProject.user.data.User;
import com.itda.C_TeamProject.user.data.UserFindPasswordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserFindInfoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void  getUserFindPassword(UserFindPasswordDTO userFindPasswordDTO) {
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
            }
        }
    }
    private String generateTemporaryPassword() {
        // 임시 비밀번호 생성 로직
        // 예: 랜덤한 문자열 생성
        return "temporaryPassword123";
    }

}
