package com.example.roles.controller;

import com.example.roles.dto.UserDto;
import com.example.roles.entity.User;
import com.example.roles.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    UserServiceImpl userService;


    @PostMapping("register")
    public ResponseEntity<UserDto> registerNewUser(@RequestBody @Validated UserDto userDto){
        UserDto newUser = userService.registerNewUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable Long id){
        UserDto userById = userService.getUserById(id);
        return new ResponseEntity<>(userById, HttpStatus.OK);

    }
    @GetMapping("allUsers")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtos = userService.allUser();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);

    }
    @PutMapping("update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto userDto1 = userService.UpdateUserById(id, userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.OK);

    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        String deleteUser = userService.deleteUser(id);
        return new ResponseEntity<>(deleteUser, HttpStatus.OK);

    }
    @GetMapping("{keyword}")
    public ResponseEntity<List<UserDto>> SearchByName(@PathVariable String keyword){
        List<UserDto> users = userService.searchByName(keyword);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("findByName/{name}")
    public ResponseEntity<List<UserDto>> findByName(@PathVariable String name){
        List<UserDto> users = userService.findByName(name);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("Name") //(http://localhost:8080/user/Name?name=Vijay&lastName=Durgade)
    public ResponseEntity<List<UserDto>> findByFirstOrLastName(@RequestParam String name,@RequestParam String lastName){
        List<UserDto> users = userService.findByFirstOrLastName(name, lastName);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("Names") //(http://localhost:8080/user/Names?name=Vansh&lastName=Durgade)
    public ResponseEntity<List<UserDto>> findByFirstAndLastName(@RequestParam String name,@RequestParam String lastName){
        List<UserDto> users = userService.findByFirstAndLastName(name, lastName);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("NamesEndWith/{chars}")
    public ResponseEntity<List<UserDto>> findByNameEndsWith(@PathVariable String chars) {
        List<UserDto> users = userService.findByNameEndsWith(chars);
        return new ResponseEntity<>(users, HttpStatus.OK);

    }
}
