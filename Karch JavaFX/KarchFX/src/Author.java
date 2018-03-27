
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
	// In this class, declare nodes for Pane
	private StackPane pane; // Initialize a stack pane
	Image myPicture = new Image("image1.jpeg"); // My picture for the Author View
	HBox imageBox; // This HBox will be used to display and align my picture
	Label eduStatus, hobbies; // Labels for displaying education status and hobbies
	MediaPlayer mediaPlayer; // MediaPlayer object for video introduction
	MediaView mediaView; // MediaView object
	Media media; // media object
	final File file = new File("video.mp4"); // Create file object defined as video.mp4 in source folder
	final String VIDEO_URL = file.toURI().toString(); // I needed this to get my video to work

	public Author() {
		pane = new StackPane(); // Declare new StackPane object

		// Label for displaying Education status
		eduStatus = new Label("\t\tEducation Status: \n\nFreshman Studying Computer Science at KSU");
		eduStatus.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));
		eduStatus.setTextFill(Color.RED);

		// Label for Displaying hobbies
		hobbies = new Label("Hobbies: \n-Watching TV, Movies\n-Playing Video Games\n-Programming");
		hobbies.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));
		hobbies.setTextFill(Color.RED);

		ImageView myPictureView = new ImageView(myPicture); // Create new imageView using myPicture image

		// Creating HBox to hold ImageView
		imageBox = new HBox();
		imageBox.getChildren().add(myPictureView); // Add imageView to HBox
		imageBox.setPadding(new Insets(10, 10, 10, 10)); // set Padding for neatness

		// Add imageBox (HBox) to pane and align
		pane.getChildren().add(imageBox);
		imageBox.setAlignment(Pos.TOP_LEFT);
		// Add education label to pane and align using setTranslate()
		pane.getChildren().add(eduStatus);
		eduStatus.setAlignment(Pos.CENTER);
		eduStatus.setTranslateX(180);
		eduStatus.setTranslateY(-200);
		// Add hobbies label to pane and align using setTranslate()
		hobbies.setAlignment(Pos.BOTTOM_LEFT);
		pane.getChildren().add(hobbies);

		hobbies.setTranslateX(-285);
		hobbies.setTranslateY(100);

		// Create media, mediaPlayer, and mediaView objects for my video
		media = new Media(VIDEO_URL);
		mediaPlayer = new MediaPlayer(media);
		mediaView = new MediaView((mediaPlayer));

		// Add media to pane and relocate/size it
		pane.getChildren().add(mediaView);
		pane.setAlignment(mediaView, Pos.CENTER_RIGHT);
		mediaView.setFitWidth(400);
		mediaView.setTranslateX(-130);
		mediaView.setTranslateY(100);

		// Media Buttons
		// Play Pause Button
		Button play = new Button(">");
		play.setOnAction(e ->
		{
			// if button is on 'play' icon, call .play() method
			if (play.getText().equals(">")) {
				mediaPlayer.play();
				play.setText("||"); // set the text of the button to 'pause' icon
			}
			else { // else, call .pause() method and set the text to 'play' icon
				mediaPlayer.pause();
				play.setText(">");
			}
		});
		// Rewind Button
		Button rewind = new Button("<<");
		rewind.setOnAction(e -> mediaPlayer.seek(Duration.ZERO));

		// Volume Slider
		Slider volSlide = new Slider();
		volSlide.setPrefWidth(150);
		volSlide.setMaxWidth(Region.USE_PREF_SIZE);
		volSlide.setMinWidth(30);
		volSlide.setValue(50);
		// bind the volume property to slider
		mediaPlayer.volumeProperty().bind(volSlide.valueProperty().divide(100));

		// Create HBox to display pause/play/rewind buttons and volume slider
		HBox mediaButtonsBox = new HBox(10); // create HBox with 10 spacing
		mediaButtonsBox.getChildren().addAll(play, rewind, volSlide); // add all buttons to HBox

		// Add our HBox mediaButtonsBox to pane
		pane.getChildren().add(mediaButtonsBox);
		mediaButtonsBox.setAlignment(Pos.CENTER_RIGHT);
		mediaButtonsBox.setTranslateX(-200);
		mediaButtonsBox.setTranslateY(-29);
	}

	// This method will return the stackpane made in this Class
	public Pane getPane() {
		return pane;
	}

}
