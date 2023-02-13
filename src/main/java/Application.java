import dao.CityDAO;
import dao.CityDAOImpl;
import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import db.objects.City;
import db.objects.Employee;

public class Application {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        CityDAO cityDAO = new CityDAOImpl();

        City citySF = new City("San Francisco");
        //cityDAO.create(citySF);

        Employee employee = new Employee("Alex", "Justas", "M",
                45, citySF);

        employeeDAO.create(employee);

        cityDAO.readAll().forEach(System.out::println);
        System.out.println(employeeDAO.readById(2));
        employeeDAO.readAll().forEach(System.out::println);

        employee.setAge(69);
        employeeDAO.updateAge(employee);
        employeeDAO.readAll().forEach(System.out::println);

        //employeeDAO.delete(employee);
        employeeDAO.readAll().forEach(System.out::println);
    }
}