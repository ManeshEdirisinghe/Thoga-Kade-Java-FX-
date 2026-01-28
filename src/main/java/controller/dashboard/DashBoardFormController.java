package controller.dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardFormController implements Initializable {

    @FXML private Button btnCustoemerManagement;
    @FXML private Button btnItemManagement;
    @FXML private Button btnOrderDetailsManagement;
    @FXML private Button btnOrderManagement;
    @FXML private Button btnPlaceOrderForm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Hover effects for all navigation buttons
        setHoverEffect(btnCustoemerManagement);
        setHoverEffect(btnItemManagement);
        setHoverEffect(btnOrderManagement);
        setHoverEffect(btnOrderDetailsManagement);
        // Place order button variable ekath add karaganna
    }

    private void setHoverEffect(Button btn) {
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #34495E; -fx-text-fill: white; -fx-alignment: BASELINE_LEFT; -fx-padding: 0 0 0 40;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #BDC3C7; -fx-alignment: BASELINE_LEFT; -fx-padding: 0 0 0 40;"));
    }

    @FXML
    void btnCustoemerManagementOnAction(ActionEvent event) {
        loadWindow("/view/customer.fxml", "Customer Management");
    }

    @FXML
    void btnItemManagementOnAction(ActionEvent event) {
        loadWindow("/view/item.fxml", "Item Management");
    }

    @FXML
    void btnPlaceOrderFormOnAction(ActionEvent event) {
        loadWindow("/view/place_order.fxml", "Place Order Form");
    }

    @FXML
    void btnOrderDetailsManagementOnAction(ActionEvent event) { }

    @FXML
    void btnOrderManagementOnAction(ActionEvent event) { }

    private void loadWindow(String path, String title) {
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(path))));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}