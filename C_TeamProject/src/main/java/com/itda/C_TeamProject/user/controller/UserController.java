package com.itda.C_TeamProject.user.controller;


import com.itda.C_TeamProject.user.data.User;
import com.itda.C_TeamProject.user.data.UserFindPasswordDTO;
import com.itda.C_TeamProject.user.data.UserHealthDTO;
import com.itda.C_TeamProject.user.data.UserPersonalDTO;
import com.itda.C_TeamProject.user.service.UserFindInfoService;
import com.itda.C_TeamProject.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> findPassword(@RequestParam UserFindPasswordDTO userFindPasswordDTO) {
        findUserInfoService.getUserFindPassword(userFindPasswordDTO);
        return ResponseEntity.ok("비밀번호 재설정 되었습니다.");
    }
}