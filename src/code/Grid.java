package code;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class Grid extends GridPane {
    private int size;
    private StackPane bottomPane;
    private int flagCounter;
    private Label leftCounter;

    public Grid(int size, StackPane bottomPane) {
        this.size = size;
        this.bottomPane = bottomPane;
        this.flagCounter = size;
        this.setAlignment(Pos.CENTER);
        this.leftCounter = new Label();
        this.leftCounter.setText("" + flagCounter);
        this.leftCounter.setFont(Font.font("Arial", 24));
        this.leftCounter.setVisible(true);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Field field = new Field(3);

                field.setOnMouseClicked(mouseEvent -> {
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
                    } else {
                        if (field.getNumber() == -1) {
                            field.setGraphic(null);
                            field.showMine();
                            bottomPane.getChildren().clear();
                            bottomPane.getChildren().add(Images.getGameOverFace());
                        } else {
                            if (field.getGraphic() != null) {
                                field.setGraphic(null);
                                flagCounter++;
                                leftCounter.setText(String.valueOf(flagCounter));
                            }
                            field.showNumber();
                        }
                    }
                });

                field.setOnMousePressed(mouseEvent -> {
                    bottomPane.getChildren().clear();
                    bottomPane.getChildren().add(leftCounter);
                    bottomPane.getChildren().add(Images.getScaredFace());
                });
                field.setOnMouseReleased(mouseEvent -> {
                    bottomPane.getChildren().clear();
                    bottomPane.getChildren().add(leftCounter);
                    bottomPane.getChildren().add(Images.getSmilingFace());
                });
                this.add(field, i, j);
            }
        }

        bottomPane.getChildren().add(leftCounter);
        bottomPane.setAlignment(leftCounter, Pos.CENTER_LEFT);
    }

}
