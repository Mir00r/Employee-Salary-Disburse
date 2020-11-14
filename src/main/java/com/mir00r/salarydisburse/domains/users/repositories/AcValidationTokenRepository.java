package com.mir00r.salarydisburse.domains.users.repositories;


import com.mir00r.salarydisburse.domains.users.models.entities.AcValidationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface AcValidationTokenRepository extends JpaRepository<AcValidationToken, Long> {
    AcValidationToken findFirstByTokenOrderByIdDesc(String token);

    AcValidationToken findFirstByUsernameOrderByIdDesc(String phone);
    int countByUserIdAndCreatedBetween(Long id, Date fromDate, Date toDate);
}
