package repository;

import db.DBConnection;
import model.dto.CustomerDTO; // Me import eka aniwaren thiyenna ona
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public ResultSet getAll() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        return connection.prepareStatement("SELECT * FROM customer").executeQuery();
    }

    @Override
    public void addCustomer(CustomerDTO c) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, c.getId());
            pstm.setObject(2, c.getTitle());
            pstm.setObject(3, c.getName());
            pstm.setObject(4, c.getDob());
            pstm.setObject(5, c.getSalary());
            pstm.setObject(6, c.getAddress());
            pstm.setObject(7, c.getCity());
            pstm.setObject(8, c.getProvince());
            pstm.setObject(9, c.getPostalCode());
            pstm.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public void updateCustomer(CustomerDTO c) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String sql = "UPDATE customer SET CustTitle=?, CustName=?, DOB=?, salary=?, CustAddress=?, City=?, Province=?, PostalCode=? WHERE CustID=?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, c.getTitle());
            pstm.setObject(2, c.getName());
            pstm.setObject(3, c.getDob());
            pstm.setObject(4, c.getSalary());
            pstm.setObject(5, c.getAddress());
            pstm.setObject(6, c.getCity());
            pstm.setObject(7, c.getProvince());
            pstm.setObject(8, c.getPostalCode());
            pstm.setObject(9, c.getId());
            pstm.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public void deleteCustomer(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM customer WHERE CustID=?");
            pstm.setObject(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
}