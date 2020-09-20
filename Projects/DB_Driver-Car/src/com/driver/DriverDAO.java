package com.driver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DriverDAO {
    private Connection connection;

    public DriverDAO(Connection connection) {
        this.connection = connection;
    }
    public Optional<Driver> findById(Long id) throws SQLException {
        ResultSet driversSet = connection.createStatement().executeQuery(String.format("select * from driver where id = %d", id));
        ResultSet carsSet = connection.createStatement().executeQuery(String.format("select * from car where driver_id = %d", id));
        List<Car> cars = new ArrayList<>();
        while (carsSet.next()){
            cars.add(new Car(carsSet.getLong("id"),
                    carsSet.getString("model"),
                    carsSet.getString("color"),
                    null));
        }        if (!driversSet.next()) return Optional.empty();

        Driver driver = new Driver(
                driversSet.getLong("id"),
                driversSet.getString("first_name"),
                driversSet.getString("last_name"),
                driversSet.getInt("age"),
                null);
        driver.setCars(cars);
        for (Car car: cars
             ) {
            car.setDriver(driver);
        }
        return Optional.of(driver);
    }
}
