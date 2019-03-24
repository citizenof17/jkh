package com.jkh.backend.repository;

import com.jkh.backend.model.User;
import com.jkh.backend.model.enums.Role;
import com.jkh.backend.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByLogin(String login);
    User findUserByPhone(String phone);
    User findUserByEmail(String email);
    User findUserByRole(Role role);
    List<User> findUsersByStatus(Status status);
    List<User> findUsersByRoleAndStatus(Role role, Status status);

    List<User> findAll();
}
