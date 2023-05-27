package cgg.a06;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class Fläche implements Shape {

    private Point anker;
    private Direction normalvektor;
    private double radius;
    private Material material;

    public Fläche(Point p, Direction n, double r, Material m) {
        this.anker = p;
        this.normalvektor = n;
        this.radius = r;
        this.material = m;
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
                return new Hit(t, position, normalvektor, material);
            }
        }
        return null;
    }

}
