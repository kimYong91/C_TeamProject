package com.itda.C_TeamProject.user.controller;

import com.itda.C_TeamProject.user.data.UserHealthDTO;
import com.itda.C_TeamProject.user.data.UserPersonalDTO;

import com.itda.C_TeamProject.user.service.UserUpdateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itda")
public class updateUserInfoController {

    @Autowired
    private UserUpdateInfoService userUpdateInfoService;

    @PatchMapping("/oneUserHealthDTO/{username}")
    UserHealthDTO updateUserHealthDTOInfo(@PathVariable("username") String username, @RequestBody UserHealthDTO userHealthDTO) {
        return userUpdateInfoService.updateUserHealthDTO(username, userHealthDTO);
    }

    @PatchMapping("/oneUserInfo/{username}")
    UserPersonalDTO updateUserInfo(@PathVariable("username") String username, @RequestBody UserPersonalDTO userPersonalDTO) {
        return userUpdateInfoService.updateUserPersonalDTO(username, userPersonalDTO);
    }



}
