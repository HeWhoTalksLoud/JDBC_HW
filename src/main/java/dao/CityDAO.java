package dao;

import db_objects.City;
import db_objects.Employee;

import java.util.List;

public interface CityDAO {
    // Добавление объекта
    void create(City city);
    // Получение объекта по id
    City readById(int id);
    // Получение всех объектов
    List<City> readAll();
    // Изменение объекта по id
    void updateNameById(int id, String name);
    // Удаление объекта по id
    void deleteById(int id);
}
