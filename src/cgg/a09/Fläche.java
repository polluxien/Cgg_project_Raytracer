package cgg.a09;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class Fläche implements Shape {

    private Point anker;
    private Direction normalvektor;
    private double radius;
    private Material material;

    public Fläche(Point anker, Direction normalvektor, double radius, Material material) {
        this.anker = anker;
        this.normalvektor = normalvektor;
        this.radius = radius;
        this.material = material;
    }

    @Override
    public Hit intersect(Ray ray) {
        double zeile1 = Vector.dotProduct(Vector.subtract(anker, ray.getD()), normalvektor);
        double zeile2 = Vector.dotProduct(normalvektor, ray.getD());
        double t = zeile1 / zeile2;

        if (ray.isValid(t)) {
            Point position = ray.pointAt(t);
            double s = Vector.length(Vector.subtract(anker, ray.getUrsprung()));

            if (radius > s) {
                double inclination = Math.acos(normalvektor.y());
                double azimuth = Math.PI + Math.atan2(normalvektor.x(), normalvektor.z());
                double u = azimuth / (2 * Math.PI);
                double v = inclination / Math.PI;
                return new Hit(t, position, normalvektor, new Point(u, v, 0), material);
            }
        }
        return null;
    }
}
