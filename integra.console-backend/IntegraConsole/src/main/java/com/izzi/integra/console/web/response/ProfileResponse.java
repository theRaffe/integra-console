package com.izzi.integra.console.web.response;

import com.izzi.integra.console.dao.entity.CatProfile;

import java.util.List;

/**
 * Created by laura.hernandez.ext on 25/11/2016.
 */
public class ProfileResponse {
    private Boolean success;
    private String message;
    private CatProfile catProfile;
    private List<CatProfile> listProfiles;

    public ProfileResponse(boolean success, String message, CatProfile catProfile){
        this.success = success;
        this.message = message;
        this.catProfile = catProfile;
    }

    public ProfileResponse(Boolean success, String message, List<CatProfile> listProfiles){
        this.success = success;
        this.message = message;
        this.listProfiles = listProfiles;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CatProfile getCatProfile() {
        return catProfile;
    }

    public void setCatProfile(CatProfile catProfile) {
        this.catProfile = catProfile;
    }

    public List<CatProfile> getListProfiles() {
        return listProfiles;
    }

    public void setListProfiles(List<CatProfile> listProfiles) {
        this.listProfiles = listProfiles;
    }
}
