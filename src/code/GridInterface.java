package code;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;


public interface GridInterface {
    int getSize();

    Field getField(int column, int row);

    void setBottomPane(StackPane bottomPane);

    boolean isGameOver();
}
