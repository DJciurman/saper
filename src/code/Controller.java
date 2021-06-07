package code;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;

public class Controller {
    @FXML
    StackPane mainPane;
    @FXML
    StackPane bottomPane;

    public void showEasyGrid(ActionEvent actionEvent) {
        mainPane.getChildren().clear();
        bottomPane.getChildren().clear();
        Grid grid = new Grid(8, bottomPane);
        mainPane.getChildren().add(grid);
        bottomPane.getChildren().add(Images.getSmilingFace());
    }

    public void showNormalGrid(ActionEvent actionEvent) {
        mainPane.getChildren().clear();
        bottomPane.getChildren().clear();
        Grid grid = new Grid(16, bottomPane);
        mainPane.getChildren().add(grid);
        bottomPane.getChildren().add(Images.getSmilingFace());
    }

    public void showHardGrid(ActionEvent actionEvent) {
        mainPane.getChildren().clear();
        bottomPane.getChildren().clear();
        Grid grid = new Grid(24, bottomPane);
        mainPane.getChildren().add(grid);
        bottomPane.getChildren().add(Images.getSmilingFace());
    }

}
