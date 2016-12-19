package com.izzi.integra.console.dao.repository;

import com.izzi.integra.console.dao.entity.CatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Rafael on 15/11/2016.
 */
public interface CatUserRepository extends JpaRepository<CatUser, Long> {

    CatUser findByUsername(String username);

    List<CatUser> findAll();

    @Modifying
    @Query(value="UPDATE CONSOLE_CAT_USER U SET U.USERNAME = ?1, U.PROFILE_ID = ?2 WHERE U.USER_ID = ?3")
    CatUser updateById(String username, Long profileId, Long userId);

    @Modifying
    @Query(value="UPDATE CONSOLE_CAT_USER U SET U.ACTIVE = ?1 WHERE U.USER_ID = ?2")
    CatUser updateStatus(String isActive, Long userId);
}
