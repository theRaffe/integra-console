package com.izzi.integra.console.dao.repository;

import com.izzi.integra.console.dao.entity.CatProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by laura.hernandez.ext on 25/11/2016.
 */
public interface CatProfileRepository  extends JpaRepository<CatProfile,Long> {
    CatProfile findByProfileName(String profileName);

    List<CatProfile> findAll();

    CatProfile findByProfileId(final Long profileId);
}
