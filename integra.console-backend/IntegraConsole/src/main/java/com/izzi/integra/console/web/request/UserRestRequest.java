package com.izzi.integra.console.web.request;

/**
 * Created by Rafael on 15/11/2016.
 */
public class UserRestRequest {
    private String username;
    private Long userId;
    private Long profileId;
    private String profileName;
    private String creationUser;
    private Boolean active;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean isActive() {
        return active;
    }

    public void setIsActive(Boolean active) {
        this.active = active;
    }
}
