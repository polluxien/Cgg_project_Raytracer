package cgg.a06;

import java.util.List;

public record Group(Shape... shapes) implements Shape {

    public Group(List<Shape> list) {
        this(list.toArray(new Shape[list.size()]));
    }

    @Override
    public Hit intersect(Ray r) {
        double tmax = Double.POSITIVE_INFINITY;
        Hit temp = shapes[0].intersect(r);
        for (Shape shape : shapes) {
            if (shape.intersect(r) != null) {
                if (tmax >= shape.intersect(r).t()) {
                    tmax = shape.intersect(r).t();
                    temp = shape.intersect(r);
                }
            }
        }
        return temp;
    }

}
