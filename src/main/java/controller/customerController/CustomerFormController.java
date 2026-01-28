package controller.customerController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.CustomerDTO;
import service.CustomerService;
import service.CustomerServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    CustomerService customerService = new CustomerServiceImpl();

    @FXML private TableColumn<?, ?> colAddress, colId, colName, colSalary;
    @FXML private TableView<CustomerDTO> tblCustomerView;
    @FXML private TextField txtAddress, txtId, txtName, txtSalary;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        loadAllCustomers();

        tblCustomerView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setSelectedValue(newValue);
            }
        });
    }

    private void setSelectedValue(CustomerDTO customer) {
        txtId.setText(customer.getId());
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtSalary.setText(String.valueOf(customer.getSalary()));
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        customerService.addCustomerDetails(txtId.getText(), txtName.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText()));
        clearFields();
        loadAllCustomers();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        customerService.deleteCustomerDetails(txtId.getText());
        clearFields();
        loadAllCustomers();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        customerService.updateCustomerDetails(txtId.getText(), txtName.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText()));
        clearFields();
        loadAllCustomers();
    }

    private void loadAllCustomers() {
        tblCustomerView.setItems(customerService.getAllCustomerDetails());
    }

    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtSalary.clear();
    }
}