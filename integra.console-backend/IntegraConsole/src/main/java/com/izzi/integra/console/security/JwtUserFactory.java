package com.izzi.integra.console.security;

import com.izzi.integra.console.dao.entity.CatUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(CatUser user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                "getFirstname",
                "getLastname",
                "getEmail",
                mapToGrantedAuthorities(user.getProfile().getProfileName()),
                true
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(final String profileName) {
        List<GrantedAuthority> ls = new ArrayList<GrantedAuthority>();
        ls.add(new SimpleGrantedAuthority(profileName));
        return ls;
    }
}
