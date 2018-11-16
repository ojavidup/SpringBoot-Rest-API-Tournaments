package com.oscarspring.tournament.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oscarspring.tournament.rest.entity.Tournament;
import com.oscarspring.tournament.rest.service.TournamentService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/v1")
public class TournamentController {
	
	@Autowired
	private TournamentService tournamentService;
	
	@GetMapping("/tournaments")
	@PreAuthorize("hasAuthority('Admin') or hasAuthority('User')")
	public ResponseEntity<List<Tournament>> getTournaments(Pageable pageable){
		
		return new ResponseEntity<List<Tournament>>(tournamentService.getTournamentsPage(pageable), HttpStatus.OK);	
	}
	
	@GetMapping("/tournaments/{tournamentId}")
	@PreAuthorize("hasAuthority('Visitor')")
	public ResponseEntity<Tournament> getTournamentById(@PathVariable("tournamentId") long tournamentId){
		
		return new ResponseEntity<Tournament>(tournamentService.getTournamentById(tournamentId), HttpStatus.OK);
	}
	
	@PostMapping("/tournaments")
	public ResponseEntity<Tournament> saveTournament(@RequestBody Tournament tournament){
		
		return new ResponseEntity<Tournament>(tournamentService.saveTournament(tournament),HttpStatus.OK);
	}
	
	@PutMapping("/tournaments")
	public ResponseEntity<Tournament> updateTournament(@RequestBody Tournament tournament){
		
		return new ResponseEntity<Tournament>(tournamentService.saveTournament(tournament),HttpStatus.OK);
	}
	
	@DeleteMapping("/tournaments/{tournamentId}")
	public ResponseEntity<String> deleteTournament(@PathVariable("tournamentId") long tournamentId){
		
		Tournament tempTournament = tournamentService.getTournamentById(tournamentId);

		tournamentService.deleteTournament(tempTournament);
		
		return new ResponseEntity<String>("Tournament deleted", HttpStatus.OK);
	}

}
