package repository;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public ResultSet getAll() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        return connection.prepareStatement("SELECT CustID, CustName, CustAddress, Salary FROM customer").executeQuery();
    }

    @Override
    public void addCustomer(String id, String name, String address, double salary) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO customer VALUES(?,?,?,?)");
            pstm.setObject(1, id);
            pstm.setObject(2, name);
            pstm.setObject(3, address);
            pstm.setObject(4, salary);
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

    @Override
    public void updateCustomer(String id, String name, String address, double salary) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE customer SET CustName=?, CustAddress=?, Salary=? WHERE CustID=?");
            pstm.setObject(1, name);
            pstm.setObject(2, address);
            pstm.setObject(3, salary);
            pstm.setObject(4, id);
            pstm.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }
}