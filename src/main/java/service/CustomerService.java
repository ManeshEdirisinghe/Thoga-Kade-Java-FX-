package service;

import javafx.collections.ObservableList;
import model.dto.CustomerDTO;

public interface CustomerService {
    boolean addCustomer(CustomerDTO customerDTO);
    boolean updateCustomer(CustomerDTO customerDTO);
    boolean deleteCustomer(String customerId);
    ObservableList<CustomerDTO> getAllCustomers();
}