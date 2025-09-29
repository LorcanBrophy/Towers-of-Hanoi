package com.example.gui_practice;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Controller {

    @FXML private Label third1;
    @FXML private Label third2;
    @FXML private Label third3;

    @FXML private Rectangle disc1;
    @FXML private Rectangle disc2;
    @FXML private Rectangle disc3;

    private Rectangle selectedDisc;
    private double offsetX;
    private double offsetY;

    private int originalTower;

    private final List<Stack<Rectangle>> towers = new ArrayList<>();

    @FXML
    public void initialize() {
        for (int i = 0; i < 3; i++) {
            towers.add(new Stack<>());
        }

        towers.get(0).push(disc1);
        towers.get(0).push(disc2);
        towers.get(0).push(disc3);

        double towerCenterX = 150;

        disc1.setLayoutX(towerCenterX - disc1.getWidth() / 2);
        disc1.setLayoutY(500);

        disc2.setLayoutX(towerCenterX - disc2.getWidth() / 2);
        disc2.setLayoutY(460);

        disc3.setLayoutX(towerCenterX - disc3.getWidth() / 2);
        disc3.setLayoutY(420);
    }

    @FXML
    public void mousePressed(MouseEvent event) {
        selectedDisc = (Rectangle) event.getSource();

        if (!isTopDisc(selectedDisc)) {
            selectedDisc = null;
            return;
        }

        offsetX = event.getSceneX() - selectedDisc.getLayoutX();
        offsetY = event.getSceneY() - selectedDisc.getLayoutY();

        for (int i = 0; i < towers.size(); i++) {
            Stack<Rectangle> tower = towers.get(i);
            if (!tower.isEmpty() && tower.peek() == selectedDisc) {
                originalTower = i;
            }
            tower.remove(selectedDisc);
        }
    }

    @FXML
    public void mouseDragged(MouseEvent event) {
        if (selectedDisc == null) return;

        selectedDisc.setLayoutX(event.getSceneX() - offsetX);
        selectedDisc.setLayoutY(event.getSceneY() - offsetY);
    }

    @FXML
    public void mouseReleased() {
        if (selectedDisc == null) return;

        double x = selectedDisc.getLayoutX();
        int selectedTower;

        if (x < 300) selectedTower = 0;
        else if (x < 600) selectedTower = 1;
        else selectedTower = 2;

        double towerCenterX = 150 + (selectedTower * 300);

        if (towers.get(selectedTower).isEmpty() || selectedDisc.getWidth() < towers.get(selectedTower).peek().getWidth()) {
            towers.get(selectedTower).push(selectedDisc);
        } else {
            selectedTower = originalTower;
            towerCenterX = 150 + (originalTower * 300);
            towers.get(originalTower).push(selectedDisc);
        }

        selectedDisc.setLayoutX(towerCenterX - (selectedDisc.getWidth() / 2));
        selectedDisc.setLayoutY(500 - (towers.get(selectedTower).size() - 1) * 40);

    }

    private boolean isTopDisc(Rectangle disc) {
        for (Stack<Rectangle> tower : towers) {
            if (!tower.isEmpty() && tower.peek() == disc) {
                return true;
            }
        }
        return false;
    }
}