package com.izzi.integra.console.service;

import com.izzi.integra.console.dao.entity.CatProfile;
import com.izzi.integra.console.dao.entity.CatUser;
import com.izzi.integra.console.dao.repository.CatProfileRepository;
import com.izzi.integra.console.dao.repository.CatUserRepository;
import com.izzi.integra.console.util.Constants;
import com.izzi.integra.console.web.request.UserRestRequest;
import com.izzi.integra.console.web.response.UserRestResponse;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

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

    public  UserRestResponse saveUser(UserRestRequest user){
        CatProfile profile = profileRepository.findByProfileName(user.getProfileName());
        try {
            return new  UserRestResponse(true, "Success",
                    userRepository.save(new CatUser(user.getUsername(),
                    user.getCreationUser(),
                    new java.util.Date(),
                    new java.util.Date(),
                    profile)));
        }catch(final Exception e){
            logger.error(MessageFormat.format("Error at persisting CatUser: {0}", ReflectionToStringBuilder.toString(user)), e);
            return new UserRestResponse(false, e.getMessage(), null);
        }
    }

    public UserRestResponse updateUser(UserRestRequest user){
        CatProfile profile = profileRepository.findByProfileName(user.getProfileName());
        try {
            return new UserRestResponse(true,
                    "Success",
                    userRepository.updateById(user.getUsername(), profile.getProfileId(), user.getUserId()));
        }catch(Exception e){
            logger.error(MessageFormat.format("Error at persisting CatUser: {0}", ReflectionToStringBuilder.toString(user)), e);
            return new UserRestResponse(false, e.getMessage(), null);
        }
    }

    public UserRestResponse deleteUser(Long userId){
        try {
            return new UserRestResponse(true, "Success", userRepository.updateStatus(Constants.N, userId));
        }catch(Exception e){
            logger.error(MessageFormat.format("Error at deleting CatUser: {0}", userId), e);
            return new UserRestResponse(false, e.getMessage(), null);
        }
    }

    public UserRestResponse activateUser(Long userId){
        try {
            return new UserRestResponse(true, "Success", userRepository.updateStatus(Constants.Y, userId));
        }catch(Exception e){
            logger.error(MessageFormat.format("Error at activating CatUser: {0}", userId), e);
            return new UserRestResponse(false, e.getMessage(), null);
        }
    }
}
