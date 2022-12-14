package com.example.roles.controller;


import com.example.roles.dto.UserDto;
import com.example.roles.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;
    @Mock
    UserServiceImpl userService;

    UserDto userDto;

    ObjectMapper objectMapper;

    @BeforeEach
            void setUp() throws IOException {
        objectMapper=new ObjectMapper();
         userDto = objectMapper.readValue(new ClassPathResource("UserDto.json").getInputStream(), UserDto.class);
         userDto.setDateOfBirth(LocalDate.now());
   /*  userDto = new UserDto(1L,"Vijay","Durgade", LocalDate.now(),
            "vija@com.com","123456","vijay12","Vijay@1234","asdfg");
    */}
    @Test
    void registerNewUser() {

        Mockito.when(userService.registerNewUser(any())).thenReturn(userDto);
        ResponseEntity<UserDto> userDtoResponseEntity = userController.registerNewUser(userDto);
        assertEquals(HttpStatus.OK,userDtoResponseEntity.getStatusCode());

    }

    @Test
    void getUserInfo() {

        Mockito.when(userService.getUserById(1L)).thenReturn(userDto);
        ResponseEntity<UserDto> userInfo = userController.getUserInfo(1L);
        assertEquals(userDto,userInfo.getBody());
    }

    @Test
    void getAllUsers() {
        List<UserDto> userDtoList= new ArrayList<>();
        Mockito.when(userService.allUser()).thenReturn(userDtoList);
        ResponseEntity<List<UserDto>> allUsers = userController.getAllUsers();
        assertEquals(userDtoList.size(),allUsers.getBody().size());
    }

    @Test
    void updateUser() {
        Mockito.when(userService.UpdateUserById(1L, userDto)).thenReturn(userDto);
        ResponseEntity<UserDto> userDtoResponseEntity = userController.updateUser(1L, userDto);
        assertEquals(userDto, userDtoResponseEntity.getBody());
    }

    @Test
    void deleteUserById() {
        Mockito.when(userService.deleteUser(1L)).thenReturn("User Deleted");
        ResponseEntity<String> stringResponseEntity = userController.deleteUserById(1L);
        assertEquals("User Deleted" , stringResponseEntity.getBody());
    }

    @Test
    void searchByName() {
        List<UserDto> userDtoList= new ArrayList<>();
        Mockito.when(userService.searchByName("keyword")).thenReturn(userDtoList);
        ResponseEntity<List<UserDto>> keyword = userController.SearchByName("keyword");
        assertEquals(userDtoList.size(),keyword.getBody().size());
    }

    @Test
    void findByName() {
        List<UserDto> userDtoList= new ArrayList<>();
        Mockito.when(userService.findByName("name")).thenReturn(userDtoList);
        ResponseEntity<List<UserDto>> name = userController.findByName("name");
        assertEquals(userDtoList.size(),name.getBody().size());
    }

    @Test
    void findByFirstOrLastName() {
        List<UserDto> userDtoList= new ArrayList<>();
        Mockito.when(userService.findByFirstOrLastName("name","name2")).thenReturn(userDtoList);
        ResponseEntity<List<UserDto>> byFirstOrLastName = userController.findByFirstOrLastName("name", "names");
        assertEquals(userDtoList.size(),byFirstOrLastName.getBody().size());

    }

    @Test
    void findByFirstAndLastName() {
        List<UserDto> userDtoList= new ArrayList<>();
        Mockito.when(userService.findByFirstAndLastName("name","name2")).thenReturn(userDtoList);
        ResponseEntity<List<UserDto>> byFirstAndLastName = userController.findByFirstAndLastName("name", "names");
        assertEquals(userDtoList.size(),byFirstAndLastName.getBody().size());

    }

    @Test
    void findByNameEndsWith() {
        List<UserDto> userDtoList= new ArrayList<>();
        Mockito.when(userService.findByNameEndsWith("name")).thenReturn(userDtoList);
        ResponseEntity<List<UserDto>> nameEndsWithList = userController.findByNameEndsWith("names");
        assertEquals(userDtoList.size(),nameEndsWithList.getBody().size());


    }
}