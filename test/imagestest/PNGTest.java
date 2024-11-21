package imagestest;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import imagecontroller.ImageFormatHandler;
import imagecontroller.ImageHandler;
import imagemodel.AdditionalImageOperations;
import imagemodel.AdditionalOperations;
import imagemodel.ExtendedImageOperations;
import imagemodel.ExtendedOperations;
import imagemodel.ImageCopy;
import imagemodel.ImageCopyInterface;
import imagemodel.ImageInterface;
import imagemodel.ImageOperations;
import imagemodel.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the image operations on a PNG image file.
 */
public class PNGTest extends AbstractTest {

  @Test
  public void inputTest() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = ih.loadImage("res/PNG/Sample.png");
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 252, 251));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test(expected = IOException.class)
  public void inputTestInvalid() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = ih.loadImage("re/PNG/Sample.png");
  }

  @Test
  public void outputTest() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = ih.loadImage("res/PNG/Sample.png");
    ih.saveImage(actual, "res/PNG/Sample1.png", "png");
    assertTrue(new File("res/PNG/Sample1.png").exists());
  }

  @Test
  public void testHorizontalFlip() throws IOException {
    ImageOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyHorizontalFlip(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(1, 0, new Pixel(193, 252, 251));
    expected.setPixel(0, 0, new Pixel(119, 90, 47));
    expected.setPixel(1, 1, new Pixel(131, 201, 172));
    expected.setPixel(0, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void invalidTestHorizontalFlip() throws IOException {
    ImageOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyHorizontalFlip(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(1, 0, new Pixel(193, 252, 251));
    expected.setPixel(0, 0, new Pixel(119, 90, 47));
    expected.setPixel(1, 1, new Pixel(131, 201, 172));
    expected.setPixel(0, 1, new Pixel(62, 130, 103));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void testVerticalFlip() throws IOException {
    ImageOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyVerticalFlip(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(1, 0, new Pixel(62, 130, 143));
    expected.setPixel(0, 0, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(193, 252, 251));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void invalidTestVerticalFlip() throws IOException {
    ImageOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyVerticalFlip(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(1, 0, new Pixel(62, 130, 143));
    expected.setPixel(0, 0, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(119, 12, 47));
    expected.setPixel(0, 1, new Pixel(193, 252, 251));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void brightness() throws IOException {
    ImageOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyBrightness(ih.loadImage(
            "res/PNG/Sample.png"), 10);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(1, 0, new Pixel(129, 100, 57));
    expected.setPixel(0, 0, new Pixel(203, 255, 255));
    expected.setPixel(1, 1, new Pixel(72, 140, 153));
    expected.setPixel(0, 1, new Pixel(141, 211, 182));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void invalidBrightness() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyBrightness(ih.loadImage(
            "res/PNG/Sample.png"), 10);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(1, 0, new Pixel(129, 100, 57));
    expected.setPixel(0, 0, new Pixel(203, 255, 255));
    expected.setPixel(1, 1, new Pixel(132, 140, 153));
    expected.setPixel(0, 1, new Pixel(141, 211, 182));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void darken() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyBrightness(ih.loadImage(
            "res/PNG/Sample.png"), -10);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(1, 0, new Pixel(109, 80, 37));
    expected.setPixel(0, 0, new Pixel(183, 242, 241));
    expected.setPixel(1, 1, new Pixel(52, 120, 133));
    expected.setPixel(0, 1, new Pixel(121, 191, 162));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void invalidDarken() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyBrightness(ih.loadImage(
            "res/PNG/Sample.png"), -10);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(1, 0, new Pixel(129, 100, 57));
    expected.setPixel(0, 0, new Pixel(203, 255, 255));
    expected.setPixel(1, 1, new Pixel(13, 140, 153));
    expected.setPixel(0, 1, new Pixel(141, 211, 182));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void redComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeRedComponent(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 193, 193));
    expected.setPixel(1, 0, new Pixel(119, 119, 119));
    expected.setPixel(0, 1, new Pixel(131, 131, 131));
    expected.setPixel(1, 1, new Pixel(62, 62, 62));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void invalidRedComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeRedComponent(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 193, 193));
    expected.setPixel(1, 0, new Pixel(157, 145, 132));
    expected.setPixel(0, 1, new Pixel(131, 131, 131));
    expected.setPixel(1, 1, new Pixel(62, 62, 62));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void blueComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeBlueComponent(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(251, 251, 251));
    expected.setPixel(1, 0, new Pixel(47, 47, 47));
    expected.setPixel(0, 1, new Pixel(172, 172, 172));
    expected.setPixel(1, 1, new Pixel(143, 143, 143));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void invalidBlueComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeBlueComponent(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(251, 251, 251));
    expected.setPixel(1, 0, new Pixel(49, 47, 47));
    expected.setPixel(0, 1, new Pixel(172, 172, 172));
    expected.setPixel(1, 1, new Pixel(143, 143, 143));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void greenComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeGreenComponent(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(252, 252, 252));
    expected.setPixel(1, 0, new Pixel(90, 90, 90));
    expected.setPixel(0, 1, new Pixel(201, 201, 201));
    expected.setPixel(1, 1, new Pixel(130, 130, 130));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void invalidGreenComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeGreenComponent(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(252, 252, 252));
    expected.setPixel(1, 0, new Pixel(90, 90, 90));
    expected.setPixel(0, 1, new Pixel(211, 201, 201));
    expected.setPixel(1, 1, new Pixel(130, 130, 130));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void valueComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeValue(ih.loadImage("res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(252, 252, 252));
    expected.setPixel(1, 0, new Pixel(119, 119, 119));
    expected.setPixel(0, 1, new Pixel(201, 201, 201));
    expected.setPixel(1, 1, new Pixel(143, 143, 143));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void invalidValueComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeValue(ih.loadImage("res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(252, 252, 252));
    expected.setPixel(1, 0, new Pixel(119, 119, 119));
    expected.setPixel(0, 1, new Pixel(211, 201, 201));
    expected.setPixel(1, 1, new Pixel(143, 143, 143));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void lumaComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeLuma(ih.loadImage("res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(239, 239, 239));
    expected.setPixel(1, 0, new Pixel(93, 93, 93));
    expected.setPixel(0, 1, new Pixel(184, 184, 184));
    expected.setPixel(1, 1, new Pixel(116, 116, 116));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void invalidLumaComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeLuma(ih.loadImage("res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(239, 239, 239));
    expected.setPixel(1, 0, new Pixel(93, 93, 93));
    expected.setPixel(0, 1, new Pixel(194, 184, 184));
    expected.setPixel(1, 1, new Pixel(116, 116, 116));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void intensityComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeIntensity(ih.loadImage("res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(232, 232, 232));
    expected.setPixel(1, 0, new Pixel(85, 85, 85));
    expected.setPixel(0, 1, new Pixel(168, 168, 168));
    expected.setPixel(1, 1, new Pixel(111, 111, 111));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void invalidIntensityComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeIntensity(ih.loadImage("res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(232, 232, 232));
    expected.setPixel(1, 0, new Pixel(85, 85, 85));
    expected.setPixel(0, 1, new Pixel(168, 168, 168));
    expected.setPixel(1, 1, new Pixel(110, 111, 111));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void rgbSplit() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actualr = io.visualizeRedComponent(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageInterface actualg = io.visualizeGreenComponent(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageInterface actualb = io.visualizeBlueComponent(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageCopyInterface expectedr = new ImageCopy(2, 2);
    expectedr.setPixel(0, 0, new Pixel(193, 193, 193));
    expectedr.setPixel(1, 0, new Pixel(119, 119, 119));
    expectedr.setPixel(0, 1, new Pixel(131, 131, 131));
    expectedr.setPixel(1, 1, new Pixel(62, 62, 62));
    assertEquals(expectedr.deepCopyImage(), actualr);
    ImageCopyInterface expectedg = new ImageCopy(2, 2);
    expectedg.setPixel(0, 0, new Pixel(252, 252, 252));
    expectedg.setPixel(1, 0, new Pixel(90, 90, 90));
    expectedg.setPixel(0, 1, new Pixel(201, 201, 201));
    expectedg.setPixel(1, 1, new Pixel(130, 130, 130));
    assertEquals(expectedg.deepCopyImage(), actualg);
    ImageCopyInterface expectedb = new ImageCopy(2, 2);
    expectedb.setPixel(0, 0, new Pixel(251, 251, 251));
    expectedb.setPixel(1, 0, new Pixel(47, 47, 47));
    expectedb.setPixel(0, 1, new Pixel(172, 172, 172));
    expectedb.setPixel(1, 1, new Pixel(143, 143, 143));
    assertEquals(expectedb.deepCopyImage(), actualb);
  }

  @Test
  public void invalidRgbSplit() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actualr = io.visualizeRedComponent(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageInterface actualg = io.visualizeGreenComponent(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageInterface actualb = io.visualizeBlueComponent(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageCopyInterface expectedr = new ImageCopy(2, 2);
    expectedr.setPixel(0, 0, new Pixel(193, 193, 193));
    expectedr.setPixel(1, 0, new Pixel(119, 119, 119));
    expectedr.setPixel(0, 1, new Pixel(131, 131, 131));
    expectedr.setPixel(1, 1, new Pixel(62, 62, 62));
    assertEquals(expectedr.deepCopyImage(), actualr);
    ImageCopyInterface expectedg = new ImageCopy(2, 2);
    expectedg.setPixel(0, 0, new Pixel(252, 252, 252));
    expectedg.setPixel(1, 0, new Pixel(90, 90, 90));
    expectedg.setPixel(0, 1, new Pixel(201, 201, 201));
    expectedg.setPixel(1, 1, new Pixel(130, 130, 130));
    assertEquals(expectedg.deepCopyImage(), actualg);
    ImageCopyInterface expectedb = new ImageCopy(2, 2);
    expectedb.setPixel(0, 0, new Pixel(251, 251, 251));
    expectedb.setPixel(1, 0, new Pixel(47, 47, 47));
    expectedb.setPixel(0, 1, new Pixel(172, 172, 172));
    expectedb.setPixel(1, 1, new Pixel(143, 143, 143));
    assertEquals(expectedb.deepCopyImage(), actualb);
  }

  @Test
  public void RgbCombine() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actualr = io.visualizeRedComponent(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageInterface actualg = io.visualizeGreenComponent(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageInterface actualb = io.visualizeBlueComponent(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageInterface combine = io.combineRGB(actualr, actualg, actualb);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 252, 251));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), combine);
  }

  @Test
  public void invalidRgbCombine() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actualr = io.visualizeRedComponent(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageInterface actualg = io.visualizeGreenComponent(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageInterface actualb = io.visualizeBlueComponent(ih.loadImage(
            "res/PNG/Sample.png"));
    ImageInterface combine = io.combineRGB(actualr, actualg, actualb);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 252, 251));
    expected.setPixel(1, 0, new Pixel(129, 90, 47));
    expected.setPixel(0, 1, new Pixel(121, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertNotEquals(expected.deepCopyImage(), combine);
  }

  @Test
  public void blur() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyBlur(ih.loadImage("res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(159, 204, 191));
    expected.setPixel(1, 0, new Pixel(122, 134, 111));
    expected.setPixel(0, 1, new Pixel(128, 190, 173));
    expected.setPixel(1, 1, new Pixel(93, 143, 137));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void invalidBlur() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyBlur(ih.loadImage("res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 252, 251));
    expected.setPixel(1, 0, new Pixel(129, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void sharped() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applySharpen(ih.loadImage("res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(209, 255, 255));
    expected.setPixel(1, 0, new Pixel(117, 76, 31));
    expected.setPixel(0, 1, new Pixel(132, 214, 187));
    expected.setPixel(1, 1, new Pixel(45, 114, 129));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void invalidSharped() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applySharpen(ih.loadImage("res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 252, 251));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(132, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void sepia() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applySepia(ih.loadImage("res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 219));
    expected.setPixel(1, 0, new Pixel(124, 111, 86));
    expected.setPixel(0, 1, new Pixel(238, 212, 165));
    expected.setPixel(1, 1, new Pixel(151, 134, 105));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void invalidSepia() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applySepia(ih.loadImage("res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 219));
    expected.setPixel(1, 0, new Pixel(124, 111, 86));
    expected.setPixel(0, 1, new Pixel(238, 212, 165));
    expected.setPixel(1, 1, new Pixel(141, 134, 105));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void compress() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.compressImage(ih.loadImage(
            "res/PNG/Sample.png"), 50);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(192, 249, 255));
    expected.setPixel(1, 0, new Pixel(120, 87, 51));
    expected.setPixel(0, 1, new Pixel(132, 204, 168));
    expected.setPixel(1, 1, new Pixel(61, 133, 139));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void wrongCompress1() throws IllegalArgumentException, IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.compressImage(ih.loadImage(
            "res/PNG/Sample.png"), 110);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(195, 249, 255));
    expected.setPixel(1, 0, new Pixel(120, 87, 51));
    expected.setPixel(0, 1, new Pixel(132, 204, 168));
    expected.setPixel(1, 1, new Pixel(61, 133, 139));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void wrongCompress2() throws IllegalArgumentException, IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.compressImage(ih.loadImage(
            "res/PNG/Sample.png"), -10);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(195, 249, 255));
    expected.setPixel(1, 0, new Pixel(120, 87, 51));
    expected.setPixel(0, 1, new Pixel(132, 204, 168));
    expected.setPixel(1, 1, new Pixel(61, 133, 139));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void wrongCompress3() throws IllegalArgumentException, IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.compressImage(null, 20);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(195, 249, 255));
    expected.setPixel(1, 0, new Pixel(120, 87, 51));
    expected.setPixel(0, 1, new Pixel(132, 204, 168));
    expected.setPixel(1, 1, new Pixel(61, 133, 139));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void histogram() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.createHistogram(ih.loadImage("res/PNG/Sample.png"));
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        assertTrue(actual.getPixel(i, j).getRed() >= 0
                && actual.getPixel(i, j).getRed() <= 255);
        assertTrue(actual.getPixel(i, j).getBlue() >= 0
                && actual.getPixel(i, j).getBlue() <= 255);
        assertTrue(actual.getPixel(i, j).getGreen() >= 0
                && actual.getPixel(i, j).getGreen() <= 255);
      }
    }
  }

  @Test
  public void invalidHistogram1() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.createHistogram(ih.loadImage("res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(192, 249, 255));
    expected.setPixel(1, 0, new Pixel(120, 87, 51));
    expected.setPixel(0, 1, new Pixel(132, 204, 168));
    expected.setPixel(1, 1, new Pixel(61, 133, 139));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidHistogram2() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.createHistogram(null);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(192, 249, 255));
    expected.setPixel(1, 0, new Pixel(120, 87, 51));
    expected.setPixel(0, 1, new Pixel(132, 204, 168));
    expected.setPixel(1, 1, new Pixel(61, 133, 139));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void colourCorrect() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.colorCorrect(ih.loadImage("res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(197, 228, 255));
    expected.setPixel(1, 0, new Pixel(123, 66, 66));
    expected.setPixel(0, 1, new Pixel(135, 177, 191));
    expected.setPixel(1, 1, new Pixel(66, 106, 162));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void invalidColour1() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.colorCorrect(ih.loadImage("res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(197, 228, 255));
    expected.setPixel(1, 0, new Pixel(123, 66, 66));
    expected.setPixel(0, 1, new Pixel(130, 170, 190));
    expected.setPixel(1, 1, new Pixel(130, 170, 190));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test(expected = NullPointerException.class)
  public void invalidColour2() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.colorCorrect(ih.loadImage(null));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(197, 228, 255));
    expected.setPixel(1, 0, new Pixel(123, 66, 66));
    expected.setPixel(0, 1, new Pixel(130, 170, 190));
    expected.setPixel(1, 1, new Pixel(130, 170, 190));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void levelsAdjust() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.levelsAdjust(ih.loadImage(
            "res/PNG/Sample.png"), 10, 15, 20);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 255));
    expected.setPixel(1, 0, new Pixel(255, 255, 255));
    expected.setPixel(0, 1, new Pixel(255, 255, 255));
    expected.setPixel(1, 1, new Pixel(255, 255, 255));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidWhiteLevelsAdjust() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.levelsAdjust(ih.loadImage(
            "res/PNG/Sample.png"), 10, 15, -10);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 255));
    expected.setPixel(1, 0, new Pixel(255, 255, 255));
    expected.setPixel(0, 1, new Pixel(255, 255, 255));
    expected.setPixel(1, 1, new Pixel(255, 255, 255));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidBlackLevelsAdjust() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.levelsAdjust(ih.loadImage(
            "res/PNG/Sample.png"), -10, 15, 20);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 255));
    expected.setPixel(1, 0, new Pixel(255, 255, 255));
    expected.setPixel(0, 1, new Pixel(255, 255, 255));
    expected.setPixel(1, 1, new Pixel(255, 255, 255));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMidLevelsAdjust() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.levelsAdjust(ih.loadImage(
            "res/PNG/Sample.png"), 10, -15, 20);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 255));
    expected.setPixel(1, 0, new Pixel(255, 255, 255));
    expected.setPixel(0, 1, new Pixel(255, 255, 255));
    expected.setPixel(1, 1, new Pixel(255, 255, 255));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidAllLevelsAdjust1() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.levelsAdjust(ih.loadImage(
            "res/PNG/Sample.png"), 20, 10, 5);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 255));
    expected.setPixel(1, 0, new Pixel(255, 255, 255));
    expected.setPixel(0, 1, new Pixel(255, 255, 255));
    expected.setPixel(1, 1, new Pixel(255, 255, 255));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidAllLevelsAdjust2() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.levelsAdjust(ih.loadImage(
            "res/PNG/Sample.png"), 10, 20, 5);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 255));
    expected.setPixel(1, 0, new Pixel(255, 255, 255));
    expected.setPixel(0, 1, new Pixel(255, 255, 255));
    expected.setPixel(1, 1, new Pixel(255, 255, 255));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidAllLevelsAdjust3() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.levelsAdjust(ih.loadImage(
            "res/PNG/Sample.png"), 10, 50, 5);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 255));
    expected.setPixel(1, 0, new Pixel(255, 255, 255));
    expected.setPixel(0, 1, new Pixel(255, 255, 255));
    expected.setPixel(1, 1, new Pixel(255, 255, 255));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidAllLevelsAdjust4() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.levelsAdjust(null, 10, 50, 100);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 255));
    expected.setPixel(1, 0, new Pixel(255, 255, 255));
    expected.setPixel(0, 1, new Pixel(255, 255, 255));
    expected.setPixel(1, 1, new Pixel(255, 255, 255));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void splitBlurCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PNG/Sample.png"), io::applyBlur);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(177, 239, 231));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(146, 213, 191));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void splitInvalidBlurCheck1() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PNG/Sample.png"), io::applyBlur);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(175, 239, 231));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(146, 213, 191));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void splitInvalidBlurCheck2() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(250, ih.loadImage(
            "res/PNG/Sample.png"), io::applyBlur);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(175, 239, 231));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(146, 213, 191));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void splitInvalidBlurCheck3() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(-50, ih.loadImage(
            "res/PNG/Sample.png"), io::applyBlur);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(175, 239, 231));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(146, 213, 191));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test(expected = IllegalArgumentException.class)
  public void splitInvalidBlurCheck4() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(50, null, io::applyBlur);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(175, 239, 231));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(146, 213, 191));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void splitSharpenCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PNG/Sample.png"), io::applySharpen);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(200, 255, 255));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(123, 194, 162));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void splitInvalidSharpenCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PNG/Sample.png"), io::applySharpen);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(200, 255, 255));
    expected.setPixel(1, 0, new Pixel(119, 80, 47));
    expected.setPixel(0, 1, new Pixel(123, 194, 162));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void splitSepiaCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PNG/Sample.png"), io::applySepia);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 219));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(238, 212, 165));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void splitInvalidSepiaCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PNG/Sample.png"), io::applySepia);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 245, 219));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(238, 212, 165));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void splitRedComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PNG/Sample.png"), io::visualizeRedComponent);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 193, 193));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 131, 131));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void splitInvalidRedComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PNG/Sample.png"), io::visualizeRedComponent);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(190, 190, 190));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 131, 131));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void splitGreenComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PNG/Sample.png"), io::visualizeGreenComponent);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(252, 252, 252));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(201, 201, 201));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void splitInvalidGreenComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PNG/Sample.png"), io::visualizeGreenComponent);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(190, 190, 190));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 131, 131));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void splitBlueComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PNG/Sample.png"), io::visualizeBlueComponent);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(251, 251, 251));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(172, 172, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void splitInvalidBlueComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PNG/Sample.png"), io::visualizeBlueComponent);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(250, 250, 250));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(172, 172, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void splitLumaComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PNG/Sample.png"), io::visualizeLuma);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(239, 239, 239));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(184, 184, 184));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void splitInvalidLumaComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PNG/Sample.png"), io::visualizeLuma);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(239, 239, 239));
    expected.setPixel(1, 0, new Pixel(19, 90, 47));
    expected.setPixel(0, 1, new Pixel(184, 184, 184));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void splitValueComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PNG/Sample.png"), io::visualizeValue);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(252, 252, 252));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(201, 201, 201));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void splitIntensityComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PNG/Sample.png"), io::visualizeIntensity);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(232, 232, 232));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(168, 168, 168));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitInvalidIntensityComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PNG/Sample.png"), io::visualizeValue);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(25, 252, 252));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(201, 201, 201));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void splitInvalidColourCorrectComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PNG/Sample.png"), io::colorCorrect);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(252, 252, 252));
    expected.setPixel(1, 0, new Pixel(19, 90, 47));
    expected.setPixel(0, 1, new Pixel(184, 184, 201));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertNotEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void splitColourCorrectComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PNG/Sample.png"), io::colorCorrect);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(230, 219, 247));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(168, 168, 168));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), actual);
  }

  @Test
  public void splitlevelAdjustComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PNG/Sample.png"),  img -> io.levelsAdjust(img, 10, 15, 20));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 255));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(255, 255, 255));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), actual);
  }


  @Test
  public void multipleFunction() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applySepia(ih.loadImage("res/PNG/Sample.png"));
    ImageInterface afterop = io.applyVerticalFlip(actual);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(238, 212, 165));
    expected.setPixel(1, 0, new Pixel(151, 134, 105));
    expected.setPixel(0, 1, new Pixel(255, 255, 219));
    expected.setPixel(1, 1, new Pixel(124, 111, 86));
    assertEquals(expected.deepCopyImage(), afterop);
  }

  @Test
  public void testEquals1() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface one = ih.loadImage("res/PNG/Sample.png");
    assertEquals(one, one);
  }

  @Test
  public void testEquals2() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface one = ih.loadImage("res/PNG/Sample.png");
    ImageInterface two = ih.loadImage("res/PNG/Sample.png");
    assertEquals(one, two);
    assertEquals(two, one);
  }

  @Test
  public void testEquals3() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface one = ih.loadImage("res/PNG/Sample.png");
    ImageInterface two = ih.loadImage("res/PNG/Sample.png");
    ImageInterface three = ih.loadImage("res/PNG/Sample.png");
    assertEquals(one, two);
    assertEquals(two, three);
    assertEquals(one, three);
  }

  @Test
  public void testEquals4() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface one = ih.loadImage("res/PNG/Sample.png");
    ImageInterface two = ih.loadImage("res/JPG/Sample.jpg");
    assertNotEquals(one, two);
  }

  @Test
  public void testHash1() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface one = ih.loadImage("res/PNG/Sample.png");
    assertEquals(one.hashCode(), one.hashCode());
  }

  @Test
  public void testHash2() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface one = ih.loadImage("res/PNG/Sample.png");
    ImageInterface two = ih.loadImage("res/JPG/Sample.jpg");
    assertNotEquals(one.hashCode(), two.hashCode());
  }

  @Test
  public void testHash3() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface one = ih.loadImage("res/PNG/Sample.png");
    ImageInterface two = ih.loadImage("res/PNG/Sample.png");
    assertEquals(one.hashCode(), two.hashCode());
  }

  @Test
  public void multipleFunction2() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyBlur(ih.loadImage("res/PNG/Sample.png"));
    ImageInterface afterop = io.applyHorizontalFlip(actual);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(122, 134, 111));
    expected.setPixel(1, 0, new Pixel(159, 204, 191));
    expected.setPixel(0, 1, new Pixel(93, 143, 137));
    expected.setPixel(1, 1, new Pixel(128, 190, 173));
    assertEquals(expected.deepCopyImage(), afterop);
  }

  @Test
  public void multipleFunction3() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyBlur(ih.loadImage("res/PNG/Sample.png"));
    ImageInterface afterop = io.applyVerticalFlip(actual);
    ImageInterface afterop2 = io.applyHorizontalFlip(afterop);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(93, 143, 137));
    expected.setPixel(1, 0, new Pixel(128, 190, 173));
    expected.setPixel(0, 1, new Pixel(122, 134, 111));
    expected.setPixel(1, 1, new Pixel(159, 204, 191));
    assertEquals(expected.deepCopyImage(), afterop2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void downsizeInvalid1() throws IOException {
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface temp = io.downscaleImage(null, 100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void downsizeInvalid2() throws IOException {
    AdditionalOperations io = new AdditionalImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface temp = io.downscaleImage(ih.loadImage("res/PNG/Sample.png"),
            100, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void downsizeInvalid3() throws IOException {
    AdditionalOperations io = new AdditionalImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface temp = io.downscaleImage(ih.loadImage("res/PNG/Sample.png"),
            1, 100);
  }

  @Test
  public void downsize() throws IOException {
    AdditionalOperations io = new AdditionalImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.downscaleImage(ih.loadImage("res/PNG/Sample.png"),
            1, 1);
    ImageCopyInterface expected = new ImageCopy(1, 1);
    expected.setPixel(0, 0, new Pixel(193, 252, 251));
    assertEquals(expected.deepCopyImage(), actual);
  }

  ImageInterface mask;

  @Before
  public void setUp() {
    ImageCopyInterface temp = new ImageCopy(1, 1);
    temp.setPixel(0, 0, new Pixel(0, 0, 0));
    mask = temp.deepCopyImage();
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidBlurMask1() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PNG/Sample.png");
    ImageInterface transformed = io.applyBlur(image, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidBlurMask2() {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface transformed = io.applyBlur(null, null);
  }

  @Test
  public void blurMask() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PNG/Sample.png");
    ImageInterface transformed = io.applyBlur(image, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(159, 204, 191));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), transformed);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSharpenMask1() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PNG/Sample.png");
    ImageInterface transformed = io.applySharpen(image, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSharpenMask2() {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface transformed = io.applySharpen(null, null);
  }

  @Test
  public void sharpenMask() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PNG/Sample.png");
    ImageInterface transformed = io.applySharpen(image, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(209, 255, 255));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), transformed);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSepiaMask1() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PNG/Sample.png");
    ImageInterface transformed = io.applySepia(image, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSepiaMask2() {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface transformed = io.applySepia(null, null);
  }

  @Test
  public void sepiaMask() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PNG/Sample.png");
    ImageInterface transformed = io.applySepia(image, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 219));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), transformed);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidLumaMask1() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PNG/Sample.png");
    ImageInterface transformed = io.visualizeLuma(image, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidLumaMask2() {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface transformed = io.visualizeLuma(null, null);
  }

  @Test
  public void lumaMask() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PNG/Sample.png");
    ImageInterface transformed = io.visualizeLuma(image, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(239, 239, 239));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), transformed);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidRedMask1() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PNG/Sample.png");
    ImageInterface transformed = io.visualizeRedComponent(image, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidRedMask2() {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface transformed = io.visualizeRedComponent(null, null);
  }

  @Test
  public void redMask() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PNG/Sample.png");
    ImageInterface transformed = io.visualizeRedComponent(image, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 193, 193));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), transformed);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidBlueMask1() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PNG/Sample.png");
    ImageInterface transformed = io.visualizeBlueComponent(image, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidBlueMask2() {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface transformed = io.visualizeBlueComponent(null, null);
  }

  @Test
  public void blueMask() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PNG/Sample.png");
    ImageInterface transformed = io.visualizeBlueComponent(image, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(251, 251, 251));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), transformed);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidGreenMask1() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PNG/Sample.png");
    ImageInterface transformed = io.visualizeGreenComponent(image, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidGreenMask2() {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface transformed = io.visualizeGreenComponent(null, null);
  }

  @Test
  public void greenMask() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PNG/Sample.png");
    ImageInterface transformed = io.visualizeGreenComponent(image, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(252, 252, 252));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), transformed);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidIntensityMask1() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PNG/Sample.png");
    ImageInterface transformed = io.visualizeIntensity(image, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidIntensityMask2() {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface transformed = io.visualizeIntensity(null, null);
  }

  @Test
  public void intensityMask() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PNG/Sample.png");
    ImageInterface transformed = io.visualizeIntensity(image, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(232, 232, 232));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), transformed);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidValueMask1() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PNG/Sample.png");
    ImageInterface transformed = io.visualizeValue(image, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidValueMask2() {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface transformed = io.visualizeValue(null, null);
  }

  @Test
  public void valueMask() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PNG/Sample.png");
    ImageInterface transformed = io.visualizeValue(image, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(252, 252, 252));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), transformed);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidBrightenMask1() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PNG/Sample.png");
    ImageInterface transformed = io.applyBrightness(image, 50, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidBrightenMask2() {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface transformed = io.applyBrightness(null, 50, null);
  }

  @Test
  public void brightenMask() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PNG/Sample.png");
    ImageInterface transformed = io.applyBrightness(image, 50, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(243, 255, 255));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), transformed);
  }

  @Test
  public void darkenMask() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PNG/Sample.png");
    ImageInterface transformed = io.applyBrightness(image, -50, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(143, 202, 201));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), transformed);
  }

  @Test
  public void testScript() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    execute("run res/Scripts/PNG/test.txt");
    ImageInterface actual = ih.loadImage("res/PNG/Sample-save.png");
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 252, 251));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertEquals(expected.deepCopyImage(), actual);
  }
}