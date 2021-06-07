package code;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class Grid extends GridPane {
private int size;
    public Grid(int size){
        this.size = size;
        this.setAlignment(Pos.CENTER);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.add(new Field(), i, j);
            }
        }
    }

    public int getSize() {
        return size;
    }
}
