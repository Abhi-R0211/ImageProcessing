package imagestest;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import imagecontroller.ImageHandler;
import imagemodel.Image;
import imagemodel.ImageOperations;
import imagemodel.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the image operations on a JPG image file.
 */
public class JPGTest extends AbstractTest {

  @Test(expected = IOException.class)
  public void inputTestInvalid() throws IOException {
    ImageHandler ih = new ImageHandler();
    Image actual = ih.loadImage("src/res/JPG/Sample.png");
  }

  @Test
  public void inputTest() throws IOException {
    ImageHandler ih = new ImageHandler();
    Image actual = ih.loadImage("src/res/JPG/Sample.jpg");
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(50, 51, 0));
    expected.setPixel(1, 0, new Pixel(194, 195, 125));
    expected.setPixel(0, 1, new Pixel(66, 67, 0));
    expected.setPixel(1, 1, new Pixel(210, 211, 141));
    assertTrue(expected.equals(actual));
  }

  @Test
  public void outputTest() throws IOException {
    ImageHandler ih = new ImageHandler();
    Image actual = ih.loadImage("src/res/JPG/Sample.jpg");
    ih.saveImage(actual, "src/res/JPG/Sample1.jpg", "jpg");
    assertEquals(true, new File("src/res/JPG/Sample1.jpg").exists());
  }

  @Test
  public void testHorizontalFlip() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.applyHorizontalFlip(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(1, 0, new Pixel(50, 51, 0));
    expected.setPixel(0, 0, new Pixel(194, 195, 125));
    expected.setPixel(1, 1, new Pixel(66, 67, 0));
    expected.setPixel(0, 1, new Pixel(210, 211, 141));
    assertTrue(expected.equals(actual));
  }

  @Test
  public void invalidTestHorizontalFlip() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.applyHorizontalFlip(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(1, 0, new Pixel(193, 252, 251));
    expected.setPixel(0, 0, new Pixel(119, 90, 47));
    expected.setPixel(1, 1, new Pixel(131, 201, 172));
    expected.setPixel(0, 1, new Pixel(62, 130, 103));
    assertFalse(expected.equals(actual));
  }

  @Test
  public void testVerticalFlip() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.applyVerticalFlip(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(1, 0, new Pixel(210, 211, 141));
    expected.setPixel(0, 0, new Pixel(66, 67, 0));
    expected.setPixel(1, 1, new Pixel(194, 195, 125));
    expected.setPixel(0, 1, new Pixel(50, 51, 0));
    assertTrue(expected.equals(actual));
  }

  @Test
  public void invalidTestVerticalFlip() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.applyVerticalFlip(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(1, 0, new Pixel(62, 130, 143));
    expected.setPixel(0, 0, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(119, 12, 47));
    expected.setPixel(0, 1, new Pixel(193, 252, 251));
    assertFalse(expected.equals(actual));
  }

  @Test
  public void brightness() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.applyBrightness(ih.loadImage("src/res/JPG/Sample.jpg"), 10);
    Image expected = new Image(2, 2);
    expected.setPixel(1, 0, new Pixel(204, 205, 135));
    expected.setPixel(0, 0, new Pixel(60, 61, 10));
    expected.setPixel(1, 1, new Pixel(220, 221, 151));
    expected.setPixel(0, 1, new Pixel(76, 77, 10));
    assertTrue(expected.equals(actual));
  }

  @Test
  public void invalidBrightness() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.applyBrightness(ih.loadImage("src/res/JPG/Sample.jpg"), 10);
    Image expected = new Image(2, 2);
    expected.setPixel(1, 0, new Pixel(129, 100, 57));
    expected.setPixel(0, 0, new Pixel(203, 255, 255));
    expected.setPixel(1, 1, new Pixel(132, 140, 153));
    expected.setPixel(0, 1, new Pixel(141, 211, 182));
    assertFalse(expected.equals(actual));
  }

  @Test
  public void darken() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.applyBrightness(ih.loadImage("src/res/JPG/Sample.jpg"), -10);
    Image expected = new Image(2, 2);
    expected.setPixel(1, 0, new Pixel(184, 185, 115));
    expected.setPixel(0, 0, new Pixel(40, 41, 0));
    expected.setPixel(1, 1, new Pixel(200, 201, 131));
    expected.setPixel(0, 1, new Pixel(56, 57, 0));
    assertTrue(expected.equals(actual));
  }

  @Test
  public void invalidDarken() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.applyBrightness(ih.loadImage("src/res/JPG/Sample.jpg"), -10);
    Image expected = new Image(2, 2);
    expected.setPixel(1, 0, new Pixel(129, 100, 57));
    expected.setPixel(0, 0, new Pixel(203, 255, 255));
    expected.setPixel(1, 1, new Pixel(13, 140, 153));
    expected.setPixel(0, 1, new Pixel(141, 211, 182));
    assertFalse(expected.equals(actual));
  }

  @Test
  public void redComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.visualizeRedComponent(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(50, 50, 50));
    expected.setPixel(1, 0, new Pixel(194, 194, 194));
    expected.setPixel(0, 1, new Pixel(66, 66, 66));
    expected.setPixel(1, 1, new Pixel(210, 210, 210));
    assertTrue(expected.equals(actual));
  }

  @Test
  public void invalidRedComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.visualizeRedComponent(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 193, 193));
    expected.setPixel(1, 0, new Pixel(157, 145, 132));
    expected.setPixel(0, 1, new Pixel(131, 131, 131));
    expected.setPixel(1, 1, new Pixel(62, 62, 62));
    assertFalse(expected.equals(actual));
  }

  @Test
  public void blueComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.visualizeBlueComponent(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(125, 125, 125));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(141, 141, 141));
    assertTrue(expected.equals(actual));
  }

  @Test
  public void invalidBlueComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.visualizeBlueComponent(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(251, 251, 251));
    expected.setPixel(1, 0, new Pixel(49, 47, 47));
    expected.setPixel(0, 1, new Pixel(172, 172, 172));
    expected.setPixel(1, 1, new Pixel(143, 143, 143));
    assertFalse(expected.equals(actual));
  }

  @Test
  public void greenComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.visualizeGreenComponent(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(51, 51, 51));
    expected.setPixel(0, 1, new Pixel(67, 67, 67));
    expected.setPixel(1, 0, new Pixel(195, 195, 195));
    expected.setPixel(1, 1, new Pixel(211, 211, 211));
    assertTrue(expected.equals(actual));
  }

  @Test
  public void invalidGreenComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.visualizeGreenComponent(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(252, 252, 252));
    expected.setPixel(1, 0, new Pixel(90, 90, 90));
    expected.setPixel(0, 1, new Pixel(211, 201, 201));
    expected.setPixel(1, 1, new Pixel(130, 130, 130));
    assertFalse(expected.equals(actual));
  }

  @Test
  public void valueComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.visualizeValue(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(51, 51, 51));
    expected.setPixel(1, 0, new Pixel(195, 195, 195));
    expected.setPixel(0, 1, new Pixel(67, 67, 67));
    expected.setPixel(1, 1, new Pixel(211, 211, 211));
    assertTrue(expected.equals(actual));
  }

  @Test
  public void invalidValueComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.visualizeValue(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(252, 252, 252));
    expected.setPixel(1, 0, new Pixel(119, 119, 119));
    expected.setPixel(0, 1, new Pixel(211, 201, 201));
    expected.setPixel(1, 1, new Pixel(143, 143, 143));
    assertFalse(expected.equals(actual));
  }

  @Test
  public void lumaComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.visualizeLuma(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(47, 47, 47));
    expected.setPixel(1, 0, new Pixel(189, 189, 189));
    expected.setPixel(0, 1, new Pixel(61, 61, 61));
    expected.setPixel(1, 1, new Pixel(205, 205, 205));
    assertTrue(expected.equals(actual));
  }

  @Test
  public void invalidLumaComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.visualizeLuma(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(239, 239, 239));
    expected.setPixel(1, 0, new Pixel(93, 93, 93));
    expected.setPixel(0, 1, new Pixel(194, 184, 184));
    expected.setPixel(1, 1, new Pixel(116, 116, 116));
    assertFalse(expected.equals(actual));
  }

  @Test
  public void intensityComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.visualizeIntensity(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(33, 33, 33));
    expected.setPixel(1, 0, new Pixel(171, 171, 171));
    expected.setPixel(0, 1, new Pixel(44, 44, 44));
    expected.setPixel(1, 1, new Pixel(187, 187, 187));
    assertTrue(expected.equals(actual));
  }

  @Test
  public void invalidIntensityComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.visualizeIntensity(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(232, 232, 232));
    expected.setPixel(1, 0, new Pixel(85, 85, 85));
    expected.setPixel(0, 1, new Pixel(168, 168, 168));
    expected.setPixel(1, 1, new Pixel(110, 111, 111));
    assertFalse(expected.equals(actual));
  }

  @Test
  public void rgbSplit() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actualr = io.visualizeRedComponent(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image actualg = io.visualizeGreenComponent(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image actualb = io.visualizeBlueComponent(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expectedr = new Image(2, 2);
    expectedr.setPixel(0, 0, new Pixel(50, 50, 50));
    expectedr.setPixel(1, 0, new Pixel(194, 194, 194));
    expectedr.setPixel(0, 1, new Pixel(66, 66, 66));
    expectedr.setPixel(1, 1, new Pixel(210, 210, 210));
    assertTrue(expectedr.equals(actualr));
    Image expectedg = new Image(2, 2);
    expectedg.setPixel(0, 0, new Pixel(51, 51, 51));
    expectedg.setPixel(0, 1, new Pixel(67, 67, 67));
    expectedg.setPixel(1, 0, new Pixel(195, 195, 195));
    expectedg.setPixel(1, 1, new Pixel(211, 211, 211));
    assertTrue(expectedg.equals(actualg));
    Image expectedb = new Image(2, 2);
    expectedb.setPixel(0, 0, new Pixel(0, 0, 0));
    expectedb.setPixel(1, 0, new Pixel(125, 125, 125));
    expectedb.setPixel(0, 1, new Pixel(0, 0, 0));
    expectedb.setPixel(1, 1, new Pixel(141, 141, 141));
    assertTrue(expectedb.equals(actualb));
  }

  @Test
  public void invalidRgbSplit() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actualr = io.visualizeRedComponent(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image actualg = io.visualizeGreenComponent(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image actualb = io.visualizeBlueComponent(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expectedr = new Image(2, 2);
    expectedr.setPixel(0, 0, new Pixel(153, 193, 193));
    expectedr.setPixel(1, 0, new Pixel(12, 119, 119));
    expectedr.setPixel(0, 1, new Pixel(121, 131, 131));
    expectedr.setPixel(1, 1, new Pixel(62, 62, 62));
    assertFalse(expectedr.equals(actualr));
    Image expectedg = new Image(2, 2);
    expectedg.setPixel(0, 0, new Pixel(242, 252, 252));
    expectedg.setPixel(1, 0, new Pixel(92, 90, 90));
    expectedg.setPixel(0, 1, new Pixel(251, 201, 201));
    expectedg.setPixel(1, 1, new Pixel(130, 130, 130));
    assertFalse(expectedg.equals(actualg));
    Image expectedb = new Image(2, 2);
    expectedb.setPixel(0, 0, new Pixel(251, 251, 251));
    expectedb.setPixel(1, 0, new Pixel(41, 47, 47));
    expectedb.setPixel(0, 1, new Pixel(162, 172, 172));
    expectedb.setPixel(1, 1, new Pixel(113, 143, 143));
    assertFalse(expectedb.equals(actualb));
  }

  @Test
  public void RgbCombine() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actualr = io.visualizeRedComponent(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image actualg = io.visualizeGreenComponent(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image actualb = io.visualizeBlueComponent(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image combine = io.combineRGB(actualr, actualg, actualb);
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(50, 51, 0));
    expected.setPixel(1, 0, new Pixel(194, 195, 125));
    expected.setPixel(0, 1, new Pixel(66, 67, 0));
    expected.setPixel(1, 1, new Pixel(210, 211, 141));
    assertTrue(expected.equals(combine));
  }

  @Test
  public void invalidRgbCombine() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actualr = io.visualizeRedComponent(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image actualg = io.visualizeGreenComponent(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image actualb = io.visualizeBlueComponent(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image combine = io.combineRGB(actualr, actualg, actualb);
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 252, 251));
    expected.setPixel(1, 0, new Pixel(129, 90, 47));
    expected.setPixel(0, 1, new Pixel(121, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertFalse(expected.equals(combine));
  }

  @Test
  public void blur() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.applyBlur(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(50, 51, 0));
    expected.setPixel(1, 0, new Pixel(194, 195, 125));
    expected.setPixel(0, 1, new Pixel(66, 67, 0));
    expected.setPixel(1, 1, new Pixel(210, 211, 141));
    assertTrue(expected.equals(actual));
  }

  @Test
  public void invalidBlur() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.applyBlur(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 252, 251));
    expected.setPixel(1, 0, new Pixel(129, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertFalse(expected.equals(actual));
  }

  @Test
  public void sharped() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.applySharpen(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(50, 51, 0));
    expected.setPixel(1, 0, new Pixel(194, 195, 125));
    expected.setPixel(0, 1, new Pixel(66, 67, 0));
    expected.setPixel(1, 1, new Pixel(210, 211, 141));
    assertTrue(expected.equals(actual));
  }

  @Test
  public void invalidSharped() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.applySharpen(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 252, 251));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(132, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertFalse(expected.equals(actual));
  }

  @Test
  public void sepia() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.applySepia(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(58, 52, 40));
    expected.setPixel(1, 0, new Pixel(249, 222, 173));
    expected.setPixel(0, 1, new Pixel(77, 68, 53));
    expected.setPixel(1, 1, new Pixel(255, 241, 188));
    assertTrue(expected.equals(actual));
  }

  @Test
  public void invalidSepia() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.applySepia(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 219));
    expected.setPixel(1, 0, new Pixel(124, 111, 86));
    expected.setPixel(0, 1, new Pixel(238, 212, 165));
    expected.setPixel(1, 1, new Pixel(141, 134, 105));
    assertFalse(expected.equals(actual));
  }

  @Test
  public void multipleFunction() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.applySepia(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image afterop = io.applyVerticalFlip(actual);
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(77, 68, 53));
    expected.setPixel(1, 0, new Pixel(255, 241, 188));
    expected.setPixel(0, 1, new Pixel(58, 52, 40));
    expected.setPixel(1, 1, new Pixel(249, 222, 173));
    assertTrue(expected.equals(afterop));
  }

  @Test
  public void multipleFunction2() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.applyBlur(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image afterop = io.applyHorizontalFlip(actual);
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(194, 195, 125));
    expected.setPixel(1, 0, new Pixel(50, 51, 0));
    expected.setPixel(0, 1, new Pixel(210, 211, 141));
    expected.setPixel(1, 1, new Pixel(66, 67, 0));
    assertTrue(expected.equals(afterop));
  }

  @Test
  public void multipleFunction3() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageHandler ih = new ImageHandler();
    Image actual = io.applyBlur(ih.loadImage("src/res/JPG/Sample.jpg"));
    Image afterop = io.applyVerticalFlip(actual);
    Image afterop2 = io.applyHorizontalFlip(afterop);
    Image expected = new Image(2, 2);
    expected.setPixel(0, 0, new Pixel(210, 211, 141));
    expected.setPixel(1, 0, new Pixel(66, 67, 0));
    expected.setPixel(0, 1, new Pixel(194, 195, 125));
    expected.setPixel(1, 1, new Pixel(50, 51, 0));
    assertTrue(expected.equals(afterop2));
  }

  @Test
  public void testEquals1() throws IOException {
    ImageHandler ih = new ImageHandler();
    Image one = ih.loadImage("src/res/JPG/Sample.jpg");
    assertTrue(one.equals(one));
  }

  @Test
  public void testEquals2() throws IOException {
    ImageHandler ih = new ImageHandler();
    Image one = ih.loadImage("src/res/JPG/Sample.jpg");
    Image two = ih.loadImage("src/res/JPG/Sample.jpg");
    assertTrue(one.equals(two));
    assertTrue(two.equals(one));
  }

  @Test
  public void testEquals3() throws IOException {
    ImageHandler ih = new ImageHandler();
    Image one = ih.loadImage("src/res/JPG/Sample.jpg");
    Image two = ih.loadImage("src/res/JPG/Sample.jpg");
    Image three = ih.loadImage("src/res/JPG/Sample.jpg");
    assertTrue(one.equals(two));
    assertTrue(two.equals(three));
    assertTrue(one.equals(three));
  }

  @Test
  public void testEquals4() throws IOException {
    ImageHandler ih = new ImageHandler();
    Image one = ih.loadImage("src/res/PNG/Sample.png");
    Image two = ih.loadImage("src/res/JPEG/Sample.jpeg");
    assertFalse(one.equals(two));
  }

  @Test
  public void testHash1() throws IOException {
    ImageHandler ih = new ImageHandler();
    Image one = ih.loadImage("src/res/JPG/Sample.jpg");
    assertTrue(one.hashCode() == one.hashCode());
  }

  @Test
  public void testHash2() throws IOException {
    ImageHandler ih = new ImageHandler();
    Image one = ih.loadImage("src/res/PNG/Sample.png");
    Image two = ih.loadImage("src/res/JPG/Sample.jpg");
    assertFalse(one.hashCode() == two.hashCode());
  }

  @Test
  public void testHash3() throws IOException {
    ImageHandler ih = new ImageHandler();
    Image one = ih.loadImage("src/res/JPG/Sample.jpg");
    Image two = ih.loadImage("src/res/JPG/Sample.jpg");
    assertTrue(one.hashCode() == two.hashCode());
  }
}
