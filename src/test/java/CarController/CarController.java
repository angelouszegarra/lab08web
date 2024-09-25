package CarController;
 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import CarDAOImpl.CarDAOImpl;
import CarDao.CarDAO;
import DBConfig.DBConfig;
import Car.Car;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/car")
public class CarController extends HttpServlet {
    private CarDAO carDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Connection connection = DBConfig.getConnection();
            carDAO = new CarDAOImpl(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("addCar.jsp").include(request, response); // Include addCar.jsp
    List<Car> cars = carDAO.getAllCars();
    request.setAttribute("cars", cars);
    request.getRequestDispatcher("car.jsp").include(request, response); // Include car.jsp
}

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        int year = Integer.parseInt(request.getParameter("year"));
        
        // Create a new car object
        Car newCar = new Car();
        newCar.setBrand(brand);
        newCar.setModel(model);
        newCar.setYear(year);
        
        // Add the new car to the database
        try {
            carDAO.addCar(newCar);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database error
            // You can redirect the user to an error page or display an error message
            response.sendRedirect("error.jsp");
            return;
        }
        
        // Redirect the user back to the list of cars
        response.sendRedirect("car");
    }
}

