/** @author henrik.tramberend@beuth-hochschule.de */
package cgg;

import cgtools.*;

public class Image {

  private int width;
  private int height;
  private double[] data;

  public Image(int width, int height) {
    this.width = width;
    this.height = height;
    this.data = new double[width*3*height];
  }

  public void setPixel(int x, int y, Color color) {
    int index = (width*y+x) *3;
    data[index] = color.r();
    data[index + 1] = color.g();
    data[index + 2] = color.b();
  }

  public void write(String filename) {
    // Use cggtools.ImageWriter.write() to implement this.
    ImageWriter.write(filename, data, width, height);
  }

}
