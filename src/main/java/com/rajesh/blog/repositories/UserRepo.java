package com.rajesh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajesh.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
