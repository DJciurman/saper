package code;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.io.Serializable;

/**
 * Klasa tworząca pojedyncze, klikalne pole
 */
public class Field extends Button implements Serializable {
    private int number;
    private boolean hidden;
    private boolean flag;
    private int xValue;
    private int yValue;

    /**
     * Tworzy nowe pole o określonej wartości i współrzędnych
     * @param value wartość wskazująca ile min jest w sąsiedztwie
     * @param xValue wsp X
     * @param yValue wsp Y
     */
    public Field(int value, int xValue, int yValue) {
        correctFieldParameters();
        this.number = value;
        this.xValue = xValue;
        this.yValue = yValue;
        this.hidden = true;
        this.flag = false;
    }

    /**
     * Ustala wymiary i wygląd pola
     */
    public void correctFieldParameters(){
        this.setMinSize(35.0, 35.0);
        this.setPrefSize(40.0, 40.0);
        this.setText(null);
        this.setFont(Font.font("Arial", 18));
    }

    /**
     * Sprawdza czy pole jest oznaczone flagą
     * @return true jeśli powyższe jest prawdą
     */
    public boolean isFlag() {
        return flag;
    }

    /**
     * Ustala czy pole jest flagą
     * @param flag true jeśli pole ma być flagą, inaczej false
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * Pokazuje na polu grafikę miny
     */
    public void showMine() {
        this.setGraphic(Images.getMineImage());
    }

    /**
     * Pokazuje na polu ilość sąsiadujących z nim min
     */
    public void showNumber() {
        this.setText(String.valueOf(number));
        this.hidden = false;
    }

    /**
     * Pokazuje na polu grafikę flagi
     */
    public void showFlag() {
        this.setGraphic(Images.getFlag());
    }

    /**
     * Sprawdza czy pole zostało już odkryte
     * @return true jeśli pole nie zostało odkryte
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * Ustala czy pole jest odkryte
     * @param hidden true jeśli pole ma być zakryte
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * Zwraca ilość min sąsiadujących z tym polem lub -1 jeśli pole jest miną
     * @return liczba min lub -1
     */
    public int getNumber() {
        return number;
    }

    /**
     * Zwraca wsp X pola
     * @return wsp X
     */
    public int getxValue() {
        return xValue;
    }

    /**
     * Zwraca wsp Y pola
     * @return wsp Y
     */
    public int getyValue() {
        return yValue;
    }
}
