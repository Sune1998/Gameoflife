package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.canvas.*;



public class UIlogic extends VBox {
    private Button runButton;
    private Canvas canvas;

    private Affine affine;
    private Logic logic;

    public UIlogic(){
        this.runButton = new Button("run");
        this.runButton.setOnAction(actionEvent -> {
            logic.runSim();
            draw();
        });

        this.canvas = new Canvas(400,400);
        this.getChildren().addAll(this.runButton, this.canvas);

        this.affine = new Affine();
        this.affine.appendScale(400 / 10f, 400 / 10f);

        this.logic = new Logic(10, 10);

        logic.Alive(2,2);
        logic.Alive(3,2);
        logic.Alive(4,2);

        logic.Alive(5,5);
        logic.Alive(5,6);
        logic.Alive(6,5);
        logic.Alive(6,6);
        logic.Alive(4,5);
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
