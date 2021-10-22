package com.skillstorm.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.skillstorm.bean.Car;

public class CarDAO {

	public final static String url = "jdbc:mysql://localhost:3306/cars-api";
	public final static String username = "root";
	public final static String password = "root4root";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error loading JDBC driver.");
			e.printStackTrace();
		}
	}

	public Car create(Car car) throws SQLException {
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "insert into Car (vin, car_make, car_model, year, color, cost, convertible, sports) values (?, ?, ?, ?, ?, ? ,?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, car.getVin());
			stmt.setString(2, car.getCar_make());
			stmt.setString(3, car.getCar_model());
			stmt.setInt(4, car.getYear());
			stmt.setString(5, car.getColor());
			stmt.setDouble(6, car.getCost());
			if (car.getConvertible() == null){
				stmt.setBoolean(7, false);
			} else {
				stmt.setBoolean(7, true);
			}
			if (car.getSports() == null){
				stmt.setBoolean(8, false);
			} else {
				stmt.setBoolean(8, true);
			}

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return car;
	}

	public Car findByVin(String vin) throws SQLException {

		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			Car car;
			String sql = "select vin, car_make, car_model, year, color, cost, convertible, sports from Car where vin = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, vin);
			ResultSet rs = stmt.executeQuery();
			rs.next();

			car = new Car(rs.getString("vin"), rs.getString("car_make"), rs.getString("car_model"), rs.getInt("year"),
					rs.getString("color"), rs.getDouble("cost"), rs.getBoolean("convertible"), rs.getBoolean("sports"));

			System.out.println(car);
			return car;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Invalid VIN");
			return null;
		}
	}
/*
	public Car findByCarMake(String car_make) {

		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			Car car;
			String sql = "select * from Car where car_make = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, car_make);
			ResultSet rs = stmt.executeQuery();
			rs.next();

			car = new Car(rs.getString("vin"), rs.getString("car_make"), rs.getString("car_model"), rs.getInt("year"),
					rs.getString("color"), rs.getDouble("cost"), rs.getBoolean("convertible"), rs.getBoolean("sports"));

			System.out.println(car);
			return car;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection to DB failed");
			return null;
		}
	}*/
/*
	public Car findByYear(int year) {
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			Car car;
			// String sql = "select vin, car_make, car_model, year, color, cost,
			// convertible, sports from Car where year = ?";
			String sql = "select * from Car where year = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, year);
			ResultSet rs = stmt.executeQuery();
			rs.next();

			car = new Car(rs.getString("vin"), rs.getString("car_make"), rs.getString("car_model"), rs.getInt("year"),
					rs.getString("color"), rs.getDouble("cost"), rs.getBoolean("convertible"), rs.getBoolean("sports"));

			System.out.println(car);
			return car;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection to DB failed");
			return null;
		}

	}*/

	public void delete(String vin) throws SQLException {
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
		//	Car car;
			String sql = "delete from Car where vin = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, vin);
			stmt.executeUpdate();

			System.out.println("Deleted Car with vin " + vin);

		} catch (SQLException e) {
			e.printStackTrace();
			// System.out.println("Connection to DB failed");

		}

	}

	public void update(String color, Double cost, String vin) throws SQLException {
		
		try(Connection conn = DriverManager.getConnection(url, username, password)) {
			//String sql = "update car set car_make = ?, car_model = ?, year = ? color = ?, cost = ?, convertivle = ?, sports = ? where vin = ?";
			String sql = "update Car set color = ?, cost = ? where vin = ?";
			System.out.println("Java sql "+sql);

			PreparedStatement stmt = conn.prepareStatement(sql);
		//	stmt.setString(1, car.getCar_make());
	//		System.out.println("this is car make for update" + car.getCar_make());
		//	stmt.setString(2, car.getCar_model());
			//stmt.setInt(3, car.getYear());
			stmt.setString(1,  color);
			stmt.setDouble(2, cost);
			//stmt.setBoolean(6, car.getConvertible());
		//	stmt.setBoolean(7, car.getSports());
			
			
			//if (car.getConvertible() == null){
		//		stmt.setBoolean(, false);
		//	} else {
		//		stmt.setBoolean(6, true);
			//}
		//	if (car.getSports() == null){
		//		stmt.setBoolean(7, false);
		//	} else {
		//		stmt.setBoolean(7, true);
		//	}
			
			stmt.setString(3, vin);
			//System.out.println(car.toString());
			System.out.println(sql);
			
			stmt.executeUpdate();
	
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	public List<Car> findAll() {

		List<Car> allCars = new LinkedList<>();
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "select vin, car_make, car_model, year, color, cost, convertible, sports from car";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Car car = new Car(rs.getString("vin"), rs.getString("car_make"), rs.getString("car_model"),
						rs.getInt("year"), rs.getString("color"), rs.getDouble("cost"), rs.getBoolean("convertible"),
						rs.getBoolean("sports"));
				allCars.add(car);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allCars;

	}

}
