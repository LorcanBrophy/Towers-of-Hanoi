package com.example.gui_practice;

import javafx.fxml.FXML;

import javafx.scene.shape.Rectangle;

import javafx.scene.input.MouseEvent;


public class Controller {
    @FXML private Rectangle disc1;

    private double offsetX;
    private double offsetY;

    @FXML
    public void mousePressed(MouseEvent mouseEvent) {
        offsetX = mouseEvent.getSceneX() - disc1.getLayoutX();
        offsetY = mouseEvent.getSceneY() - disc1.getLayoutY();
    }

    @FXML
    public void mouseDragged(MouseEvent mouseEvent) {
        disc1.setLayoutX(mouseEvent.getSceneX() - offsetX);
        disc1.setLayoutY(mouseEvent.getSceneY() - offsetY);
    }
    // I can make it so you check the nearest disc and move it into the right position, rather than making specific conditions for each disc
    public void mouseReleased() {
        if (disc1.getLayoutX() < 300) {
            disc1.setLayoutX(100);
            disc1.setLayoutY(500);
        } else if (disc1.getLayoutX() < 600) {
            disc1.setLayoutX(400);
            disc1.setLayoutY(500);
        } else {
            disc1.setLayoutX(700);
            disc1.setLayoutY(500);
        }
    }
}