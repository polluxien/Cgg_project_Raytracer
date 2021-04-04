package cgg.a01;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import static cgtools.Vector.*;

public class ConstantColorTest {

  @Test
  public void testSampledColor() {
    ConstantColor cc = new ConstantColor(color(1, 1, 1));
    assertThat(cc.getColor(4.3, 5.6), is(equalTo(color(1, 1, 1))));
  }
}