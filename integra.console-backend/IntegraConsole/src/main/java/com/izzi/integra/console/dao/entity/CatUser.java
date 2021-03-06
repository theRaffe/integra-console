package com.izzi.integra.console.dao.entity;

import com.izzi.integra.console.dao.entity.converter.ActiveConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Rafael on 15/11/2016.
 */
@Entity
@Table(name = "CONSOLE_CAT_USER")
public class CatUser {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONSOLE_SEQ_USER")
    @SequenceGenerator(name = "CONSOLE_SEQ_USER", sequenceName = "CONSOLE_SEQ_USER", allocationSize = 1)
    private Long id;

    @Column(name = "USERNAME", length = 50)
    @NotNull
    private String username;

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date creationDate;

    @Column(name = "CREATION_USER", length = 50)
    @NotNull
    private String creationUser;

    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastUpdate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PROFILE_ID")
    private CatProfile profile;

    @Column(name = "ACTIVE")
    @Convert(converter = ActiveConverter.class)
    private Boolean active;

    public CatUser(){}
    
    public CatUser(String username, String creationUser, Date creationDate, CatProfile profile){
        this.username = username;
        this.creationUser = creationUser;
        this.creationDate = creationDate;
        this.lastUpdate = creationDate;
        this.profile = profile;
        this.active = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public CatProfile getProfile() {
        return profile;
    }

    public void setProfile(CatProfile profile) {
        this.profile = profile;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
