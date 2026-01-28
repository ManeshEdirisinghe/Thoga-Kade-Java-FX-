package service.impl;

import model.dto.CustomerDTO;
import model.entity.Customer;
import repository.custom.CustomerRepository;
import repository.custom.impl.CustomerRepositoryImpl;
import service.CustomerService;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerServiceImpl implements CustomerService {

    // Repository eka interface eken katha karanna [cite: 116]
    private final CustomerRepository customerRepository = new CustomerRepositoryImpl();

    @Override
    public boolean addCustomer(CustomerDTO dto) {
        try {
            // DTO eka Entity ekakata convert karala save karanna [cite: 121, 123]
            // Oyaage database schema eke thiyena columns 9 ma mehema pass karanna
            return customerRepository.save(new Customer(
                    dto.getId(),
                    dto.getTitle(),
                    dto.getName(),
                    dto.getDob(),
                    dto.getSalary(),
                    dto.getAddress(),
                    dto.getCity(),
                    dto.getProvince(),
                    dto.getPostalCode()
            ));
        } catch (SQLException e) {
            // Error eka handle karanna [cite: 124, 127]
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<CustomerDTO> getAllCustomers() {
        // Me thiyenne UI eke table ekata data ganna widiya [cite: 111, 201]
        ObservableList<CustomerDTO> customerDTOS = FXCollections.observableArrayList();
        try {
            for (Customer entity : customerRepository.findAll()) {
                customerDTOS.add(new CustomerDTO(
                        entity.getCustID(), entity.getCustTitle(), entity.getCustName(),
                        entity.getDob(), entity.getSalary(), entity.getCustAddress(),
                        entity.getCity(), entity.getProvince(), entity.getPostalCode()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerDTOS;
    }

    // Anith methods (update, delete) interface ekata anuwa implement karanna [cite: 108, 109]
    @Override public boolean updateCustomer(CustomerDTO dto) { return false; }
    @Override public boolean deleteCustomer(String customerId) { return false; }
}