/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a02;

import cgg.*;

public class Main {

  public static void main(String[] args) {
    final int width = 1920;
    final int height = 1080;

    ColoredDiscs cDiscs = new ColoredDiscs(200, 75);

    // Creates an image and iterates over all pixel positions inside the image.
    Image image = new Image(width, height);
    //image.sample(cDiscs);
    image.supersampling(cDiscs, 100);

    // Write the image to disk.
    final String filename = "doc/a02-discs-supersampling.png";
    image.write(filename);
    System.out.println("Wrote image: " + filename);
  }
}
