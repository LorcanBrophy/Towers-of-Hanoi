package com.example.gui_practice;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class Controller {

    private Rectangle selectedDisc;
    private double offsetX;
    private double offsetY;

    @FXML
    public void mousePressed(MouseEvent event) {
        selectedDisc = (Rectangle) event.getSource();
        offsetX = event.getSceneX() - selectedDisc.getLayoutX();
        offsetY = event.getSceneY() - selectedDisc.getLayoutY();
    }

    @FXML
    public void mouseDragged(MouseEvent event) {
        selectedDisc.setLayoutX(event.getSceneX() - offsetX);
        selectedDisc.setLayoutY(event.getSceneY() - offsetY);
    }

    @FXML
    public void mouseReleased(MouseEvent event) {
        double x = selectedDisc.getLayoutX();

        if (x < 300) {
            selectedDisc.setLayoutX(50);
            selectedDisc.setLayoutY(500);
        } else if (x < 600) {
            selectedDisc.setLayoutX(350);
            selectedDisc.setLayoutY(500);
        } else {
            selectedDisc.setLayoutX(650);
            selectedDisc.setLayoutY(500);
        }
    }
}