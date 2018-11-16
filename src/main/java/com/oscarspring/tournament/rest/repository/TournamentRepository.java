package com.oscarspring.tournament.rest.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.oscarspring.tournament.rest.entity.Tournament;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Serializable>, PagingAndSortingRepository<Tournament, Serializable> {
	
	public abstract Tournament findById(long tournamentId);

}
