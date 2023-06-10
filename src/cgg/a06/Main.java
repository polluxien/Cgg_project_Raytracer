/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a06;

import cgg.*;
import cgtools.*;

import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.*;
import cgtools.Matrix;

public class Main {

  static List<Shape> shapes = new ArrayList<>();

  public static void main(String[] args) {
    final int width = 1920;
    final int height = 1080;
    Material_diffused graublau = new Material_diffused(Vector.color(0.0, 0.02, 0.02));
    Material_diffused grün = new Material_diffused(Vector.color(0, 0.7, 0.1));
    Material_diffused grün2 = new Material_diffused(Vector.color(0.35, 0.8, 0));
    Material_diffused grün3 = new Material_diffused(Vector.color(0.1, 0.4, 0.4));
    Material_diffused pink = new Material_diffused(Vector.color(2, 0.2, 0.3));
    Material_diffused shiny = new Material_diffused(Vector.color(2));
    Material_diffused yello = new Material_diffused(Vector.color(2, 2, 0));


    Lochkamera camera = new Lochkamera(width, height, toRadians(70),
        // Matrix.multiply(Matrix.translation(0, 0.2, 2.5),
             Matrix.rotation(Vector.xAxis, 20)
        //     Matrix.translation(0, 2, 0))
            );

    shapes.add(new Hintergrund(new Hintergrundmaterial(Vector.white)));
    shapes.add(new Fläche(new Point(0.0, -0.5, 0.0), new Direction(0, 1, 0), 2, yello));

    //--- Kugeln
    shapes.add(new Kugel(new Point(0, 1.0, -10.0), 0.5, grün3));
    shapes.add(new Kugel(new Point(-1, 0.5, -10.0), 0.6, pink));
    shapes.add(new Kugel(new Point(0.5, 0.4, -12.5), 0.3, grün));
    shapes.add(new Kugel(new Point(-0.4, -0.3, -12.0), 0.2, pink));
    shapes.add(new Kugel(new Point(0.13, -0.35, -12.0), 0.18, grün));

    shapes.add(new Kugel(new Point(2, 1.0, -9.0), 0.5, grün));
    shapes.add(new Kugel(new Point(1, 0.5, -10.0), 0.6, grün));
    shapes.add(new Kugel(new Point(-3.5, 0.4, -8.5), 0.3, pink));
    shapes.add(new Kugel(new Point(-4, 1, -12.0), 0.8, grün3));
    shapes.add(new Kugel(new Point(5, -0.35, -12.0), 0.18, grün2));
   
    //--- Laternen
    for(int i = -60; i < 0; i+= 2.5){
      shapes.add((new Zylinder(new Point(-2, 1.5, i), 1, 0.5, shiny)));
      shapes.add((new Zylinder(new Point(-2, -0.5, i), 2, 0.15, graublau)));

      shapes.add((new Zylinder(new Point(2, 1.5, i), 1, 0.5, shiny)));
      shapes.add((new Zylinder(new Point(2, -0.5, i), 2, 0.15, graublau)));
    }

    //--- Pyramiden
    shapes.add(new Pyramide(new Point(0, 4.5, -80), 35, 30, pink));
    shapes.add(new Pyramide(new Point(-4, 1, -8), 3.5, 3, grün));

    // ------------

    var scene = new Group(shapes);

    RayTracer content = new RayTracer(camera, scene);

    // Creates an image and iterates over all pixel positions inside the image.
    var image = new Image(width, height);
    image.supersampling(content, 10);

    // Write the image to disk.
    final String filename = "doc/a06-camera.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);
  }

}
