package Gameoflife;

import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.canvas.*;
import javafx.scene.transform.NonInvertibleTransformException;


public class UIlogic extends VBox {
    private Button runButton;
    private Canvas canvas;

    private Affine affine;
    private Logic logic;

    private int drawMode;

    public UIlogic(){
        this.runButton = new Button("run");
        this.runButton.setOnAction(actionEvent -> {
            logic.runSim();
            draw();
        });

        this.canvas = new Canvas(400,400);
        this.canvas.setOnMousePressed(this::handleDraw);
        this.canvas.setOnMouseDragged(this::handleDraw);

        this.setOnKeyPressed(this::onKeyPressed);

        this.getChildren().addAll(this.runButton, this.canvas);



        this.affine = new Affine();
        this.affine.appendScale(400 / 10f, 400 / 10f);

        this.logic = new Logic(10, 10);
    }

    private void onKeyPressed(KeyEvent keyEvent){
        if (keyEvent.getCode() == KeyCode.I) {
            this.drawMode = 1;
            System.out.println("insert mode");
        }else if (keyEvent.getCode() == KeyCode.D){
            this.drawMode = 0;
            System.out.println("delete mode");
        }
    }

    private void handleDraw(MouseEvent event){
        double mouseX = event.getX();
        double mouseY = event.getY();

        try {
            Point2D modelCoord = this.affine.inverseTransform(mouseX, mouseY);

            int simX = (int) modelCoord.getX();
            int simY = (int) modelCoord.getY();

            System.out.println(simX + "," + simY);

            this.logic.setState(simX, simY, drawMode);
            draw();

        }catch (NonInvertibleTransformException e) {
            System.out.println("error!");
        }
    }


    public void draw(){
       GraphicsContext g = this.canvas.getGraphicsContext2D();
       g.setTransform(this.affine);

       g.setFill(Color.LIGHTGRAY);
       g.fillRect(0, 0, 450, 450);

       g.setFill(Color.BLACK);
        for (int x = 0; x < this.logic.width; x++) {
            for (int y = 0; y < this.logic.height; y++) {
                if (this.logic.getState(x,y) == 1){
                    g.fillRect(x, y, 1, 1);
                }
            }
        }
        g.setStroke(Color.GRAY);
        g.setLineWidth(0.05);
        for (int x = 0; x <= this.logic.width; x++) {
            g.strokeLine(x, 0, x, 10);

        }

        for (int y = 0; y <= this.logic.height ; y++) {
            g.strokeLine(0,y,10,y);

        }
    }

}
