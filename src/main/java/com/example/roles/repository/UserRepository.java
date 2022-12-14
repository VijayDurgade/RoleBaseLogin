package com.example.roles.repository;

import com.example.roles.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByFirstName(String name);

    List<User> findByFirstNameOrLastName(String firstName,String lastName);


    List<User> findByFirstNameAndLastName(String firstName,String lastName);

    @Query("select u from User u where u.firstName like %?1")
    List<User> findByNameEndsWith(String chars);

}
