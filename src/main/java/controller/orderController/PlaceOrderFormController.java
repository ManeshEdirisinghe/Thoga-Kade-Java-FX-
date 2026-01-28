package controller.orderController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PlaceOrderFormController {
    // UI eke thiyena fx:id walata galapenna me tika danna
    @FXML private ComboBox<?> cmbCustomerId;
    @FXML private ComboBox<?> cmbItemCode;
    @FXML private Label lblOrderId;
    @FXML private Label lblOrderDate;
    @FXML private Label lblTotal;
    @FXML private TableView<?> tblCart;
    @FXML private TableColumn<?, ?> colCode, colDescription, colQty, colUnitPrice, colTotal;
    @FXML private TextField txtCustomerName, txtDescription, txtQty, txtQtyOnHand, txtUnitPrice;

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
    }
}