package com.mir00r.salarydisburse.domains.activities.services;


import com.mir00r.salarydisburse.domains.activities.models.entities.Activity;
import com.mir00r.salarydisburse.domains.users.models.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ActivityService {

    Activity save(Activity activity);

    Activity findFirst();

    Activity findLast(User user);

    Page<Activity> findByUser(User user, int page, int size);

    Activity findOne(long id);

    List<Activity> findAll();

    void delete(Long id);
}
