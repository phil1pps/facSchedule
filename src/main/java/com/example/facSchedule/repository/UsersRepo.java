package com.example.facSchedule.repository;

import com.example.facSchedule.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepo extends CrudRepository<Users, Long> {
    Users findByUsername(String username);

    Users findByUsernameAndPassword(String login, String password);
}
