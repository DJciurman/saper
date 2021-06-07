package code;

import javafx.scene.control.Button;

public class Field extends Button {
    private int number;

    public Field(int value) {
        this.setMinSize(15.0, 15.0);
        this.setPrefSize(25.0, 25.0);
        this.number = value;
        this.setText(null);
    }

    public void showMine() {
        this.setGraphic(Images.getMineImage());
    }

    public void showNumber() {
        this.setText(String.valueOf(number));
    }

    public void showFlag() {
        this.setGraphic(Images.getFlag());
    }

    public int getNumber() {
        return number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
