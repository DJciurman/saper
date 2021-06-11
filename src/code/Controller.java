package code;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    StackPane mainPane;
    @FXML
    StackPane bottomPane;
    @FXML
    Button newGameButton;
    @FXML
    Button endGameButton;

    GameMode gameMode;

    enum GameMode {
        easy,
        normal,
        hard
    }

    public void newGame(ActionEvent actionEvent) {
        if (gameMode == GameMode.easy)
            showEasyGrid(actionEvent);
        else if (gameMode == GameMode.normal)
            showNormalGrid(actionEvent);
        else
            showHardGrid(actionEvent);
    }

    public void endGame(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void rules(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("help.fxml"));
        stage.setTitle("Zasady gry");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showEasyGrid(ActionEvent actionEvent) {
        gameMode = GameMode.easy;
        mainPane.getChildren().clear();
        Grid grid = new Grid(8, bottomPane);
        bottomPane.getChildren().clear();
        mainPane.getChildren().add(grid);
        bottomPane.getChildren().add(Images.getSmilingFace());
    }

    public void showNormalGrid(ActionEvent actionEvent) {
        gameMode = GameMode.normal;
        mainPane.getChildren().clear();
        Grid grid = new Grid(16, bottomPane);
        bottomPane.getChildren().clear();
        mainPane.getChildren().add(grid);
        bottomPane.getChildren().add(Images.getSmilingFace());
    }

    public void showHardGrid(ActionEvent actionEvent) {
        gameMode = GameMode.hard;
        mainPane.getChildren().clear();
        Grid grid = new Grid(24, bottomPane);
        bottomPane.getChildren().clear();
        mainPane.getChildren().add(grid);
        bottomPane.getChildren().add(Images.getSmilingFace());
    }

}
