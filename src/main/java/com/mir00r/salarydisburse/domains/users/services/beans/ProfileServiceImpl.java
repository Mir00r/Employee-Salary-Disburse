package com.mir00r.salarydisburse.domains.users.services.beans;

import com.mir00r.salarydisburse.commons.PageAttr;
import com.mir00r.salarydisburse.commons.utils.Validator;
import com.mir00r.salarydisburse.config.security.SecurityConfig;
import com.mir00r.salarydisburse.domains.users.models.entities.Profile;
import com.mir00r.salarydisburse.domains.users.models.entities.User;
import com.mir00r.salarydisburse.domains.users.repositories.ProfileRepository;
import com.mir00r.salarydisburse.domains.users.services.ProfileService;
import com.mir00r.salarydisburse.domains.users.services.UserService;
import com.mir00r.salarydisburse.exceptions.forbidden.ForbiddenException;
import com.mir00r.salarydisburse.exceptions.notfound.NotFoundException;
import com.mir00r.salarydisburse.exceptions.notfound.ProfileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UserService userService;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, UserService userService) {
        this.profileRepository = profileRepository;
        this.userService = userService;
    }

    @Override
    public Profile save(Profile profile, String username) throws NotFoundException {
        if (profile == null || username == null)
            throw new IllegalArgumentException("Profile or username can not be null");
        User user = this.userService.findByUsername(username).orElseThrow(() -> new ProfileNotFoundException("Could not find profile with username: " + username));
        profile.setUser(user);
        Profile exProfile = this.profileRepository.findByUserUsername(username);
        if (exProfile != null) {
            profile.setId(exProfile.getId());
            if (Validator.nullOrEmpty(profile.getPhoto()))
                profile.setPhoto(exProfile.getPhoto());
        }

        // update name of user
        user.setName(profile.getName());
        this.userService.save(user);

        SecurityConfig.updateAuthentication(profile.getUser());
        return this.profileRepository.save(profile);
    }

    @Override
    public Profile getProfileByUserId(Long id) throws ProfileNotFoundException, ForbiddenException {
        Profile profile = this.profileRepository.findByUserId(id);
        if (profile == null) throw new ProfileNotFoundException("Could not find profile for user id: " + id);
        return profile;
    }

    @Override
    public Profile getOneProfile(Long id) throws ProfileNotFoundException, ForbiddenException {
        if (id == null || !this.isExists(id))
            throw new ProfileNotFoundException("Profile could not found.");
        Profile profile = this.profileRepository.findById(id).orElse(null);
        if (profile == null)
            throw new ProfileNotFoundException("Profile could not be found.");
        if (!profile.hasAuthorizedAccess()) throw new ForbiddenException("You can not access this profile.");
        return profile;
    }

    @Override
    public Profile getProfileByUsername(String username) throws ProfileNotFoundException, ForbiddenException {
        Profile profile = this.profileRepository.findByUserUsername(username);
        if (profile == null)
            throw new ProfileNotFoundException("Profile could not be found.");
        if (!profile.hasAuthorizedAccess()) throw new ForbiddenException("You can not access this profile.");
        return profile;
    }

    @Override
    public List<Profile> getAllProfile() {
        return this.profileRepository.findAll();
    }

    @Override
    public Page<Profile> getAllProfilePaginated(int pageNumber) {
        return this.profileRepository.findAll(PageAttr.getPageRequest(pageNumber));
    }

    @Override
    public Long countProfile() {
        return this.profileRepository.count();
    }

    @Override
    public void delete(Long id) throws ProfileNotFoundException {
        if (id == null || !this.isExists(id))
            throw new ProfileNotFoundException("Profile could not found.");
        this.profileRepository.deleteById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return this.profileRepository.existsById(id);
    }


}
