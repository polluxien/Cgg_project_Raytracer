/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a05;

import cgg.*;
import cgtools.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    final int width = 1920;
    final int height = 1080;
    Material_diffused graublau = new Material_diffused(Vector.color(0.0, 0.02, 0.02));
    Material_diffused grün = new Material_diffused(Vector.color(0, 0.7, 0.1));
    Material_diffused grün2 = new Material_diffused(Vector.color(0.35, 0.8, 0));
    Material_diffused grün3 = new Material_diffused(Vector.color(0.1, 0.4, 0.4));

    Lochkamera camera = new Lochkamera(width, height, Math.PI / 3);
    List<Shape> shapes = new ArrayList<>();

    shapes.add(new Hintergrund(new Hintergrundmaterial(Vector.white)));
    shapes.add(new Fläche(new Point(0.0, -0.5, 0.0), new Direction(0, 1, 0), 2, graublau));

    shapes.add(new Kugel(new Point(0, 0.0, -3.0), 0.5, grün3));
    shapes.add(new Kugel(new Point(-1, 0, -3.0), 0.6, grün2));
    shapes.add(new Kugel(new Point(0.5, 0.4, -2.5), 0.3, grün));
    shapes.add(new Kugel(new Point(-0.4, -0.3, -2.0), 0.2, grün2));
    shapes.add(new Kugel(new Point(0.13, -0.35, -2.0), 0.18, grün));

    // ------------

    var scene = new Group(shapes);

    RayTracer content = new RayTracer(camera, scene);

    // Creates an image and iterates over all pixel positions inside the image.
    var image = new Image(width, height);
    image.supersampling(content, 5);

    // Write the image to disk.
    final String filename = "doc/a05-diffuse-spheres.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);
  }

}
