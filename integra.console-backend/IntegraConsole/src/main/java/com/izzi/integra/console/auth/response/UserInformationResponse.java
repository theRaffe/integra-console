package com.izzi.integra.console.auth.response;

import com.izzi.integra.console.dao.entity.UserMenu;

import java.util.Set;

/**
 * Created by Rafael on 13/12/2016.
 */
public class UserInformationResponse {

    private boolean success;
    private String message;
    private String profileName;
    private Set<UserMenu> menuItems;

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Set<UserMenu> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(Set<UserMenu> menuItems) {
        this.menuItems = menuItems;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserInformationResponse(String profileName, Set<UserMenu> menuItems) {
        this.profileName = profileName;
        this.menuItems = menuItems;
        this.success = true;
        this.message = "";
    }

    public UserInformationResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
