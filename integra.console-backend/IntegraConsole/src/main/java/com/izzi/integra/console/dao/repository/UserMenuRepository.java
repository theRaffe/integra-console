package com.izzi.integra.console.dao.repository;

import com.izzi.integra.console.dao.entity.UserMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Rafael on 16/11/2016.
 */
public interface UserMenuRepository extends Repository<UserMenu, Long> {
    @Query(value = "select  " +
            "        sys_connect_by_path( menu_item_name, '/' ) path_id,\n" +
            "        ORDER_ID,\n" +
            "        MENU_ITEM_ID,\n" +
            "        MENU_ITEM_NAME,\n" +
            "        MENU_PARENT_ID,\n" +
            "        MENU_ACTION\n" +
            "from CONSOLE_USER_MENU\n" +
            "where CONSOLE_USER_MENU.USERNAME = ?1\n" +
            "START WITH CONSOLE_USER_MENU.menu_parent_id IS NULL \n" +
            "CONNECT BY CONSOLE_USER_MENU.menu_parent_id = PRIOR CONSOLE_USER_MENU.menu_item_id\n" +
            "order siblings by CONSOLE_USER_MENU.ORDER_ID",
            nativeQuery = true)
    List<UserMenu> findMenuByUser(
            //@Param("username")
            String username
    );
}
