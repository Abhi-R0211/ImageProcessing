package modeltest;

import org.junit.Test;

import imagemodel.Image;
import imagemodel.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the methods of the Image class.
 */
public class ImageTest {

  @Test
  public void testConstructor() {
    Image obj = new Image(2, 2);
    Pixel output = obj.getPixel(0, 0);
    assertEquals(255, output.getRed());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor0Width() {
    Image obj = new Image(0, 2);
    Pixel output = obj.getPixel(0, 0);
    assertEquals(0, output.getRed());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor0Height() {
    Image obj = new Image(2, 0);
    Pixel output = obj.getPixel(0, 0);
    assertEquals(0, output.getRed());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeWidth() {
    Image obj = new Image(-2, 2);
    Pixel output = obj.getPixel(0, 0);
    assertEquals(0, output.getRed());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeHeight() {
    Image obj = new Image(2, -2);
    Pixel output = obj.getPixel(0, 0);
    assertEquals(0, output.getRed());
  }

  @Test
  public void testGetWidth() {
    Image obj = new Image(2, 3);
    assertEquals(2, obj.getWidth());
  }

  @Test
  public void testGetHeight() {
    Image obj = new Image(2, 3);
    assertEquals(3, obj.getHeight());
  }

  @Test
  public void setAndGetPixel() {
    Image obj = new Image(2, 3);
    obj.setPixel(0, 0, new Pixel(20, 30, 40));
    Pixel output = obj.getPixel(0, 0);
    assertEquals(20, output.getRed());
    assertEquals(30, output.getGreen());
    assertEquals(40, output.getBlue());
  }
}
