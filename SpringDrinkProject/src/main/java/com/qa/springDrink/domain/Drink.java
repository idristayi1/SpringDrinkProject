package com.qa.springDrink.domain;


import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity used to create a Table
@Entity
public class Drink {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

		    
	// @Column as below denotes a column in the table
		    
	@Column(unique = true, nullable = false)
	private String name;


	@Column
	private String make;
		    
	@Column
	private int volume;

	@Column
	private String taste;
		    
	@Column
	private String colour;
	
	
	// Default Constructor
	public Drink() {
		super();
	}

	// Main Drink Constructor
	public Drink(String name, String make, int volume, String taste, String colour) {
		super();
		this.name = name;
		this.make = make;
		this.volume = volume;
		this.taste = taste;
		this.colour = colour;
	}

	
	//Constructor for Testing
	public Drink(long id, String name, String make, int volume, String taste, String colour) {
		super();
		this.id = id;
		this.name = name;
		this.make = make;
		this.volume = volume;
		this.taste = taste;
		this.colour = colour;
	}

	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getTaste() {
		return taste;
	}

	public void setTaste(String taste) {
		this.taste = taste;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
 
	//Hashcode for Comparison
	@Override
	public int hashCode() {
		return Objects.hash(colour, make, name, taste, volume);
	}

	//To-String for presentation
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Drink other = (Drink) obj;
		return Objects.equals(colour, other.colour) && Objects.equals(make, other.make)
				&& Objects.equals(name, other.name) && Objects.equals(taste, other.taste) && volume == other.volume;
	}

	@Override
	public String toString() {
		return "Drink [id=" + id + ", name=" + name + ", make=" + make + ", volume=" + volume + ", taste=" + taste
				+ ", colour=" + colour + "]";
	}

	


}

