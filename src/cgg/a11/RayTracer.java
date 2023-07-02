package cgg.a11;

import cgtools.Vector;
import cgtools.Color;
import cgtools.Sampler;
import cgtools.Vector;

public class RayTracer implements Sampler {
    private Lochkamera camera;
    private Group scene;
    private World world;

    public RayTracer(Lochkamera camera, Group scene) {
        this.camera = camera;
        this.scene = scene;
        this.world = new World(null, scene);
    }

    public RayTracer(Lochkamera camera, World world) {
        this.world = world;
        this.camera = camera;
        this.scene = world.scene;
    }

    @Override
    public Color getColor(double x, double y) {
        Ray ray = camera.generateRay(x, y);
        Hit hit = scene.intersect(ray);
        Color color = null;

        if (hit != null) {
            color = calculateRadiance(scene, ray, 5);
        }
        return color;
    }

    public Color calculateRadiance(Shape scene, Ray ray, int depth) {
        if (depth == 0) {
            return Vector.black;
        }
        Hit hit = scene.intersect(ray);
        if (hit != null) {
            Material material = hit.m();
            Color albedo = material.albedo(ray, hit);
            Color emission = material.emissoin(ray, hit);

            Ray secondaryScatteredRay = material.scatteredRay(ray, hit);
            if (secondaryScatteredRay != null) {
                Color incomingIntensity = Vector.black;
                if (world.lights != null) {
                    for (Light light : world.lights) {
                        Color intensity = light.incomingIntensity(hit, scene);
                        intensity = Vector.multiply(intensity, albedo);
                        incomingIntensity = Vector.add(incomingIntensity, intensity);
                    }
                }
                Color a1 = Vector.add(albedo, emission);
                Color radiance = Vector.multiply(a1, calculateRadiance(scene, secondaryScatteredRay, depth - 1));

                if (incomingIntensity != null) {
                    radiance = Vector.add(radiance, incomingIntensity);
                }
                return radiance;
            }
            return emission;
        }
        return Vector.black;
    }
}