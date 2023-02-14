package com.fractals.fractals.model;

import javafx.scene.canvas.GraphicsContext;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс, отвечающий за прорисовку кривой Коха.
 */
public class Fractals  {

    private GraphicsContext g;

    private FlagStatus flags;

    public void init(GraphicsContext graphicsContext, FlagStatus flagStatus) {
        this.g = graphicsContext;
        this.flags = flagStatus;

        paint();
    }

    private void paint() {

        List<Line2D> figure = new ArrayList<>();

        switch (flags.pattern) {
            case "Line" -> {

                if (flags.dFlag) {

                    figure = List.of(new Line2D.Double(40, 200, 420, 200));

                    //drawCurveKochRecur(new Line2D.Double(40, 200, 420, 200), flags.iterCount, iterType);
                } else {

                    figure = List.of(new Line2D.Double(420, 200, 40, 200));

                    //drawCurveKochRecur(new Line2D.Double(420, 200, 40, 200), flags.iterCount, iterType);
                }
            }
            case "Triangle" -> {
                double a = 254; // Длина стороны треугольника (px)
                double p1x = 90; // Координата x нижней левой точки основания треугольника (px)
                double p1y = 330; // Координата y нижней левой точки основания треугольника (px)
                double p2x = p1x + a;
                double p2y = p1y;
                double h = Math.sqrt(Math.pow(a, 2) - Math.pow((a / 2), 2) / 4);
                double pmx = (p1x + p2x) / 2;
                double pmy = (p1y + p2y) / 2;

                double p3x = pmx + (h * (p1y - pmy)) / (a / 2);
                double p3y = pmy + (h * (p1x - pmx)) / (a / 2);

                if (flags.sidesToMod[0] == 1) {     //left
                    figure.add(new Line2D.Double(p1x, p1y, p2x, p2y));
                }
                if (flags.sidesToMod[1] == 1) {     //right
                    figure.add(new Line2D.Double(p2x, p2y, p3x, p3y));
                }
                if (flags.sidesToMod[2] == 1) {     //down
                    figure.add(new Line2D.Double(p3x, p3y, p1x, p1y));
                }

                figure.addAll(
                        List.of(
                                new Line2D.Double(p1x, p1y, p2x, p2y),
                                new Line2D.Double(p2x, p2y, p3x, p3y),
                                new Line2D.Double(p3x, p3y, p1x, p1y))
                );

            }

            case "Square" -> {

                double a = 250;

                double px1 = 100;
                double py1 = 100;

                double px2 = px1 + a;
                double py2 = py1;

                double px3 = px2;
                double py3 = py2 + a;

                double px4 = px1;
                double py4 = py1 + a;

                if (flags.dFlag) {

                    if (flags.sidesToMod[3] == 1) {     //up
                        figure.add(new Line2D.Double(px1, py1, px2, py2));
                    } else {
                        drawLine(new Line2D.Double(px1, py1, px2, py2));
                    }

                    if (flags.sidesToMod[1] == 1) {     //right
                        figure.add(new Line2D.Double(px2, py2, px3, py3));
                    } else {
                        drawLine(new Line2D.Double(px2, py2, px3, py3));
                    }

                    if (flags.sidesToMod[2] == 1) {     //down
                        figure.add(new Line2D.Double(px3, py3, px4, py4));
                    } else {
                        drawLine(new Line2D.Double(px3, py3, px4, py4));
                    }

                    if (flags.sidesToMod[0] == 1) {     //left
                        figure.add(new Line2D.Double(px4, py4, px1, py1));
                    } else {
                        drawLine(new Line2D.Double(px4, py4, px1, py1));
                    }

                } else {

                    if (flags.sidesToMod[3] == 1) {     //up
                        figure.add(new Line2D.Double(px2, py2, px1, py1));
                    } else {
                        drawLine(new Line2D.Double(px2, py2, px1, py1));
                    }

                    if (flags.sidesToMod[1] == 1) {     //right
                        figure.add(new Line2D.Double(px3, py3, px2, py2));
                    } else {
                        drawLine(new Line2D.Double(px3, py3, px2, py2));
                    }

                    if (flags.sidesToMod[2] == 1) {     //down
                        figure.add(new Line2D.Double(px4, py4, px3, py3));
                    } else {
                        drawLine(new Line2D.Double(px4, py4, px3, py3));
                    }

                    if (flags.sidesToMod[0] == 1) {     //left
                        figure.add(new Line2D.Double(px1, py1, px4, py4));
                    } else {
                        drawLine(new Line2D.Double(px1, py1, px4, py4));
                    }
                }
            }
        }

        switch (flags.iterType) {

            case "All" -> figure = createLine(figure, flags.iterCount, 0, 1);

            case "Even" -> figure = createLine(figure, flags.iterCount, 0, 2);

            case "Odd" -> figure = createLine(figure, flags.iterCount, 1, 2);
        }

        figure.forEach(this::drawLine);
    }

    private List<Line2D> createLine(List<Line2D> lines, int iterCount, int start, int iterType) {

        for (int i = 0; i < iterCount; i++) {

            List<Line2D> temp1 = new ArrayList<>();

            for (int j = start; j < lines.size(); j += iterType) {

                List<Line2D> temp2 = lineSplitter(lines.get(j));

                temp1.addAll(temp2);

                if (iterType == 2) {

                    if (j + 1 < lines.size()) {
                        temp1.add(lines.get(j+1));
                    }

                    if (j == 1) {
                        temp1.add(lines.get(0));
                    }
                }
            }

            if ((lines.size() == 1) && temp1.isEmpty()) {
                drawLine(lines.get(0));
            }

            lines = temp1;
        }

        return lines;
    }

    private List<Line2D> lineSplitter(Line2D line) {

        double a = line.getP1().distance(line.getP2());
        a = a / 3;

        double h = Math.sqrt(Math.pow(a, 2) - Math.pow((a / 2), 2) / 4);

        Point2D ps = line.getP1();
        Point2D pe = line.getP2();

        Point2D pm = new Point2D.Double((ps.getX() + pe.getX()) / 2, (ps.getY() + pe.getY()) / 2);
        Point2D p1 = new Point2D.Double((2 * ps.getX() + pe.getX()) / 3, (2 * ps.getY() + pe.getY()) / 3);
        Point2D p2 = new Point2D.Double((2 * pe.getX() + ps.getX()) / 3, (2 * pe.getY() + ps.getY()) / 3);
        Point2D p3 = new Point2D.Double(
                pm.getX() + (h * (-p2.getY() + pm.getY())) / (a / 2),
                pm.getY() + (h * (p2.getX() - pm.getX())) / (a / 2)
        );

        return List.of(
                new Line2D.Double(p2, pe),
                new Line2D.Double(p3, p2),
                new Line2D.Double(p1, p3),
                new Line2D.Double(ps, p1)
        );
    }

    public void drawLine(Line2D line) {

        g.beginPath();
        g.setStroke(flags.color);
        g.moveTo(line.getX1(), line.getY1());
        g.lineTo(line.getX2(), line.getY2());
        g.stroke();

    }

}