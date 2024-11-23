package modeltest;

import org.junit.Test;

import imagemodel.Pixel;
import imagemodel.PixelInterface;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the methods of the Pixel class.
 */
public class PixelTest {

  @Test
  public void testConstructor() {
    PixelInterface pixel = new Pixel(10, 20, 30);
    assertEquals(60, pixel.getRed() + pixel.getGreen() + pixel.getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorRed1() {
    PixelInterface pixel = new Pixel(-10, 20, 30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorRed2() {
    PixelInterface pixel = new Pixel(300, 20, 30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorGreen1() {
    PixelInterface pixel = new Pixel(10, -20, 30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorGreen2() {
    PixelInterface pixel = new Pixel(30, 2000, 30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorBlue1() {
    PixelInterface pixel = new Pixel(10, 20, -30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructorBlue2() {
    PixelInterface pixel = new Pixel(300, 20, 300);
  }

  @Test
  public void testGetRed() {
    PixelInterface pixel = new Pixel(10, 20, 30);
    assertEquals(10, pixel.getRed());
  }

  @Test
  public void testGetGreen() {
    PixelInterface pixel = new Pixel(10, 20, 30);
    assertEquals(20, pixel.getGreen());
  }

  @Test
  public void testGetBlue() {
    PixelInterface pixel = new Pixel(10, 20, 30);
    assertEquals(30, pixel.getBlue());
  }

  @Test
  public void testString() {
    PixelInterface pixel = new Pixel(10, 20, 30);
    assertEquals("ImageModel.Pixel(10, 20, 30)", pixel.toString());
  }
}
