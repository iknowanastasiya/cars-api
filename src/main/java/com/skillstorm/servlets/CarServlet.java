package com.skillstorm.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.bean.Car;
import com.skillstorm.data.CarDAO;

@WebServlet(urlPatterns = "/cars")
public class CarServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CarDAO dao = new CarDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("this is param " +req.getParameter("vin"));
		if (req.getParameter("vin") != null) {
			
			Car car = null;
			try {
			dao.findByVin(req.getParameter("vin"));
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			String json = new ObjectMapper().writeValueAsString (car);
			System.out.println(json);
			
			resp.getWriter().print(json);
			
		} else {
			List <Car> cars = dao.findAll();
			String json =  new ObjectMapper().writeValueAsString (cars);
			
			System.out.println("All cars " +json);
			resp.getWriter().print(json);
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		InputStream requestBody = req.getInputStream();
		Car car = new ObjectMapper().readValue(requestBody, Car.class);
		System.out.println(car);
		Car newCar = null;
		try {
			newCar = dao.create(car);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resp.getWriter().print(new ObjectMapper().writeValueAsString(newCar));
		resp.setStatus(201);
		resp.setContentType("application/json");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//InputStream requestBody = req.getInputStream();
	//	Car car = new ObjectMapper().readValue(requestBody, Car.class);
	//	System.out.println("Object from resp "+ car);

	//	String vin = req.getParameter("vin");
	//	System.out.println(vin);
		if (req.getParameter("vin") != null) {
			String colorN = req.getParameter("color");
			Double costN = Double.parseDouble(req.getParameter("cost"));

			String vinN = req.getParameter("vin");

		
			try {
				dao.update(colorN, costN, vinN);
				System.out.println(colorN);
				System.out.println(costN);
				System.out.println(vinN);

				
				resp.setStatus(200);
				//resp.setContentType("application/json");

				
				System.out.println("Car updated");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		}else {
			System.out.println("Invalid Request");
			resp.setStatus(400);
		}

		
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("vin") != null) {
			String vin = req.getParameter("vin");
	System.out.println("delete vin "+ vin);
			
	try {dao.delete(vin);}
	catch (SQLException e) {e.printStackTrace(); }
			resp.setStatus(200);

		}
		else {
			System.out.println("Invalid Request");
			resp.setStatus(400);
		}
	}
}
