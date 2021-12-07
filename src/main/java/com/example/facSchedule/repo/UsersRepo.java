package com.example.facSchedule.repo;

import com.example.facSchedule.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepo extends CrudRepository<Users, Long> {
    Users findByUsername(String username);
}
