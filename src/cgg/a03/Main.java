/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a03;

import cgg.*;
import cgtools.Point;
import cgtools.Random;
import cgtools.Vector;

import static java.lang.Math.*;

import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {
    final int width = 1920;
    final int height = 1080;

    var camera = new Lochkamera(width, height, toRadians(220));

    var kugeln = new ArrayList<Kugel>();
    int n = 10;
    for (int i = 0; i < n; i++) {
      for (int y = 0; y < n; y++) {
        kugeln.add(
            new Kugel(new Point(
                i * (double) width / (n - 1) - width / 2,
                y * (double) height / (n - 1) - height / 2,
                500),
                width / 10 / 2, Vector.color(Random.random(), 0.1, 0.15)));
      }
    }

    var content = new RayTracer(camera, kugeln);

    // Creates an image and iterates over all pixel positions inside the image.
    var image = new Image(width, height);
    image.supersampling(content, 100);

    // Write the image to disk.
    final String filename = "doc/a03-spheres.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);
  }
}
