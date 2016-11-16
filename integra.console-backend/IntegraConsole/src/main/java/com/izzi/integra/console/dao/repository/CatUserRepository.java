package com.izzi.integra.console.dao.repository;

import com.izzi.integra.console.dao.entity.CatUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Rafael on 15/11/2016.
 */
public interface CatUserRepository extends JpaRepository<CatUser, Long> {

    CatUser findByUsername(String username);
}
