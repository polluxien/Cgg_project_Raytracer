package cgg.a05;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Vector;

public class Material_diffused implements Material {

    private Color color;

    public Material_diffused(Color color) {
        this.color = color;
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
        double x = Random.random() * 2 - 1;
        double y = Random.random() * 2 - 1;
        double z = Random.random() * 2 - 1;
        while (x * x + y * y + z * z > 1) {
            x = Random.random() * 2 - 1;
            y = Random.random() * 2 - 1;
            z = Random.random() * 2 - 1;
        }
        Direction d = Vector.normalize(Vector.add(h.n(), Vector.direction(x, y, z)));
        return new Ray(d, 0.001, Double.POSITIVE_INFINITY);

    }

}
