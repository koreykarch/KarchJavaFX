
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.io.File;

public class Author extends Pane {
    private StackPane pane;
    Image myPicture = new Image("image1.jpeg");
    HBox imageBox;
    Label eduStatus, hobbies;
    MediaPlayer mediaPlayer;
    MediaView mediaView;
    Media media;
    final File file = new File("video.mp4");
    final String VIDEO_URL = file.toURI().toString();

    public Author() {
        pane = new StackPane();
        //Label for displaying Education status
        eduStatus = new Label("\t\tEducation Status: \n\nFreshman Studying Computer Science at KSU");
        eduStatus.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));
        eduStatus.setTextFill(Color.RED);
        //Label for Displaying hobbies
        hobbies = new Label("Hobbies: \n-Watching TV, Movies\n-Playing Video Games\n-Programming");
        hobbies.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));
        hobbies.setTextFill(Color.RED);

        ImageView myPictureView = new ImageView(myPicture);

        //Creating HBox to hold ImageView so it is easy to align
        imageBox = new HBox();
        imageBox.getChildren().add(myPictureView);
        imageBox.setPadding(new Insets(10, 10, 10, 10));

        pane.getChildren().add(imageBox);
        imageBox.setAlignment(Pos.TOP_LEFT);

        pane.getChildren().add(eduStatus);
        eduStatus.setAlignment(Pos.CENTER);
        eduStatus.setTranslateX(180);
        eduStatus.setTranslateY(-200);

        hobbies.setAlignment(Pos.BOTTOM_LEFT);
        pane.getChildren().add(hobbies);

        hobbies.setTranslateX(-285);
        hobbies.setTranslateY(100);

        media = new Media(VIDEO_URL);
        mediaPlayer = new MediaPlayer(media);
        mediaView = new MediaView((mediaPlayer));


        //Setting media to pane
        pane.getChildren().add(mediaView);
        pane.setAlignment(mediaView, Pos.CENTER_RIGHT);
        mediaView.setFitWidth(400);
        mediaView.setTranslateX(-130);
        mediaView.setTranslateY(100);

        //Media Buttons
        //Play Pause Button
        Button play = new Button(">");
        play.setOnAction(e -> {
            if (play.getText().equals(">")) {
                mediaPlayer.play();
                play.setText("||");
            }
            else {
                mediaPlayer.pause();
                play.setText(">");
            }
        });
        // Rewind Button
        Button rewind = new Button("<<");
        rewind.setOnAction(e -> mediaPlayer.seek(Duration.ZERO));

        //Volume Slider
        Slider volSlide = new Slider();
        volSlide.setPrefWidth(150);
        volSlide.setMaxWidth(Region.USE_PREF_SIZE);
        volSlide.setMinWidth(30);
        volSlide.setValue(50);
        mediaPlayer.volumeProperty().bind(volSlide.valueProperty().divide(100));

        HBox mediaButtonsBox = new HBox(10);
        mediaButtonsBox.getChildren().addAll(play, rewind, volSlide);

        pane.getChildren().add(mediaButtonsBox);
        mediaButtonsBox.setAlignment(Pos.CENTER_RIGHT);
        mediaButtonsBox.setTranslateX(-200);
        mediaButtonsBox.setTranslateY(-29);
    }

    public Pane getPane() {
        return pane;
    }

}



