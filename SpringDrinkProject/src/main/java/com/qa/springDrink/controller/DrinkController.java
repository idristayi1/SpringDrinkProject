/// CONTROLLER 

package com.qa.springDrink.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.springDrink.domain.Drink;
import com.qa.springDrink.service.DrinkService;

@RestController
@RequestMapping("/drink")		//path from which call will be made at Postman

public class DrinkController {
	
	private DrinkService service;
	
	private DrinkController(DrinkService service) {
		this.service = service;
		
	}
	//Create
	@PostMapping("/create")		//path from which new data will be created/entered at Postman
	public ResponseEntity<Drink> createDrink(@RequestBody Drink drink) {
		return new ResponseEntity<Drink>(this.service.create(drink), HttpStatus.CREATED);
	}
	
	//Read All					
	@GetMapping("/readAll")		//path from which existing data will be read at Postman
	
	public ResponseEntity<List<Drink>> readAllDrink() {
		return new ResponseEntity<List<Drink>>(this.service.getAll(), HttpStatus.OK);
		
	}
	
	//Read By Id
	@GetMapping("/readById/{id}")	//path from which existing data will be read at Postman by id
	
	public ResponseEntity<Drink>getById(@PathVariable long id) {

			return new ResponseEntity<Drink>(this.service.getById(id), HttpStatus.OK);
		}

	//Update
	@PutMapping("/update/{id}")		//path from which existing data will be updated/modified at Postman by id
	
	public ResponseEntity<Drink> updateMain(@PathVariable long id, @RequestBody Drink drink) {

			return new ResponseEntity<Drink>(this.service.update(id, drink), HttpStatus.ACCEPTED);
		}

	
	//Delete
	@DeleteMapping("/delete/{id}")	//path from which existing data can be deleted at Postman by id
	
	public ResponseEntity<Boolean> deleteMain(@PathVariable long id) {
		return (this.service.delete(id)) ? new ResponseEntity<Boolean>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
}

