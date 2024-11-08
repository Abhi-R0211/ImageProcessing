package modeltest;

import org.junit.Test;

import java.util.List;

import imagemodel.Image;
import imagemodel.ImageInterface;
import imagemodel.Pixel;
import imagemodel.PixelInterface;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the methods of the Image class.
 */
public class ImageTest {

  @Test
  public void testConstructor() {
    ImageInterface obj = new Image(2, 2);
    PixelInterface output = obj.getPixel(0, 0);
    assertEquals(255, output.getRed());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor0Width() {
    ImageInterface obj = new Image(0, 2);
    PixelInterface output = obj.getPixel(0, 0);
    assertEquals(0, output.getRed());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor0Height() {
    ImageInterface obj = new Image(2, 0);
    PixelInterface output = obj.getPixel(0, 0);
    assertEquals(0, output.getRed());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeWidth() {
    ImageInterface obj = new Image(-2, 2);
    PixelInterface output = obj.getPixel(0, 0);
    assertEquals(0, output.getRed());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeHeight() {
    ImageInterface obj = new Image(2, -2);
    PixelInterface output = obj.getPixel(0, 0);
    assertEquals(0, output.getRed());
  }

  @Test
  public void testGetWidth() {
    ImageInterface obj = new Image(2, 3);
    assertEquals(2, obj.getWidth());
  }

  @Test
  public void testGetHeight() {
    ImageInterface obj = new Image(2, 3);
    assertEquals(3, obj.getHeight());
  }

  @Test
  public void testConstructor2() {
    ImageInterface obj = new Image(2, 2, List.of(List.of(
            new Pixel(255, 255, 255))));
    PixelInterface output = obj.getPixel(0, 0);
    assertEquals(255, output.getRed());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor0Width2() {
    ImageInterface obj = new Image(0, 2, List.of(List.of(
            new Pixel(255, 255, 255))));
    PixelInterface output = obj.getPixel(0, 0);
    assertEquals(0, output.getRed());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor0Height2() {
    ImageInterface obj = new Image(2, 0, List.of(List.of(
            new Pixel(255, 255, 255))));
    PixelInterface output = obj.getPixel(0, 0);
    assertEquals(0, output.getRed());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeWidth2() {
    ImageInterface obj = new Image(-2, 2, List.of(List.of(
            new Pixel(255, 255, 255))));
    PixelInterface output = obj.getPixel(0, 0);
    assertEquals(0, output.getRed());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeHeight2() {
    ImageInterface obj = new Image(2, -2, List.of(List.of(
            new Pixel(255, 255, 255))));
    PixelInterface output = obj.getPixel(0, 0);
    assertEquals(0, output.getRed());
  }
}
