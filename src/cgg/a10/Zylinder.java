package cgg.a10;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public record Zylinder(Point point, double height, double radius, Material material) implements Shape {

    @Override
    public Hit intersect(Ray ray) {
        Direction oc = Vector.subtract(ray.getUrsprung(), point);
        double a = Math.pow(ray.getD().x(), 2) + Math.pow(ray.getD().z(), 2);
        double b = 2 * (oc.x() * ray.getD().x() + oc.z() * ray.getD().z());
        double c = Math.pow(oc.x(), 2) + Math.pow(oc.z(), 2) - Math.pow(radius, 2);
        double discriminant = Math.pow(b, 2) - 4 * a * c;

        if (discriminant >= 0) {
            double t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            double t2 = (-b + Math.sqrt(discriminant)) / (2 * a);

            double y1 = ray.getUrsprung().y() + t1 * ray.getD().y();
            double y2 = ray.getUrsprung().y() + t2 * ray.getD().y();
            boolean hit1 = (y1 >= point.y() && y1 <= point.y() + height);
            boolean hit2 = (y2 >= point.y() && y2 <= point.y() + height);

            if (hit1 && ray.isValid(t1)) {
                Direction normal = Vector.normalize(new Direction(oc.x(), 0, oc.z()));
                double inclination = Math.acos(normal.y());
                double azimuth = Math.PI + Math.atan2(normal.x(), normal.z());
                double u = azimuth / (2 * Math.PI);
                double v = inclination / Math.PI;
                Point intersectionPoint = ray.pointAt(t1);

                return new Hit(t1, intersectionPoint, normal, new Point(u, v, 0), material);
            } else if (hit2 && ray.isValid(t2)) {
                Direction normal = Vector.normalize(new Direction(oc.x(), 0, oc.z()));
                double inclination = Math.acos(normal.y());
                double azimuth = Math.PI + Math.atan2(normal.x(), normal.z());
                double u = azimuth / (2 * Math.PI);
                double v = inclination / Math.PI;
                Point intersectionPoint = ray.pointAt(t2);
                return new Hit(t2, intersectionPoint, normal, new Point(u, v, 0), material);
            }
        }

        return null;
    }
}
