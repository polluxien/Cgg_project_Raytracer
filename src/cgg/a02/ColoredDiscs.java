package cgg.a02;

import java.util.ArrayList;
import java.util.List;
import cgtools.*;
import static cgtools.Vector.*;

import cgtools.Color;

public class ColoredDiscs implements Sampler {

    private List<Disc> discList = null;
    private Color color = black;

    public ColoredDiscs(int anzahl, int maxRadius) {
        discList = new ArrayList<Disc>();
        for (int i = 0; i < anzahl; i++) {
            Double x = (Random.random() * ((1800 - 100) + 1)) + 100;
            Double y = (Random.random() * ((900 - 100) + 1)) + 100;
            Double radius = (Random.random() * ((maxRadius - 20) + 1)) + 20;
            Color color = new Color(Random.random(), Random.random(), Random.random());

            Disc disc = new Disc(x, y, radius, color);
            discList.add(disc);
        }
    }

    public List<Disc> getDiscs() {
        return discList;
    }

    @Override
    public Color getColor(double x, double y) {

        Color über_color = color;
        double radius = Double.MAX_VALUE;

        for (Disc disc : discList) {
            if (disc.isPointInDisc(x, y)) {
                if (disc.getRadius() < radius) {
                    radius = disc.getRadius();
                    über_color = disc.getColor(x, y);
                }

            }
        }
        return über_color;
    }
}
