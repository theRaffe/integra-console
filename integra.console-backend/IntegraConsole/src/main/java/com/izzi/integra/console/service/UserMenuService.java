package com.izzi.integra.console.service;

import com.izzi.integra.console.dao.entity.ProfileMenu;
import com.izzi.integra.console.dao.entity.UserMenu;
import com.izzi.integra.console.dao.repository.ProfileMenuRepository;
import com.izzi.integra.console.dao.repository.UserMenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Set;

/**
 * Created by Rafael on 16/11/2016.
 */
@Service
public class UserMenuService {
    private static final Logger logger = LoggerFactory.getLogger(UserMenuService.class);

    @Autowired
    private UserMenuRepository userMenuRepository;

    @Autowired
    private ProfileMenuRepository profileMenuRepository;

    public Set<UserMenu> getMenuByUser(final String username) {
        logger.debug(MessageFormat.format("Entry getting menu of user:[{0}]", username));
        try {
            Set<UserMenu> ls = userMenuRepository.findMenuByUser(username);
            logger.info(MessageFormat.format("get List<UserMenu>:", ls));
            return ls;
        } catch (final Exception e) {
            logger.error(MessageFormat.format("An Exception at getting menu of user: {0}", username), e);
            return null;
        }
    }

    public Set<ProfileMenu> getProfileMenu(final Long profileId) {
        try {
            final Set<ProfileMenu> ls = profileMenuRepository.findByProfileId(profileId);
            return ls;
        } catch (final Exception e) {
            logger.error(MessageFormat.format("An Exception at getting menu of profile: {0}", profileId), e);
            return null;
        }
    }

}
