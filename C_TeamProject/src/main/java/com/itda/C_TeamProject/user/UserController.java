package com.itda.C_TeamProject.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itda")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/oneUserHealthDTO")
    ResponseEntity<UserHealthDTO> getUserDTOInfo(@RequestParam String id) {
        UserHealthDTO userInfoDTOById = userService.getUserInfoDTOById(id);
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

    @PostMapping("/createUser")
    public User createNewUser(@RequestBody User user) {
        User createUser = userService.createUser(user);
        return createUser;
    }

    @PostMapping("/oneUserHealthDTO/{id}")
    UserHealthDTO updateUserHealthDTOInfo(@PathVariable("id") String id, @RequestBody UserHealthDTO userDTO) {
        return userService.updateUserHealthDTO(id, userDTO);
    }

    @GetMapping("/test")
    String test() {
        return "테스트";
    }
}