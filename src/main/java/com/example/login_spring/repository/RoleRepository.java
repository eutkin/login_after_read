package com.example.login_spring.repository;

import com.example.login_spring.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleReosotory")
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRole (String role);
}
