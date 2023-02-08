import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import db.objects.Employee;

public class Application {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();

        Employee employee = new Employee("Lars", "von Trier", "M",
                68, 2);

        employeeDAO.create(employee);
        System.out.println(employeeDAO.readById(2));
        employeeDAO.readAll().forEach(System.out::println);

        employee.setAge(69);
        employeeDAO.updateAge(employee);
        employeeDAO.readAll().forEach(System.out::println);

        employeeDAO.delete(employee);
        employeeDAO.readAll().forEach(System.out::println);

    }

}