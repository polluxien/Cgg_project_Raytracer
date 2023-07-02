package cgg.a11;

import cgtools.Color;
import cgtools.Matrix;
import cgtools.Sampler;
import static cgtools.Vector.*;
import static cgtools.Matrix.*;

public record Tt(Matrix m, Sampler sum) implements Sampler {

    @Override
    public Color getColor(double u, double v) {
        var uv = multiply(m, point(u, v, 0));
        return sum.getColor(uv.x(), uv.y());
    }

}
