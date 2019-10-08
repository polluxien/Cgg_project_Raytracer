package cgg;

import cgtools.*;
import static cgtools.Color.*;

public class A01 {

    public static void main(String[] args) {
        final int width = 160;
        final int height = 90;
        final String filename = "doc/a01-image.png";

        class ConstantColor {
            Color color;

            ConstantColor(Color color) {
                this.color = color;
            }

            Color getColor(double x, double y) {
                return color;
            }
        }

        Image image = new Image(width, height);

        ConstantColor allGray = new ConstantColor(green);
        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                image.setPixel(x, y, allGray.getColor(x, y));
            }
        }

        image.write(filename);
        System.out.println("Wrote image: " + filename);
    }
}
