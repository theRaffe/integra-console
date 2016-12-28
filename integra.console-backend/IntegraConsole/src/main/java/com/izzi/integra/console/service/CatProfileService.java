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

    public ProfileResponse getProfiles() {
        try {
            return new ProfileResponse(true, "Success", profileRepository.findAll());
        } catch (final Exception e) {
            logger.error("Error at getting all profiles", e);
            return new ProfileResponse(false, e.getMessage(), new ArrayList<CatProfile>());
        }
    }

    public ProfileResponse saveProfile(ProfileRequest profile) {
        try {
            return new ProfileResponse(true, "Success",
                    profileRepository.save(new CatProfile(profile.getProfileName(),
                            profile.getCreationUser(),
                            new java.util.Date())));
        } catch (final Exception e) {
            logger.error(MessageFormat.format("Error at persisting CatProfile: {0}", ReflectionToStringBuilder.toString(profile)), e);
            return new ProfileResponse(false, e.getMessage(), null);
        }
    }

    public ProfileResponse updateProfile(ProfileRequest profile) {
        try {
            final CatProfile catProfile = profileRepository.findByProfileId(profile.getProfileId());
            catProfile.setProfileName(profile.getProfileName());
            catProfile.setLastUpdate(new java.util.Date());
            return new ProfileResponse(true,
                    "Success",
                    profileRepository.save(catProfile));
        } catch (final Exception e) {
            logger.error(MessageFormat.format("Error at persisting CatProfile: {0}", ReflectionToStringBuilder.toString(profile)), e);
            return new ProfileResponse(false, e.getMessage(), null);
        }
    }

    public ProfileResponse deleteProfile(Long profileId) {
        return doActivationProfile(profileId, false);
    }

    public ProfileResponse activateProfile(Long profileId) {
        return doActivationProfile(profileId, true);
    }

    private ProfileResponse doActivationProfile(final Long profileId, final boolean active) {
        try {
            final CatProfile catProfile = profileRepository.findByProfileId(profileId);
            catProfile.setActive(active);
            catProfile.setLastUpdate(new java.util.Date());
            return new ProfileResponse(true, "Success", profileRepository.save(catProfile));
        } catch (Exception e) {
            logger.error(MessageFormat.format("Error at activating/deleting CatProfile: {0}", profileId), e);
            return new ProfileResponse(false, e.getMessage(), null);
        }
    }
}
