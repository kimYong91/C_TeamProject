package com.itda.C_TeamProject.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class User {

    @Id
    private String user_Id;         // 아이디
    private String password;        // 비밀번호
    private String email;           // 이메일
    private String phoneNumber;     // 핸드폰 번호
    private int userAge;            // 나이
    private char userGender;        // 성별
    private int userWeight;         // 몸무게
    private double userHeight;      // 키
    private int basalMetabolism;    // 기초대사랑
    @Column(name = "join_date", nullable = false )
    private LocalDateTime joinDate; // 가입 날짜

    public User(String user_Id, String password, String email, String phoneNumber, int userAge, char userGender, int userWeight, double userHeight, int basalMetabolism) {
        this.user_Id = user_Id;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userAge = userAge;
        this.userGender = userGender;
        this.userWeight = userWeight;
        this.userHeight = userHeight;
        this.basalMetabolism = basalMetabolism;
        this.joinDate = LocalDateTime.now();
    }

    public UserHealthDTO toDTO() {
        return new UserHealthDTO(userAge, userGender, userWeight, userHeight, basalMetabolism);
    }
}
