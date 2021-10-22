package com.skillstorm.bean;

import java.util.Objects;

public class Car {

	private String vin;
	private String car_make;
	private String car_model;
	private int year;
	private String color;
	private double cost;
	private Boolean convertible;
	private Boolean sports;
	
	
	public Car() {
		super();
	}


	public Car(String vin, String car_make, String car_model, int year, String color, double cost, Boolean convertible,
			Boolean sports) {
		super();
		this.vin = vin;
		this.car_make = car_make;
		this.car_model = car_model;
		this.year = year;
		this.color = color;
		this.cost = cost;
		this.convertible = convertible;
		this.sports = sports;
	}


	public String getVin() {
		return vin;
	}


	public void setVin(String vin) {
		this.vin = vin;
	}


	public String getCar_make() {
		return car_make;
	}


	public void setCar_make(String car_make) {
		this.car_make = car_make;
	}


	public String getCar_model() {
		return car_model;
	}


	public void setCar_model(String car_model) {
		this.car_model = car_model;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public double getCost() {
		return cost;
	}


	public void setCost(double cost) {
		this.cost = cost;
	}


	public Boolean getConvertible() {
		return convertible;
	}


	public void setConvertible(Boolean convertible) {
		this.convertible = convertible;
	}


	public Boolean getSports() {
		return sports;
	}


	public void setSports(Boolean sports) {
		this.sports = sports;
	}


	@Override
	public int hashCode() {
		return Objects.hash(car_make, car_model, color, convertible, cost, sports, vin, year);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(car_make, other.car_make) && Objects.equals(car_model, other.car_model)
				&& Objects.equals(color, other.color) && Objects.equals(convertible, other.convertible)
				&& Double.doubleToLongBits(cost) == Double.doubleToLongBits(other.cost)
				&& Objects.equals(sports, other.sports) && Objects.equals(vin, other.vin) && year == other.year;
	}


	@Override
	public String toString() {
		return "Car [vin=" + vin + ", car_make=" + car_make + ", car_model=" + car_model + ", year=" + year + ", color="
				+ color + ", cost=" + cost + ", convertible=" + convertible + ", sports=" + sports + "]";
	}
	
		
		

}

