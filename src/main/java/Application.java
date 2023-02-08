import dao.CityDAO;
import dao.CityDAOImpl;
import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import db_objects.City;
import db_objects.Employee;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        final String user = "postgres";
        final String pass = "minuscan";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (final Connection connection = DriverManager.getConnection(url, user, pass)) {

            // Задание 1.
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT first_name, last_name, gender, city_name\n" +
                            "FROM employees\n" +
                            "INNER JOIN city\n" +
                            "ON employees.city_id = city.city_id\n" +
                            "WHERE id = (?);"
            );

            // Задаем id.
            statement.setInt(1, 2);

            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String name = "Имя: " + resultSet.getString("first_name");
                String lastName = "Фамилия: " + resultSet.getString("last_name");
                String gender = "Пол: " + resultSet.getString("gender");
                String city = "Город: " + resultSet.getString("city_name");

                System.out.println(name);
                System.out.println(lastName);
                System.out.println(gender);
                System.out.println(city);
            }

            // Задание 2.
            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);
            CityDAO cityDAO = new CityDAOImpl(connection);

            City cityBangkok = new City("Bangkok");
            Employee newEmployee = new Employee("JC", "Denton", "M",
                    29, cityBangkok);

            cityDAO.create(cityBangkok);
            employeeDAO.create(newEmployee);

            List<Employee> employeeList = new LinkedList<>(employeeDAO.readAll());

            employeeList.forEach(System.out::println);

            employeeDAO.deleteById(13);
            System.out.println();
            System.out.println(employeeDAO.readById(1));

            employeeDAO.updateAgeById(2, 120);

            employeeList.forEach(System.out::println);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}