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
    private String username;         // 아이디
    private String password;        // 비밀번호
    private String email;           // 이메일
    private String phoneNumber;     // 핸드폰 번호
    private int userAge;            // 나이
    private String userGender;        // 성별
    private int userWeight;         // 몸무게
    private double userHeight;      // 키
    private double basalMetabolism;    // 기초대사랑
    @Column(name = "join_date", nullable = false )
    private LocalDateTime joinDate; // 가입 날짜


    public User(String username, String password, String email, String phoneNumber, int userAge, String userGender, int userWeight, double userHeight) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userAge = userAge;
        this.userGender = userGender;
        this.userWeight = userWeight;
        this.userHeight = userHeight;
        double gender = calculateBMR(userHeight, userAge, userWeight, userGender);
        this.basalMetabolism = gender;
        this.joinDate = LocalDateTime.now();
    }

    public UserHealthDTO toDTO() {
        return new UserHealthDTO(userAge, userGender, userWeight, userHeight, basalMetabolism);
    }

    public UserPersonalDTO toPersonalDTO() {
        return new UserPersonalDTO(username, password, email, phoneNumber);
    }

    public static double calculateBMR(double height, int age, double weight, String gender) {
        double bmr = 0;

        if (gender.equalsIgnoreCase("남")) {
            bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else if (gender.equalsIgnoreCase("여")) {
            bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }

        return bmr;
    }
}
