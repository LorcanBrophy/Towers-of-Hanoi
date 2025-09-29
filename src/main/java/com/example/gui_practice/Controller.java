package com.example.gui_practice;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import javafx.scene.input.MouseEvent;


public class Controller {
    @FXML private Pane tower1, tower2, tower3;
    @FXML private Rectangle base1, base2, base3;
    @FXML private Rectangle disc1, disc2, disc3;

    @FXML
    public void initialize() {
        setupBase(base1, tower1);
        setupBase(base2, tower2);
        setupBase(base3, tower3);
    }

    private void setupBase(Rectangle base, Pane tower) {
        base.widthProperty().bind(tower.widthProperty());
        base.layoutYProperty().bind(tower.heightProperty().subtract(base.heightProperty()));
    }


    private void setupDisc() {}

    /*
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
    }*/
}