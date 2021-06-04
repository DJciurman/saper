package code;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Field extends Button {
    private int number;

    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (number == -1) {
                Field.this.setGraphic(Images.getMineImage());
            } else {
                Field.this.setText(String.valueOf(number));
            }
        }
    };

    public Field(int value) {
        this.setMinSize(15.0, 15.0);
        this.setPrefSize(25.0, 25.0);
        this.setOnAction(event);
        this.number = value;
    }

}
