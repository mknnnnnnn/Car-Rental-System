package carRentalSystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class MainSceneController {

    @FXML
    private Button carsButton;
    @FXML
    private Button costumersButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button rentButton;

    public void switchToCarScene() throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("carScene.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 550);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToCostumersScene() throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("customerScene.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 550);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToRentScene() throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("rentScene.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 550);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void carsButtonOnAction(ActionEvent e) throws IOException {
        Stage stage = (Stage) carsButton.getScene().getWindow();
        switchToCarScene();
    }

    public void costumersButtonOnAction(ActionEvent e) throws IOException {
        Stage stage = (Stage) costumersButton.getScene().getWindow();
        switchToCostumersScene();
    }

    public void rentButtonOnAction(ActionEvent e) throws IOException {
        Stage stage = (Stage) rentButton.getScene().getWindow();
        switchToRentScene();
    }

    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
