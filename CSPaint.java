//I worked on the homework assignment alone, using only course materials.
//javac --module-path javafx-sdk-11.0.2/lib --add-modules=javafx.controls fileName.java
//java --module-path javafx-sdk-11.0.2/lib --add-modules=javafx.controls fileName
import java.applet.*;
import java.awt.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.RadioButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.geometry.Pos;

/**
* CSPaint: This class paints on a canvas
* @author Tiffany Su (tsu40)
* @version 1331
*/

public class CSPaint extends Application {
    private int count = 0; // must be private and have accesor methods
    /**
    * The getCount() method returns the instance variable count
    * @return the value of the instance variable count is returned
    */
    public int getCount() {
        return count;
    }
    /**
    * The setCount() method allows the user to set the number of shapes or the value for count
    * @param c the value you are setting count to
    * @return the count value is returned
    */
    public int setCount(int c) {
        count = c;
        return count;
    }

    private Color theColor = Color.BLACK;

    @Override
    /**
    *The start method creates a canvas and allows the user to draw or make circles
    * and erase their "painting" on this canvas. It also allows the user to keep
    * track of the number of circles that were made and the coordinates of the
    * mouse in the canvas in the bottom of the pane. And users can change the
    * color of their painting by typing a color in the text field. If an invalid
    * color is typed, then an error pop-up window will come up
    */
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

        StackPane sPane = new StackPane();
        Canvas canvas = new Canvas(650, 450);
        sPane.getChildren().add(canvas);
        sPane.setStyle("-fx-background-color: white");
        pane.setCenter(sPane);

        //button & labels
        Label options = new Label("Choose an option");
        Label type = new Label("Type a color:");
        Label sandp = new Label("submit color then paint !");
        Label goaway = new Label("clear canvas");
        Button submit = new Button("submit");
        submit.setStyle("-fx-text-fill: green");
        Button clear = new Button("clear");
        clear.setStyle("-fx-text-fill: red");
        Label label = new Label("Using CSPaint");
        final ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("draw");
        rb1.setToggleGroup(group);
        rb1.setUserData("one");
        RadioButton rb2 = new RadioButton("erase");
        rb2.setToggleGroup(group);
        rb2.setUserData("two");
        RadioButton rb3 = new RadioButton("circle");
        rb3.setToggleGroup(group);
        rb3.setUserData("three");
        RadioButton rb4 = new RadioButton("square");
        rb4.setToggleGroup(group);
        rb4.setUserData("four");
        RadioButton rb5 = new RadioButton("oval");
        rb5.setToggleGroup(group);
        rb5.setUserData("five");
        RadioButton rb6 = new RadioButton("rectangle");
        rb6.setToggleGroup(group);
        rb6.setUserData("six");

        TextField textfield = new TextField();
        VBox vbox = new VBox(options, rb1, rb2, rb3, rb4, rb5, rb6);
        VBox vbox2 = new VBox(vbox, type, textfield, submit, sandp, clear, goaway);
        vbox.setSpacing(10);
        vbox2.setSpacing(15);
        vbox.setAlignment(Pos.BASELINE_CENTER);
        pane.setLeft(vbox2);

        Text shapes = new Text(String.format("Number of Shape: %d", count));
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged((MouseEvent pme) -> {
                if (group.getSelectedToggle().getUserData().equals("one")) {
                    gc.setFill(theColor);
                    gc.fillOval(pme.getX(), pme.getY(), 4, 4);
                } else if (group.getSelectedToggle().getUserData().equals("two")) {
                    gc.setFill(Color.WHITE);
                    gc.fillOval(pme.getX(), pme.getY(), 20, 20);
                    if (count < 0) {
                        count = 0;
                    }
                    shapes.setText(String.format("Number of shape: %d", count));
                }
            });

        canvas.setOnMousePressed((MouseEvent cme) -> {
                if (group.getSelectedToggle().getUserData().equals("three")) {
                    gc.setFill(theColor);
                    gc.fillOval(cme.getX(), cme.getY(), 30, 30);
                    count++;
                    shapes.setText(String.format("Number of shape: %d", count));
                } else if (group.getSelectedToggle().getUserData().equals("two")) {
                    gc.setFill(Color.WHITE);
                    gc.fillOval(cme.getX(), cme.getY(), 20, 20);
                    if (count < 0) {
                        count = 0;
                    }
                    shapes.setText(String.format("Number of shape: %d", count));
                } else if (group.getSelectedToggle().getUserData().equals("four")) {
                    gc.setFill(theColor);
                    gc.fillRect(cme.getX(), cme.getY(), 30, 30);
                    count++;
                    shapes.setText(String.format("Number of shape: %d", count));
                } else if (group.getSelectedToggle().getUserData().equals("five")) {
                    gc.setFill(theColor);
                    gc.fillOval(cme.getX(), cme.getY(), 15, 30);
                    count++;
                    shapes.setText(String.format("Number of shape: %d", count));
                } else if (group.getSelectedToggle().getUserData().equals("six")) {
                    gc.setFill(theColor);
                    gc.fillRect(cme.getX(), cme.getY(), 30, 15);
                    count++;
                    shapes.setText(String.format("Number of shape: %d", count));
                }
            });

        Text coords = new Text();
        canvas.setOnMouseMoved((MouseEvent c) -> {
                coords.setText("(" + c.getX() + ", " + c.getY() + ")");
            });

        HBox hbox = new HBox(coords, shapes);
        hbox.setSpacing(10);
        pane.setBottom(hbox);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CSPaint");
        primaryStage.show();

        clear.setOnAction((cc) -> {
                count = 0;
                GraphicsContext cgc = canvas.getGraphicsContext2D();
                cgc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                shapes.setText(String.format("Number of shape: %d", count));

            });
        submit.setOnAction((sub) -> {
                try {
                    theColor = Color.valueOf(textfield.getText());
                } catch (IllegalArgumentException iae) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Look, an Error Dialog");
                    alert.setContentText("the text you entered is not a valid color");
                    alert.showAndWait();
                }

            });
    }
}
