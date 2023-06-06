package cgg.a07;

import cgtools.Color;

public class Hintergrundmaterial implements Material{

    private Color color;

    public Hintergrundmaterial(Color color){
        this.color = color;
    }

    @Override
    public Color albedo(Ray r, Hit h) {
        return null;
    }

    @Override
    public Color emissoin(Ray r, Hit h) {
        return color;
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
       return null;
    }
    
}
