package com.springboot.crud.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.springboot.crud.entities.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    
    List<User> findByName(String name);
    
}