package com.izzi.integra.console.service;

import com.izzi.integra.console.dao.entity.CatProfile;
import com.izzi.integra.console.dao.repository.CatProfileRepository;
import com.izzi.integra.console.util.Constants;
import com.izzi.integra.console.web.request.ProfileRequest;
import com.izzi.integra.console.web.response.ProfileResponse;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;

/**
 * Created by laura.hernandez.ext on 25/11/2016.
 */
@Service
public class CatProfileService {

    private static final Logger logger = LoggerFactory.getLogger(CatProfileService.class);

    @Autowired
    private CatProfileRepository profileRepository;

    public ProfileResponse getProfiles(){
        try{
            return new ProfileResponse(true, "Success", profileRepository.findAll());
        }catch(final Exception e){
            logger.error("Error at getting all profiles", e);
            return new ProfileResponse(false, e.getMessage(), new ArrayList<CatProfile>());
        }
    }

    public  ProfileResponse saveProfile(ProfileRequest profile){
        CatProfile catProf = null;
        try {
            return new ProfileResponse(true, "Success",
                    profileRepository.save(new CatProfile(profile.getProfileName(),
                            profile.getCreationUser(),
                            new java.util.Date(),
                            new java.util.Date())));
        }catch(final Exception e){
            logger.error(MessageFormat.format("Error at persisting CatProfile: {0}", ReflectionToStringBuilder.toString(profile)), e);
            return new ProfileResponse(false, e.getMessage(), catProf);
        }
    }

    public ProfileResponse updateProfile(ProfileRequest profile){
        CatProfile catProf = null;
        try {
            return new ProfileResponse(true,
                    "Success",
                    profileRepository.updateById(profile.getProfileName(), profile.getProfileId()));
        }catch(Exception e){
            logger.error(MessageFormat.format("Error at persisting CatProfile: {0}", ReflectionToStringBuilder.toString(profile)), e);
            return new ProfileResponse(false, e.getMessage(), catProf);
        }
    }

    public ProfileResponse deleteProfile(Long profileId){
        CatProfile catProf = null;
        try {
            return new ProfileResponse(true, "Success", profileRepository.updateStatus(Constants.N, profileId));
        }catch(Exception e){
            logger.error(MessageFormat.format("Error at deleting CatProfile: {0}", profileId), e);
            return new ProfileResponse(false, e.getMessage(), catProf);
        }
    }

    public ProfileResponse activateProfile(Long profileId){
        CatProfile catProf = null;
        try {
            return new ProfileResponse(true, "Success", profileRepository.updateStatus(Constants.Y, profileId));
        }catch(Exception e){
            logger.error(MessageFormat.format("Error at activating CatProfile: {0}", profileId), e);
            return new ProfileResponse(false, e.getMessage(), catProf);
        }
    }
}
