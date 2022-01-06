package com.qa.springDrink.service;
import java.util.List;

public interface ServiceMethods <T> {
	
	// CRUD METHODS
	
	//CREATE
	T create (T t);
	
	//Read All
	List<T> getAll ();
	
	//Read By Id
	T getById(long id);
	
	//UPDATE
	T update(long id, T t);
	
	//DELETE
	boolean delete(long id);
}
