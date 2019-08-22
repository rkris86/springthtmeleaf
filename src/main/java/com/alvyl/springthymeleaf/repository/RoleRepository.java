package com.alvyl.springthymeleaf.repository;

import com.alvyl.springthymeleaf.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByUsername(String username);
}
