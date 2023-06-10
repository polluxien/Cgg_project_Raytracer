package cgg.a08;

import cgg.*;
import cgtools.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class Main {

  static List<Shape> shapes = new ArrayList<>();
  static Material_diffused graublau = new Material_diffused(Vector.color(0.0, 0.02, 0.02));
  static Material_diffused weiß = new Material_diffused(Vector.color(1, 1, 1));
  static Material_diffused schwarz = new Material_diffused(Vector.color(0, 0, 0));

  public static List<Shape> characterCreator() {
    List<Shape> character = new ArrayList<Shape>();
    Material_diffused körperfarbe = new Material_diffused(
        Vector.color(Random.random(), Random.random(), Random.random()));
    character.add(new Kugel(new Point(0, 2, -12), 2, körperfarbe)); // Körper
    character.add(new Kugel(new Point(-0.6, 2.5, -10.5), 0.5, weiß)); // Auge links
    character.add(new Kugel(new Point(-0.68, 2.5, -10), 0.1, schwarz)); // Pupille links
    character.add(new Kugel(new Point(0.6, 2.5, -10.5), 0.5, weiß)); // Auge rechts
    character.add(new Kugel(new Point(0.68, 2.5, -10), 0.1, schwarz)); // Pupille rechts
    character.add(new Zylinder(new Point(-0.83, -1, -11.5), 1.8, 0.6, körperfarbe));// Bein links
    character.add(new Zylinder(new Point(0.83, -1, -11.5), 1.8, 0.6, körperfarbe));// Bein rechts
    return character;
  }

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    final int width = 1920;
    final int height = 1080;

    Lochkamera camera = new Lochkamera(width, height, Math.toRadians(120), Matrix.rotation(Vector.xAxis, 0));

    shapes.add(new Hintergrund(new Hintergrundmaterial(Vector.color(1, 1, 1))));

    double anzahl = 30;
    double Xmin = -20;
    double Xmax = 20;
    double Ymin = -2;
    double Ymax = 5;
    double Zmin = -30;
    double Zmax = 2;

    for (int i = 0; i < anzahl; i++) {
      double randX = new Random().nextDouble(Xmax - Xmin + 1) + Xmin;
      double randY = new Random().nextDouble(Ymax - Ymin + 1) + Ymin;
      double randZ = new Random().nextDouble(Zmax - Zmin + 1) + Zmin;
      double randomAngle = new Random().nextDouble(360 - 0 + 1) + 0;
      Matrix translate = Matrix.translation(randX, randY, randZ);
      Matrix rotation = Matrix.rotation(randX, randY, randZ, randomAngle);
      Group character = new Group(new Transformation((Matrix.multiply(translate, rotation))), characterCreator());
      Collections.addAll(shapes, character);
    }

    var scene = new Group(new Transformation(Matrix.translation(0, 0, 0)), shapes);

    RayTracer content = new RayTracer(camera, scene);

    var image = new Image(width, height);

    Instant go = Instant.now();
    try {
      image.sampleParallel(content, 4);
      //für benchmark tests wurde n = 5 gesetzt
    } catch (Exception e) {
      e.printStackTrace();
    }
    Instant stop = Instant.now();
    Duration duration = Duration.between(go, stop);
    System.out.println(duration);

    final String imageFilename = "doc/a08-benchmark-scene.png";
    image.write(imageFilename);
    System.out.println("Wrote image: " + imageFilename);

  }
}