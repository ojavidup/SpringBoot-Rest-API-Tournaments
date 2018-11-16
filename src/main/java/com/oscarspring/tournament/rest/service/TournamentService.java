package com.oscarspring.tournament.rest.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.oscarspring.tournament.rest.entity.Tournament;

public interface TournamentService {
	
//	public List<Tournament> getTournaments();
	
	public List<Tournament> getTournamentsPage(Pageable pageable);

	public Tournament getTournamentById(long tournamentId);

	public Tournament saveTournament(Tournament tournament);

	public void deleteTournament(Tournament tempTournament);

}
