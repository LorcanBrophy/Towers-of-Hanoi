package com.example.gui_practice;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.util.Stack;

public class Controller {

    @FXML private Label third1;
    @FXML private Label third2;
    @FXML private Label third3;

    @FXML private Rectangle disc1;
    @FXML private Rectangle disc2;

    private Rectangle selectedDisc;
    private double offsetX;
    private double offsetY;

    private final Stack<Rectangle>[] towers = new Stack[3];

    @FXML
    public void initialize() {
        for (int i = 0; i < 3; i++) {
            towers[i] = new Stack<>();
        }
        towers[0].push(disc1);
        towers[0].push(disc2);

        double towerCenterX = 150;

        disc1.setLayoutX(towerCenterX - disc1.getWidth() / 2);
        disc1.setLayoutY(500);

        disc2.setLayoutX(towerCenterX - disc2.getWidth() / 2);
        disc2.setLayoutY(460);

        updateLabels();
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

        for (Stack<Rectangle> disc : towers) {
            disc.remove(selectedDisc);
        }
        updateLabels();
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

        if (towers[selectedTower].isEmpty() || selectedDisc.getWidth() < towers[selectedTower].peek().getWidth()) {
            towers[selectedTower].push(selectedDisc);

            double towerCenterX = 150 + (selectedTower * 300);
            selectedDisc.setLayoutX(towerCenterX - (selectedDisc.getWidth() / 2));

            selectedDisc.setLayoutY(500 - (towers[selectedTower].size() - 1) * 40);
        } else {
            towers[0].push(selectedDisc);
            selectedDisc.setLayoutX(50);
            selectedDisc.setLayoutY(500 - (towers[0].size() - 1) * 40);
        }

        updateLabels();
    }

    private void updateLabels() {
        third1.setText(String.valueOf(towers[0].size()));
        third2.setText(String.valueOf(towers[1].size()));
        third3.setText(String.valueOf(towers[2].size()));
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