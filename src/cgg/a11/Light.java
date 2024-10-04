package cgg.a11;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;

public interface Light {
    
    public Color incomingIntensity(Hit hit, Shape scene);

}
