package cgg.a06;

import cgtools.*;

public record Triangle(Point point1, Point point2, Point point3, Material material) implements Shape {
    public Hit intersect(Ray ray) {
        Direction edge1 = Vector.subtract(point2, point1);
        Direction edge2 = Vector.subtract(point3, point1);
        Direction pVec = Vector.crossProduct(ray.getD(), edge2);
        double determinant = Vector.dotProduct(edge1, pVec);

        if (Math.abs(determinant) < 1e-8)
            return null; // Strahl und Ebene des Dreiecks sind parallel

        double invDeterminant = 1.0 / determinant;
        Direction tVec = Vector.subtract(ray.getUrsprung(), point1);
        double u = Vector.dotProduct(tVec, pVec) * invDeterminant;

        if (u < 0 || u > 1)
            return null; // Der Schnittpunkt liegt nicht innerhalb des Dreiecks

        Direction qVec = Vector.crossProduct(tVec, edge1);
        double v = Vector.dotProduct(ray.getD(), qVec) * invDeterminant;

        if (v < 0 || u + v > 1)
            return null; // Der Schnittpunkt liegt nicht innerhalb des Dreiecks

        double t = Vector.dotProduct(edge2, qVec) * invDeterminant;
        if (t < ray.getTmin() || t > ray.getTmax())
            return null; // Der Schnittpunkt liegt außerhalb des erlaubten Bereichs

        Direction normal = Vector.normalize(Vector.crossProduct(edge1, edge2));
        Point intersectionPoint = ray.pointAt(t);

        return new Hit(t, intersectionPoint, normal, material);
    }
}