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

    @Modifying
    @Query(value="UPDATE CONSOLE_CAT_PROFILE P SET P.PROFILE_NAME = ?1, WHERE P.PROFILE_ID = ?2")
    CatProfile updateById(String profileName, Long profileId);

    @Modifying
    @Query(value="UPDATE CONSOLE_CAT_PROFILE P SET P.ACTIVE = ?1 WHERE P.PROFILE_ID = ?2")
    CatProfile updateStatus(String isActive, Long profileId);
}
