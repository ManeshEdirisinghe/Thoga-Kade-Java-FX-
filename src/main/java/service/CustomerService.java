package service;

import javafx.collections.ObservableList;
import model.dto.CustomerDTO;

public interface CustomerService {
    void addCustomerDetails(String id, String name, String address, double salary);
    void deleteCustomerDetails(String id);
    void updateCustomerDetails(String id, String name, String address, double salary);
    ObservableList<CustomerDTO> getAllCustomerDetails();
}