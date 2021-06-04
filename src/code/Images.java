package code;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Images {
    private final static ImageView mineImage = new ImageView(new Image("file:././mine.png"));
    private final static ImageView gameOverFace = new ImageView(new Image("file:././GameOverFace.png"));
    private final static ImageView scaredFace = new ImageView(new Image("file:././ScaredFace.png"));
    private final static ImageView smilingFace = new ImageView(new Image("file:././SmilingFace.png"));

    public static ImageView getMineImage() {
        mineImage.setPreserveRatio(true);
        mineImage.setFitHeight(22);
        return mineImage;
    }

    public static ImageView getGameOverFace() {
        gameOverFace.setPreserveRatio(true);
        gameOverFace.setFitHeight(50);
        return gameOverFace;
    }

    public static ImageView getScaredFace() {
        scaredFace.setPreserveRatio(true);
        scaredFace.setFitHeight(50);
        return scaredFace;
    }

    public static ImageView getSmilingFace() {
        smilingFace.setPreserveRatio(true);
        smilingFace.setFitHeight(50);
        return smilingFace;
    }
}
