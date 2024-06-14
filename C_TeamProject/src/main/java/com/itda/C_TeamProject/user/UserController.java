package com.itda.C_TeamProject.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itda")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/oneUserDTO")
    ResponseEntity<UserDTO> getUserDTOInfo(@RequestParam String id) {
        UserDTO userInfoDTOById = userService.getUserInfoDTOById(id);
        if (userInfoDTOById == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(userInfoDTOById);
        }
    }

    @GetMapping("/oneUser")
    ResponseEntity<User> getOneUserAllInfo(@RequestParam String id) {
        User userInfoById = userService.getUserInfoById(id);
        if (userInfoById == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(userInfoById);
        }
    }

    @PostMapping("/oneUser")
    User createNewUser(@RequestBody User user) {
        User createUser = userService.createUser(user);
        return createUser;
    }

    @PostMapping("/oneUserDTO/{id}")
    UserDTO updateUserDTOInfo(@RequestBody String id, @RequestBody UserDTO userDTO) {
        return userService.updateUserDTO(id, userDTO);
    }
}