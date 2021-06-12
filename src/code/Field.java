package code;

import javafx.scene.control.Button;

public class Field extends Button {
    private int number;
    private boolean hidden;
    private int xValue;
    private int yValue;

    public Field(int value) {
        this.setMinSize(15.0, 15.0);
        this.setPrefSize(25.0, 25.0);
        this.number = value;
        this.setText(null);
        hidden = true;
    }

    @Override
    public void fire() {
        if (number == -1)
            showMine();
        else
            showNumber();
        hidden = false;
    }

    public void showMine() {
        this.setGraphic(Images.getMineImage());
    }

    public void showNumber() {
        this.setText(String.valueOf(number));
        this.hidden = false;
    }

    public void showFlag() {
        this.setGraphic(Images.getFlag());
    }

    public void showAllMines() {
        if (number == -1)
            showMine();
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void flagAllMines() {
        if (number == -1)
            showFlag();
    }

    public boolean isHidden() {
        return hidden;
    }

    public int getNumber() {
        return number;
    }

    public void setxValue(int xValue) {
        this.xValue = xValue;
    }

    public void setyValue(int yValue) {
        this.yValue = yValue;
    }

    public int getxValue() {
        return xValue;
    }

    public int getyValue() {
        return yValue;
    }
}
