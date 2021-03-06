package com.mir00r.salarydisburse.domains.activities.repositories;

import com.mir00r.salarydisburse.domains.activities.models.entities.Activity;
import com.mir00r.salarydisburse.domains.users.models.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Long> {
    Activity findFirstBy();
    Activity findFirstByUserOrderByIdDesc(User user);
    Page<Activity> findByUser(User user, Pageable pageable);

    Long countByCreatedBetween(Date fromDate, Date toDate);
}
