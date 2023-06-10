// @author henrik.tramberend@beuth-hochschule.de
package cgg;

import cgtools.*;
import static cgtools.Vector.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import cgg.a08.OnePixel;

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
    double gamma = 1 / 4.2;
    data[index] = Math.pow(color.r(), gamma);
    data[index + 1] = Math.pow(color.g(), gamma);
    data[index + 2] = Math.pow(color.b(), gamma);
  }

  public void write(String filename) {
    ImageWriter.write(filename, data, width, height);
  }

  public void sample(Sampler sum) {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        setPixel(x, y, sum.getColor(x, y));
      }
    }
  }

  public void supersampling(Sampler sum, int n) {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Color pixelColor = black;
        for (int i = 0; i < n; i++) {
          double rx = Random.random();
          double ry = Random.random();
          double xs = x + (rx + rx) / n;
          double ys = y + (ry + ry) / n;
          pixelColor = add(pixelColor, sum.getColor(xs, ys));
        }
        setPixel(x, y, divide(pixelColor, n * n));
      }
    }
  }

  public void sampleParallel(Sampler sum, int n) throws InterruptedException, ExecutionException {
    //int cores = Runtime.getRuntime().availableProcessors();
    int cores = 3;
    System.out.println("Du benutzt " + cores + " Kerne");
    ExecutorService pool = Executors.newFixedThreadPool(cores);
    List<Future<Color>> pixels = new ArrayList<>();
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Future<Color> pixel = pool.submit(new OnePixel(sum, x, y, n));
        pixels.add(pixel);
      }
    }
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Color color = pixels.get(x * height + y).get();
        setPixel(x, y, color);
      }
    }
    pool.shutdown();
  }
}