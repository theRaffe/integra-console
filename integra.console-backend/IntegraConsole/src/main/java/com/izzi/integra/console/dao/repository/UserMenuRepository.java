package com.izzi.integra.console.dao.repository;

import com.izzi.integra.console.dao.entity.UserMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Set;

/**
 * Created by Rafael on 16/11/2016.
 */
public interface UserMenuRepository extends Repository<UserMenu, Long> {
    @Query(value = "SELECT  " +
            "        SYS_CONNECT_BY_PATH( menu_item_name, '/' ) PATH_ID,\n" +
            "        ORDER_ID,\n" +
            "        MENU_ITEM_ID,\n" +
            "        MENU_ITEM_NAME,\n" +
            "        MENU_PARENT_ID,\n" +
            "        MENU_ACTION\n" +
            "FROM CONSOLE_USER_MENU\n" +
            "WHERE CONSOLE_USER_MENU.USERNAME = ?1\n" +
            "AND MENU_PARENT_ID IS NULL\n" +
            "START WITH CONSOLE_USER_MENU.menu_parent_id IS NULL \n" +
            "CONNECT BY CONSOLE_USER_MENU.menu_parent_id = PRIOR CONSOLE_USER_MENU.menu_item_id\n" +
            "AND CONSOLE_USER_MENU.USERNAME = PRIOR CONSOLE_USER_MENU.USERNAME\n" +
            "ORDER SIBLINGS BY CONSOLE_USER_MENU.ORDER_ID",
            nativeQuery = true)
    Set<UserMenu> findMenuByUser(
            //@Param("username")
            String username
    );
}
