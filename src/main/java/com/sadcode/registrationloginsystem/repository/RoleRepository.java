package com.sadcode.registrationloginsystem.repository;

import com.sadcode.registrationloginsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
