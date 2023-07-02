package cgg.a11;

import cgtools.Direction;
import cgtools.Point;

public record Hit(double t, Point x, Direction n, Point uv, Material m) {

    public Hit(double t, Point x, Direction n, Material m) {
        this(t, x, n, new Point(0,0,0), m);
    }

    public static Hit closest(Hit a, Hit b) {
        if (a == null) {
            return b;
        } else if (b == null) {
            return a;
        } else {
            return a.t <= b.t ? a : b;
        }
    }

    public int compareTo(Hit h) {
        return Double.compare(t, h.t);
    }

    public static Hit min(Hit a, Hit b) {
        return a.t <= b.t ? a : b;
    }

    public static Hit ma(Hit a, Hit b) {
        return a.t >= b.t ? a : b;
    }
}
