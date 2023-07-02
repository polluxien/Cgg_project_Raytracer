package cgg.a11;

import static cgtools.Vector.*;

import cgtools.Direction;
import cgtools.Point;

public record Hintergrund(Material material) implements Shape {

    @Override
    public Hit intersect(Ray ray) {
        double t = Double.POSITIVE_INFINITY;
        if (!ray.isValid(t)) {
            return null;
        }
        var inclination = Math.acos(ray.getD().y());
        var azimuth = Math.PI + Math.atan2(ray.getD().x(), ray.getD().z());
        var u = azimuth / (2 * Math.PI);
        var v = inclination / Math.PI;
        Point hitPoint = ray.pointAt(t);
        Direction hitNormal = multiply(-1, ray.getD());

        return new Hit(t, hitPoint, hitNormal, new Point(u, v, 0), material);
    }

}
