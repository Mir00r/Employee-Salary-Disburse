package com.mir00r.salarydisburse.domains.users.services;


import com.mir00r.salarydisburse.domains.users.models.entities.Profile;
import com.mir00r.salarydisburse.exceptions.exists.UserAlreadyExistsException;
import com.mir00r.salarydisburse.exceptions.forbidden.ForbiddenException;
import com.mir00r.salarydisburse.exceptions.invalid.UserInvalidException;
import com.mir00r.salarydisburse.exceptions.notfound.NotFoundException;
import com.mir00r.salarydisburse.exceptions.notfound.ProfileNotFoundException;
import com.mir00r.salarydisburse.exceptions.nullpointer.NullPasswordException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProfileService {
    Profile save(Profile profile, String username) throws NotFoundException, UserAlreadyExistsException, NullPasswordException, UserInvalidException;

    Profile getProfileByUserId(Long id) throws ProfileNotFoundException, ForbiddenException;

    Profile getOneProfile(Long id) throws ProfileNotFoundException, ForbiddenException;

    Profile getProfileByUsername(String username) throws ProfileNotFoundException, ForbiddenException;

    List<Profile> getAllProfile();

    Page<Profile> getAllProfilePaginated(int pageNumber);

    Long countProfile();

    void delete(Long id) throws ProfileNotFoundException;

    boolean isExists(Long id);

}
