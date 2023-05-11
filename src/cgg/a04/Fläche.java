package cgg.a04;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class Fläche implements Shape {

    private Point anker;
    private Direction normalvektor;
    private double radius;
    private Color color;

    public Fläche(Point p, Direction n, double r, Color c) {
        this.anker = p;
        this.normalvektor = n;
        this.radius = r;
        this.color = c;
    }

    @Override
    public Hit intersect(Ray ray) {
        double zeile1 = Vector.dotProduct(Vector.subtract(anker, ray.getUrsprung()), normalvektor);
        double zeile2 = Vector.dotProduct(normalvektor, ray.getD());
        double t = zeile1 / zeile2;

        if (ray.isValid(t)) {
            Point position = ray.pointAt(t);
            double s = Vector.length(Vector.subtract(anker, ray.getUrsprung()));

            if (radius > s) {
                return new Hit(t, position, normalvektor, color);
            }
        }
        return null;
    }

}
