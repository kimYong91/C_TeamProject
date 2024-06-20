package com.itda.C_TeamProject.user.controller;

import com.itda.C_TeamProject.user.data.User;
import com.itda.C_TeamProject.user.data.UserPersonalDTO;
import com.itda.C_TeamProject.user.service.UserUpdateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itda")
public class updateUserInfoController {

    @Autowired
    private UserUpdateInfoService userUpdateInfoService;

    @PostMapping("/updatePersonal")
    public ResponseEntity<UserPersonalDTO> updatePersonal(@RequestBody User updateUser) {
        UserPersonalDTO userPersonalDTO = userUpdateInfoService.updatePersonal(updateUser);
        return ResponseEntity.ok(userPersonalDTO);
    }
}
