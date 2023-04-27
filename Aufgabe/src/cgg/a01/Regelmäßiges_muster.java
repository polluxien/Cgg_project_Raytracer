package cgg.a01;

import cgtools.Color;
import static cgtools.Vector.*;

public class Regelmäßiges_muster {

    private Color color_eins;
    private Color color_zwei;
    private int größe;

    //private int width;
    private int height;

    public Regelmäßiges_muster(int width, int height, Color eins, Color zwei, int größe) {
        this.color_eins = eins;
        this.color_zwei = zwei;
        this.größe = größe;
       // this.width = width;
        this.height = height;
    }

    public Color getColor(double x, double y) {
        // if ((x / größe + y / größe) % 2 == 0) {
        //     return multiply(x / width, color_eins);
        // } else if ((x / größe + y / größe) % 3 == 0) {
        //     return multiply(y / height, color_zwei);
        // }
        // return multiply(y / height, red);

        int größeX = (int) x % größe;
        int größeY = (int) y % größe;
        int indexX = (int) Math.floor((float) x / größe);
        int indexY = (int) Math.floor((float) y / größe);
        if ((indexX + indexY) % 2 == 0) {
            if (größeY > (größe - größeX)) {
                return color_zwei;
            } else {
                return multiply(y / height, color_eins);
            }
        } else {
            if (größeY > größeX) {
                return multiply(y / height, color_eins);
            } else {
                return color_zwei;
            }
        }

    }

}