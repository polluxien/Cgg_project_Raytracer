package cgg.a02;

import cgtools.Color;
import cgtools.Sampler;

public class Disc implements Sampler {

    private double position_x;
    private double position_y;
    private double radius;
    private Color color;

    public Disc(double position_x, double position_y, double radius, Color color) {
        this.position_x = position_x;
        this.position_y = position_y;
        this.radius = radius;
        this.color = color;
    }

    public boolean isPointInDisc(double x, double y) {
        double xs = x - position_x - 0.5;
        double ys = y - position_y - 0.5;

        double distance = Math.sqrt(xs * xs + ys * ys);
        return distance <= radius;
    }

    public Color getColor(double x, double y) {
        return color;
    }

    public double getRadius() {
        return radius;
    }

}