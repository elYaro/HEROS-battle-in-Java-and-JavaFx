package com.heroes.model;

import com.heroes.view.UnitView;
import javafx.animation.*;
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
import java.util.Collection;
import java.util.List;

public class MouseUtils extends Pane {


    public static void moveToSquare(Unit unit, Square square) {
        if (unit.getUnitView().getDefaultPhoto() == null)
            return;

        double targetX = 0;
        double targetY = square.getLayoutY() - 215;

        if (unit.getOwner().getName().equals("P2")) {
            targetX = square.getLayoutX() - 225;
        }
        if (unit.getOwner().getName().equals("P1")) {
            targetX = square.getLayoutX() - 165;
        }

        double sourceX = unit.getUnitView().getDefaultPhoto().getLayoutX() + unit.getUnitView().getDefaultPhoto().getTranslateX();
        double sourceY = unit.getUnitView().getDefaultPhoto().getLayoutY() + unit.getUnitView().getDefaultPhoto().getTranslateY();

        Timeline timeLine = new Timeline();

        Collection<KeyFrame> frames = timeLine.getKeyFrames();
        Duration frameGap = Duration.millis(146);
        Duration frameTime = Duration.ZERO;
        for (Image img : unit.getUnitView().getMoveAnimation()) {
            frameTime = frameTime.add(frameGap);
            frames.add(new KeyFrame(frameTime, e -> unit.getUnitView().getDefaultPhoto().setImage(img)));
        }
        timeLine.setCycleCount(Animation.INDEFINITE);
        timeLine.play();

        double moveTime = (Math.sqrt(Math.pow((targetX - sourceX), 2) + Math.pow((sourceY - targetY), 2))) * 6;

        animateCardMovement(unit, sourceX, sourceY, targetX, targetY, Duration.millis(moveTime));

        unit.setX(square.getLocationX());       //@Yaro: updates the location X of unit to the destination X
        unit.setY(square.getLocationY());       //@Yaro: updates the location Y of unit to the destination Y

        new Thread(() -> {
            try {
                Thread.sleep((long) (Math.abs(moveTime)));
                timeLine.stop();
                unit.getUnitView().getDefaultPhoto().setImage(unit.getUnitView().getStandPhoto());
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();

    }

    private static void animateCardMovement(
            Unit unit, double sourceX, double sourceY,
            double targetX, double targetY, Duration duration) {


        Path path = new Path();
        path.getElements().add(new MoveToAbs(unit.getUnitView().getDefaultPhoto(), sourceX, sourceY));
        path.getElements().add(new LineToAbs(unit.getUnitView().getDefaultPhoto(), targetX, targetY));

        PathTransition pathTransition = new PathTransition(duration, path, unit.getUnitView().getDefaultPhoto());
        pathTransition.setInterpolator(Interpolator.EASE_IN);


        ParallelTransition pt = new ParallelTransition(unit.getUnitView().getDefaultPhoto(), pathTransition);
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
