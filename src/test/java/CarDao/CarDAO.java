package CarDao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Car.Car;
import java.sql.SQLException;
import java.util.List;

public interface CarDAO {
    Car getCarById(int id);
    List<Car> getAllCars();
 void addCar(Car car) throws SQLException;
}

