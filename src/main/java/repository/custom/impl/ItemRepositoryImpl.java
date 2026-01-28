package repository.custom.impl;

import db.DBConnection;
import model.entity.Item;
import repository.custom.ItemRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {

    @Override
    public boolean save(Item entity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String SQL = "INSERT INTO item VALUES(?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1, entity.getItemCode());
        preparedStatement.setString(2, entity.getDescription());
        preparedStatement.setString(3, entity.getPackSize());
        preparedStatement.setDouble(4, entity.getUnitPrice());
        preparedStatement.setInt(5, entity.getQtyOnHand());

        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean update(Item entity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String SQL = "UPDATE item SET Description = ?, PackSize = ?, UnitPrice = ?, QtyOnHand = ? WHERE ItemCode = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setString(1, entity.getDescription());
        preparedStatement.setString(2, entity.getPackSize());
        preparedStatement.setDouble(3, entity.getUnitPrice());
        preparedStatement.setInt(4, entity.getQtyOnHand());
        preparedStatement.setString(5, entity.getItemCode());

        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String SQL = "DELETE FROM item WHERE ItemCode = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, id);

        return preparedStatement.executeUpdate() > 0;
    }

    @Override
    public Item findById(String id) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String SQL = "SELECT * FROM item WHERE ItemCode = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new Item(
                    resultSet.getString("ItemCode"),
                    resultSet.getString("Description"),
                    resultSet.getString("PackSize"),
                    resultSet.getDouble("UnitPrice"),
                    resultSet.getInt("QtyOnHand")
            );
        }
        return null;
    }

    @Override
    public List<Item> findAll() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String SQL = "SELECT * FROM item";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Item> items = new ArrayList<>();

        while (resultSet.next()) {
            items.add(new Item(
                    resultSet.getString("ItemCode"),
                    resultSet.getString("Description"),
                    resultSet.getString("PackSize"),
                    resultSet.getDouble("UnitPrice"),
                    resultSet.getInt("QtyOnHand")
            ));
        }
        return items;
    }
}
