
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
    ArrayList<ImageView> imageList;
    ArrayList<ImageView> guesses;
    ArrayList<ImageView> guessesRight;



    Label winMSG;
   Text scoreCounter;
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

    private GridPane gridPane;
    Random rand = new Random();

    public Game() {

        gridPane = new GridPane();
    //Basically, I created an arrayList for the ImageViews to hold the images
        //And I created a guesses ArrayList which holds up to two ImageView objects based on onMouseClickEvent
        //Once two ImageViews are added to this list, it checks if the images are the same in the isMatch() method
        //If two ImageViews get added, they are added to guessedRight list
        guesses = new ArrayList<>();
        imageList = new ArrayList<>();
        guessesRight = new ArrayList<>();


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


        for (int i=0; i<20; i++) {
            imageList.get(i).setOnMouseClicked(event);
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (!imageList.isEmpty()) {
                    int tempNum = rand.nextInt(imageList.size());

                    gridPane.setRowIndex(imageList.get(tempNum), i);

                    gridPane.setColumnIndex(imageList.get(tempNum), j);

                    gridPane.getChildren().addAll(imageList.get(tempNum));


                    imageList.get(tempNum).setOpacity(0);
                    imageList.get(tempNum).setFitHeight(98);
                    imageList.get(tempNum).setFitWidth(98);
                    imageList.remove(tempNum);
                }
            }
        }
            //Message for winning
        winMSG = new Label("You win!");
        winMSG.setFont(Font.font("Verdana", 22));
        winMSG.setTextFill(Color.RED);

        winMSG.setTranslateX(420);
        winMSG.setTranslateY(-200);

        //Score label
        scoreCounter = new Text("Score: " + guessesRight.size()/2 + " /10");
        scoreCounter.setFont(Font.font("Verdana", 20));
        scoreCounter.setFill(Color.RED);


        scoreCounter.setTranslateX(400);
        scoreCounter.setTranslateY(300);

        gridPane.getChildren().add(scoreCounter);

        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);


    }
    //Event for uncovering
    EventHandler<MouseEvent> event = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(final MouseEvent ME ) {
            Object obj = ME.getSource();
            if (!guessesRight.contains((ImageView) obj)){
                FadeTransition uncover = new FadeTransition(Duration.seconds(.5));
                uncover.setFromValue(0);
                uncover.setToValue(1);
                FadeTransition cover = new FadeTransition(Duration.seconds(.75));
                cover.setFromValue(1);
                cover.setToValue(0);
                FadeTransition cover2 = new FadeTransition(Duration.seconds(.75));
                cover2.setFromValue(1);
                cover2.setToValue(0);

                if (guesses.size() < 2) {
                    guesses.add((ImageView) obj);
                    System.out.println("List size: " + guesses.size());
                    if (obj instanceof ImageView) {
                        uncover.setNode(((ImageView) obj));
                        uncover.play();
                    }
                }
                if (guesses.size() == 2) {
                    if (isMatch(guesses.get(1), guesses.get(0))) {
                        System.out.println("Good Match!");
                        guessesRight.add(guesses.get(1));
                        guessesRight.add(guesses.get(0));
                        guesses.get(1).setOpacity(1);
                        guesses.get(0).setOpacity(1);
                        guesses.clear();
                        scoreCounter.setText("Score: " + guessesRight.size()/2 + " /10");
                        System.out.println("List size: " + guesses.size());
                    }
                    else {
                        System.out.println("Not a match");
                        cover.setNode(guesses.get(1));
                        cover.play();
                        cover2.setNode(guesses.get(0));
                        cover2.play();
                        guesses.clear();
                        System.out.println("List size: " + guesses.size());
                    }
                }

            }
            else {
                System.out.println("Spaces already guessed correctly");
            }
            if ((guessesRight.size() == 20) && !(gridPane.getChildren().contains(winMSG))) {
                gridPane.getChildren().add(winMSG);
            }
        }
    };

    public boolean isMatch(ImageView image1, ImageView image2) {
        if ((image1 == this.pancake && image2 == this.pancake2) || (image1 == this.pancake2 && image2 == this.pancake)) {
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
        if ((image1 == this.pie && image2 == this.pie2) || (image1 == this.pie2 && image2 == this.pie)){
            return true;
        }
        if ((image1 == this.fries && image2 == this.fries2) || (image1 == this.fries2 && image2 == this.fries)) {
            return true;
        }
        if ((image1 == this.coke && image2 == this.coke2) || (image1 == this.coke2 && image2 == this.coke)){
            return true;
        }
        if ((image1 == this.icecream && image2 == this.icecream2) || (image1 == this.icecream2 && image2 == this.icecream)){
            return true;
        }
        if ((image1 == this.shake && image2 == this.shake2) || (image1 == this.shake2 && image2 == this.shake)){
            return true;
        }
        if ((image1 == this.pizza && image2 == this.pizza2) || (image1 == this.pizza2 && image2 == this.pizza)) {
            return true;
        }
        return false;
    }
    public void fadeBoard() {
        for (int i=0; i<20; i++) {
            FadeTransition gameFade = new FadeTransition(Duration.seconds(1), gridPane.getChildren().get(i));
            gameFade.setFromValue(1);
            gameFade.setToValue(0);
            gameFade.play();
        }
    }

    public GridPane getPane() {
        return gridPane;
    }

}
