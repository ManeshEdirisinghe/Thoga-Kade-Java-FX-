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

    // Service Layer eka connect kirima [cite: 72, 112, 114]
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

        loadAllCustomers();
    }

    private void loadAllCustomers() {
        // Database eke data table ekata danna
        tblCustomer.setItems(customerService.getAllCustomers());
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        // UI eken data ganna [cite: 83, 85, 89]
        CustomerDTO dto = new CustomerDTO(
                txtId.getText(),
                txtTitle.getText(),
                txtName.getText(),
                txtDob.getText(),
                Double.parseDouble(txtSalary.getText()),
                txtAddress.getText(),
                txtCity.getText(),
                txtProvince.getText(),
                txtPostalCode.getText()
        );

        if (customerService.addCustomer(dto)) {
            loadAllCustomers();
            clearFields();
        }
    }

    // FXML error eka fix karanna me methods aniwaaryayen thiyenna ona
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        // Update logic eka liyanna
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