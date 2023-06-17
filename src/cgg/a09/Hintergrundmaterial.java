package cgg.a09;

import cgtools.Color;
import cgtools.Sampler;
import cgtools.Vector;

public record Hintergrundmaterial(Sampler emissoin) implements Material {

    public Hintergrundmaterial(Color color){
        // var sampler = new Sampler(){
        //  public Color getColor(double x, double y){
        //     return color;
        //    }
        // };
        this((double x, double y) -> color);
    }

    @Override
    public Color albedo(Ray r, Hit h) {
        return Vector.black;
    }

    @Override
    public Color emissoin(Ray r, Hit h) {
        return emissoin.getColor(h.uv().x(), h.uv().y());
    }

    @Override
    public Ray scatteredRay(Ray r, Hit h) {
        return null;
    }

}
