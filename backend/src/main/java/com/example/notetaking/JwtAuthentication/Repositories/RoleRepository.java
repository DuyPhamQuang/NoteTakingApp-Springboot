package com.example.notetaking.JwtAuthentication.Repositories;

import com.example.notetaking.JwtAuthentication.Entity.ERole;
import com.example.notetaking.JwtAuthentication.Entity.Role;
import com.example.notetaking.Repositories.SearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository("RoleRepository")
public interface RoleRepository extends SearchRepository<Role, Long> {
    Set<Role> findByName(ERole name);
}
