package cgg.a07;

import cgtools.Color;

public interface Material {

    public Color albedo(Ray r, Hit h);
    public Color emissoin(Ray r, Hit h);
    public Ray scatteredRay(Ray r, Hit h);   
    
}
