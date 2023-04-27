package cgg.a01;
import cgtools.Color;

public class Schachbrett_muster {

    private Color color_eins;
    private Color color_zwei;
    private int größe;
    
    public Schachbrett_muster(Color eins, Color zwei, int größe){
        this.color_eins = eins;
        this.color_zwei = zwei;
        this.größe = größe;
    }

    public Color getColor(int x, int y) {
        if((x / größe + y / größe) % 2 == 0){
           return color_eins;
        } else {
            return color_zwei;
        }
      }
}
