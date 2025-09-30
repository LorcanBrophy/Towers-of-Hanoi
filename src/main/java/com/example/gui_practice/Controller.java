package com.example.gui_practice;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Controller {

    public Label title;
    public Label moveCount;
    @FXML private Label winText;


    @FXML private Rectangle disc1;
    @FXML private Rectangle disc2;
    @FXML private Rectangle disc3;

    @FXML private Rectangle selectedDisc;

    private double offsetX;
    private double offsetY;

    private int originalTower;

    private int moveCounter;
    private boolean gameOver = false;

    private final List<Stack<Rectangle>> towers = new ArrayList<>();

    @FXML
    public void initialize() {
        for (int i = 0; i < 3; i++) {
            towers.add(new Stack<>());
        }

        towers.getFirst().push(disc1);
        towers.getFirst().push(disc2);
        towers.getFirst().push(disc3);

        double towerCenterX = 150;

        disc1.setLayoutX(towerCenterX - disc1.getWidth() / 2);
        disc1.setLayoutY(500);

        disc2.setLayoutX(towerCenterX - disc2.getWidth() / 2);
        disc2.setLayoutY(460);

        disc3.setLayoutX(towerCenterX - disc3.getWidth() / 2);
        disc3.setLayoutY(420);

        List<Rectangle> discs = List.of(disc1, disc2, disc3);

        for (Rectangle disc : discs) {
            disc.setOnMouseEntered(_ -> disc.setCursor(isTopDisc(disc) ? Cursor.OPEN_HAND : Cursor.DEFAULT));
            disc.setOnMouseExited(_ -> disc.setCursor(Cursor.DEFAULT));
        }

        moveCount.setText("Moves: " + moveCounter);
    }

    @FXML
    public void mousePressed(MouseEvent event) {
        if (gameOver) return;

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
        if (gameOver) return;

        selectedDisc.setCursor(Cursor.CLOSED_HAND);

        selectedDisc.setLayoutX(event.getSceneX() - offsetX);
        selectedDisc.setLayoutY(event.getSceneY() - offsetY);
    }

    @FXML
    public void mouseReleased() {
        if (selectedDisc == null) return;
        if (gameOver) return;

        double x = selectedDisc.getLayoutX();
        int selectedTower;

        if (x < 300) selectedTower = 0;
        else if (x < 600) selectedTower = 1;
        else selectedTower = 2;

        double towerCenterX = 150 + (selectedTower * 300);

        if (towers.get(selectedTower).isEmpty() || selectedDisc.getWidth() < towers.get(selectedTower).peek().getWidth()) {
            towers.get(selectedTower).push(selectedDisc);

            if (selectedTower != originalTower) {
                moveCounter++;
                moveCount.setText("Moves: " + moveCounter);
            }

        } else {
            selectedTower = originalTower;
            towerCenterX = 150 + (originalTower * 300);
            towers.get(originalTower).push(selectedDisc);
        }

        selectedDisc.setLayoutX(towerCenterX - (selectedDisc.getWidth() / 2));
        selectedDisc.setLayoutY(500 - (towers.get(selectedTower).size() - 1) * 40);

        if (towers.get(2).size() == 3) gameFinished();
    }

    private boolean isTopDisc(Rectangle disc) {
        for (Stack<Rectangle> tower : towers) {
            if (!tower.isEmpty() && tower.peek() == disc) {
                return true;
            }
        }
        return false;
    }

    private void gameFinished() {
        gameOver = true;

        winText.setText("YOU WIN!");
    }

    public void resetGame() {
        for (Stack<Rectangle> tower : towers) {
            tower.clear();
        }

        towers.getFirst().push(disc1);
        towers.getFirst().push(disc2);
        towers.getFirst().push(disc3);

        disc1.setLayoutX(150 - disc1.getWidth() / 2);
        disc1.setLayoutY(500);

        disc2.setLayoutX(150 - disc2.getWidth() / 2);
        disc2.setLayoutY(460);

        disc3.setLayoutX(150 - disc3.getWidth() / 2);
        disc3.setLayoutY(420);

        gameOver = false;
        moveCounter = 0;
        winText.setText("");

        moveCount.setText("Moves: " + moveCounter); // update move counter cause it stayed at the previous num
    }
}