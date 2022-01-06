package com.qa.springDrink.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class DrinkTest {
	
	@Test
	public void testEquals() {
		EqualsVerifier.forClass(Drink.class).usingGetClass().verify();
	}
	
	@Test
	public void mainContructor() {
		Drink drink = new Drink("Pepsi", "Pepsi-co", 33, "Citrus", "Black");
		
		//checks that when we create an object using our constructor, nothing is created as a null field
		assertNotNull(drink.getName());
		assertNotNull(drink.getMake());
		assertNotNull(drink.getVolume());
		assertNotNull(drink.getTaste());
		assertNotNull(drink.getColour());
		
		//checks that when we create an object using our constructor, the exact object identity is created 
		assertEquals(drink.getName(), "Pepsi");
		assertEquals(drink.getMake(), "Pepsi-co");
		assertEquals(drink.getVolume(), 33);
		assertEquals(drink.getTaste(), "Citrus");
		assertEquals(drink.getColour(), "Black");
		
	}
	@Test
	public void settersTest() {
		
		Drink drink = new Drink();
		
		drink.setName("Pepsi");
		drink.setMake("Pepsi-co");
		drink.setVolume(33);
		drink.setTaste("Citrus");
		drink.setColour("Black");
		
		assertNotNull(drink.getName());
		assertNotNull(drink.getMake());
		assertNotNull(drink.getVolume());
		assertNotNull(drink.getTaste());
		assertNotNull(drink.getColour());
		
		assertEquals(drink.getName(), "Pepsi");
		assertEquals(drink.getMake(), "Pepsi-co");
		assertEquals(drink.getVolume(), 33);
		assertEquals(drink.getTaste(), "Citrus");
		assertEquals(drink.getColour(), "Black");
		
				
	}
	@Test
	public void toStringTest() {
		
		Drink drink = new Drink("Pepsi", "Pepsi-co", 33, "Citrus", "Black");
		
		assertEquals(drink.toString(), "Drink [name=Pepsi, make=Pepsi-co, volume=33, taste=Citrus, colour=Black]");
	}
	


}
