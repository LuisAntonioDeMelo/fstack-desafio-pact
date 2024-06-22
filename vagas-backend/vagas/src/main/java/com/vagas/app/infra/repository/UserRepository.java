package com.vagas.app.infra.repository;

import com.vagas.app.domain.Role;
import com.vagas.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByLogin(String login);

//    @Query("select u from User u join u.pessoa p where u.role = :role and u.login = :login")
//    User obterDadosUser(String login, Role role);
}