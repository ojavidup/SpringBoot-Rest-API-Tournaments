package com.oscarspring.tournament.rest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="authorities")
public class Authority {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_authority")
	private long id;
	
	@Column(name="authority")
	@NaturalId
	@Enumerated(EnumType.STRING)
	private AuthorityName authority;
	
	public Authority() {
	}

	public Authority(AuthorityName authority) {
		this.authority = authority;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AuthorityName getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityName authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "Authority [id=" + id + ", authority=" + authority + "]";
	}

}
