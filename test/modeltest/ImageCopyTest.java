package modeltest;

import org.junit.Test;

import java.util.List;

import imagemodel.Image;
import imagemodel.ImageCopy;
import imagemodel.ImageCopyInterface;
import imagemodel.ImageInterface;
import imagemodel.Pixel;

import static org.junit.Assert.assertTrue;

/**
 * Test class for the ImageCopy class.
 */
public class ImageCopyTest {

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor0Width() {
    ImageCopyInterface obj = new ImageCopy(0, 2);
    assertTrue(obj.deepCopyImage().equals(obj.deepCopyImage()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor0Height() {
    ImageCopyInterface obj = new ImageCopy(2, 0);
    assertTrue(obj.deepCopyImage().equals(obj.deepCopyImage()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeWidth() {
    ImageCopyInterface obj = new ImageCopy(-10, 2);
    assertTrue(obj.deepCopyImage().equals(obj.deepCopyImage()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeHeight() {
    ImageCopyInterface obj = new ImageCopy(10, -2);
    assertTrue(obj.deepCopyImage().equals(obj.deepCopyImage()));
  }

  @Test
  public void testConstructorandCopy() {
    ImageCopyInterface obj = new ImageCopy(1, 1);
    ImageInterface obj2 = new Image(1, 1, List.of(List.of(
            new Pixel(255, 255, 255))));
    assertTrue(obj.deepCopyImage().equals(obj2));
  }

  @Test
  public void testSetPixel() {
    ImageInterface obj2 = new Image(1, 1, List.of(List.of(
            new Pixel(255, 255, 255))));
    ImageCopyInterface obj = new ImageCopy(1, 1);
    obj.setPixel(0, 0, new Pixel(255, 255, 255));
    assertTrue(obj.deepCopyImage().equals(obj2));
  }
}
