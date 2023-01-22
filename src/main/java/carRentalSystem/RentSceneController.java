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

public class RentSceneController implements Initializable {
    ObservableList<CarsConstructor> listM1;
    ObservableList<CustumersConstructor> listM2;
    int index = -1;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    @FXML
    private Button cancelButton;

    @FXML
    private TextField idCarTextField;

    @FXML
    private TextField idCustomerTextField;

    @FXML
    private TableView<CarsConstructor> tableCars;

    @FXML
    private TableColumn<CarsConstructor, Integer> tableColumnCarID;

    @FXML
    private TableColumn<CarsConstructor, String> tableColumnClass;

    @FXML
    private TableColumn<CarsConstructor, String> tableColumnColor;

    @FXML
    private TableColumn<CustumersConstructor, Integer> tableColumnCustomerID;

    @FXML
    private TableColumn<CustumersConstructor, String> tableColumnFirstName;

    @FXML
    private TableColumn<CustumersConstructor, String> tableColumnLastName;

    @FXML
    private TableColumn<CarsConstructor, String> tableColumnModel;

    @FXML
    private TableColumn<CustumersConstructor, String> tableColumnNumber;

    @FXML
    private TableColumn<CarsConstructor, String> tableColumnRented;

    @FXML
    private TableColumn<CarsConstructor, String> tableColumnSeries;

    @FXML
    private TableView<CustumersConstructor> tableCustomers;

    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void showCars() {
        tableColumnCarID.setCellValueFactory(new PropertyValueFactory<CarsConstructor, Integer>("carId"));
        tableColumnSeries.setCellValueFactory(new PropertyValueFactory<CarsConstructor, String>("series"));
        tableColumnModel.setCellValueFactory(new PropertyValueFactory<CarsConstructor, String>("model"));
        tableColumnColor.setCellValueFactory(new PropertyValueFactory<CarsConstructor, String>("color"));
        tableColumnClass.setCellValueFactory(new PropertyValueFactory<CarsConstructor, String>("clas"));
        tableColumnRented.setCellValueFactory(new PropertyValueFactory<CarsConstructor, String>("rented"));
        listM1 = DatabaseConnection.getDataCars();
        tableCars.setItems(listM1);
    }

    public void showCustomers() {
        tableColumnCustomerID.setCellValueFactory(new PropertyValueFactory<CustumersConstructor, Integer>("customerId"));
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<CustumersConstructor, String>("firstName"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<CustumersConstructor, String>("lastName"));
        tableColumnNumber.setCellValueFactory(new PropertyValueFactory<CustumersConstructor, String>("number"));
        listM2 = DatabaseConnection.getDataCustomers();
        tableCustomers.setItems(listM2);
    }
    public void rent(){
        connection = DatabaseConnection.getConnection();
        String sql = "Update cars set rented = ? where IdCar = ?";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, idCustomerTextField.getText());
            preparedStatement.setString(2, idCarTextField.getText());
            preparedStatement.execute();
            showCars();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getSelectedCar(javafx.scene.input.MouseEvent mouseEvent) {
        index = tableCars.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        idCarTextField.setText(tableColumnCarID.getCellData(index).toString());
    }
    public void getSelectedCustomer(javafx.scene.input.MouseEvent mouseEvent) {
        index = tableCustomers.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        idCustomerTextField.setText(tableColumnCustomerID.getCellData(index).toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCars(); showCustomers();
    }
}

