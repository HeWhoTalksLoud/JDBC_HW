package dao;

import db.objects.City;

import java.util.List;

public interface CityDAO {
    // Добавление объекта
    void create(City city);
    // Получение объекта по id
    City readById(int id);
    // Получение всех объектов
    List<City> readAll();
    // Изменение объекта по id
    void updateName(City city);
    // Удаление объекта по id
    void delete(City city);
}
