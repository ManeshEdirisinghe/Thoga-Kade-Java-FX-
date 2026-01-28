package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.CustomerDTO;
import repository.CustomerRepository;
import repository.CustomerRepositoryImpl;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository = new CustomerRepositoryImpl();

    @Override
    public void addCustomerDetails(String id, String name, String address, double salary) {
        customerRepository.addCustomer(id, name, address, salary);
    }

    @Override
    public void deleteCustomerDetails(String id) {
        customerRepository.deleteCustomer(id);
    }

    @Override
    public void updateCustomerDetails(String id, String name, String address, double salary) {
        customerRepository.updateCustomer(id, name, address, salary);
    }

    @Override
    public ObservableList<CustomerDTO> getAllCustomerDetails() {
        ObservableList<CustomerDTO> customerList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = customerRepository.getAll();
            while (resultSet.next()) {
                customerList.add(new CustomerDTO(
                        resultSet.getString("CustID"),
                        resultSet.getString("CustName"),
                        resultSet.getString("CustAddress"),
                        resultSet.getDouble("Salary")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }
}