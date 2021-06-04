package code;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class Grid extends GridPane {

    public Grid(int size){
        this.setAlignment(Pos.CENTER);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.add(new Field(), i, j);
            }
        }
    }
}
