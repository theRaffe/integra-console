package com.izzi.integra.console.dao.repository;

import com.izzi.integra.console.dao.entity.ProfileMenu;
import org.springframework.data.repository.Repository;

import java.util.Set;

/**
 * Created by Rafael on 14/12/2016.
 */
public interface ProfileMenuRepository extends Repository<ProfileMenu, Long> {

    Set<ProfileMenu> findByProfileId(Long profileId);
}
