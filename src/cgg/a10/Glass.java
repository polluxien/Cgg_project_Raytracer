package cgg.a10;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Vector;

public class Glass implements Material {

    private Color color;
    private double n1;
    private double n2;

    public Glass(double n2) {
        this.color = Vector.white;
        this.n1 = 1.0;
        this.n2 = n2;
    }

    @Override
    public Color albedo(Ray r, Hit h) {
        return color;
    }

    @Override
    public Color emissoin(Ray r, Hit h) {
        return Vector.black;
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        Direction scattered = null;
        Direction normal = Vector.normalize(h.n());
        Direction direction = r.getD();

        if (Vector.dotProduct(direction, normal) > 0) {
            normal = Vector.negate(normal);
            double temp = n1;
            n1 = n2;
            n2 = temp;
        }

        if (refract(direction, normal, n1, n2) != null) {
            if (Random.random() > schlick(direction, normal, n1, n2)) {
                scattered = refract(direction, normal, n1, n2);
            } else {
                scattered = reflect(direction, normal);
            }
        } else {
            scattered = reflect(direction, normal);
        }
        return new Ray(h.x(), scattered, 0.0001, Double.POSITIVE_INFINITY);
    }

    private double schlick(Direction direction, Direction normal, double n1, double n2) {
        double r0 = Math.pow((n1 - n2) / (n1 + n2), 2);
        double speculiarReflectionFactor = r0 + (1 - r0) * Math.pow((1 + Vector.dotProduct(normal, direction)), 5);
        return speculiarReflectionFactor;
    }

    private Direction refract(Direction direction, Direction normal, double n1, double n2) {
        double r = n1 / n2;
        double c = Vector.dotProduct(Vector.negate(normal), direction);
        double discriminant = 1.0 - r * r * (1.0 - c * c);

        if (discriminant > 0) {
            Direction dt = Vector.add(Vector.multiply(r, direction),
                    Vector.multiply(r * c - Math.sqrt(discriminant), normal));
            return dt;
        }
        return null;
    }

    private Direction reflect(Direction direction, Direction normal) {
        return Vector.subtract(direction,
                (Vector.multiply(2, Vector.multiply(Vector.dotProduct(normal, direction), normal))));
    }

}
