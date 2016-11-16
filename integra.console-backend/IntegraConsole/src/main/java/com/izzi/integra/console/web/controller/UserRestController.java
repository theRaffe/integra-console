package com.izzi.integra.console.web.controller;

import com.izzi.integra.console.service.CatUserService;
import com.izzi.integra.console.web.response.UserRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

/**
 * Created by Rafael on 15/11/2016.
 */
@RestController
public class UserRestController {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private CatUserService catUserService;

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseEntity<?> getUsers(@RequestParam(value = "username", defaultValue = "World") String username) {

        logger.debug(MessageFormat.format("searching user {0}", username));
        final UserRestResponse userRestResponse = catUserService.loadUserByUsername(username);

        return ResponseEntity.ok(userRestResponse);
    }
}
