package DAO;

import java.sql.SQLException;
import java.util.List;

import MyArrayList.MyArrayList;
import models.User;

public interface UserCrud<T> {
    public void create(T t) throws SQLException;

    public void save(T t) throws SQLException;

    public T getByID(int id) throws SQLException;
    public MyArrayList<T> getAll() throws SQLException;

    public void deleteByID(int id) throws SQLException;
}
