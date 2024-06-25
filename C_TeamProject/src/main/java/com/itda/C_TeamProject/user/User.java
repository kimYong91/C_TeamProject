package com.itda.C_TeamProject.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

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
    private String dateOfBirth;       // 생년월일
    private int userAge;
    private String userGender;        // 성별
    private int userWeight;         // 몸무게
    private double userHeight;      // 키
    private double basalMetabolism;    // 기초대사랑
    @Column(name = "join_date", nullable = false )
    private LocalDateTime joinDate; // 가입 날짜

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

        return Math.ceil(bmr);
    }

    public static int calculateAge(String birthDateString) {
        // String을 LocalDate로 변환
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(birthDateString, dateFormatter);

        // 현재 날짜 가져오기
        LocalDate currentDate = LocalDate.now();

        // Period를 사용하여 나이 계산
        Period age = Period.between(birthDate, currentDate);

        // 나이 반환
        return age.getYears();
    }
}
