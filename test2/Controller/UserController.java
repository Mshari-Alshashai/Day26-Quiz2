package com.example.test2.Controller;

import com.example.test2.ApiResponse.ApiResponse;
import com.example.test2.Model.User;
import com.example.test2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get-users")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/add-user")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added"));
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody @Valid User user,Errors errors){
        if (errors.hasErrors()) return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        if (userService.updateUser(id,user)) return ResponseEntity.status(200).body(new ApiResponse("User updated"));
        return ResponseEntity.status(400).body(new ApiResponse("User not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        if (userService.deleteUser(id)) return ResponseEntity.status(200).body(new ApiResponse("User is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("User not found"));
    }

    @GetMapping("/get-by-balance/{balance}")
    public ResponseEntity getByBalance(@PathVariable double balance){
        return ResponseEntity.status(200).body(userService.getByBalance(balance));
    }

    @GetMapping("/get-by-age/{age}")
    public ResponseEntity getByAge(@PathVariable int age){
        return ResponseEntity.status(200).body(userService.getByAge(age));
    }
}
