package cgg.a10;

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

        public static Point pointCreator() {
                double Xmin = -15;
                double Xmax = 15;
                double Ymin = -10;
                double Ymax = 10;
                double Zmin = -20;
                double Zmax = 6;

                double randX = new Random().nextDouble(Xmax - Xmin + 1) + Xmin;
                double randY = new Random().nextDouble(Ymax - Ymin + 1) + Ymin;
                double randZ = new Random().nextDouble(Zmax - Zmin + 1) + Zmin;
                return new Point(randX, randY, randZ);
        }

        // Grafiken
        static Material_diffused muster1 = new Material_diffused(
                        new Tt(Matrix.rotation(0.5, 0, 0, 20), new ImageTexture("doc/Texturen/Muster-1.jpeg")));
        static Material_diffused muster2 = new Material_diffused(
                        new Tt(Matrix.rotation(0.5, 0, 0, 20), new ImageTexture("doc/Texturen/Muster-2.jpeg")));

        public static void main(String[] args) throws InterruptedException, ExecutionException {
                final int width = 1920;
                final int height = 1080;

                Lochkamera camera = new Lochkamera(width, height, Math.toRadians(90), Matrix.rotation(Vector.xAxis, 0));

                shapes.add(new Hintergrund(new Hintergrundmaterial(new Tt(Matrix.translation(0, 0, 100), new ImageTexture("doc/Texturen/Pano-1.jpeg")))));

                // Shapes
                // shapes.add(new Fläche(new Point(0.0, -0.5, 0.0), new Direction(0, 1, 0), 2,
                //                 graublau));

                // -- Kugeln

                double anzahl = 20;

                for (int i = 0; i < anzahl; i++) {
                        shapes.add(new Kugel(pointCreator(), (int) Random.random() +3 , new Spiegel(white, 0.05)));
                        shapes.add(new Kugel(pointCreator(), (int) Random.random() +3 , new Glass(1.5)));
                        shapes.add(new Kugel(pointCreator(),(int) Random.random() +3 , new Material_diffused(Vector.color(Random.random())))); 

                }

                var scene = new Group(new Transformation(Matrix.translation(0, 0, 0)), shapes);

                RayTracer content = new RayTracer(camera, scene);

                var image = new Image(width, height);
                Instant go = Instant.now();
                try {
                        image.sampleParallel(content, 10);
                        // für benchmark tests wurde n = 5 gesetzt
                } catch (Exception e) {
                        e.printStackTrace();
                }

                Instant stop = Instant.now();
                Duration duration = Duration.between(go, stop);
                System.out.println(duration);

                final String imageFilename = "doc/a10.png";
                image.write(imageFilename);
                System.out.println("Wrote image: " + imageFilename);
        }
}