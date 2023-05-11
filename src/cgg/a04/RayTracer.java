package cgg.a04;

import cgtools.*;

public record RayTracer(Lochkamera camara, Group scene) implements Sampler {

    @Override
    public Color getColor(double x, double y) {
        Ray ray = camara.generateRay(x, y);
        Hit hit = scene.intersect(ray);
        return shade(hit.n(), hit.r());
    }

    private Color shade(Direction n, Color c) {
        Direction d = Vector.normalize(Vector.direction(1, 1, 0.5));
        Color ambient = Vector.multiply(0.2, c);
        Color diffuse = Vector.multiply(0.8 * Math.max(0, Vector.dotProduct(d, n)), c);
        return Vector.add(ambient, diffuse);
    }

}
