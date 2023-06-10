package cgg.a08;

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
        Direction scatteredDirection = Vector.add(h.n(), new Direction(Random.random(),Random.random(),Random.random()));
        return new Ray(h.x(), scatteredDirection, r.getTmin(), Double.POSITIVE_INFINITY);

    }

}
