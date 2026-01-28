package service;

import javafx.collections.ObservableList;
import model.dto.CustomerDTO;

public interface CustomerService {
    void addCustomerDetails(CustomerDTO customer);
    void deleteCustomerDetails(String id);
    void updateCustomerDetails(CustomerDTO customer); // Meka check karanna
    ObservableList<CustomerDTO> getAllCustomerDetails();
}