package code;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;

public class Controller {
    @FXML
    StackPane mainPane;
    @FXML
    StackPane bottomPane;

    public void showEasyGrid(ActionEvent actionEvent) {
        mainPane.getChildren().clear();
        Grid grid = new Grid(8);
        mainPane.getChildren().add(grid);
        bottomPane.getChildren().add(Images.getSmilingFace());
    }

    public void showNormalGrid(ActionEvent actionEvent) {
        mainPane.getChildren().clear();
        Grid grid = new Grid(16);
        mainPane.getChildren().add(grid);

    }

    public void showHardGrid(ActionEvent actionEvent) {
        mainPane.getChildren().clear();
        Grid grid = new Grid(24);
        mainPane.getChildren().add(grid);

    }

    public void showSmilingFace(MouseEvent mouseEvent) {
        bottomPane.getChildren().clear();
        bottomPane.getChildren().add(Images.getSmilingFace());
    }

    public void showScaredFace(ActionEvent actionEvent) {
        bottomPane.getChildren().clear();
        bottomPane.getChildren().add(Images.getScaredFace());
    }

    public void showGameOverFace(ActionEvent actionEvent) {
        bottomPane.getChildren().clear();
        bottomPane.getChildren().add(Images.getGameOverFace());
    }


}
