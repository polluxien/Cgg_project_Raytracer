package cgg.a11;

import cgtools.*;
import java.util.concurrent.Callable;

public record OnePixel(Sampler sum, int x, int y, int n) implements Callable<Color> {

  public Color call() throws Exception {
    Color pixelColor = Vector.black;
    for (int i = 0; i < n; i++) {
      double rx = Random.random();
      double ry = Random.random();
      double xs = x + (rx + rx) / n;
      double ys = y + (ry + ry) / n;
      pixelColor = Vector.add(pixelColor, sum.getColor(xs, ys));
    }
    return Vector.divide(pixelColor, n * n);
  }
}
