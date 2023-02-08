package dao;

import db.objects.Employee;

import java.util.List;

public interface EmployeeDAO {
    // Добавление объекта
    void create(Employee employee);
    // Получение объекта по id
    Employee readById(int id);
    // Получение всех объектов
    List readAll();
    // Изменение объекта по id
    void updateAge(Employee employee);
    // Удаление объекта по id
    void delete(Employee employee);
}
