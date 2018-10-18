package com.heroes.model;

import com.heroes.view.UnitView;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import javax.swing.text.html.ImageView;
import java.util.List;

public class MouseUtils extends Pane {


    public static void slideToDestCard(UnitView unit, Square square) {
        if (unit.getDefaultPhoto() == null)
            return;
        double targetX;
        double targetY;
        targetX = square.getLayoutX() - 185;
        targetY = square.getLayoutY() - 215;
        double sourceX = unit.getDefaultPhoto().getLayoutX() + unit.getDefaultPhoto().getTranslateX();
        double sourceY = unit.getDefaultPhoto().getLayoutY() + unit.getDefaultPhoto().getTranslateY();
        animateCardMovement(unit, sourceX, sourceY,
                targetX, targetY, Duration.millis(Math.abs((targetX+targetY) - (sourceX + sourceY))*7), e -> {
//                    unit.getDropShadow().setRadius(2);
//                    unit.getDropShadow().setOffsetX(0);
//                    unit.getDropShadow().setOffsetY(0);
                });

    }

    private static void animateCardMovement(
            UnitView unit, double sourceX, double sourceY,
            double targetX, double targetY, Duration duration,
            EventHandler<ActionEvent> doAfter) {

        Path path = new Path();
        path.getElements().add(new MoveToAbs(unit.getDefaultPhoto(), sourceX, sourceY));
        path.getElements().add(new LineToAbs(unit.getDefaultPhoto(), targetX, targetY));

        PathTransition pathTransition = new PathTransition(duration, path, unit.getDefaultPhoto());
        pathTransition.setInterpolator(Interpolator.EASE_IN);
        pathTransition.setOnFinished(doAfter);


        ParallelTransition pt = new ParallelTransition(unit.getDefaultPhoto(), pathTransition);
        pt.play();
    }


    private static class MoveToAbs extends MoveTo {
        MoveToAbs(Node node, double x, double y) {
            super(x - node.getLayoutX() + node.getLayoutBounds().getWidth() / 2,
                    y - node.getLayoutY() + node.getLayoutBounds().getHeight() / 2);
        }
    }

    private static class LineToAbs extends LineTo {
        LineToAbs(Node node, double x, double y) {
            super(x - node.getLayoutX() + node.getLayoutBounds().getWidth() / 2,
                    y - node.getLayoutY() + node.getLayoutBounds().getHeight() / 2);
        }
    }

}
