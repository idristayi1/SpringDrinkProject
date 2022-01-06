package com.qa.springDrink.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.springDrink.domain.Drink;
import com.qa.springDrink.service.DrinkService;


@RunWith(SpringRunner.class)
@WebMvcTest

public class DrinkControllerUnitTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private DrinkService service;
	
	@Test
	public void createTest() throws Exception {
		Drink drink = new Drink("Pepsi", "Pepsi-co", 33, "Citrus", "Black");
		
		String entryAsJSON = this.mapper.writeValueAsString(drink);
		
		Mockito.when(this.service.create(drink)).thenReturn(drink);
		
		mvc.perform(post("/drink/create")
		.contentType(MediaType.APPLICATION_JSON)
		.content(entryAsJSON))
		.andExpect(status().isCreated())
		.andExpect(content().json(entryAsJSON));
		
		}
	
	
	@Test
	public void getAllTest() throws Exception {
		
		List<Drink> availableDrinks = new ArrayList();
			
		Drink firstDrink = new Drink("Pepsi", "Pepsi-co", 33, "Citrus", "Black");
		Drink secondDrink = new Drink("Fanta", "Coca-cola", 50, "Orange", "Yellow");
	
		Mockito.when(this.service.getAll()).thenReturn(availableDrinks);
		
		
		mvc.perform(get("/drink/readAll"))
				.andExpect(status().isOk());

				
		
	}

	@Test
	public void getByIdTest() throws Exception{
		
		long id = 1;
		Drink firstDrink = new Drink(1L, "Pepsi", "Pepsi-co", 33, "Citrus", "Black");
		
			
		Mockito.when(this.service.getById(id)).thenReturn(firstDrink);
		
		mvc.perform(get("/drink/readById/1", 1))
					.andExpect(status().isOk());
	
		
	
	}
	
}