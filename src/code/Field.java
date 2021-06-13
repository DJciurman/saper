package code;

import javafx.scene.control.Button;

import java.io.Serializable;

public class Field extends Button implements Serializable {
    private int number;
    private boolean hidden;
    private boolean flag;
    private int xValue;
    private int yValue;

    public Field(int value, int xValue, int yValue) {
        correctFieldParameters();
        this.number = value;
        this.xValue = xValue;
        this.yValue = yValue;
        this.hidden = true;
        this.flag = false;
    }

    public void correctFieldParameters(){
        this.setMinSize(15.0, 15.0);
        this.setPrefSize(25.0, 25.0);
        this.setText(null);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
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

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
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
