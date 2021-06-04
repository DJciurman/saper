package code;

import javafx.scene.control.Button;

public class Field extends Button {
    private boolean mine;
    private int number;

    public Field() {
        this.setMinSize(15.0, 15.0);
        this.setPrefSize(25.0, 25.0);
    }
}
