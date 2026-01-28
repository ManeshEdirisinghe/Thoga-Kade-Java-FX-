package controller;

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
import service.impl.CustomerServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    // Service Layer eka connect kirima [cite: 72, 116]
    private final CustomerService customerService = new CustomerServiceImpl();

    @FXML private TableView<CustomerDTO> tblCustomer;
    @FXML private TableColumn<?, ?> colId;
    @FXML private TableColumn<?, ?> colName;
    @FXML private TableColumn<?, ?> colAddress;
    @FXML private TableColumn<?, ?> colSalary;
    @FXML private TableColumn<?, ?> colCity;
    @FXML private TableColumn<?, ?> colProvince;

    @FXML private TextField txtId;
    @FXML private TextField txtTitle;
    @FXML private TextField txtName;
    @FXML private TextField txtDob;
    @FXML private TextField txtSalary;
    @FXML private TextField txtAddress;
    @FXML private TextField txtCity;
    @FXML private TextField txtProvince;
    @FXML private TextField txtPostalCode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Table Columns initialize kirima
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));

        // Table selection listener: Row ekak click karaddi fields fill wenna
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setSelectedValue(newValue);
            }
        });

        loadAllCustomers();
    }

    private void loadAllCustomers() {
        // Service layer eken data ganna
        tblCustomer.setItems(customerService.getAllCustomers());
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            CustomerDTO dto = new CustomerDTO(
                    txtId.getText(), txtTitle.getText(), txtName.getText(),
                    txtDob.getText(), Double.parseDouble(txtSalary.getText()),
                    txtAddress.getText(), txtCity.getText(), txtProvince.getText(),
                    txtPostalCode.getText()
            );

            if (customerService.addCustomer(dto)) {
                loadAllCustomers();
                clearFields();
            }
        } catch (NumberFormatException e) {
            // Salary empty nam ena error eka handle kirima
            System.out.println("Invalid input: Please enter numbers for Salary");
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            CustomerDTO dto = new CustomerDTO(
                    txtId.getText(), txtTitle.getText(), txtName.getText(),
                    txtDob.getText(), Double.parseDouble(txtSalary.getText()),
                    txtAddress.getText(), txtCity.getText(), txtProvince.getText(),
                    txtPostalCode.getText()
            );
            if (customerService.updateCustomer(dto)) {
                loadAllCustomers();
                clearFields();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Salary format");
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (customerService.deleteCustomer(txtId.getText())) {
            loadAllCustomers();
            clearFields();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void setSelectedValue(CustomerDTO customer) {
        txtId.setText(customer.getId());
        txtTitle.setText(customer.getTitle());
        txtName.setText(customer.getName());
        txtDob.setText(customer.getDob());
        txtSalary.setText(String.valueOf(customer.getSalary()));
        txtAddress.setText(customer.getAddress());
        txtCity.setText(customer.getCity());
        txtProvince.setText(customer.getProvince());
        txtPostalCode.setText(customer.getPostalCode());
    }

    private void clearFields() {
        txtId.clear();
        txtTitle.clear();
        txtName.clear();
        txtDob.clear();
        txtSalary.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();
    }
}