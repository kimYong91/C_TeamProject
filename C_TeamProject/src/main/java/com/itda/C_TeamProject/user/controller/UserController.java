package com.itda.C_TeamProject.user.controller;


import com.itda.C_TeamProject.user.data.*;
import com.itda.C_TeamProject.user.service.UserFindInfoService;
import com.itda.C_TeamProject.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/itda")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserFindInfoService findUserInfoService;

    @GetMapping("/oneUserHealthDTO")
    ResponseEntity<UserHealthDTO> getUserDTOInfo(@RequestParam String id) {
        UserHealthDTO userInfoDTOById = userService.getUserInfoDTOById(id);
        if (userInfoDTOById == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(userInfoDTOById);
        }
    }

    @GetMapping("/oneUserInfo")
    ResponseEntity<User> getOneUserInfo(@RequestParam String id) {
        User userInfoById = userService.getUserInfoByUserName(id);
        if (userInfoById == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(userInfoById);
        }
    }

    @PostMapping("/createUser")
    public User createNewUser(@RequestBody User user) {
        User createUser = userService.createUser(user);
        return createUser;
    }

    @PostMapping("/oneUserHealthDTO/{id}")
    UserHealthDTO updateUserHealthDTOInfo(@PathVariable("id") String id, @RequestBody UserHealthDTO userDTO) {
        return userService.updateUserHealthDTO(id, userDTO);
    }

    @PostMapping("/oneUserInfo/{id}")
    UserPersonalDTO updateUserInfo(@PathVariable("id") String id, @RequestBody UserPersonalDTO userPersonalDTO) {
        return userService.updateUserPersonalDTO(id, userPersonalDTO);
    }

    @PostMapping("/findPassword")
    public ResponseEntity<Map<String, String>> findPassword(@RequestBody UserFindPasswordDTO userFindPasswordDTO) {
        String password = findUserInfoService.getUserFindPassword(userFindPasswordDTO);
        Map<String, String> map = new HashMap<>();
        map.put("newPassword", password);
        return ResponseEntity.ok(map);
    }

    @PostMapping("/findUsername")
    public ResponseEntity<Map<String, String>> findUsername(@RequestBody UserFindNameDTO userFindNameDTO) {
        String username = findUserInfoService.getUserFindName(userFindNameDTO);
        System.out.println(username);
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        return ResponseEntity.ok(map);
    }
}