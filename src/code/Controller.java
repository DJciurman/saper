package code;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        Parent root = FXMLLoader.load(getClass().getResource("Help.fxml"));
        stage.setTitle("Zasady gry");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void creators(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Creators.fxml"));
        stage.setTitle("Twórcy");
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
        bottomPane.getChildren().add(newGameButton);
        bottomPane.getChildren().add(endGameButton);
    }

    public void showNormalGrid(ActionEvent actionEvent) {
        gameMode = GameMode.normal;
        mainPane.getChildren().clear();
        Grid grid = new Grid(16, bottomPane);
        bottomPane.getChildren().clear();
        mainPane.getChildren().add(grid);
        bottomPane.getChildren().add(Images.getSmilingFace());
        bottomPane.getChildren().add(newGameButton);
        bottomPane.getChildren().add(endGameButton);
    }

    public void showHardGrid(ActionEvent actionEvent) {
        gameMode = GameMode.hard;
        mainPane.getChildren().clear();
        Grid grid = new Grid(24, bottomPane);
        bottomPane.getChildren().clear();
        mainPane.getChildren().add(grid);
        bottomPane.getChildren().add(Images.getSmilingFace());
        bottomPane.getChildren().add(newGameButton);
        bottomPane.getChildren().add(endGameButton);
    }

    public void saveGame(ActionEvent actionEvent){

    }

    public void loadGame(ActionEvent actionEvent){
        //ładowanie pliku

        //gamemode = załadowany gamemode
        mainPane.getChildren().clear();
        //Grid grid = załadowany grid
        bottomPane.getChildren().clear();
        //mainPane.getChildren().add(grid);
        bottomPane.getChildren().add(Images.getSmilingFace());
        bottomPane.getChildren().add(newGameButton);
        bottomPane.getChildren().add(endGameButton);
    }

}
