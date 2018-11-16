package com.oscarspring.tournament.rest.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tournaments")
public class Tournament implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tournament")
	private long id;
	
	@Column(name="tournament_name")
	private String name;
	
	@Column(name="teams_number")
	private int teamsNumber;
	
	@Column(name="players_no")
	private int playersNumber;
	
	@Column(name="players_in_game")
	private int playersInGame;
	
	@Column(name="inscription_cost")
	private double inscriptionCost;
	
	@Column(name="start_date")
	private java.sql.Date startDate;
	
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="id_field")
	private Field idField;

	public Tournament() {
	}

	public Tournament(String name, int teamsNumber, int playersNumber, int playersInGame,
			double inscriptionCost, Date startDate) {
		this.name = name;
		this.teamsNumber = teamsNumber;
		this.playersNumber = playersNumber;
		this.playersInGame = playersInGame;
		this.inscriptionCost = inscriptionCost;
		this.startDate = startDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTeamsNumber() {
		return teamsNumber;
	}

	public void setTeamsNumber(int teamsNumber) {
		this.teamsNumber = teamsNumber;
	}

	public int getPlayersNumber() {
		return playersNumber;
	}

	public void setPlayersNumber(int playersNumber) {
		this.playersNumber = playersNumber;
	}

	public int getPlayersInGame() {
		return playersInGame;
	}

	public void setPlayersInGame(int playersInGame) {
		this.playersInGame = playersInGame;
	}

	public double getInscriptionCost() {
		return inscriptionCost;
	}

	public void setInscriptionCost(double inscriptionCost) {
		this.inscriptionCost = inscriptionCost;
	}

	public java.sql.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}

	public Field getIdField() {
		return idField;
	}

	public void setIdField(Field idField) {
		this.idField = idField;
	}

	@Override
	public String toString() {
		return "Tournament [id=" + id + ", name=" + name + ", teamsNumber=" + teamsNumber + ", playersNumber="
				+ playersNumber + ", playersInGame=" + playersInGame + ", inscriptionCost=" + inscriptionCost
				+ ", startDate=" + startDate + "]";
	}
}
