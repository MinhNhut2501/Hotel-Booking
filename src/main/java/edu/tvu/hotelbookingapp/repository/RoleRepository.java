package edu.tvu.hotelbookingapp.repository;

import edu.tvu.hotelbookingapp.model.Role;
import edu.tvu.hotelbookingapp.model.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleType(RoleType roleType);
}
