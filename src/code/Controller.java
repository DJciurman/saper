package code;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.awt.event.ActionEvent;

public class Controller {
    @FXML
    StackPane mainPane;

    public void showEasyGrid(javafx.event.ActionEvent actionEvent) {
        mainPane.getChildren().clear();
        Grid grid = new Grid(8);
        mainPane.getChildren().add(grid);

    }

    public void showNormalGrid(javafx.event.ActionEvent actionEvent) {
        mainPane.getChildren().clear();
        Grid grid = new Grid(16);
        mainPane.getChildren().add(grid);

    }

    public void showHardGrid(javafx.event.ActionEvent actionEvent) {
        mainPane.getChildren().clear();
        Grid grid = new Grid(24);
        mainPane.getChildren().add(grid);

    }
}
