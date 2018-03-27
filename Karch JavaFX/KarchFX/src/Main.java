import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Author author = new Author(); // creating object of Author class, where author view is designed
		Game game = new Game(); // Object of Game class

		// Setting up stage
		primaryStage.setTitle("JavaFX Project Karch");
		primaryStage.setResizable(false);
		
		// General Layout for App
		BorderPane rootPane = new BorderPane();
		
		// Hbox used to display the viewButtons (buttons to change views)
		HBox viewButtons = new HBox(200); // Horizontal Box for displaying view buttons, set to 500 spacing
		viewButtons.setPadding(new Insets(10, 30, 20, 30)); // setting Padding for HBox
		// Creating a Vertical Box node in order to line up the course information
		// (Labels) for home view, will be placed
		// in center of rootPane
		VBox courseData = new VBox(50);
		// Labels for Course information
		Label courseName = new Label("Programming Principles II | CS1302/09");
		courseName.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
		// Will display due date
		Label dueDate = new Label("March 22, 2018");
		dueDate.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
		// Displays professor's name
		Label profName = new Label("Prof. Carlos A. Cepeda Mora");
		profName.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));

		// Adding labels to Vbox
		courseData.getChildren().addAll(courseName, profName, dueDate);
		courseData.setAlignment(Pos.CENTER);

		// Welcome Message for home view (label)
		Label welcomeMSG = new Label("Welcome to Korey Karch's JavaFX Project!");
		welcomeMSG.setFont(new Font("Century Gothic", 35));
		// Setting color of Welcome Message to Blue
		welcomeMSG.setTextFill(Color.BLUE);

		// Creating a fadetransition object for the Welcome Message on Home Page
		FadeTransition messageFade = new FadeTransition(Duration.seconds(.75), welcomeMSG); // Only want it to last .75
																							// seconds, set it to
																							// Welcome MSG
		// I want the message to fade in and back out
		messageFade.setFromValue(1.0);
		messageFade.setToValue(0.0);
		messageFade.setCycleCount(Animation.INDEFINITE); // play forever
		messageFade.setAutoReverse(true); // reverse the animation is set to True, so it will fade back in and out
		messageFade.play(); // Set to play once the WElcome message is added to a Pane

		// Game Reset Button for Game class
		Button replayGameButton = new Button("Reset Game");
		// Editing the size of the button
		replayGameButton.setPrefSize(150, 60);

		// Boolean to check if game was reset, will be set to true when replayButton is
		// pressed and will be used in
		// gameView button to decide if it should set rootPane Center to a newGame pane
		// or just game Pane
		AtomicBoolean wasGameReset = new AtomicBoolean(false);

		// Setting the event of replayButton that is defined in Game Class, used to
		// reset game board/Values
		replayGameButton.setOnAction(e ->
		{
			Game newGame = new Game(); // Create a new Game object so all of the fields are reinitialized, and all
										// values are back at zero/default
			// set the rootPane's center to the getPane function defined in my Game class
			rootPane.setCenter(newGame.getPane());
			newGame.fadeBoard(); // FadeTransition animation is applied to all elements in the GridPane
			wasGameReset.set(true); // Setting this AtomicBoolean to true because the game was reset
		});

		// Creating View Buttons here
		// Author View Button
		Button authorView = new Button("Author View");
		authorView.setPrefSize(150, 50);

		authorView.setStyle("-fx-font-size: 15 Courier New; -fx-base: #40E0D0; -fx-font-weight: bold");
		// Button Event Handler
		authorView.setOnAction(e ->
		{
			// When the authorView button is pressed, clear the Top of rootPane, and set the
			// center
			// of rootpane to the Pane designed in Author Class
			rootPane.setTop(null); // when button is pressed, clear the top element of root pane
			rootPane.setCenter(author.getPane()); // set the center element of root pane to Author Pane

		});
		// Button to switch to gameView
		Button gameView = new Button("Game View");
		gameView.setPrefSize(150, 50); // Setting size for button
		gameView.setStyle("-fx-font-size: 15 Courier New; -fx-base: #40E0D0; -fx-font-weight: bold"); // Changing the
																										// button's font
																										// style and
																										// size
		// Game Button action handler
		gameView.setOnAction(e ->
		{
			// Add the replayButton to the top of our rootPane
			rootPane.setTop(replayGameButton);
			rootPane.setAlignment(replayGameButton, (Pos.TOP_CENTER)); // Position the button
			// if the Reset Button has not been pressed (meaning its the first game), set
			// rootPane center to game Pane
			if (wasGameReset.get() == false) {
				rootPane.setCenter(game.getPane());
			}
			// If the reset Button has been pressed (wasGameReset == true), create a new
			// Game object and add it to center of RootPane
			else {
				// reset button works by creating a newGame object and setting the center
				// element of root pane to the Pane returned from newGame.getPane() method
				Game newGame = new Game();
				rootPane.setCenter(newGame.getPane());
			}
		});

		Button homeView = new Button("Home View");
		homeView.setPrefSize(150, 50);
		homeView.setStyle("-fx-font-size: 15 Courier New; -fx-base: #40E0D0; -fx-font-weight: bold");
		// Button Event Handler
		homeView.setOnAction(e ->
		{
			rootPane.setCenter(courseData); // When the home view button is pressed, set root pane's center element to
											// courseData VBox
			rootPane.setTop(welcomeMSG); // and set the top element to the welcome message
		});

		BorderPane.setAlignment(welcomeMSG, Pos.TOP_CENTER); // Aligning my welcome message to Top Center of Border Pane
		welcomeMSG.setTranslateY(100); // Moving the welcome message down 100 pixels

		// Adding view Buttons to the HBox that will display the view Buttons
		viewButtons.getChildren().add(gameView);
		viewButtons.getChildren().add(homeView);
		viewButtons.getChildren().add(authorView);
		viewButtons.setAlignment(Pos.BOTTOM_CENTER);

		rootPane.setCenter(courseData); // setting center of root pane to our course information
		rootPane.setTop(welcomeMSG); // setting top of root pane to Welcome message
		rootPane.setBottom(viewButtons); // Setting our HBox for buttons on the bottom of the BorderPane

		// Creating and setting scene
		Scene scene = new Scene(rootPane, 1000, 750);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
