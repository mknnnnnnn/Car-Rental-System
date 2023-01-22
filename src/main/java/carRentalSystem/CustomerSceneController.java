package carRentalSystem;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class CustomerSceneController implements Initializable {

    ObservableList<CustumersConstructor> listM;
    int index = -1;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField numberTextField;
    @FXML
    private TableColumn<CustumersConstructor, Integer> tableColumnCustomerID;
    @FXML
    private TableColumn<CustumersConstructor, String> tableColumnFirstName;
    @FXML
    private TableColumn<CustumersConstructor, String> tableColumnLastName;
    @FXML
    private TableColumn<CustumersConstructor, String> tableColumnNumber;
    @FXML
    private TableView<CustumersConstructor> tableCustomers;

    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void addCustomers() {
        connection = DatabaseConnection.getConnection();
        String sql = "insert into customers (firstName, lastName, number) values (?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, firstNameTextField.getText());
            preparedStatement.setString(2, lastNameTextField.getText());
            preparedStatement.setString(3, numberTextField.getText());
            preparedStatement.execute();
            updateCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteCustomers() {
        connection = DatabaseConnection.getConnection();
        String sql = "delete from customers where idCustomer = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tableColumnCustomerID.getCellData(index).toString());
            preparedStatement.execute();
            updateCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void editCustomers() {
        try {
            connection = DatabaseConnection.getConnection();
            String value1 = tableColumnCustomerID.getCellData(index).toString();
            String value2 = firstNameTextField.getText();
            String value3 = lastNameTextField.getText();
            String value4 = numberTextField.getText();
            String sql = "update customers set idCustomer= '" + value1 + "',firstName= '" + value2 + "',lastName= '" + value3 + "',number= '" + value4 + "' where idCustomer='" + value1 + "' ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            updateCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateCustomers() {
        tableColumnCustomerID.setCellValueFactory(new PropertyValueFactory<CustumersConstructor, Integer>("customerId"));
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<CustumersConstructor, String>("firstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<CustumersConstructor, String>("lastName"));
        tableColumnNumber.setCellValueFactory(new PropertyValueFactory<CustumersConstructor, String>("number"));
        listM = DatabaseConnection.getDataCustomers();
        tableCustomers.setItems(listM);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateCustomers();
    }

    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {
        index = tableCustomers.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        firstNameTextField.setText(tableColumnFirstName.getCellData(index));
        lastNameTextField.setText(tableColumnLastName.getCellData(index));
        numberTextField.setText(tableColumnNumber.getCellData(index));
    }
}
