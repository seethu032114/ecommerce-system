package user_service.controller;

import org.springframework.web.bind.annotation.*;
import user_service.entity.User;
import user_service.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }
}
