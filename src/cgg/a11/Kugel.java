package cgg.a11;

import cgtools.Direction;
import cgtools.Point;
import static cgtools.Vector.*;

public record Kugel(Point point, double radius, Material material) implements Shape {

    public Hit intersect(Ray ray) {
        Direction shift = subtract(ray.getUrsprung(), point);
        double a = dotProduct(ray.getD(), ray.getD());
        double b = 2 * dotProduct(shift, ray.getD());
        double c = dotProduct(shift, shift) - Math.pow(radius, 2);

        double discriminant = Math.pow(b, 2) - 4 * a * c;

        if (discriminant >= 0) {
            double t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            double t2 = (-b + Math.sqrt(discriminant)) / (2 * a);

            if (ray.isValid(t1) && t1 < t2) {
                Point intersectionPoint = ray.pointAt(t1);
                Direction n = normalize(subtract(intersectionPoint, point));
                double u = 0.5 + Math.atan2(n.z(), n.x()) / (2 * Math.PI);
                double v = 0.5 - Math.asin(n.y()) / Math.PI;
                return new Hit(t1, intersectionPoint, n, new Point(u, v, 0), material);
            }

            if (ray.isValid(t2) && t2 < t1) {
                Point intersectionPoint = ray.pointAt(t2);
                Direction n = normalize(subtract(intersectionPoint, point));
                double u = 0.5 + Math.atan2(n.z(), n.x()) / (2 * Math.PI);
                double v = 0.5 - Math.asin(n.y()) / Math.PI;
                return new Hit(t2, intersectionPoint, n, new Point(u, v, 0), material);
            }
        }

        return null;
    }

}
