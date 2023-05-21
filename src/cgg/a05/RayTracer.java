package cgg.a05;

import cgtools.*;

public record RayTracer(Lochkamera camara, Group scene) implements Sampler {

    @Override
    public Color getColor(double x, double y) {
        Ray ray = camara.generateRay(x, y);
        Hit hit = scene.intersect(ray);
        Color color = null;

        if (hit != null) {
            color = calculateRadiance(scene, ray, 5);
        }
        return color;
    }

    // public Color shade(Direction n, Color c) {
    // Direction d = Vector.normalize(Vector.direction(1, 1, 0.5));
    // Color ambient = Vector.multiply(0.2, c);
    // Color diffuse = Vector.multiply(0.8 * Math.max(0, Vector.dotProduct(d, n)),
    // c);
    // return Vector.add(ambient, diffuse);
    // }

    public Color calculateRadiance(Shape scene, Ray ray, int depth) {
        if (depth == 0)
            return Vector.black;
        Hit hit = scene.intersect(ray);
        Material material = hit.m();
        Color color_albedo = material.albedo(ray, hit);
        Color color_emissoin = material.emissoin(ray, hit);
        Ray ray_scattert = material.scatteredRay(ray, hit);
        if (hit.m().scatteredRay(ray, hit) != null) {
            return Vector.add(color_emissoin,
                    Vector.multiply(color_albedo, calculateRadiance(scene, ray_scattert, depth - 1)));
        }
        return color_emissoin;
    }

}
