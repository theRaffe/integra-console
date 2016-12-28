package com.izzi.integra.console.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by Rafael on 14/12/2016.
 */
@Embeddable
public class ProfileMenuKey implements Serializable {

    @Column(name = "PROFILE_ID", nullable = false)
    private int profileId;

    @Column(name = "MENU_ID", nullable = false)
    private int menuId;

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
}
