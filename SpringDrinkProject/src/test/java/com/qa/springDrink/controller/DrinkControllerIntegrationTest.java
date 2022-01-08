package com.qa.springDrink.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.springDrink.domain.Drink;


@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:springDrink-schema.sql", "classpath:springDrink-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")


public class DrinkControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired ObjectMapper mapper;
	
	private final Drink TestDrink = new Drink ("Apple", "Fruit-co", 25, "sweet", "colourless");
	
	private final Drink TestSavedDrink = new Drink("Apple", "Fruit-co", 25, "sweet", "colourless");
	
	@Test
	public void createTest() throws Exception {
		
		Drink entry = new Drink("Apple", "Fruit-co", 25, "sweet", "colourless");
		Drink result = new Drink(2L, "Apple", "Fruit-co", 25, "sweet", "colourless");
		
		String entryAsJSON = this.mapper.writeValueAsString(entry);
		String resultAsJSON = this.mapper.writeValueAsString(result);
		
		mvc.perform(post("/drink/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(entryAsJSON))
				.andExpect(status().isCreated())
				.andExpect(content().json(resultAsJSON));
		
		
	}
	
	@Test
	public void readByIdTest() throws Exception {
		
		mvc.perform(get("/drink/readById/1"))
				.andExpect(status().isOk())
				.andExpect(content().json(this.mapper.writeValueAsString(TestSavedDrink)));
		
		}
	
	@Test
	public void readAllTest() throws Exception {
		
		final List<Drink> AllDrinks = new ArrayList<>();
		
		AllDrinks.add(TestSavedDrink);
		
		final String resultString = mvc.perform(request(HttpMethod.GET, "/drink/readAll")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		List<Drink> returned = Arrays.asList(mapper.readValue(resultString, Drink[].class));
		assertThat(returned).contains(this.TestDrink).hasSize(1);
	
	
	}
	
	@Test
	public void UpdateTest() throws Exception {
        final Drink newDrink = new Drink("Apple", "Fruit-co", 25, "sweet", "colourless");
        String resultString = this.mvc
                .perform(put("/drink/update/1").contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(newDrink)))
                .andExpect(status().isAccepted()).andReturn().getRequest().getContentAsString();

        Drink returned = this.mapper.readValue(resultString, Drink.class);
        assertThat(returned).isEqualTo(newDrink);
    }
	
	 @Test
	    public void DeleteTest() throws Exception {
	        this.mvc.perform(delete("/drink/delete/1")).andExpect(status().isNoContent());
	    }
	


}
