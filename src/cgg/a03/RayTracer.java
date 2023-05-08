package cgg.a03;

import java.util.ArrayList;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Sampler;
import cgtools.Vector;

public class RayTracer implements Sampler {
    private Lochkamera camera;
    private ArrayList<Kugel> kugeln;

    public RayTracer(Lochkamera camera, ArrayList<Kugel> kugeln) {
        this.camera = camera;
        this.kugeln = kugeln;
    }

    @Override
    public Color getColor(double x, double y) {
        Color color = Vector.black;
        Color sphereColor;
        Ray r = camera.generateRay(x, y);
        double tmax = Double.POSITIVE_INFINITY;
        for (Kugel kugel : kugeln) {
            if (kugel.intersect(r) != null) {
                if (tmax > kugel.intersect(r).t()) {
                    tmax = kugel.intersect(r).t();
                    sphereColor = shade(kugel.intersect(r).n(), kugel.color());
                    return sphereColor;
                }
            }
        }
        return color;
    }

    private Color shade(Direction n, Color c) {
        Direction d = Vector.normalize(Vector.direction(1, 1, 0.5));
        Color ambient = Vector.multiply(0.1, c);
        Color diffuse = Vector.multiply(0.9 * Math.max(0, Vector.dotProduct(d, n)), c);
        return Vector.add(ambient, diffuse);
    }

}
