package repository;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T> {

    boolean save(T entity) throws SQLException;

    boolean update(T entity) throws SQLException;

    boolean delete(String id) throws SQLException;

    T findById(String id) throws SQLException;

    List<T> findAll() throws SQLException;
}
