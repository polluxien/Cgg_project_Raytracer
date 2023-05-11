package cgg.a04;

import cgtools.Color;
import cgtools.Vector;

public class Hintergrund implements Shape{

    private Color color;

    public Hintergrund(Color color){
        this.color = color;
    }

    @Override
    public Hit intersect(Ray ray) {
        double t = Double.POSITIVE_INFINITY;
        return new Hit(t, ray.pointAt(t) ,Vector.negate(ray.getD()) , color);
    }
    
}
