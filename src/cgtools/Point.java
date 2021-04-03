/** @author henrik.tramberend@beuth-hochschule.de */
package cgtools;

import static cgtools.Vector.*;

public final class Point {
  public final double x, y, z;

  protected Point(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  @Override
  public String toString() {
    return String.format("(Pnt: %.2f %.2f %.2f)", x, y, z);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Point))
      return false;
    if (o == this)
      return true;
    Point v = (Point) o;
    return Util.isZero(length(subtract(this, v)));
  }
}
