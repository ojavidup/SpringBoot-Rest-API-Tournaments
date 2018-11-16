package com.oscarspring.tournament.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oscarspring.tournament.rest.entity.Tournament;
import com.oscarspring.tournament.rest.repository.TournamentRepository;

@Service
public class TournamentServiceImpl implements TournamentService {
	
	@Autowired
	private TournamentRepository tournamentRepository;

//	@Override
//	@Transactional
//	public List<Tournament> getTournaments() {
//		
//		return tournamentRepository.findAll();
//	}

	@Override
	@Transactional
	public Tournament getTournamentById(long tournamentId) {

		return tournamentRepository.findById(tournamentId);
	}
	
	public Tournament saveTournament(Tournament tournament) {
		
		return tournamentRepository.save(tournament);
	}

	@Override
	@Transactional
	public void deleteTournament(Tournament tempTournament) {
		
		tournamentRepository.delete(tempTournament);
	}

	@Override
	@Transactional
	public List<Tournament> getTournamentsPage(Pageable pageable) {
		
		return tournamentRepository.findAll(pageable).getContent();
	}

}
