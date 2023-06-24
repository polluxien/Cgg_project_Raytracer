package cgg.a10;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Sampler;
import cgtools.Vector;

// public class Material_diffused implements Material {

//     private Color color;

//     public Material_diffused(Color color) {
//         this.color = color;
//     }

public record Material_diffused(Sampler albedo) implements Material {

    public Material_diffused(Color color){
      this((double x, double y) -> color);
    }

    @Override
    public Color albedo(Ray r, Hit h) {
        return albedo.getColor(h.uv().x(), h.uv().y());
    }

    @Override
    public Color emissoin(Ray r, Hit h) {
        return Vector.black;
    }

    @Override
    // public Ray scatteredRay(Ray r, Hit h) {
    //     Direction scatteredDirection = Vector.add(h.n(), new Direction(Random.random(),Random.random(),Random.random()));
    //     return new Ray(h.x(), scatteredDirection, r.getTmin(), Double.POSITIVE_INFINITY);

    // }
    public Ray scatteredRay(Ray ray, Hit hit) {
        double x = Random.random() * 2 - 1;
        double y = Random.random() * 2 - 1;
        double z = Random.random() * 2 - 1;
        while (x * x + y * y + z * z > 1) {
            x = Random.random() * 2 - 1;
            y = Random.random() * 2 - 1;
            z = Random.random() * 2 - 1;
        }
        Direction scatteredDirection = Vector.normalize(Vector.add(hit.n(), Vector.direction(x, y, z)));
        return new Ray(hit.x(), scatteredDirection, ray.getTmin(), Double.POSITIVE_INFINITY);
    }

}
