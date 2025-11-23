package com.fxapp;

import com.Interpolation.Interpolation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;
import com.rasterization.Rasterization;
import javafx.scene.paint.Color;


public class RasterizationController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Canvas canvas;

    @FXML
    private TextField xField;
    @FXML
    private TextField yField;
    @FXML
    private TextField aField;
    @FXML
    private TextField bField;

    @FXML
    private Button rastButton;

    @FXML
    private Button interpolateButton;

    @FXML
    private ColorPicker startColorPicker;

    @FXML
    private ColorPicker endColorPicker;

    @FXML
    private ComboBox<String> metodComboBox;

    private final String[] items = {"Bbox", "Центр"};

    @FXML
    private void initialize() {

        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) ->
                canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) ->
                canvas.setHeight(newValue.doubleValue()));

        interpolateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    int x =  Integer.parseInt(xField.getText());
                    int y =  Integer.parseInt(yField.getText());
                    int a =  Integer.parseInt(aField.getText());
                    int b =  Integer.parseInt(bField.getText());
                    String metod = metodComboBox.getValue();
                    Color startColor = startColorPicker.getValue();
                    Color endColor = endColorPicker.getValue();
                    switch (metod) {
                        case "Bbox":
                            interpolateOval(x, y, a, b, startColor, endColor);
                            break;
                        case "Центр":
                            interpolateOval(x-a, y-b, a, b, startColor, endColor);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("некорректный ввод");
                }
            }
        });

        metodComboBox.getItems().addAll(items);
        rastButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    int x =  Integer.parseInt(xField.getText());
                    int y =  Integer.parseInt(yField.getText());
                    int a =  Integer.parseInt(aField.getText());
                    int b =  Integer.parseInt(bField.getText());
                    String metod = metodComboBox.getValue();
                    switch (metod) {
                        case "Bbox":
                            drawOvalWithBbox(x, y, a, b);
                            break;
                        case "Центр":
                            drawOvalWithBbox(x-a, y-b, a, b);
                    }
                } catch(NumberFormatException e) {
                    System.out.println("некорректный ввод");
                    return;
                }

            }
        });

    }



    private void drawOvalWithBbox(int x, int y, int width, int height) {
        Rasterization.drawOvalWithBbox(canvas.getGraphicsContext2D(), x, y, width, height, Color.BLACK);
    }

    private void interpolateOval(int x, int y, int width, int height, Color startColor, Color endColor) {
        Interpolation.interpolateOval(canvas.getGraphicsContext2D(), startColor, endColor, x, y, width, height);
    }

}

