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

import java.io.*;

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
    Grid grid;

    enum GameMode {
        easy,
        normal,
        hard
    }

    private static class SaveLoadManager {

        public static void saveGame(Grid gridToSave) throws IOException {
            if (!gridToSave.isGameOver()) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("savedGame.dat"));
                objectOutputStream.writeObject(gridToSave);
                objectOutputStream.close();
            }
        }

        public static Grid loadGame() throws SaveFileNotFoundException {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream((new FileInputStream("savedGame.dat")));
                return (Grid) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException exception) {
                throw new SaveFileNotFoundException();
            }
        }
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
        grid = new Grid(8, bottomPane);
        bottomPane.getChildren().clear();
        mainPane.getChildren().add(grid);
        bottomPane.getChildren().add(Images.getSmilingFace());
        bottomPane.getChildren().add(newGameButton);
        bottomPane.getChildren().add(endGameButton);
    }

    public void showNormalGrid(ActionEvent actionEvent) {
        gameMode = GameMode.normal;
        mainPane.getChildren().clear();
        grid = new Grid(16, bottomPane);
        bottomPane.getChildren().clear();
        mainPane.getChildren().add(grid);
        bottomPane.getChildren().add(Images.getSmilingFace());
        bottomPane.getChildren().add(newGameButton);
        bottomPane.getChildren().add(endGameButton);
    }

    public void showHardGrid(ActionEvent actionEvent) {
        gameMode = GameMode.hard;
        mainPane.getChildren().clear();
        grid = new Grid(24, bottomPane);
        bottomPane.getChildren().clear();
        mainPane.getChildren().add(grid);
        bottomPane.getChildren().add(Images.getSmilingFace());
        bottomPane.getChildren().add(newGameButton);
        bottomPane.getChildren().add(endGameButton);
    }

    public void saveGame(ActionEvent actionEvent) throws IOException {
        SaveLoadManager.saveGame(grid);
    }

    public void loadGame(ActionEvent actionEvent) throws IOException {
        try {
            grid = SaveLoadManager.loadGame();
            switch (grid.getSize()) {
                case 8:
                    gameMode = GameMode.easy;
                    break;

                case 16:
                    gameMode = GameMode.normal;
                    break;

                case 24:
                    gameMode = GameMode.hard;
                    break;
            }
            mainPane.getChildren().clear();
            grid.setBottomPane(bottomPane);
            bottomPane.getChildren().clear();
            grid.showGridAfterLoad();
            mainPane.getChildren().add(grid);
            bottomPane.getChildren().add(Images.getSmilingFace());
            bottomPane.getChildren().add(newGameButton);
            bottomPane.getChildren().add(endGameButton);
        } catch (SaveFileNotFoundException exception) {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("ErrorWindow.fxml"));
            stage.setTitle("Błąd");
            stage.setScene(new Scene(root, 320, 100));
            stage.show();
        }
    }

}
