/**  Professor: Dr. Eickemeyer
*    02.08.2018
*    Exercise18_35 uses recursion to draw out an I Fractal Example similar to an H Fractal.
*/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.shape.Line;
import javafx.scene.input.KeyCode;

public class Exercise18_35 extends Application{

  //Override the start method
  @Override
  public void start(Stage primStage) throws Exception{
    //I create a fractalPane object called iTreePane, and then draw it with an order of 0.
    fractalPane iTreePane = new fractalPane();
    iTreePane.draw();
    //iTreePane is added to the BorderPane pane
    BorderPane pane = new BorderPane(iTreePane);

    //TextField for the amount of trees is given a base of 0 and a pref. column
    //count of 3.
    TextField count = new TextField();
    count.setPrefColumnCount(2);
    count.setAlignment(Pos.BASELINE_RIGHT);
    count.setText("0");

    //The following checks if enter is pressed
    count.setOnKeyPressed( e -> {
      if(e.getCode() == KeyCode.ENTER){
        try {
          iTreePane.setOrder(Integer.parseInt(count.getText()));
        } catch (NumberFormatException ex){
          iTreePane.setOrder(0);
          count.setText("0");
        }
        iTreePane.draw();
      }
    });

    //Label for the Order box is created, and all is placed in hBox
    Label order = new Label("Enter an Order: ");
    HBox hBox = new HBox(10, order, count);
    //HBox is centered, and then set along the bottom of the Pane.
    hBox.setAlignment(Pos.BASELINE_CENTER);
    pane.setBottom(hBox);
    //Insets are padding all around a square, so I give 5 for each side.
    pane.setPadding(new Insets(5));

    //Scene is created, with the pane added and then shown.
    Scene scene = new Scene(pane);
    primStage.setScene(scene);
    primStage.setTitle("Exercise18_35");
    primStage.show();
    iTreePane.requestFocus();


  }

  //fractalPane class extends Pane class and has fractalPane constructor.
  public class fractalPane extends Pane{
    double recentSize;
    int order = 0;
    double w;
    double h;

    //fractalpane constructor creates default width of 600 and height of 600
    fractalPane() {
      w = 600;
      h = 600;
      recentSize = Math.min(w, h) * 0.4;
      setMinSize(w, h);
      //draw is called to draw the initial I.
      draw();
    }

    //Initial Draw for the first I. No recursion, as order is 0 automatically.
    public void draw(){
      getChildren().clear();
      double x = w * 0.70;
      double y = h * 0.30;
      draw(x, y, order, recentSize);
    }

    //draw method with 4 parameters creates the Lines for the I, and keeps track of order.
    public void draw(double x, double y, int order, double recentSize){
      Line lineOne = new Line(x, y, x - recentSize, y);
      Line lineTwo = new Line(x, y + recentSize, x - recentSize, y + recentSize);
      Line lineThree = new Line(x - (recentSize / 2), y, x - (recentSize / 2), y + recentSize);
      getChildren().addAll(lineOne, lineTwo, lineThree);

      //recursion occurs here. If order > 0, it must keep going.
      if (order > 0) {
        double halfSize = recentSize / 2;
        double offset = halfSize / 2;
        draw(lineOne.getStartY() + offset, lineOne.getEndX() - halfSize / 2, order - 1, halfSize);
        draw(lineTwo.getStartY() + offset, lineOne.getEndX() - halfSize / 2, order - 1, halfSize);
        draw(lineOne.getEndY() + offset, lineOne.getStartX() - halfSize / 2, order - 1, halfSize);
        draw(lineTwo.getEndY() + offset, lineOne.getStartX() - halfSize / 2, order - 1, halfSize);
      }
    }

    //Method sets order given by user and updates it as show in line 27-37.
    public void setOrder(int order) {
      this.order = order;
    }
  }

  public static void main(String[] args){
    Application.launch(args);
  }

}
