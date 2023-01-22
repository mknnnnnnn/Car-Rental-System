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

public class CarSceneController implements Initializable {

    ObservableList<CarsConstructor> listM;
    int index = -1;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    @FXML
    private TableView<CarsConstructor> tableCars;
    @FXML
    private TableColumn<CarsConstructor, Integer> tableColumnCarID;
    @FXML
    private TableColumn<CarsConstructor, String> tableColumnClass;
    @FXML
    private TableColumn<CarsConstructor, String> tableColumnColor;
    @FXML
    private TableColumn<CarsConstructor, String> tableColumnModel;
    @FXML
    private TableColumn<CarsConstructor, String> tableColumnRented;
    @FXML
    private TableColumn<CarsConstructor, String> tableColumnSeries;
    @FXML
    private TextField classTextField;
    @FXML
    private TextField colorTextField;
    @FXML
    private TextField modelTextField;
    @FXML
    private TextField rentedTextField;
    @FXML
    private TextField seriesTextField;
    @FXML
    private Button cancelButton;

    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void addCars() {
        connection = DatabaseConnection.getConnection();
        String sql = "insert into cars (series, model, color, class, rented) values (?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, seriesTextField.getText());
            preparedStatement.setString(2, modelTextField.getText());
            preparedStatement.setString(3, colorTextField.getText());
            preparedStatement.setString(4, classTextField.getText());
            preparedStatement.setString(5, rentedTextField.getText());
            preparedStatement.execute();
            updateCars();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCars() {
        connection = DatabaseConnection.getConnection();
        String sql = "delete from cars where idCar = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tableColumnCarID.getCellData(index).toString());
            preparedStatement.execute();
            updateCars();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editCars() {
        try {
            connection = DatabaseConnection.getConnection();
            String value1 = tableColumnCarID.getCellData(index).toString();
            String value2 = seriesTextField.getText();
            String value3 = modelTextField.getText();
            String value4 = colorTextField.getText();
            String value5 = classTextField.getText();
            String value6 = rentedTextField.getText();
            String sql = "update cars set idCar= '" + value1 + "',series= '" + value2 + "',model= '" + value3 + "',color= '" + value4 + "',class= '" + value5 + "', rented = '" + value6 + "' where idCar='" + value1 + "' ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            updateCars();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCars() {
        tableColumnCarID.setCellValueFactory(new PropertyValueFactory<CarsConstructor, Integer>("carId"));
        tableColumnSeries.setCellValueFactory(new PropertyValueFactory<CarsConstructor, String>("series"));
        tableColumnModel.setCellValueFactory(new PropertyValueFactory<CarsConstructor, String>("model"));
        tableColumnColor.setCellValueFactory(new PropertyValueFactory<CarsConstructor, String>("color"));
        tableColumnClass.setCellValueFactory(new PropertyValueFactory<CarsConstructor, String>("clas"));
        tableColumnRented.setCellValueFactory(new PropertyValueFactory<CarsConstructor, String>("rented"));
        listM = DatabaseConnection.getDataCars();
        tableCars.setItems(listM);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateCars();
    }

    public void getSelected(javafx.scene.input.MouseEvent mouseEvent) {
        index = tableCars.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        seriesTextField.setText(tableColumnSeries.getCellData(index));
        modelTextField.setText(tableColumnModel.getCellData(index));
        colorTextField.setText(tableColumnColor.getCellData(index));
        classTextField.setText(tableColumnClass.getCellData(index));
        rentedTextField.setText(tableColumnRented.getCellData(index));
    }
}
