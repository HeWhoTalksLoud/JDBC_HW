package dao;

import db_objects.City;
import db_objects.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    private Connection connection;

    public EmployeeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Employee employee) {
        int cityId = 0;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT city_id FROM city WHERE city_name = (?)"
        )) {

            statement.setString(1, employee.getCity().getName());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                cityId = rs.getInt("city_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO employees (first_name, last_name, gender, age, city_id) " +
                        "VALUES ((?), (?), (?), (?), (?))"
        )) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, cityId);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Employee readById(int id) {
        Employee employee = new Employee();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * " +
                    "FROM employees " +
                    "INNER JOIN city " +
                    "ON employees.city_id = city.city_id " +
                    "WHERE id = (?)"
        )) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                employee.setId(Integer.parseInt(resultSet.getString("id")));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(Integer.parseInt(resultSet.getString("age")));
                employee.setCity(new City(Integer.parseInt(resultSet
                        .getString("employees.city_id")),
                        resultSet.getString("city_name")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> readAll() {
        List<Employee> employeeList = new LinkedList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * " +
                        "FROM employees " +
                        "INNER JOIN city " +
                        "ON employees.city_id = city.city_id "
        )) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString("id"));
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = Integer.parseInt(resultSet.getString("age"));
                City city = new City(Integer.parseInt(resultSet
                        .getString("city_id")),
                        resultSet.getString("city_name"));

                employeeList.add(new Employee(id, firstName, lastName, gender, age ,city));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    @Override
    public void updateAgeById(int id, int age) {

        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE employees SET age = (?) WHERE id = (?)"
        )) {
            statement.setInt(1, age);
            statement.setInt(2, id);
            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteById(int id) {

        try(PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM employees WHERE id=(?)")) {

            statement.setInt(1, id);
            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
