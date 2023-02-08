package dao;

import db_objects.Employee;

import java.util.List;

public interface EmployeeDAO {
    // Добавление объекта
    void create(Employee employee);
    // Получение объекта по id
    Employee readById(int id);
    // Получение всех объектов
    List<Employee> readAll();
    // Изменение объекта по id
    void updateAgeById(int id, int age);
    // Удаление объекта по id
    void deleteById(int id);
}
