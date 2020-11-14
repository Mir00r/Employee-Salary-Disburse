package com.mir00r.salarydisburse.domains.users.repositories;

import com.mir00r.salarydisburse.domains.users.models.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    Profile findByUserUsername(String username);
    Profile findByUserId(Long userId);

}
