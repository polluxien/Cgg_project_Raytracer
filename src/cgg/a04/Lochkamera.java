package cgg.a04;

import cgtools.Direction;
import cgtools.Vector;

public class Lochkamera {

    private int width;
    private int height;
    private double öffnungswinkel;

    public Lochkamera(int width, int height, double d) {
        this.width = width;
        this.height = height;
        this.öffnungswinkel = d;
    }

    public Ray generateRay(double x, double y) {
        double xv = x - width / 2.0;
        double yv = -(y - height / 2.0);
        double zv = -((width / 2.0) / Math.tan(öffnungswinkel / 2.0));
        Direction u = Vector.normalize(new Direction(xv, yv, zv));
        return new Ray(u, 0, Double.POSITIVE_INFINITY);
    }

}
