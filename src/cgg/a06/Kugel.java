package cgg.a06;

import cgtools.*;

public record Kugel(Point point, double radius, Material material) implements Shape{

  public Hit intersect(Ray ray) {
    Direction shift = Vector.subtract(ray.getUrsprung(), point);
    double a = Vector.dotProduct(ray.getD(), ray.getD());
    double b = 2 * Vector.dotProduct(shift, ray.getD());
    double c = Vector.dotProduct(shift, shift) - Math.pow(radius, 2);

    double discriminant = Math.pow(b, 2) - 4 * a * c;
    double t1 = (-b - Math.sqrt(discriminant)) / 2 * a;
    double t2 = (-b + Math.sqrt(discriminant)) / 2 * a;

    if (0 <= discriminant) {

        if (ray.isValid(t1) && t1 < t2) {
            Direction n = Vector.divide(Vector.subtract(ray.pointAt(t1), point), radius);
            return new Hit(t1, ray.pointAt(t1), Vector.normalize(n), material);
        }

        if (ray.isValid(t2) && t2 < t1) {
            Direction n = Vector.divide(Vector.subtract(ray.pointAt(t2), point), radius);
            return new Hit(t2, ray.pointAt(t2), Vector.normalize(n), material);
        }
    }
    return null;
}

}
