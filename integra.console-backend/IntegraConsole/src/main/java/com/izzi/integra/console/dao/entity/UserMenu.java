package com.izzi.integra.console.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.izzi.integra.console.dao.converter.RouterLinkConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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

    @Column(name = "ORDER_ID")
    @JsonIgnore
    private Long orderId;

    @Column(name = "MENU_ITEM_NAME")
    @JsonProperty("label")
    private String menuItemName;

    @OneToMany(
            cascade = {CascadeType.PERSIST},
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @JoinColumn(name = "MENU_PARENT_ID")
    @OrderColumn(name = "ORDER_ID")
    @JsonProperty("items")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<UserMenu> items;

    @Column(name = "MENU_ACTION")
    @Convert(converter = RouterLinkConverter.class)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("routerLink")
    private String[] menuAction;

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

    public Set<UserMenu> getItems() {
        return items;
    }

    public void setItems(Set<UserMenu> items) {
        this.items = items;
    }

    public String[] getMenuAction() {
        return menuAction;
    }

    public void setMenuAction(String[] menuAction) {
        this.menuAction = menuAction;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

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
