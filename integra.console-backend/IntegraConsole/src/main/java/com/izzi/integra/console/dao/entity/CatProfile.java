package com.izzi.integra.console.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Rafael on 18/11/2016.
 */
@Entity
@Table(name = "CONSOLE_CAT_PROFILE")
public class CatProfile {

    @Id
    @Column(name = "PROFILE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONSOLE_SEQ_PROFILE")
    @SequenceGenerator(name = "CONSOLE_SEQ_PROFILE", sequenceName = "CONSOLE_SEQ_PROFILE", allocationSize = 1)
    private Long profileId;

    @Column(name = "PROFILE_NAME", length = 50)
    @NotNull
    private String profileName;

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date creationDate;

    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastUpdate;

    @Column(name = "CREATION_USER", length = 50)
    @NotNull
    private String creationUser;

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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }
}
