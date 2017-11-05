package com.mes.msgboard.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "user")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(dataType = "string", required = false, name = "id", value = "id")
	private Integer id;

	@Column(name = "name")
	@ApiModelProperty(dataType = "string", required = true, name = "name", value = "name")
	@NotNull
	private String name;

	@Column(name = "email",unique=true)
	@NotNull
	@ApiModelProperty(dataType = "string", required = true, name = "email", value = "email")
	private String email;

	@Column(name = "password")
	@NotNull
	@ApiModelProperty(dataType = "string", required = true, name = "password", value = "password")
	private String password;

	@Column(name = "usename",unique=true)
	@NotNull
	@ApiModelProperty(dataType = "string", required = true, name = "userName", value = "userName")
	private String username;

	@Column(name = "is_active")
	@ApiModelProperty(dataType = "boolean", required = false, name = "isActive", value = "isActive")
	private boolean isActive;

	@Column(name = "image_url")
	@ApiModelProperty(dataType = "string", required = false, name = "imageUrl", value = "imageUrl")
	private String imageUrl;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role")
	@ApiModelProperty(dataType = "string", required = true, name = "role", value = "role")
	private Role role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User(Integer id, String name, String email, String password, String userName, boolean isActive,
			String imageUrl, Role role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.username = userName;
		this.isActive = isActive;
		this.imageUrl = imageUrl;
		this.role = role;
	}
	
	public User(String name, String email, String password, String userName, boolean isActive,
			String imageUrl, Role role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.username = userName;
		this.isActive = isActive;
		this.imageUrl = imageUrl;
		this.role = role;
	}

	public User() {

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new GrantedAuthority() {

			private static final long serialVersionUID = 7505731086143313899L;

			@Override
			public String getAuthority() {
				return role.getValue();
			}
		};
		authorities.add(authority);
		return authorities;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return this.isActive;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.isActive;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return this.isActive;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.isActive;
	}

}
