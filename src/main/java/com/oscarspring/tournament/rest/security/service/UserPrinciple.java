package com.oscarspring.tournament.rest.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oscarspring.tournament.rest.entity.User;

public class UserPrinciple implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private String email;
	
	@JsonIgnore
	private String password;
	
	private int enable;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserPrinciple(Long id, String name, String email, String password, int enable,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.enable = enable;
		this.authorities = authorities;
	}

	public static UserPrinciple build(User user) {
		List<GrantedAuthority> authorities = user.getAuthorities().stream().map(authority ->
				new SimpleGrantedAuthority(authority.getAuthority().name())).collect(Collectors.toList());
		
		return new UserPrinciple(
				user.getId(),
				user.getName(),
				user.getEmail(),
				user.getPassword(),
				user.getEnable(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
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
		return true;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }

}
