package cgg.a07;

import cgtools.Direction;
import cgtools.Point;

public record Hit(double t, Point x, Direction n, Material m) {
}
