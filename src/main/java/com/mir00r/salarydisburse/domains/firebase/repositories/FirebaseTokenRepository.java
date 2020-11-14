package com.mir00r.salarydisburse.domains.firebase.repositories;

import com.mir00r.salarydisburse.domains.firebase.models.entities.FirebaseUserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirebaseTokenRepository extends JpaRepository<FirebaseUserToken, Long> {
    FirebaseUserToken findByUserId(Long userId);
}
