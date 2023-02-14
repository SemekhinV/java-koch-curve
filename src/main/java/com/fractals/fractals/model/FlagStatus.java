package com.fractals.fractals.model;

import javafx.scene.paint.Color;

import java.util.Arrays;


public class FlagStatus {

    public boolean dFlag;

    public int iterCount = 0;

    public Color color = Color.CYAN;

    public String iterType = "All";

    public String pattern = "Line";


    public int[] sidesToMod = new int[]{1,1,1,1};

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void setSidesToMod(int id, boolean value) {
        this.sidesToMod[id] = value ? 1 : 0;
    }


    public void setdFlag(boolean dFlag) {
        this.dFlag = dFlag;
    }


    public void setIterCount(int iterCount) {
        this.iterCount = iterCount;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setIterType(String iterType) {
        this.iterType = iterType;
    }

    public void setSidesToMod(int[] sidesToMod) {
        this.sidesToMod = sidesToMod;
    }

    @Override
    public String toString() {
        return "FlagStatus{" +
                "dFlag=" + dFlag +
                ", iterCount=" + iterCount +
                ", color=" + color +
                ", iterType='" + iterType + '\'' +
                ", pattern='" + pattern + '\'' +
                ", sidesToMod=" + Arrays.toString(sidesToMod) +
                '}';
    }
}
