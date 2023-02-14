package com.fractals.fractals.controller;

import com.fractals.fractals.model.FlagStatus;
import com.fractals.fractals.model.Fractals;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ButtonController {

    @FXML
    private CheckBox all;
    @FXML
    private Button printStart;
    @FXML
    private CheckBox leftSide;
    @FXML
    private CheckBox rightSide;
    @FXML
    private CheckBox downSide;
    @FXML
    private CheckBox upSide;
    @FXML
    private Button linePattern;
    @FXML
    private Button trianglePattern;
    @FXML
    private Button squarePattern;
    @FXML
    private Canvas fractalPanel;
    @FXML
    private AnchorPane root;
    @FXML
    private SplitPane mainPane;
    @FXML
    private AnchorPane flagPanel;
    @FXML
    private AnchorPane patternPanel;
    @FXML
    private ToggleButton allIter;
    @FXML
    private ToggleButton evenIter;          //Четные
    @FXML
    private ToggleButton oddIter;           //Нечетные
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Slider iterCount;
    @FXML
    private CheckBox dFlagButton;

    private Fractals fractals;

    private FlagStatus flagStatus;
    @FXML
    protected void onAllIterButtonClick(ActionEvent event) {
        flagStatus.setIterType("All");
    }
    @FXML
    protected void onEvenIterButtonClick(ActionEvent event) {
        flagStatus.setIterType("Even");
    }
    @FXML
    protected void onOddIterButtonClick(ActionEvent event) {
        flagStatus.setIterType("Odd");
    }

    public ButtonController() {
        this.fractals = new Fractals();
        this.flagStatus = new FlagStatus();
    }

    @FXML
    protected void onSliderMove(MouseEvent mouseEvent) {
        flagStatus.setIterCount((int)iterCount.getValue());
    }
    @FXML
    protected void onDFlagPress(MouseEvent mouseEvent) {
        flagStatus.setdFlag(dFlagButton.isSelected());
    }
    @FXML
    protected void onLinePatternPress(ActionEvent event) {
        flagStatus.setPattern("Line");
    }
    @FXML
    protected void onTrianglePatternPress(ActionEvent event) {
        flagStatus.setPattern("Triangle");
    }
    @FXML
    protected void onSquarePatternPress(ActionEvent event) {
        flagStatus.setPattern("Square");
    }

    public void printStart(ActionEvent event) {

        //Очистка поля перед каждой отрисовкой
        fractalPanel.getGraphicsContext2D().clearRect(
                fractalPanel.getLayoutX(),
                fractalPanel.getLayoutY(),
                460,
                480);

        //Устанавливаем флаги сторон для прорисовки

        if (!all.isSelected()) {
            flagStatus.setSidesToMod(0, leftSide.isSelected());
            flagStatus.setSidesToMod(1, rightSide.isSelected());
            flagStatus.setSidesToMod(2, downSide.isSelected());
            flagStatus.setSidesToMod(3, upSide.isSelected());
        } else {
            flagStatus.setSidesToMod(new int[]{1,1,1,1});
        }

        //Устанавливаем цвет
        flagStatus.setColor(colorPicker.getValue());

        //Запускаем обработку всех параметров, которая потом вызовет отрисовку
        fractals.init(fractalPanel.getGraphicsContext2D(), flagStatus);
    }
}