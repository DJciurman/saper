package code;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;


public interface GridInterface {
    int getSize();

    Field getField(int column, int row);

    void setBottomPane(StackPane bottomPane);

    void setNewGameButton(Button newGameButton);

    void setEndGameButton(Button endGameButton);

    boolean isGameOver();
}
