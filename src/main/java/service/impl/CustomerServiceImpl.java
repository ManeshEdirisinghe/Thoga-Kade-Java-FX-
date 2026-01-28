package service.impl;

import model.dto.CustomerDTO;
import model.entity.Customer;
import repository.custom.CustomerRepository;
import repository.custom.impl.CustomerRepositoryImpl;
import service.custom.CustomerService;
import java.sql.SQLException;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository = new CustomerRepositoryImpl(); [cite: 116]

    @Override
    public boolean addCustomer(CustomerDTO dto) {
        try {
            // DTO to Entity conversion [cite: 120, 154]
            return customerRepository.save(new Customer(
                    dto.getId(), dto.getTitle(), dto.getName(), dto.getDob(),
                    dto.getSalary(), dto.getAddress(), dto.getCity(),
                    dto.getProvince(), dto.getPostalCode()
            ));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}