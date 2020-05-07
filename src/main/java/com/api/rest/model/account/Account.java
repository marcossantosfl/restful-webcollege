package com.api.rest.model.account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Account implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String login;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	private String name;
	
	private String email;

	@OneToMany(mappedBy="account", orphanRemoval = true, cascade = CascadeType.ALL, fetch =FetchType.LAZY)
	private List<Telephone> telephone = new ArrayList<Telephone>();
	
	//roles
	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(name = "account_role", uniqueConstraints = @UniqueConstraint (columnNames = {"account_id","role_id"}, name = "unique_role_user"), 
	joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id", table = "account", unique = false,
	foreignKey = @ForeignKey(name = "account_fk", value = ConstraintMode.CONSTRAINT)), 
	inverseJoinColumns = @JoinColumn (name = "role_id", referencedColumnName = "id", table = "role", unique = false, updatable = false,
	foreignKey = @ForeignKey (name="role_fk", value = ConstraintMode.CONSTRAINT)))
	private List<Role> roles = new ArrayList<Role>();
	
	@OneToMany(mappedBy="account", orphanRemoval = true, cascade = CascadeType.ALL, fetch =FetchType.LAZY)
	private List<Subject> subject = new ArrayList<Subject>();
	
	@OneToMany(mappedBy="account", orphanRemoval = true, cascade = CascadeType.ALL, fetch =FetchType.LAZY)
	private List<Coming> coming = new ArrayList<Coming>();


	public List<Telephone> getTelephones() {
		return telephone;
	}
	
	public void setTelephones(List<Telephone> telephones) {
		this.telephone = telephones;
	}
	
	public List<Subject> getSubject() {
		return subject;
	}

	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}
	
	public List<Coming> getComing() {
		return coming;
	}

	public void setComing(List<Coming> coming) {
		this.coming = coming;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}	
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
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

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		return roles;
	}

	@JsonIgnore
	@Override
	public String getUsername() {
		return null;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return false;
	}

}
