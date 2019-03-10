package com.jkh.backend.repository;

import com.jkh.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByLogin(String login);
    User findUserByPhone(String phone);
    User findUserByEmail(String email);
}
