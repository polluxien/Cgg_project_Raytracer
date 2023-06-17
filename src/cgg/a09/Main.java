package cgg.a09;

import cgg.*;
import cgtools.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static cgtools.Vector.*;
import static cgtools.Matrix.*;

import java.time.Duration;
import java.time.Instant;

public class Main {

    static List<Shape> shapes = new ArrayList<>();

    // Farben
    static Material_diffused graublau = new Material_diffused(Vector.color(0.0, 0.02, 0.02));
    static Material_diffused weiß = new Material_diffused(Vector.color(1, 1, 1));
    static Material_diffused schwarz = new Material_diffused(Vector.color(0, 0, 0));

    // Grafiken
    static List<Material_diffused> texturen = new ArrayList<Material_diffused>();
    static Material_diffused muster1 = new Material_diffused(
            new Tt(Matrix.rotation(0.5, 0, 0, 20), new ImageTexture("doc/Texturen/Muster-1.jpeg")));
    static Material_diffused muster2 = new Material_diffused(
            new Tt(Matrix.rotation(0.5, 0, 0, 20), new ImageTexture("doc/Texturen/Muster-2.jpeg")));
    static Material_diffused muster3 = new Material_diffused(
            new Tt(Matrix.rotation(0.5, 0, 0, 20), new ImageTexture("doc/Texturen/Muster-3.jpeg")));
    static Material_diffused muster4 = new Material_diffused(
            new Tt(Matrix.rotation(0.5, 0, 0, 20), new ImageTexture("doc/Texturen/Muster-4.jpeg")));
    static Material_diffused muster5 = new Material_diffused(
            new Tt(Matrix.rotation(0.5, 0, 0, 20), new ImageTexture("doc/Texturen/Muster-5.jpeg")));
    static Material_diffused muster6 = new Material_diffused(
            new Tt(Matrix.rotation(0.5, 0, 0, 20), new ImageTexture("doc/Texturen/Muster-6.jpeg")));
    static Material_diffused muster7 = new Material_diffused(
            new Tt(Matrix.rotation(0.5, 0, 0, 20), new ImageTexture("doc/Texturen/Muster-7.jpeg")));
    static Material_diffused muster8 = new Material_diffused(
            new Tt(Matrix.rotation(0.5, 0, 0, 20), new ImageTexture("doc/Texturen/Muster-8.jpeg")));

    static Material_diffused gras = new Material_diffused(
            new Tt(Matrix.rotation(0.5, 0, 0, 20), new ImageTexture("doc/Texturen/Gras.jpeg")));
    static Material_diffused world = new Material_diffused(
            new Tt(Matrix.rotation(0.5, 0, 0, 20), new ImageTexture("doc/Texturen/World.jpeg")));
    static Material_diffused metall = new Material_diffused(
            new Tt(Matrix.rotation(0.5, 0, 0, 20), new ImageTexture("doc/Texturen/Metall.jpeg")));

    public static List<Shape> characterCreator() {
        List<Shape> character = new ArrayList<Shape>();
        int ran = new Random().nextInt(texturen.size());
        character.add(new Kugel(new Point(0, 2, -12), 2, texturen.get(ran))); // Körper
        character.add(new Kugel(new Point(-0.6, 2.5, -10.5), 0.5, weiß)); // Auge links
        character.add(new Kugel(new Point(-0.68, 2.5, -10), 0.1, schwarz)); // Pupille links
        character.add(new Kugel(new Point(0.6, 2.5, -10.5), 0.5, weiß)); // Auge rechts
        character.add(new Kugel(new Point(0.68, 2.5, -10), 0.1, schwarz)); // Pupille rechts
        character.add(new Zylinder(new Point(-0.83, -1, -11.5), 1.8, 0.6, texturen.get(ran)));// Bein links
        character.add(new Zylinder(new Point(0.83, -1, -11.5), 1.8, 0.6, texturen.get(ran)));// Bein rechts
        return character;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final int width = 1920;
        final int height = 1080;

        Lochkamera camera = new Lochkamera(width, height, Math.toRadians(90), Matrix.rotation(Vector.xAxis, 0));

        shapes.add(new Hintergrund(
                new Hintergrundmaterial(
                        new Tt(Matrix.rotation(0.5, 0, 0, 20), new ImageTexture("doc/Texturen/Pano-2.jpeg")))));

        // Muster
        Collections.addAll(texturen, muster1, muster2, muster3, muster4, muster5, muster6, muster7, muster8);

        // Character
        double anzahl = 50;
        double Xmin = -40;
        double Xmax = 30;
        double Ymin = 1.5;
        double Ymax = 6;
        double Zmin = -30;
        double Zmax = 2;

        for (int i = 0; i < anzahl; i++) {
            double randX = new Random().nextDouble(Xmax - Xmin + 1) + Xmin;
            double randY = new Random().nextDouble(Ymax - Ymin + 1) + Ymin;
            double randZ = new Random().nextDouble(Zmax - Zmin + 1) + Zmin;
            double randomAngle = new Random().nextDouble(360 - 0 + 1) + 0;
            Matrix translate = Matrix.translation(randX, randY, randZ);
            Matrix rotation = Matrix.rotation(randX, randY, randZ, randomAngle);
            Group character = new Group(new Transformation((Matrix.multiply(translate,
                    rotation))), characterCreator());
            Collections.addAll(shapes, character);
        }

        // Shapes
        shapes.add(new Fläche(new Point(0.0, -0.5, 0.0), new Direction(0, 1, 0), 2, gras));

        var scene = new Group(new Transformation(Matrix.translation(0, 0, 0)), shapes);

        RayTracer content = new RayTracer(camera, scene);

        var image = new Image(width, height);
        Instant go = Instant.now();
        try {
            image.sampleParallel(content, 4);
            // für benchmark tests wurde n = 5 gesetzt
        } catch (Exception e) {
            e.printStackTrace();
        }
        Instant stop = Instant.now();
        Duration duration = Duration.between(go, stop);
        System.out.println(duration);

        final String imageFilename = "doc/a09.png";
        image.write(imageFilename);
        System.out.println("Wrote image: " + imageFilename);
    }
}