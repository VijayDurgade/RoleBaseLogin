package com.example.roles.service.impl;

import com.example.roles.dto.UserDto;
import com.example.roles.entity.User;
import com.example.roles.exception.UserException;
import com.example.roles.repository.UserRepository;
import com.example.roles.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;


    private User dtoTOEntity(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }

    private UserDto entityToDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user = dtoTOEntity(userDto);
        User save = userRepository.save(user);
        UserDto userDto1 = entityToDto(save);
        return userDto1;

    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> byId = userRepository.findById(id);

        if (byId.isPresent()) {
            User user = byId.get();
            return entityToDto(user);
        } else {
            throw new UserException("User not found with id : " + id, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<UserDto> allUser() {
        List<User> all = userRepository.findAll();
        List<UserDto> newLists = new ArrayList<>();
        for (User value : all) {
            newLists.add(entityToDto(value));
        }
        return newLists;
    }

    @Override
    public UserDto UpdateUserById(Long id, UserDto userDto) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user1 = dtoTOEntity(userDto);
            User save = userRepository.save(user1);
            return entityToDto(save);

        } else {
            throw new UserException("User not found with id : " + id, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public String deleteUser(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            userRepository.deleteById(id);
            return "User Deleted";
        } else {
            throw new UserException("User not found with id : " + id, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<UserDto> searchByName(String keyword) {
        List<UserDto> listOfUser = new ArrayList<>();
        List<User> all = userRepository.findAll();
        for (User newList : all) {
            if (newList.getUserName().toLowerCase().startsWith(keyword)) {
                listOfUser.add(entityToDto(newList));
            } else {
                throw new UserException("User not found with this : " + keyword, HttpStatus.BAD_REQUEST);
            }
        }
        return listOfUser;
    }

    @Override
    public List<UserDto> findByName(String name) {
        List<User> byFirstName = userRepository.findByFirstName(name);
        if (byFirstName.isEmpty()) {
            throw new UserException("User not found with this : " + name, HttpStatus.BAD_REQUEST);

        }
        List<UserDto> listOfUser = new ArrayList<>();

        for (User user : byFirstName) {
            UserDto userDto = entityToDto(user);
            listOfUser.add(userDto);
        }
        return listOfUser;
    }

    @Override
    public List<UserDto> findByFirstOrLastName(String name, String lastName) {
        List<User> byFirstName = userRepository.findByFirstNameOrLastName(name, lastName);
        if (byFirstName.isEmpty()) {
            throw new UserException("User not found with this : " + name, HttpStatus.BAD_REQUEST);

        }
        List<UserDto> listOfUser = new ArrayList<>();

        for (User user : byFirstName) {
            UserDto userDto = entityToDto(user);
            listOfUser.add(userDto);
        }
        return listOfUser;
    }

    @Override
    public List<UserDto> findByFirstAndLastName(String name, String lastName) {
        List<User> byFirstName = userRepository.findByFirstNameAndLastName(name, lastName);
        if (byFirstName.isEmpty()) {
            throw new UserException("User not found with this : " + name, HttpStatus.BAD_REQUEST);

        }
        List<UserDto> listOfUser = new ArrayList<>();

        for (User user : byFirstName) {
            UserDto userDto = entityToDto(user);
            listOfUser.add(userDto);
        }
        return listOfUser;
    }

    @Override
    public List<UserDto> findByNameEndsWith(String chars) {
        List<User> users = userRepository.findByNameEndsWith(chars);
        if (users.isEmpty()){
            throw new UserException("User not found with this : " + chars, HttpStatus.BAD_REQUEST);
        }

        return  users.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}