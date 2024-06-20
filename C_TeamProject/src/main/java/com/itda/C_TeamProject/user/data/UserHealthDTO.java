package com.itda.C_TeamProject.user.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserHealthDTO {
    private String userGender;        // 성별
    private int userWeight;         // 몸무게
    private double userHeight;      // 키
    private double userBasalMetabolism;    // 기초대사랑

    public User toEntity(){
        User user = User.builder()
                .userGender(userGender)
                .userHeight(userHeight)
                .userWeight(userWeight)
                .basalMetabolism(userBasalMetabolism)
                .build();
        return user;
    }
}
