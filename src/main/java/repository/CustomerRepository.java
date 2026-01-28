package repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerRepository {
    ResultSet getAll() throws SQLException;
    void addCustomer(String id, String name, String address, double salary);
    void deleteCustomer(String id);
    void updateCustomer(String id, String name, String address, double salary);
}