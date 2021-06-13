package code;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Klasa do zapewniania grafik i ikonek
 */
public class Images {
    private final static Image mine = new Image("file:././mine.png");
    private final static Image gameOverFace = new Image("file:././GameOverFace.png");
    private final static Image scaredFace = new Image("file:././ScaredFace.png");
    private final static Image smilingFace = new Image("file:././SmilingFace.png");
    private final static Image flag = new Image("file:././Flag.png");


    public static ImageView getMineImage() {
        ImageView mineImage = new ImageView(mine);
        mineImage.setPreserveRatio(true);
        mineImage.setFitHeight(22);
        return mineImage;
    }

    public static ImageView getGameOverFace() {
        ImageView gameOverFaceImage = new ImageView(gameOverFace);
        gameOverFaceImage.setPreserveRatio(true);
        gameOverFaceImage.setFitHeight(50);
        return gameOverFaceImage;
    }

    public static ImageView getScaredFace() {
        ImageView scaredFaceImage = new ImageView(scaredFace);
        scaredFaceImage.setPreserveRatio(true);
        scaredFaceImage.setFitHeight(50);
        return scaredFaceImage;
    }

    public static ImageView getSmilingFace() {
        ImageView smilingFaceImage = new ImageView(smilingFace);
        smilingFaceImage.setPreserveRatio(true);
        smilingFaceImage.setFitHeight(50);
        return smilingFaceImage;
    }

    public static ImageView getFlag() {
        ImageView flagImage = new ImageView(flag);
        flagImage.setPreserveRatio(true);
        flagImage.setFitHeight(22);
        return flagImage;
    }


}
