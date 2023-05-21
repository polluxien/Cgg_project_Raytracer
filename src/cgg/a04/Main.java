/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a04;

import cgg.*;
import cgtools.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    final int width = 1920;
    final int height = 1080;

    // List<Shape> shapes1 = new ArrayList<>();

    // var camera = new Lochkamera(width, height, Math.PI / 3);
    // Shape background = new Hintergrund(Vector.black);
    // Shape ground = new Fläche(new Point(0.0, -0.5, 0.0),new Direction(0, 1, 0),
    // 1, Vector.white);
    // Shape globe1 = new Kugel(new Point(-1.0, -0.25, -2.5), 0.7, Vector.red);
    // Shape globe2 = new Kugel(new Point(0.0, -0.25, -2.5), 0.5, Vector.green);
    // Shape globe3 = new Kugel(new Point(1.0, -0.25, -2.5), 0.7, Vector.blue);
    // Collections.addAll(shapes1, background, ground, globe1, globe2, globe3);

    List<Shape> shapes2 = new ArrayList<>();

    var camera = new Lochkamera(width, height, Math.PI / 2);
    shapes2.add(new Hintergrund(Vector.white));
    double y = 0.05;
    for (var i = 1.8; i >= -1.8; i -= 0.1) {
      Color color = Vector.color(10 + i, y, y);
      shapes2.add(new Kugel(new Point(i + 1.7, 0, -5), y, color));
      y += 0.03;
    }

    shapes2.add(new Kugel(new Point(-1.8, 0, -7), 2.5, Vector.black));
    shapes2.add(new Kugel(new Point(-1.8, 0.4, -5), 0.7, Vector.white));
    shapes2.add(new Kugel(new Point(-1.6, 0.4, -4), 0.3, Vector.black));
    shapes2.add(new Kugel(new Point(-1.8, -4, -7), 2.5, Vector.black));
    shapes2.add(new Kugel(new Point(-1.7, -4, -6), 1.95, Vector.white));
    //shapes2.add(new Fläche(new Point(0.0, -1.5, 0),new Direction(1, 1, 0), y,Vector.green));

    var scene = new Group(shapes2);

    RayTracer content = new RayTracer(camera, scene);

    // Creates an image and iterates over all pixel positions inside the image.
    var image = new Image(width, height);
    image.supersampeling(content, 4);

    // Write the image to disk.
    final String filename = "doc/a04-scene.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);
  }

}
