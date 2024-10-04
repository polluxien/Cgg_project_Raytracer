package cgg.a11;

import cgtools.Matrix;

public class Transformation {
    Matrix orignal;
    Matrix invert;
    Matrix transpose;

    public Transformation(Matrix m) {
        this.orignal = m;
        this.invert = Matrix.invert(m);
        this.transpose = Matrix.transpose(invert);
    }

    public Ray transform(Ray r) {
        return new Ray(Matrix.multiply(invert, r.getUrsprung()), Matrix.multiply(invert, r.getD()), r.getTmin(),
                r.getTmax());
    }

    public Hit transform(Hit h) {
        return new Hit(h.t(),
                Matrix.multiply(orignal, h.x()),
                Matrix.multiply(invert, h.n()),
                h.uv(),
                h.m());
    }

}
