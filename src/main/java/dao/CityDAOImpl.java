package dao;

import db.objects.City;
import db.objects.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CityDAOImpl implements CityDAO {

    public CityDAOImpl() {

    }

    @Override
    public void create(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory()
                .openSession();){
            Transaction transaction = session.beginTransaction();
            session.save(city);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public City readById(int id) {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .get(City.class, id);
    }

    @Override
    public List readAll() {
        List cities = (List)  HibernateSessionFactoryUtil
                .getSessionFactory().openSession().createQuery("From City ").list();
        return cities;
    }

    @Override
    public void updateName(City city) {

        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(city);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(City city) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(city);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
