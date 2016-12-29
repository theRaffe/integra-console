package com.izzi.integra.console.web.controller;

import com.izzi.integra.console.service.CatProfileService;
import com.izzi.integra.console.web.request.ProfileRequest;
import com.izzi.integra.console.web.response.ProfileResponse;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

/**
 * Created by laura.hernandez.ext on 25/11/2016.
 */
@RestController
public class ProfileRestController {

    private static final Logger logger = LoggerFactory.getLogger(ProfileRestController.class);

    @Autowired
    CatProfileService catProfileService;


    @RequestMapping(value = "/getProfiles", method = RequestMethod.GET)
    public ResponseEntity<?> getProfiles(){
        logger.debug("Searching all profiles");
        ProfileResponse profileResponse = catProfileService.getProfiles();
        return ResponseEntity.ok(profileResponse);
    }

    @RequestMapping(value="/addProfile",  method = RequestMethod.POST)
    public ResponseEntity<?> addProfile(@RequestBody ProfileRequest profileRequest){
        logger.debug(MessageFormat.format("Updating profile {0}", ReflectionToStringBuilder.toString(profileRequest)));
        ProfileResponse profileResponse = catProfileService.saveProfile(profileRequest);
        return ResponseEntity.ok(profileResponse);
    }

    @RequestMapping(value="/modifyProfile",  method = RequestMethod.POST)
    public ResponseEntity<?> modifyProfile(@RequestBody ProfileRequest profileRequest){
        logger.debug(MessageFormat.format("Updating profile {0}", ReflectionToStringBuilder.toString(profileRequest)));
        ProfileResponse profileResponse = catProfileService.updateProfile(profileRequest);
        return ResponseEntity.ok(profileResponse);
    }

    @RequestMapping(value="/deleteProfile",  method = RequestMethod.POST)
    public ResponseEntity<?> deleteProfile(@RequestBody ProfileRequest profileRequest){
        logger.debug(MessageFormat.format("Deleting profile {0}", ReflectionToStringBuilder.toString(profileRequest)));
        ProfileResponse profileResponse = catProfileService.deleteProfile(profileRequest.getProfileId());
        return ResponseEntity.ok(profileResponse);
    }


}
