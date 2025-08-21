package com.todomanagment.repository;

import com.todomanagment.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RoleRepository  extends JpaRepository<Role,Long> {
    // Repository interface for Role entity
    Role findByName(String name); // Find role by name


}
