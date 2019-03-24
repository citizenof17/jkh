package com.jkh.backend.repository;

import com.jkh.backend.model.Flat;
import com.jkh.backend.model.User;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByLogin(String login);
    User findUserByPhone(String phone);
    User findUserByEmail(String email);

    List<User> findAll();
}
