package cgg.a06;

import cgtools.Vector;

public class Hintergrund implements Shape{

    private Material material;

    public Hintergrund(Material material){
        this.material = material;
    }

    @Override
    public Hit intersect(Ray ray) {
        double t = Double.POSITIVE_INFINITY;
        return new Hit(t, ray.pointAt(t) ,Vector.negate(ray.getD()) , material);
    }
    
}
