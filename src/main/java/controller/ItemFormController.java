package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.ItemDTO;
import service.ItemService;
import service.impl.ItemServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    private final ItemService itemService = new ItemServiceImpl();

    private ObservableList<ItemDTO> itemDTOS = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colitemCode;

    @FXML
    private TableView<ItemDTO> tblItemView;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtPackSize;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtUnitPrice;

    //--------------------------------------Initialize method------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //load table columns
        colitemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        loadAllItems();

        //set selected row to the fields
        tblItemView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValues) -> {
            if (newValues != null) {
                setSelectedValue(newValues);
            }
        });
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        ItemDTO itemDTO = new ItemDTO(
                txtItemCode.getText(),
                txtDescription.getText(),
                txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText())
        );

        itemService.addItem(itemDTO);
        clearFields();
        loadAllItems();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        itemService.deleteItem(txtItemCode.getText());
        clearFields();
        loadAllItems();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        ItemDTO itemDTO = new ItemDTO(
                txtItemCode.getText(),
                txtDescription.getText(),
                txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQtyOnHand.getText())
        );

        itemService.updateItem(itemDTO);
        clearFields();
        loadAllItems();
    }

    //--------------------------------------All methods------------------------------------
    private void loadAllItems() {
        itemDTOS.clear();
        itemDTOS.addAll(itemService.getAllItems());
        tblItemView.setItems(itemDTOS);
    }

    //set selected row data to the fields
    private void setSelectedValue(ItemDTO selectedValue) {
        if (selectedValue == null) {
            clearFields();
            return;
        }
        txtItemCode.setText(selectedValue.getItemCode());
        txtDescription.setText(selectedValue.getDescription());
        txtPackSize.setText(selectedValue.getPackSize());
        txtUnitPrice.setText(String.valueOf(selectedValue.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(selectedValue.getQtyOnHand()));
    }

    //clear all fields method
    private void clearFields() {
        txtItemCode.clear();
        txtDescription.clear();
        txtPackSize.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
    }
}
