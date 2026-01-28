package repository.custom.impl;

import db.DBConnection;
import model.entity.Customer;
import repository.custom.CustomerRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public boolean save(Customer entity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, entity.getCustID());
        pstm.setString(2, entity.getCustTitle());
        pstm.setString(3, entity.getCustName());
        pstm.setString(4, entity.getDob());
        pstm.setDouble(5, entity.getSalary());
        pstm.setString(6, entity.getCustAddress());
        pstm.setString(7, entity.getCity());
        pstm.setString(8, entity.getProvince());
        pstm.setString(9, entity.getPostalCode());
        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean update(Customer entity) throws SQLException {
        // Thama liyala nathi nisa danata return false danna
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        return connection.prepareStatement("DELETE FROM Customer WHERE CustID='" + id + "'").executeUpdate() > 0;
    }

    @Override
    public List<Customer> findAll() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        ResultSet rs = connection.prepareStatement("SELECT * FROM Customer").executeQuery();
        List<Customer> customerList = new ArrayList<>();
        while (rs.next()) {
            customerList.add(new Customer(
                    rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getDouble(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9)
            ));
        }
        return customerList;
    }

    // ME METHOD EKA MISSING NISA THAMAI ERROR EKA ENNE
    @Override
    public Customer findById(String id) throws SQLException {
        // Danata null return karanna, passe search logic eka liyamu
        return null;
    }
}