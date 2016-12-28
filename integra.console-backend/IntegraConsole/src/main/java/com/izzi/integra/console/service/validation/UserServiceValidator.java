package com.izzi.integra.console.service.validation;

import com.izzi.integra.console.dao.entity.CatProfile;
import com.izzi.integra.console.dao.entity.CatUser;

/**
 * Created by Rafael on 22/12/2016.
 */
public class UserServiceValidator {

    public static ResultValidation validateUserUpdate(final CatUser user, final CatProfile profile) {
        final StringBuilder sb = new StringBuilder();
        if (user == null) {
            sb.append("User not found!!");
        }

        if (profile == null) {
            sb.append("Profile not found!!");
        }

        return new ResultValidation(sb.length() == 0, sb.toString());
    }
}
