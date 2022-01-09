package com.qa.springDrink.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.springDrink.domain.Drink;

public interface DrinkRepo extends JpaRepository<Drink, Long> {
	
	

}
