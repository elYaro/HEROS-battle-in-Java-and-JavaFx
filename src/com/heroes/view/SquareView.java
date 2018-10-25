package com.heroes.view;

import com.heroes.model.Square;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class SquareView extends Pane {

    private Rectangle rect;
    private Node rectView;
    private static double width = 53;
    private static double heigth = 53;
    private static double arcWidth = 53;
    private static double arcHeigth = 53;
    private Square square;
    private String name;



    public String getName() {
        return name;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Node getRectView() {
        return rectView;
    }

    public void setRectView(Node rectView) {
        this.rectView = rectView;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public SquareView(Square square) {
        this.square = square;
        this.name = square.getName() + " view";
        this.rect = newSquareView(square);
    }



    private Rectangle newSquareView(Square square){
        Rectangle rect = new Rectangle(this.width, this.heigth);
        rect.setArcHeight(this.arcWidth);
        rect.setHeight(this.arcHeigth);

        rect.setFill(Color.OLIVEDRAB);
        rect.setId(square.getName());
        rect.setStyle("-fx-opacity: 0.5");
        return rect;
    }

}
