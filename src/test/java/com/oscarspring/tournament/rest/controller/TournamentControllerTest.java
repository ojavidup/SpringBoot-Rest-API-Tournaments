package com.oscarspring.tournament.rest.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hibernate.criterion.Example;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.oscarspring.tournament.rest.entity.Tournament;
import com.oscarspring.tournament.rest.service.TournamentService;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value=TournamentController.class, secure=false)
//@AutoConfigureMockMvc
//@SpringBootTest
public class TournamentControllerTest{
	
	
	@Autowired
	private MockMvc mockMvc;
	
//	@InjectMocks
//	TournamentController tournamentController;
	
	@MockBean
	TournamentService tournamentService;
	
	java.sql.Date date = new java.sql.Date(10-11-2018);
	
//	Tournament testTournament1 = new Tournament("la ter", 10, 9, 8, 20, date);
//	Tournament testTournament2 = new Tournament("la ter2", 10, 9, 8, 20, date);
	
	Tournament mockTournament = new Tournament("la ter", 10, 9, 8, 20, date);
	List<Tournament> allTournaments = Arrays.asList(mockTournament);
	
//	String exampleTournamentJason = "{\"name\":\"Torneo\",\"teamsNumber\":\"1\", \"playersNumber\":\"1\", \"playersNumber\":\1\, \"inscriptionCost\":\10\, \"startDate\":\'2018-11-28\'}"

	
//	@Before
//	public void setUp() throws Exception{
//		mockMvc = MockMvcBuilders.standaloneSetup(tournamentController).build();
//	}
	

	@Test
	public void testGetTournaments() throws Exception{
		
//		System.out.println("==================>>>>"+allTournaments);
//		
		Mockito.when(tournamentService.getTournamentsPage(Mockito.any())).thenReturn(allTournaments);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/tournaments/").accept(MediaType.APPLICATION_JSON);
		
		String mvcResult = mockMvc.perform(requestBuilder).andReturn().getResponse().getContentAsString();
		

		
		String expected = "[{\"id\":0,\"name\":\"la ter\",\"teamsNumber\":10,\"playersNumber\":9,\"playersInGame\":8,\"inscriptionCost\":20.0,\"startDate\":\"1969-12-31\",\"idField\":null}]";
		
		System.out.println("====================>>>>>>"+mvcResult);
		System.out.println("====================>>>>>>"+expected);
		
		
		JSONAssert.assertEquals(expected, mvcResult, false);
//		
//		System.out.println("==========="+testTournament1.getName());
//		
//		this.mockMvc.perform(get("/v1/tournaments")
//				.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$[0].name", is(testTournament1.getName())));
		
		
		
		
		
		
		
		
		
//		
//		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/tournaments/{tournamentId}")
//				.accept(MediaType.APPLICATION_JSON)).andReturn();
//		
//		System.out.println("=============="+mvcResult.getResponse());
//		
//		Mockito.verify(tournamentService).getTournaments();


	}

}
