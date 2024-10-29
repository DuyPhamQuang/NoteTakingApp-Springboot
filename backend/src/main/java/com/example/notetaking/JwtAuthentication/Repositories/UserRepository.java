package com.example.notetaking.JwtAuthentication.Repositories;

import java.util.Optional;

import com.example.notetaking.JwtAuthentication.Entity.User;
import com.example.notetaking.Repositories.SearchRepository;
import com.example.notetaking.Repositories.SearchRepositoryImpl;
import org.springframework.stereotype.Repository;


@Repository("UserRepository")
public interface UserRepository extends SearchRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
