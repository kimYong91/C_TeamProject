package com.itda.C_TeamProject.user.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPersonalDTO {
    private String username;         // 아이디
    private String password;        // 비밀번호
    private String email;           // 이메일
    private String phoneNumber;     // 핸드폰 번호
    private String dateOfBirth;            // 생년월일

    public User toEntity(){
        User user = User.builder()
                .username(username)
                .password(password)
                .email(email)
                .phoneNumber(phoneNumber)
                .dateOfBirth(dateOfBirth)
                .build();
        return user;
    }
}
