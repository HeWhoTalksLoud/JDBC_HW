package dao;

import db_objects.City;
import db_objects.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CityDAOImpl implements CityDAO {
    private Connection connection;

    public CityDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(City city) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO city (city_name) " +
                        "VALUES ((?))"
        )) {
            statement.setString(1, city.getName());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public City readById(int id) {
        City city = new City();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT *  FROM city WHERE id = (?)"
        )) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                city.setId(Integer.parseInt(resultSet.getString("id")));
                city.setName(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public List<City> readAll() {
        List<City> cityList = new LinkedList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM city "
        )) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString("id"));
                String name = resultSet.getString("name");

                cityList.add(new City(id, name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cityList;
    }

    @Override
    public void updateNameById(int id, String name) {

        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE city SET city_name = (?) WHERE id = (?)"
        )) {
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteById(int id) {

        try(PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM city WHERE id=(?)")) {

            statement.setInt(1, id);
            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
