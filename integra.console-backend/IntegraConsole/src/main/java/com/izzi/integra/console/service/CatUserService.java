package com.izzi.integra.console.service;

import com.izzi.integra.console.dao.entity.CatProfile;
import com.izzi.integra.console.dao.entity.CatUser;
import com.izzi.integra.console.dao.repository.CatProfileRepository;
import com.izzi.integra.console.dao.repository.CatUserRepository;
import com.izzi.integra.console.service.validation.ResultValidation;
import com.izzi.integra.console.web.request.UserRestRequest;
import com.izzi.integra.console.web.response.UserRestResponse;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

import static com.izzi.integra.console.service.validation.UserServiceValidator.validateUserUpdate;

/**
 * Created by Rafael on 15/11/2016.
 */
@Service
public class CatUserService {

    private static final Logger logger = LoggerFactory.getLogger(CatUserService.class);

    @Autowired
    private CatUserRepository userRepository;
    @Autowired
    private CatProfileRepository profileRepository;

    public UserRestResponse loadUserByUsername(String username) {
        try {
            return new UserRestResponse(true, "success", userRepository.findByUsername(username));
        } catch (final Exception e) {
            logger.error(MessageFormat.format("Error at getting CatUser: {0}", username), e);
            return new UserRestResponse(false, e.getMessage(), null);
        }
    }

    public UserRestResponse saveUser(final UserRestRequest user) {
        try {
            final CatProfile profile = profileRepository.findByProfileName(user.getProfileName());
            return new UserRestResponse(true, "Success",
                    userRepository.save(new CatUser(user.getUsername(),
                            user.getCreationUser(),
                            new java.util.Date(),
                            profile)));
        } catch (final Exception e) {
            logger.error(MessageFormat.format("Error at persisting CatUser: {0}", ReflectionToStringBuilder.toString(user)), e);
            return new UserRestResponse(false, e.getMessage(), null);
        }
    }

    public UserRestResponse updateUser(final UserRestRequest user) {
        try {
            final CatProfile profile = profileRepository.findByProfileName(user.getProfileName());
            final CatUser catUser = userRepository.findByUsername(user.getUsername());

            final ResultValidation resultValidation = validateUserUpdate(catUser, profile);
            if (!resultValidation.isSuccess()) {
                return new UserRestResponse(false,
                        resultValidation.getMessage(),
                        null);
            }

            catUser.setProfile(profile);
            catUser.setLastUpdate(new java.util.Date());
            return new UserRestResponse(true,
                    "Success",
                    userRepository.save(catUser));
        } catch (final Exception e) {
            logger.error(MessageFormat.format("Error at persisting CatUser: {0}", ReflectionToStringBuilder.toString(user)), e);
            return new UserRestResponse(false, e.getMessage(), null);
        }
    }

    public UserRestResponse deleteUser(final Long userId) {
        return doActivationUser(userId, false);
    }

    public UserRestResponse activateUser(final Long userId) {
        return doActivationUser(userId, true);
    }

    private UserRestResponse doActivationUser(final Long userId, final boolean active) {
        try {
            final CatUser catUser = userRepository.findById(userId);
            catUser.setActive(active);
            catUser.setLastUpdate(new java.util.Date());
            return new UserRestResponse(true, "Success", userRepository.save(catUser));
        } catch (Exception e) {
            logger.error(MessageFormat.format("Error at activating/deleting CatUser: {0}", userId), e);
            return new UserRestResponse(false, e.getMessage(), null);
        }
    }

}
