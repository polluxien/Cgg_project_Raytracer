package cgg.a09;

import cgtools.*;
import static cgtools.Vector.*;

public class Ray {

    private Point ursprung;
    private Direction d;
    private double tmin;
    private double tmax;

    public Ray(Direction d, double tmin, double tmax) {
        this.ursprung = new Point(0, 0, 0);
        this.d = d;
        this.tmin = tmin;
        this.tmax = tmax;

    }

    public Ray(Point ursprung, Direction d, double tmin, double tmax) {
        this.ursprung = ursprung;
        this.d = d;
        this.tmin = tmin;
        this.tmax = tmax;

    }

    public Point pointAt(double t) {
        return Vector.add(multiply(d, t), ursprung);
    }

    public boolean isValid(double t) {
        return t > tmin && t <= tmax;
    }

    public double getTmin() {
        return tmin;
    }

    public void setTmin(double tmin) {
        this.tmin = tmin;
    }

    public double getTmax() {
        return tmax;
    }

    public void setTmax(double tmax) {
        this.tmax = tmax;
    }

    public Point getUrsprung() {
        return ursprung;
    }

    public void setUrsprung(Point ursprung) {
        this.ursprung = ursprung;
    }

    public Direction getD() {
        return d;
    }

    public void setD(Direction d) {
        this.d = d;
    }

}
