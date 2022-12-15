package com.example.roles.service.impl;

import com.example.roles.dto.UserDto;
import com.example.roles.entity.User;
import com.example.roles.exception.UserException;
import com.example.roles.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    UserRepository userRepository;

    UserDto userDto;

    @Mock
    User user;

    @BeforeEach
    void setUp(){

        userDto = new UserDto(1L,"Vijay","Durgade", LocalDate.now(),
                "vija@com.com","123456","vijay12","Vijay@1234","asdfg");
        user = new User(1L,"Vijay","Durgade", LocalDate.now(),
                "vija@com.com","123456","vijay12","Vijay@1234","asdfg");
    }




    @Test
    void registerNewUser() {
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        UserDto userDto1 = userServiceImpl.registerNewUser(userDto);
        assertEquals(userDto.getUserName(), userDto1.getUserName());
    }
    @Test
    void getUserById() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        UserDto userById = userServiceImpl.getUserById(1L);
        assertEquals(user.getUserName(),userById.getUserName());

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());
        UserException userException = assertThrows(UserException.class, () -> userServiceImpl.getUserById(1L));
        assertEquals(HttpStatus.BAD_REQUEST, userException.getHttpStatus());

    }

    @Test
    void allUser() {
        List<User> newLists = new ArrayList<>();
        newLists.add(user);
        newLists.add(user);
        newLists.add(user);
        Mockito.when(userRepository.findAll()).thenReturn(newLists);
        List<UserDto> userDtos = userServiceImpl.allUser();
        assertEquals(newLists.size(),userDtos.size());

    }

    @Test
    void updateUserById() {
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(user));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        UserDto userDto1 = userServiceImpl.UpdateUserById(1L, userDto);
        assertEquals(user.getUserName(), userDto1.getUserName());

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());
        UserException userException = assertThrows(UserException.class, () -> userServiceImpl.UpdateUserById(1L,userDto));
        assertEquals(HttpStatus.BAD_REQUEST,userException.getHttpStatus());

    }

    @Test
    void deleteUser() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        assertEquals("User Deleted", userServiceImpl.deleteUser(1L));

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());
        UserException userException = assertThrows(UserException.class, () -> userServiceImpl.deleteUser(1L));
        assertEquals(HttpStatus.BAD_REQUEST,userException.getHttpStatus());

    }

    @Test
    void searchByName() {
        List<User> newLists = new ArrayList<>();
        newLists.add(user);
        newLists.add(user);
        newLists.add(user);
        Mockito.when(userRepository.findAll()).thenReturn(newLists);
        List<UserDto> keyword = userServiceImpl.searchByName("vij");
        assertEquals(newLists.size(),keyword.size());

        Mockito.when(userRepository.findAll()).thenReturn(newLists);
        //List<UserDto> keyword1 = userServiceImpl.searchByName("asv");
        UserException asv = assertThrows(UserException.class, () -> userServiceImpl.searchByName("asv"));
        assertEquals(HttpStatus.BAD_REQUEST,asv.getHttpStatus());
    }

    @Test
    void findByName() {
        List<User> newLists = new ArrayList<>();
        newLists.add(user);
        newLists.add(user);
        newLists.add(user);
        Mockito.when(userRepository.findByFirstName("vijay")).thenReturn(newLists);
        List<UserDto> firstNameList = userServiceImpl.findByName("vijay");
        assertEquals(newLists.size(),firstNameList.size());

        UserException asv = assertThrows(UserException.class, () -> userServiceImpl.findByName("xyz"));
        assertEquals(HttpStatus.BAD_REQUEST,asv.getHttpStatus());
    }
    @Test
    void findByFirstOrLastName() {
        List<User> newLists = new ArrayList<>();
        newLists.add(user);
        newLists.add(user);
        newLists.add(user);
        Mockito.when(userRepository.findByFirstNameOrLastName("vijay","durgade")).thenReturn(newLists);
        List<UserDto> byFirstOrLastName = userServiceImpl.findByFirstOrLastName("vijay", "durgade");
        assertEquals(newLists.size(),byFirstOrLastName.size());

        UserException userException = assertThrows(UserException.class, () -> userServiceImpl.findByFirstOrLastName("xyz", "asdfg"));
        assertEquals(HttpStatus.BAD_REQUEST,userException.getHttpStatus());

    }

    @Test
    void findByFirstAndLastName() {
        List<User> newLists = new ArrayList<>();
        newLists.add(user);
        newLists.add(user);
        newLists.add(user);
        Mockito.when(userRepository.findByFirstNameAndLastName("vijay","durgade")).thenReturn(newLists);
        List<UserDto> byFirstAndLastName = userServiceImpl.findByFirstAndLastName("vijay", "durgade");
        assertEquals(newLists.size(), byFirstAndLastName.size());

        UserException userException = assertThrows(UserException.class, () -> userServiceImpl.findByFirstAndLastName("firstName", "lastName"));
        assertEquals(HttpStatus.BAD_REQUEST,userException.getHttpStatus());

    }

    @Test
    void findByNameEndsWith() {
        List<User> newLists = new ArrayList<>();
        newLists.add(user);
        newLists.add(user);
        newLists.add(user);
        Mockito.when(userRepository.findByNameEndsWith("chars")).thenReturn(newLists);
        List<UserDto> nameEndsWithList = userServiceImpl.findByNameEndsWith("chars");
        assertEquals(newLists.size(), nameEndsWithList.size());

        UserException userException = assertThrows(UserException.class, () -> userServiceImpl.findByNameEndsWith("jay"));
        assertEquals(HttpStatus.BAD_REQUEST,userException.getHttpStatus());

    }
}