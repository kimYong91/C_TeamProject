package com.itda.C_TeamProject.user.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserHealthDTO {
    private int userAge;             // 나이
    private String userGender;        // 성별
    private int userWeight;         // 몸무게
    private double userHeight;      // 키
    private double basalMetabolism;    // 기초대사랑

    public User toEntity(){
        User user = User.builder()
                .userAge(userAge)
                .userGender(userGender)
                .userHeight(userHeight)
                .userWeight(userWeight)
                .basalMetabolism(basalMetabolism)
                .build();
        return user;
    }
}
