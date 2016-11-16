package com.izzi.integra.console.service;

import com.izzi.integra.console.dao.repository.CatUserRepository;
import com.izzi.integra.console.web.response.UserRestResponse;
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

    public UserRestResponse loadUserByUsername(String username) {
        try {
            return new UserRestResponse(true, "success", userRepository.findByUsername(username));
        } catch (final Exception e) {
            logger.error(MessageFormat.format("Error at getting CatUser: {0}", username), e);
            return new UserRestResponse(false, e.getMessage(), null);
        }
    }
}
