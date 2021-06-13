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

public class Grid extends GridPane implements GridInterface{

    private int size;
    private StackPane bottomPane;
    private int flagCounter;
    private Label leftCounter;
    private Label rightCounter;
    private boolean gameOver;
    private Button newGameButton;
    private Button endGameButton;
    private Field[][] fields;

    private final Timeline timeline = new Timeline();

    private int czas = 0;

    private GameTime gameTime;

    public Grid(int size, StackPane bottomPane) {
        this.size = size;
        this.bottomPane = bottomPane;
        if(size == 8)
            flagCounter = 10;
        else if(size == 16)
            flagCounter = 40;
        else
            flagCounter = 99;

        gameTime = new GameTime(czas);

        this.setAlignment(Pos.CENTER);
        this.leftCounter = new Label();
        this.leftCounter.setText("" + flagCounter);
        this.leftCounter.setFont(Font.font("Arial", 24));
        this.leftCounter.setVisible(true);
        this.leftCounter.setTextFill(Paint.valueOf("#991f00"));
        this.rightCounter = new Label();
        this.rightCounter.setText("" + gameTime);
        this.rightCounter.setFont(Font.font("Arial", 24));
        this.rightCounter.setVisible(true);
        this.rightCounter.setTextFill(Paint.valueOf("#991f00"));

        this.gameOver = false;
        this.newGameButton = (Button) bottomPane.lookup("#newGameButton");
        this.endGameButton = (Button) bottomPane.lookup("#endGameButton");

         fields = new Field[size][size];

         timeline.setCycleCount(Animation.INDEFINITE);
         timeline.setAutoReverse(true);

        this.createAndFillGrid();
    }

    private void createAndFillGrid() {

        Operations operations = new Operations(size);
        int[][] table;
        table = operations.randomizer();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Field field = new Field(table[i][j], i, j);
                fields[i][j] = field;



                field.setOnMouseClicked(mouseEvent -> {
                    if (!gameOver) {
                        if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                            if (field.getGraphic() != null) {
                                field.setGraphic(null);
                                flagCounter++;
                                leftCounter.setText(String.valueOf(flagCounter));
                            } else if (field.getText() == null && flagCounter != 0) {
                                field.showFlag();
                                flagCounter--;
                                leftCounter.setText(String.valueOf(flagCounter));
                            }
                        } else if (field.getGraphic() == null) {
                            if (field.getNumber() == -1) {
                                field.setGraphic(null);
                                field.showMine();
                                bottomPane.getChildren().clear();
                                bottomPane.getChildren().add(Images.getGameOverFace());
                                for (Node node : this.getChildren()) {
                                    Field f = (Field) node;
                                    f.showAllMines();
                                }
                                newGameButton.setVisible(true);
                                endGameButton.setVisible(true);
                                bottomPane.getChildren().add(newGameButton);
                                bottomPane.getChildren().add(endGameButton);
                                gameOver = true;
                                timeline.stop();
                            } else {
                                showAllNulls(field.getxValue(), field.getyValue());
                                //field.showNumber();

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
                                        f.flagAllMines();
                                    }
                                    timeline.stop();
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
                    }
                });
                field.setOnMouseReleased(mouseEvent -> {
                    if (!gameOver) {
                        bottomPane.getChildren().clear();
                        bottomPane.getChildren().add(leftCounter);
                        bottomPane.getChildren().add(rightCounter);
                        timeline.play();
                        bottomPane.getChildren().add(Images.getSmilingFace());
                    }
                });

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
            rightCounter.setText("" + gameTime);
            gameTime.incrementCzas();
        }));
    }

    public void showAllNulls(int x, int y)
    {
        if (fields[x][y].getNumber() == 0 && fields[x][y].getGraphic() == null)
        {
            for (int i = -1; i < 2; i++)
            {
                for (int j = -1; j < 2; j++)
                {
                    if ((x + i) < 0 || (y + j) < 0)
                    {
                        continue;
                    }
                    if ((x + i) > (size - 1) || (y + j) > (size - 1))
                    {
                        continue;
                    }
                    fields[x][y].showNumber();
                    if (fields[x + i][y + j].isHidden() == true && fields[x + i][y + j].getGraphic() == null)
                    {
                        showAllNulls(x + i, y + j);
                    }
                }
            }
        }
        else if (fields[x][y].getNumber() > 0 && fields[x][y].getGraphic() == null)
        {
            fields[x][y].showNumber();
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Field getField(int column, int row) {
        for (Node node : this.getChildren()) {
            if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row) {
                return (Field) node;
            }
        }
        return null;
    }

    public Label getRightCounter()
    {
        return rightCounter;
    }

}
