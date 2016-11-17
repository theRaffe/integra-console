package com.izzi.integra.console.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Rafael on 16/11/2016.
 */
@Entity
@Table(name = "CONSOLE_USER_MENU")
public class UserMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MENU_ITEM_ID")
    @JsonIgnore
    private Long menuItemId;
    /*@Column(name = "PATH_ID")
    private String pathId;*/
    @Column(name = "ORDER_ID")
    @JsonIgnore
    private Long orderId;

    @Column(name = "MENU_ITEM_NAME")
    @JsonProperty("label")
    private String menuItemName;
    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(name = "MENU_PARENT_ID")
    @OrderColumn(name = "ORDER_ID")
    @JsonProperty("items")
    private List<UserMenu> items;

    @Column(name = "MENU_ACTION")
    private String menuAction;

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public List<UserMenu> getItems() {
        return items;
    }

    public void setItems(List<UserMenu> items) {
        this.items = items;
    }

    public String getMenuAction() {
        return menuAction;
    }

    public void setMenuAction(String menuAction) {
        this.menuAction = menuAction;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /*public String getPathId() {
        return pathId;
    }

    public void setPathId(String pathId) {
        this.pathId = pathId;
    }*/

    @Override
    public String toString() {
        return "UserMenu{" +
                "menuItemId=" + menuItemId +
                ", orderId=" + orderId +
                ", menuItemName='" + menuItemName + '\'' +
                ", items=" + items +
                ", menuAction='" + menuAction + '\'' +
                '}';
    }
}
