package com.skillstorm.bean;

import java.sql.SQLException;

import com.skillstorm.data.CarDAO;

public class CarMain {
	
	public static void main(String[] args) throws SQLException {
		
		
		CarDAO dao = new CarDAO();
		//Car car = new Car("120flhroiwes", "Car", "Car_test", 1990, "red", 19020.00, true, false);
		//car = dao.create(car);
		//System.out.println(car);
	
	
		Car car1;
		Car car2;
		Car car3;
		Car car4;
		
		String car_make= "GMC";
		String vin = "120flhroi";
		int year =2001;
		
	//	car1 = dao.findByYear(year);
	//	car2= dao.findByVin(vin);
		//car3=dao.findByCarMake(car_make);
		
		//dao.delete(vin);
		
		dao.findAll();
		
	//	System.out.println(car1);
	
	//	System.out.println(car2);
	
		//System.out.println(car3);

		
		//System.out.println("*** BEFORE UPDATE ***");
		//List<Movie> movies = dao.findAll();
		//System.out.println(movies);
		
		// updating first record from rating = 4 to rating = 5
	//	Movie newMovie = movies.get(0);
	//	newMovie.setRating(5);
	//	dao.update(newMovie);
	//	
	//	System.out.println("*** AFTER UPDATE ***");
	//	movies = dao.findAll();
	//	System.out.println(movies);
		

	}

}
