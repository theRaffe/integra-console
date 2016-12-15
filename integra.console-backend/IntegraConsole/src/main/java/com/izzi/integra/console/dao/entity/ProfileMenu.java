package com.izzi.integra.console.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Rafael on 14/12/2016.
 */
@Entity
@Table(name = "CONSOLE_PROFILE_MENU_VW")
public class ProfileMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    /*@EmbeddedId
    private ProfileMenuKey profileMenuKey;
    */
    @Id
    @Column(name = "MENU_ITEM_ID")
    private Long menuId;

    @Column(name = "PROFILE_ID")
    private Long profileId;

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="MENU_PARENT_ID")
    @JsonIgnore
    private ProfileMenu menuParent;

    @OneToMany(mappedBy="menuParent")
    private Set<ProfileMenu> menuItems;

    @Column(name="MENU_ITEM_NAME")
    private String menuItemName;

    /*public ProfileMenuKey getProfileMenuKey() {
        return profileMenuKey;
    }

    public void setProfileMenuKey(ProfileMenuKey profileMenuKey) {
        this.profileMenuKey = profileMenuKey;
    }
    */
    public ProfileMenu getMenuParent() {
        return menuParent;
    }

    public void setMenuParent(ProfileMenu menuParent) {
        this.menuParent = menuParent;
    }

    public Set<ProfileMenu> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(Set<ProfileMenu> menuItems) {
        this.menuItems = menuItems;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
