package repository;

import model.dto.CustomerDTO;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerRepository {
    ResultSet getAll() throws SQLException;
    void addCustomer(CustomerDTO customer);
    void deleteCustomer(String id);
    void updateCustomer(CustomerDTO customer);
}