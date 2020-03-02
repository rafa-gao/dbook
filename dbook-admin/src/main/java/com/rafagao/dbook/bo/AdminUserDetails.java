package com.rafagao.dbook.bo;

import com.rafagao.dbook.domain.DbookAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author rafa gao
 */


public class AdminUserDetails implements UserDetails {

    @Autowired
    private DbookAdmin dbookAdmin;

    public AdminUserDetails(DbookAdmin dbookAdmin) {
        this.dbookAdmin = dbookAdmin;
    }

    public int getUserid(){
        return dbookAdmin.getId();
    }


    /**
     * 用户应当拥有的权限
     *
     * @param
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //权限表还没有建立，随后完善
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("Admin"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.dbookAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return this.dbookAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !dbookAdmin.getDeleted();
    }
}
