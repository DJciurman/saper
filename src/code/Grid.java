package code;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.Serializable;
/**
 * Klasa tworząca całą planszę do gry
 */
public class Grid extends GridPane implements GridInterface, Serializable {

    private int size;
    private transient StackPane bottomPane;
    private int flagCounter;
    private transient Label leftCounter;
    private transient Label rightCounter;
    private boolean gameOver;
    private static transient Button newGameButton;
    private static transient Button endGameButton;
    private Field[][] fields;
    private int[][] table;
    private transient FatherOfGameTime gameTime;
    private transient Timeline timeline;
    private int czas;

    /**
     * Tworzenie nowej planszy o określonym rozmiarze oraz dostępie do dolnej belki
     * @param size rozmiar
     * @param bottomPane dolna belka
     */
    public Grid(int size, StackPane bottomPane) {
        this.size = size;
        this.bottomPane = bottomPane;
        if (size == 8)
            flagCounter = 10;
        else if (size == 16)
            flagCounter = 40;
        else
            flagCounter = 99;
        this.createTimer();
        this.createFlagCounter();
        this.gameOver = false;
        this.czas = 0;
        newGameButton = (Button) bottomPane.lookup("#newGameButton");
        endGameButton = (Button) bottomPane.lookup("#endGameButton");
        fields = new Field[size][size];
        this.createAndFillGrid();
    }

    /**
     * Tworzy stoper
     */
    private void createTimer() {
        gameTime = new GameTime(czas);
        timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        this.rightCounter = new Label();
        this.rightCounter.setText("" + gameTime);
        this.rightCounter.setFont(Font.font("Arial", 24));
        this.rightCounter.setVisible(true);
        this.rightCounter.setTextFill(Paint.valueOf("#991f00"));

    }

    /**
     * Tworzy licznik flag
     */
    private void createFlagCounter() {
        this.setAlignment(Pos.CENTER);
        this.leftCounter = new Label();
        this.leftCounter.setText("" + flagCounter);
        this.leftCounter.setFont(Font.font("Arial", 24));
        this.leftCounter.setVisible(true);
        this.leftCounter.setTextFill(Paint.valueOf("#991f00"));

    }

    /**
     * Metoda pokazująca planszę po wczytaniu gry z pliku
     */
    public void showGridAfterLoad() {
        createTimer();
        createFlagCounter();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fields[i][j].correctFieldParameters();
                setMouseEvents(fields[i][j]);
                if (!fields[i][j].isHidden()) {
                    fields[i][j].showNumber();
                } else if (fields[i][j].isFlag()) {
                    fields[i][j].showFlag();
                }
                this.add(fields[i][j], i, j);
            }
        }

        newGameButton.setVisible(false);
        endGameButton.setVisible(false);
        bottomPane.getChildren().add(leftCounter);
        bottomPane.setAlignment(leftCounter, Pos.CENTER_LEFT);
        bottomPane.getChildren().add(rightCounter);
        bottomPane.setAlignment(rightCounter, Pos.CENTER_RIGHT);

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), e -> {
            gameTime.incrementCzas();
            this.czas = gameTime.getCzas();
            rightCounter.setText("" + gameTime);
        }));
        timeline.play();
    }

    /**
     * Tworzy nową planszę
     */
    private void createAndFillGrid() {
        Operations operations = new Operations(size);

        table = operations.randomizer();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Field field = new Field(table[i][j], i, j);
                fields[i][j] = field;
                setMouseEvents(field);
                this.add(field, i, j);
            }
        }
        newGameButton.setVisible(false);
        endGameButton.setVisible(false);
        bottomPane.getChildren().add(leftCounter);
        bottomPane.setAlignment(leftCounter, Pos.CENTER_LEFT);
        bottomPane.getChildren().add(rightCounter);
        bottomPane.setAlignment(rightCounter, Pos.CENTER_RIGHT);

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), e -> {
            gameTime.incrementCzas();
            this.czas = gameTime.getCzas();
            rightCounter.setText("" + gameTime);
        }));
    }

    /**
     * Odkrywa na planszy zera oraz inne pola sąsiadujące z klikniętym polem
     * @param x pozycja X klikniętego pola
     * @param y pozycja Y klikniętego pola
     */
    public void showAllNulls(int x, int y) {
        if (fields[x][y].getNumber() == 0 && fields[x][y].getGraphic() == null) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if ((x + i) < 0 || (y + j) < 0) {
                        continue;
                    }
                    if ((x + i) > (size - 1) || (y + j) > (size - 1)) {
                        continue;
                    }
                    fields[x][y].showNumber();
                    fields[x][y].setHidden(false);
                    if (fields[x + i][y + j].isHidden() == true && fields[x + i][y + j].getGraphic() == null) {
                        showAllNulls(x + i, y + j);
                    }
                }
            }
        } else if (fields[x][y].getNumber() > 0 && fields[x][y].getGraphic() == null) {
            fields[x][y].showNumber();
            fields[x][y].setHidden(false);
        }
    }

    /**
     * Ustawienie wydarzeń na kliknięcia myszką
     * @param field pole którego dotyczą ustawienia
     */
    private void setMouseEvents(Field field) {
        field.setOnMouseClicked(mouseEvent -> {
            if (!gameOver) {
                if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    if (field.getGraphic() != null) {
                        field.setGraphic(null);
                        flagCounter++;
                        leftCounter.setText(String.valueOf(flagCounter));
                        field.setFlag(false);
                    } else if (field.getText() == null && flagCounter != 0) {
                        field.showFlag();
                        flagCounter--;
                        leftCounter.setText(String.valueOf(flagCounter));
                        field.setFlag(true);
                    }
                } else if (field.getGraphic() == null) {
                    if (field.getNumber() == -1) {
                        field.setGraphic(null);
                        field.showMine();
                        bottomPane.getChildren().clear();
                        bottomPane.getChildren().add(Images.getGameOverFace());
                        for (Node node : this.getChildren()) {
                            Field f = (Field) node;
                            if(f.getNumber() == -1)
                                f.showMine();
                        }
                        newGameButton.setVisible(true);
                        endGameButton.setVisible(true);
                        bottomPane.getChildren().add(newGameButton);
                        bottomPane.getChildren().add(endGameButton);
                        gameOver = true;
                    } else {
                        showAllNulls(field.getxValue(), field.getyValue());
                        boolean win = true;
                        for (Node node : this.getChildren()) {
                            Field f = (Field) node;
                            if (f.isHidden() && f.getNumber() != -1) {
                                win = false;
                                break;
                            }
                        }
                        if (win) {
                            for (Node node : this.getChildren()) {
                                Field f = (Field) node;
                                if(f.getNumber() == -1)
                                    f.showFlag();
                            }
                            newGameButton.setVisible(true);
                            endGameButton.setVisible(true);
                            timeline.stop();
                            showWinLabel();
                            leftCounter.setText("0");
                        }
                    }
                }
            }
        });

        field.setOnMousePressed(mouseEvent -> {
            if (!gameOver) {
                bottomPane.getChildren().clear();
                bottomPane.getChildren().add(leftCounter);
                bottomPane.getChildren().add(rightCounter);
                bottomPane.getChildren().add(Images.getScaredFace());
                timeline.play();
                bottomPane.getChildren().add(newGameButton);
                bottomPane.getChildren().add(endGameButton);
            }
        });

        field.setOnMouseReleased(mouseEvent -> {
            if (!gameOver) {
                bottomPane.getChildren().clear();
                bottomPane.getChildren().add(leftCounter);
                bottomPane.getChildren().add(rightCounter);
                bottomPane.getChildren().add(Images.getSmilingFace());
                bottomPane.getChildren().add(newGameButton);
                bottomPane.getChildren().add(endGameButton);
            }
        });

    }

    /**
     * Zwraca rozmiar boku kwadratowej planszy
     * @return rozmiar boku planszy
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * Zwraca wskazane pole z planszy
     * @param column współrzędna X pola
     * @param row współrzędna Y pola
     * @return wskazane pole
     */
    @Override
    public Field getField(int column, int row) {
        for (Node node : this.getChildren()) {
            if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row) {
                return (Field) node;
            }
        }
        return null;
    }

    /**
     * Pokazuje napis "GRATULACJE!" o określonej stylistyce
     */
    private void showWinLabel() {
        Label win = new Label();
        win.setText("GRATULACJE!");
        win.setFont(Font.font("Bauhaus 93", 36));
        win.setVisible(true);
        bottomPane.getChildren().add(win);
        bottomPane.setAlignment(win, Pos.BOTTOM_CENTER);
    }

    /**
     * przypisuje do planszy dolną belkę z parametru
     * @param bottomPane dolna belka
     */
    @Override
    public void setBottomPane(StackPane bottomPane) {
        this.bottomPane = bottomPane;
        this.newGameButton = (Button) bottomPane.lookup("#newGameButton");
        this.endGameButton = (Button) bottomPane.lookup("#endGameButton");
    }

    /**
     * Sprawdzanei czy gra na tej planszy się zakończyła
     * @return true jeśli gra się zakończyła, inaczej false
     */
    @Override
    public boolean isGameOver() {
        return gameOver;
    }

}
