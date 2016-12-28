package com.izzi.integra.console.web.controller;

import com.izzi.integra.console.auth.response.UserInformationResponse;
import com.izzi.integra.console.dao.entity.ProfileMenu;
import com.izzi.integra.console.dao.entity.UserMenu;
import com.izzi.integra.console.security.JwtTokenUtil;
import com.izzi.integra.console.service.CatUserService;
import com.izzi.integra.console.service.UserMenuService;
import com.izzi.integra.console.web.request.UserRestRequest;
import com.izzi.integra.console.web.response.UserRestResponse;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Set;

/**
 * Created by rafael.briones.ext on 15/11/2016.
 */
@RestController
public class UserRestController {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private CatUserService catUserService;

    @Autowired
    private UserMenuService userMenuService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@RequestParam(value = "username", defaultValue = "") String username) {

        logger.debug(MessageFormat.format("searching user {0}", username));
        final UserRestResponse userRestResponse = catUserService.loadUserByUsername(username);
        return ResponseEntity.ok(userRestResponse);
    }

    @RequestMapping(value = "/getMenuUser", method = RequestMethod.GET)
    public ResponseEntity<?> getMenuUser(@RequestParam(value = "username") String username) {

        logger.debug(MessageFormat.format("getting menu of user {0}", username));
        //Set<UserMenu> ls = userMenuService.getMenuByUser(username);
        Set<ProfileMenu> ls = userMenuService.getProfileMenu((long) 1);

        return ResponseEntity.ok(ls);
    }

    @RequestMapping(value = "/getUserInformation", method = RequestMethod.GET)
    public ResponseEntity<?> getUserInformation(HttpServletRequest request) {
        final String authToken = request.getHeader(this.tokenHeader);
        logger.debug(MessageFormat.format("getting user's information, profile, menu etc", authToken));
        final String username = jwtTokenUtil.getUsernameFromToken(authToken);

        if (username != null) {
            final UserRestResponse userRestResponse = catUserService.loadUserByUsername(username);
            final String profileName = userRestResponse.isSuccess() && userRestResponse.getCatUser() != null ? userRestResponse.getCatUser().getProfile().getProfileName() : "";
            Set<UserMenu> ls = userMenuService.getMenuByUser(username);
            final UserInformationResponse response = new UserInformationResponse(profileName, ls);
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.ok(new UserInformationResponse(false, "error at authorization token, couldn't get username!"));
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody UserRestRequest userRestRequest) {

        logger.debug(MessageFormat.format("Adding user {0}",  ReflectionToStringBuilder.toString(userRestRequest)));
        final UserRestResponse userRestResponse = catUserService.saveUser(userRestRequest);

        return ResponseEntity.ok(userRestResponse);
    }

    @RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
    public ResponseEntity<?> modifyUser(@RequestBody UserRestRequest userRestRequest) {

        logger.debug(MessageFormat.format("Updating user {0}",  ReflectionToStringBuilder.toString(userRestRequest)));
        final UserRestResponse userRestResponse = catUserService.updateUser(userRestRequest);

        return ResponseEntity.ok(userRestResponse);
    }


    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ResponseEntity<?> deleteUser(@RequestBody UserRestRequest userRestRequest) {

        logger.debug(MessageFormat.format("Deleting user {0}",  ReflectionToStringBuilder.toString(userRestRequest)));
        final UserRestResponse userRestResponse = catUserService.deleteUser(userRestRequest.getUserId());

        return ResponseEntity.ok(userRestResponse);
    }


}
