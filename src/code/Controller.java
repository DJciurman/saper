package code;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.awt.event.ActionEvent;

public class Controller {
    @FXML
    GridPane easyGrid;

    public void showEasyGrid(javafx.event.ActionEvent actionEvent) {
        easyGrid.setVisible(true);
    }
}
