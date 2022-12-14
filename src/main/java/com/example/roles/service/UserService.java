package com.example.roles.service;

import com.example.roles.dto.UserDto;
import com.example.roles.entity.User;

import java.util.List;

public interface UserService {


    public UserDto registerNewUser(UserDto userDto);


    public UserDto getUserById(Long id);

    public List<UserDto> allUser();

    public UserDto UpdateUserById(Long id, UserDto userDto);

    public String deleteUser(Long id);

    public List<UserDto> searchByName(String keyword);

    List<UserDto> findByName(String name);

    List<UserDto> findByFirstOrLastName(String name, String lastName);

    List<UserDto> findByFirstAndLastName(String name, String lastName);

    List<UserDto> findByNameEndsWith(String chars);
}
