package controller.customerController;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.CustomerDTO;
import service.CustomerService;
import service.CustomerServiceImpl;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    CustomerService customerService = new CustomerServiceImpl();

    @FXML private TableView<CustomerDTO> tblCustomerView;
    @FXML private TableColumn<?, ?> colId, colTitle, colName, colDob, colSalary, colAddress, colCity, colProvince, colPostalCode;
    @FXML private TextField txtId, txtTitle, txtName, txtDob, txtSalary, txtAddress, txtCity, txtProvince, txtPostalCode;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        loadAllCustomers();

        tblCustomerView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) setSelectedValue(newVal);
        });
    }

    private void setSelectedValue(CustomerDTO c) {
        txtId.setText(c.getId()); txtTitle.setText(c.getTitle()); txtName.setText(c.getName());
        txtDob.setText(c.getDob()); txtSalary.setText(String.valueOf(c.getSalary()));
        txtAddress.setText(c.getAddress()); txtCity.setText(c.getCity());
        txtProvince.setText(c.getProvince()); txtPostalCode.setText(c.getPostalCode());
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        customerService.addCustomerDetails(new CustomerDTO(txtId.getText(), txtTitle.getText(), txtName.getText(), txtDob.getText(), Double.parseDouble(txtSalary.getText()), txtAddress.getText(), txtCity.getText(), txtProvince.getText(), txtPostalCode.getText()));
        loadAllCustomers();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        customerService.updateCustomerDetails(new CustomerDTO(txtId.getText(), txtTitle.getText(), txtName.getText(), txtDob.getText(), Double.parseDouble(txtSalary.getText()), txtAddress.getText(), txtCity.getText(), txtProvince.getText(), txtPostalCode.getText()));
        loadAllCustomers();
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        customerService.deleteCustomerDetails(txtId.getText());
        loadAllCustomers();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void loadAllCustomers() {
        tblCustomerView.setItems(customerService.getAllCustomerDetails());
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

        tblCustomerView.getSelectionModel().clearSelection();
    }
}