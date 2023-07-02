package cgg.a11;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Vector;

public record Spiegel(Color color, double reflexionskoeffizienten) implements Material {

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
        // Zufällige Richtung r = x * 2 - 1, y * 2 - 1, z * 2 - 1, siehe Foliensatz
        // Stochastisches Raytracing S. 5
        double x = Random.random() * 2 - 1;
        double y = Random.random() * 2 - 1;
        double z = Random.random() * 2 - 1;
        Direction reflection = Vector.subtract(r.getD(),
                Vector.multiply(2 * Vector.dotProduct(r.getD(), h.n()), h.n()));

        if (reflexionskoeffizienten != 0) {
            Direction randomD = Vector.direction(x, y, z);
            reflection = Vector.add(reflection, Vector.multiply(reflexionskoeffizienten, randomD));
        }
        return new Ray(h.x(), reflection, r.getTmin(), r.getTmax());
    }

}
