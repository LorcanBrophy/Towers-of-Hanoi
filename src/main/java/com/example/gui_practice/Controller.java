package com.example.gui_practice;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import javafx.scene.input.MouseEvent;


public class Controller {
    @FXML private Rectangle rect1;

    private double offsetX;
    private double offsetY;

    @FXML
    public void mousePressed(MouseEvent mouseEvent) {
        offsetX = mouseEvent.getSceneX() - rect1.getLayoutX();
        offsetY = mouseEvent.getSceneY() - rect1.getLayoutY();
    }

    @FXML
    public void mouseDragged(MouseEvent mouseEvent) {
        rect1.setLayoutX(mouseEvent.getSceneX() - offsetX);
        rect1.setLayoutY(mouseEvent.getSceneY() - offsetY);
    }
}