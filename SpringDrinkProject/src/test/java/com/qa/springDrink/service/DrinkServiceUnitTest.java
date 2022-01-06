package com.qa.springDrink.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.springDrink.domain.Drink;
import com.qa.springDrink.repo.DrinkRepo;


@SpringBootTest
public class DrinkServiceUnitTest {
	
	
// Autowiring mocks into the service class
	@Autowired
	private DrinkService service;
	
	//Mocking the repository class so as not to directly depend on the repo class itself

	@MockBean
	private DrinkRepo repo;
	
	@Test
	public void createTest() {
		Drink input = new Drink("Fanta", "Coca-cola", 50, "Orange", "Yellow");
		Drink output = new Drink(1L,"Fanta", "Coca-cola", 50, "Orange", "Yellow");
	
	//Testing the actual method (within the create method from DrinkService)
	Mockito.when(this.repo.save(input)).thenReturn(output);
	
	//Checking the expected value (output) is the same as the actual value (in the method itself)
	assertEquals(output, this.service.create(input));
	
	
	//Verifying that repo is run 1 time, and that it saves the input
	Mockito.verify(this.repo, Mockito.times(1)).save(input);
	}
	
	
	@Test
	public void getAllTest() {
		
		List<Drink> drinks = new ArrayList();
			
		Drink firstDrink = new Drink("Fanta", "Coca-cola", 50, "Orange", "Yellow");
		Drink secondDrink = new Drink("Pepsi", "Pepsi-co", 33, "Citrus", "Black");
		
	//Testing the actual method i.e. the getAll method from DrinkService
		Mockito.when(this.repo.findAll()).thenReturn(drinks);
		
		
	//Here we are checking the expected value (output) is the same as the actual value (method itself)		
		List<Drink> expected = this.service.getAll();
		
		assertEquals(expected, drinks);
		
	}

	@Test
	public void getByIdTest() {
		
		long id = 1;
		Drink firstDrink = new Drink(1l,"Fanta", "Coca-cola", 50, "Orange", "Yellow");

		
	//Testing the actual method i.e. the getAll method from DrinkService
		
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(firstDrink));
		
		Optional<Drink> expected = Optional.of(this.service.getById(id));
	
	//Checking the expected value is not null
		
		assertNotNull(this.service.getById(id));
		
		
	
	}
	
	@Test
	public void updateTest() {
		
		long id = 1;
		
		Drink firstDrink = new Drink(1L, "Fanta", "Coca-cola", 50, "Orange", "Yellow");
		firstDrink.setVolume(33);
		firstDrink.setColour("Strawberry");
		
		Drink updatedFirstDrink = new Drink(1L, "Fanta", "Coca-cola", 33, "Orange", "Strawberry");
		
		Mockito.when(this.repo.saveAndFlush(firstDrink)).thenReturn(updatedFirstDrink);
		
		assertEquals(updatedFirstDrink.getVolume(),firstDrink.getVolume());
		assertEquals(updatedFirstDrink.getColour(),firstDrink.getColour());
			
		
	}
	
	@Test
	public void deleteTest() {
		
		long id = 1L;
		Drink firstDrink = new Drink(1L, "Fanta", "Coca-cola", 50, "Orange", "Yellow");
		
//Mockito.when(this.repo.deleteById(id)).thenReturn(this.service.getById(id).equals(null));

		
		this.service.delete(id);
		this.service.delete(id);
		
		Mockito.verify(this.repo, Mockito.times(2)).deleteById(id);
		

		
		
	}
}