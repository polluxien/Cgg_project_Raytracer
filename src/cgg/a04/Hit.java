package cgg.a04;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;

public record Hit(double t, Point x, Direction n, Color r) {
}
