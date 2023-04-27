/** @author henrik.tramberend@beuth-hochschule.de */
package cgg;

import cgtools.*;
import static cgtools.Vector.*;

public class Image {

  private int width;
  private int height;
  private double[] data;

  public Image(int width, int height) {
    this.width = width;
    this.height = height;
    this.data = new double[width * 3 * height];
  }

  public void setPixel(int x, int y, Color color) {
    int index = (width * y + x) * 3;
    double gamma = 1 / 2.2;
    data[index] = Math.pow(color.r(), gamma);
    data[index + 1] = Math.pow(color.g(), gamma);
    data[index + 2] = Math.pow(color.b(), gamma);
  }

  public void write(String filename) {
    // Use cggtools.ImageWriter.write() to implement this.
    ImageWriter.write(filename, data, width, height);
  }

  public void sample(Sampler kreis) {
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        setPixel(x, y, kreis.getColor(x, y));
      }
    }
  }

  public void stratifiedsampler(Sampler kreis, int n) {
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        Color pixelcolor = black;
        for (int i = 0; i < n; i++) {
          double rx = Random.random();
          double ry = i + Random.random();
          double xs = x + rx;
          double ys = y + ry;

          pixelcolor = add(pixelcolor, kreis.getColor(xs, ys));
        }
        setPixel(x, y, divide(pixelcolor, n));
      }
    }
  }
}
