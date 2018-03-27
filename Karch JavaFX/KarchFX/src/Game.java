
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Game extends GridPane {
	// Declare nodes and imageViews that will be used on Pane
	ArrayList<ImageView> imageList; // ArrayList of imageViews that will hold all of the game Images
	ArrayList<ImageView> guesses; // ArrayList that will temporarily store imageViews of attempted matches
	ArrayList<ImageView> guessesCorrect; // ArrayList that will store the imageViews of correct matches

	Label winMSG; // This label will display text "You win"
	Text scoreCounter; // Text object to display score

	/*
	 * Creating 20 imageView objects 10 objects will hold different game Images
	 * (those used in the game) the other 10 objects will hold a copy of the game
	 * Images The objects will be compared to each other in order to decide a
	 * correct match or incorrect match
	 */
	ImageView burger = new ImageView("BURGER.jpg");
	ImageView coke = new ImageView("COKE.jpg");
	ImageView cookie = new ImageView("COOKIE.jpg");
	ImageView fries = new ImageView("Fries.jpg");
	ImageView icecream = new ImageView("ICECREAM - Copy.png");
	ImageView pancake = new ImageView("PANCAKE - Copy.jpg");
	ImageView pie = new ImageView("PIE - Copy.jpg");
	ImageView pizza = new ImageView("Pizza - Copy.png");
	ImageView shake = new ImageView("SHAKE.png");
	ImageView taco = new ImageView("TACO.png");

	ImageView burger2 = new ImageView("BURGER.jpg");
	ImageView coke2 = new ImageView("COKE.jpg");
	ImageView cookie2 = new ImageView("COOKIE.jpg");
	ImageView fries2 = new ImageView("Fries.jpg");
	ImageView icecream2 = new ImageView("ICECREAM - Copy.png");
	ImageView pancake2 = new ImageView("PANCAKE - Copy.jpg");
	ImageView pie2 = new ImageView("PIE - Copy.jpg");
	ImageView pizza2 = new ImageView("Pizza - Copy.png");
	ImageView shake2 = new ImageView("SHAKE.png");
	ImageView taco2 = new ImageView("TACO.png");

	private GridPane gridPane; // A gridpane is used to display the game
	Random rand = new Random(); // Random object, used to randomly shuffle the game board

	public Game() {

		gridPane = new GridPane();
		/*
		 * Basically, I created an arrayList for the ImageViews to hold the images And I
		 * created a guesses ArrayList which holds up to two ImageView objects based on
		 * onMouseClickEvent Once two ImageViews are added to the guesses ArrayList, it
		 * checks if the images are the same using the isMatch() method If two
		 * ImageViews are a match, they are added to guessedRight list
		 */
		// The guessedRight ArrayList is used to keep score and to prevent the
		// onMouseClickEvent on correctly guessed game cells
		guesses = new ArrayList<>();
		imageList = new ArrayList<>();
		guessesCorrect = new ArrayList<>();

		// Adding all of the imageView objects to the imageList ArrayList
		imageList.add(burger);
		imageList.add(coke);
		imageList.add(cookie);
		imageList.add(fries);
		imageList.add(icecream);
		imageList.add(pancake);
		imageList.add(pie);
		imageList.add(pizza);
		imageList.add(shake);
		imageList.add(taco);
		imageList.add(burger2);
		imageList.add(coke2);
		imageList.add(cookie2);
		imageList.add(fries2);
		imageList.add(icecream2);
		imageList.add(pancake2);
		imageList.add(pie2);
		imageList.add(pizza2);
		imageList.add(shake2);
		imageList.add(taco2);

		// For loop used to set the mouseClick event to each object
		for (int i = 0; i < 20; i++) { // 20 iterations
			imageList.get(i).setOnMouseClicked(event); // add the event to each object at 'i'
		}

		// Create the game board using nested loops.
		// Since there are 20 images, the loops will iterate 10 times each
		for (int i = 0; i < 10; i++) { // row
			for (int j = 0; j < 10; j++) { // column
				if (!imageList.isEmpty()) { // if imageList is not empty
					int tempNum = rand.nextInt(imageList.size()); // generate a random number between 0 and size of the
																	// imageList and assign it to tempNum integer

					gridPane.setRowIndex(imageList.get(tempNum), i); // Set the gridPane row index to the 'tempNum' in
																		// imageList at 'i'

					gridPane.setColumnIndex(imageList.get(tempNum), j); // Set the gridPane column index to the
																		// 'tempNum' in imageList at 'j'

					gridPane.getChildren().addAll(imageList.get(tempNum)); // Add imageView element from imageList index
																			// (random number) to the class's pane

					// Set the radomly selected imageView to 0 opacity so it appears covered
					imageList.get(tempNum).setOpacity(0);
					// Set the size of the imageView
					imageList.get(tempNum).setFitHeight(98);
					imageList.get(tempNum).setFitWidth(98);
					// Finally, remove the imageView object from the imageList array so it cannot be
					// generated again
					imageList.remove(tempNum);
				}
			}
		}
		// Message for winning
		winMSG = new Label("You win!");
		winMSG.setFont(Font.font("Verdana", 22));
		winMSG.setTextFill(Color.RED);
		// Set winMSG location
		winMSG.setTranslateX(420);
		winMSG.setTranslateY(-200);

		// Score label
		scoreCounter = new Text("Score: " + guessesCorrect.size() / 2 + " /10");
		scoreCounter.setFont(Font.font("Verdana", 20));
		scoreCounter.setFill(Color.RED);

		// Set scoreCounter location
		scoreCounter.setTranslateX(400);
		scoreCounter.setTranslateY(300);

		// Add score counter to class's grid pane
		gridPane.getChildren().add(scoreCounter);

		// Set the Grid Lines to visible(true) to draw game board's borders
		gridPane.setGridLinesVisible(true);
		// Align the grid pane to center so the game board will be in the center of the
		// root pane in Main class
		gridPane.setAlignment(Pos.CENTER);

	}

	// Define mouseclick Event for uncovering and scoring match attempts
	EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
		@Override
		public void handle(final MouseEvent ME) {
			Object clickedObj = ME.getSource(); // Get the source of the tile, which will provide the imageView object

			// Conditional to check if the clicked tile has not already been correctly
			// matched
			if (!guessesCorrect.contains((ImageView) clickedObj)) { // if the clicked tile is a valid guess (not already
																	// guessed correctly)
				// Fade animation to uncover tile
				FadeTransition uncover = new FadeTransition(Duration.seconds(.5));
				uncover.setFromValue(0);
				uncover.setToValue(1);
				// Fade animation to cover first image
				FadeTransition cover = new FadeTransition(Duration.seconds(.75));
				cover.setFromValue(1);
				cover.setToValue(0);
				// Fade animation to cover second image
				FadeTransition cover2 = new FadeTransition(Duration.seconds(.75));
				cover2.setFromValue(1);
				cover2.setToValue(0);

				// If guesses ArrayList (used to store guesses) contains less than two elements
				if (guesses.size() < 2) {
					guesses.add((ImageView) clickedObj); // Add the clicked imageView object to the guesses ArrayList
					if (clickedObj instanceof ImageView) { // if clicked object is an instance of ImageView
						// set the uncover animation to clicked imageView
						uncover.setNode(((ImageView) clickedObj));
						uncover.play();
					}
				}

				// If there are 2 elements in guesses ArrayList, check if they match
				if (guesses.size() == 2) {
					// Call isMatch method to check if the two imageViews in guesses ArrayList are a
					// correct match
					// if they are a match, add the two imageViews to guessesCorrect ArrayList
					if (isMatch(guesses.get(1), guesses.get(0))) {
						guessesCorrect.add(guesses.get(1));
						guessesCorrect.add(guesses.get(0));
						guesses.get(1).setOpacity(1); // Set the opacity of the matched imageViews to 1 so they remain
														// visible
						guesses.get(0).setOpacity(1); // Set the opacity of the matched imageViews to 1 so they remain
														// visible
						guesses.clear(); // Clear the guesses ArrayList so we can store two new clicked images
						// Update the Score Counter
						scoreCounter.setText("Score: " + guessesCorrect.size() / 2 + " /10");
					}
					else { // If the images were NOT a match
							// Cover the guessed images again
						cover.setNode(guesses.get(1));
						cover.play();
						cover2.setNode(guesses.get(0));
						cover2.play();
						guesses.clear(); // Clear the guesses ArrayList so we can store two new clicked images
					}
				}

			}
			else {
				System.out.println("Spaces already guessed correctly"); // For debugging, outputs a message in console
																		// saying that tile has already been matched
			}

			// Conditional to display win Message:
			// If 20 correct guesses in guessesCorrect ArrayList and win message is not
			// already displayed
			if ((guessesCorrect.size() == 20) && !(gridPane.getChildren().contains(winMSG))) {
				// Add winMessage to grid pane
				gridPane.getChildren().add(winMSG);
			}
		}
	};

	// isMatch method is used to check if the two clicked objects (that are stored
	// in guesses ArrayList) match
	public boolean isMatch(ImageView image1, ImageView image2) {

		// Checks all possible combinations that would result in a correct match, and
		// returns true if they match
		// For example, if (image1 == taco) && (image2 == taco2) or (image1 == taco2) &&
		// (image2 == taco)
		if ((image1 == this.pancake && image2 == this.pancake2)
				|| (image1 == this.pancake2 && image2 == this.pancake)) {
			return true;
		}
		if ((image1 == this.taco && image2 == this.taco2) || image1 == this.taco2 && image2 == this.taco) {
			return true;
		}
		if ((image1 == this.burger && image2 == this.burger2) || (image1 == this.burger2 && image2 == this.burger)) {
			return true;
		}
		if ((image1 == this.cookie && image2 == this.cookie2) || (image1 == this.cookie2 && image2 == this.cookie)) {
			return true;
		}
		if ((image1 == this.pie && image2 == this.pie2) || (image1 == this.pie2 && image2 == this.pie)) {
			return true;
		}
		if ((image1 == this.fries && image2 == this.fries2) || (image1 == this.fries2 && image2 == this.fries)) {
			return true;
		}
		if ((image1 == this.coke && image2 == this.coke2) || (image1 == this.coke2 && image2 == this.coke)) {
			return true;
		}
		if ((image1 == this.icecream && image2 == this.icecream2)
				|| (image1 == this.icecream2 && image2 == this.icecream)) {
			return true;
		}
		if ((image1 == this.shake && image2 == this.shake2) || (image1 == this.shake2 && image2 == this.shake)) {
			return true;
		}
		if ((image1 == this.pizza && image2 == this.pizza2) || (image1 == this.pizza2 && image2 == this.pizza)) {
			return true;
		}
		return false;
	}

	// Method to fade the board using FadeTransition
	// This method is called when game is reset
	public void fadeBoard() {
		// for loop that iterates 20 times and adds a fadeTransition to each imageView
		// to create a fade affect to the entire board
		for (int i = 0; i < 20; i++) {
			FadeTransition gameFade = new FadeTransition(Duration.seconds(1), gridPane.getChildren().get(i));
			gameFade.setFromValue(1);
			gameFade.setToValue(0);
			gameFade.play();
		}
	}

	// Method used to return the pane, called when Main class sets Center element to
	// this pane
	public GridPane getPane() {
		return gridPane;
	}

}
