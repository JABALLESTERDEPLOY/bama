package com.tfg.security.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
public class JwtDTO {

	private Long id;
	private String token;
	private String bearer = "Bearer";
	private String username;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtDTO(Long id, String token, String username, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.token = token;
		this.username = username;
		this.authorities = authorities;
	}
}
