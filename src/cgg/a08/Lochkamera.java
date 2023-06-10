package cgg.a08;

import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Vector;

public class Lochkamera {

    private int width;
    private int height;
    private double öffnungswinkel;
    Matrix v;

    public Lochkamera(int width, int height, double d, Matrix v) {
        this.width = width;
        this.height = height;
        this.öffnungswinkel = d;
        this.v = v;
    }

    public Ray generateRay(double x, double y) {
        double xv = x - width / 2.0;
        double yv = -(y - height / 2.0);
        double zv = -((width / 2.0) / Math.tan(öffnungswinkel / 2.0));
        Direction u = Vector.normalize(new Direction(xv, yv, zv));
        return new Ray(Matrix.multiply(v, new Point(0,0,0)),Matrix.multiply(v,u), 0, Double.POSITIVE_INFINITY);
    }

}
