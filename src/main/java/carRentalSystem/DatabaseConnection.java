package carRentalSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseConnection {

    public static Connection databaseLink = null;

    public static Connection getConnection() {
        String databaseName = "database";
        String databaseUser = "root";
        String databasePassword = "BayerischeMotorenWerke";
        String url = "jdbc:mysql://localhost/" + databaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;
    }

    public static ObservableList<CarsConstructor> getDataCars(){
        Connection connection = getConnection();
        ObservableList<CarsConstructor> list = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from cars");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                list.add(new CarsConstructor(Integer.parseInt(resultSet.getString("IdCar")), resultSet.getString("series"),resultSet.getString("model"), resultSet.getString("color"),resultSet.getString("class"),resultSet.getString("rented")));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public static ObservableList<CustumersConstructor> getDataCustomers(){
        Connection connection = getConnection();
        ObservableList<CustumersConstructor> list = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from customers");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(new CustumersConstructor(
                        Integer.parseInt(resultSet.getString("idCustomer")),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("number")));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
