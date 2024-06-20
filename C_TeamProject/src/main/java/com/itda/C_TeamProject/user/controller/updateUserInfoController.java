package com.itda.C_TeamProject.user.controller;

import com.itda.C_TeamProject.user.data.User;
import com.itda.C_TeamProject.user.data.UserHealthDTO;
import com.itda.C_TeamProject.user.data.UserPersonalDTO;
import com.itda.C_TeamProject.user.service.UserUpdateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itda")
public class updateUserInfoController {

    @Autowired
    private UserUpdateInfoService userUpdateInfoService;

    @PostMapping("/oneUserHealthDTO/{id}")
    UserHealthDTO updateUserHealthDTOInfo(@PathVariable("id") String id, @RequestBody UserHealthDTO userDTO) {
        return userUpdateInfoService.updateUserHealthDTO(id, userDTO);
    }

    @PostMapping("/oneUserInfo/{id}")
    UserPersonalDTO updateUserInfo(@PathVariable("id") String id, @RequestBody UserPersonalDTO userPersonalDTO) {
        return userUpdateInfoService.updateUserPersonalDTO(id, userPersonalDTO);
    }



}
