package cgg.a11;

import cgtools.Color;
import cgtools.Vector;

public record Metall(Color color, double grad) implements Material {

    @Override
    public Color albedo(Ray r, Hit h) {
        return Vector.black;
    }

    @Override
    public Color emissoin(Ray r, Hit h) {
        return color;
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scatteredRay'");
    }
    
}
