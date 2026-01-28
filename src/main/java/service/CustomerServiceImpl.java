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
    public void addCustomerDetails(CustomerDTO customer) {
        customerRepository.addCustomer(customer);
    }

    @Override
    public void deleteCustomerDetails(String id) {
        customerRepository.deleteCustomer(id);
    }

    @Override
    public void updateCustomerDetails(CustomerDTO customer) {
        customerRepository.updateCustomer(customer); // Meka thamai override wenna ona method eka
    }

    @Override
    public ObservableList<CustomerDTO> getAllCustomerDetails() {
        ObservableList<CustomerDTO> customerList = FXCollections.observableArrayList();
        try {
            ResultSet rs = customerRepository.getAll();
            while (rs.next()) {
                customerList.add(new CustomerDTO(
                        rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getDouble(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }
}