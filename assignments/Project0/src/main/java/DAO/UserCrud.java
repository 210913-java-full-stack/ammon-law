package DAO;

import java.sql.SQLException;

import MyArrayList.MyArrayList;

//says user crud, but it's an interface used for all the dao
public interface UserCrud<T> {
    public void create(T t) throws SQLException;

    public void save(T t) throws SQLException;

    public T getByID(int id) throws SQLException;
    public MyArrayList<T> getAll() throws SQLException;

    public void deleteByID(int id) throws SQLException;
}
