/** @author henrik.tramberend@beuth-hochschule.de */
package cgtools;

import static cgtools.Vector.*;

public final class Direction {
  public final double x, y, z;

  protected Direction(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  @Override
  public String toString() {
    return String.format("(Dir: %.2f %.2f %.2f)", x, y, z);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Direction))
      return false;
    if (o == this)
      return true;
    Direction v = (Direction) o;
    return Util.isZero(length(subtract(this, v)));
  }
}
