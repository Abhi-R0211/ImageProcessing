package imagestest;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import imagecontroller.ImageFormatHandler;
import imagecontroller.ImageHandler;
import imagecontroller.P3PPMHandler;
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the image operations on a PPM image file.
 */
public class PPMTest extends AbstractTest {

  @Test
  public void inputTest() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = ih.loadImage("res/PPM/Sample.ppm");
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(100, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test(expected = IOException.class)
  public void inputTestInvalid() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = ih.loadImage("res/PPM/Sample1.ppm");
  }

  @Test
  public void outputTest() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = ih.loadImage("res/PPM/Sample.ppm");
    ih.saveImage(actual, "res/PNG/Sample1.png", "png");
    assertEquals(true, new File("res/PNG/Sample1.png").exists());
  }

  @Test
  public void testHorizontalFlip() throws IOException {
    ImageOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applyHorizontalFlip(ih.loadImage("res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(100, 0, 0));
    expected.setPixel(1, 0, new Pixel(0, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 255, 175));
    expected.setPixel(1, 1, new Pixel(0, 0, 0));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidTestHorizontalFlip() throws IOException {
    ImageOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applyHorizontalFlip(ih.loadImage("res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(1, 0, new Pixel(193, 252, 251));
    expected.setPixel(0, 0, new Pixel(119, 90, 47));
    expected.setPixel(1, 1, new Pixel(131, 201, 172));
    expected.setPixel(0, 1, new Pixel(62, 130, 103));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void testVerticalFlip() throws IOException {
    ImageOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applyVerticalFlip(ih.loadImage("res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(0, 255, 175));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(100, 0, 0));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidTestVerticalFlip() throws IOException {
    ImageOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applyVerticalFlip(ih.loadImage("res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(1, 0, new Pixel(62, 130, 143));
    expected.setPixel(0, 0, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(119, 12, 47));
    expected.setPixel(0, 1, new Pixel(193, 252, 251));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void brightness() throws IOException {
    ImageOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applyBrightness(ih.loadImage(
            "res/PPM/Sample.ppm"), 10);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(1, 0, new Pixel(110, 10, 10));
    expected.setPixel(0, 0, new Pixel(10, 10, 10));
    expected.setPixel(1, 1, new Pixel(10, 255, 185));
    expected.setPixel(0, 1, new Pixel(10, 10, 10));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidBrightness() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applyBrightness(ih.loadImage(
            "res/PPM/Sample.ppm"), 10);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(1, 0, new Pixel(129, 100, 57));
    expected.setPixel(0, 0, new Pixel(203, 255, 255));
    expected.setPixel(1, 1, new Pixel(132, 140, 153));
    expected.setPixel(0, 1, new Pixel(141, 211, 182));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void darken() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applyBrightness(ih.loadImage(
            "res/PPM/Sample.ppm"), -10);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(1, 0, new Pixel(90, 0, 0));
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 245, 165));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidDarken() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applyBrightness(ih.loadImage(
            "res/PPM/Sample.ppm"), -10);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(1, 0, new Pixel(129, 100, 57));
    expected.setPixel(0, 0, new Pixel(203, 255, 255));
    expected.setPixel(1, 1, new Pixel(13, 140, 153));
    expected.setPixel(0, 1, new Pixel(141, 211, 182));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void redComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.visualizeRedComponent(ih.loadImage(
            "res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(100, 100, 100));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 0, 0));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidRedComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.visualizeRedComponent(ih.loadImage(
            "res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 193, 193));
    expected.setPixel(1, 0, new Pixel(157, 145, 132));
    expected.setPixel(0, 1, new Pixel(131, 131, 131));
    expected.setPixel(1, 1, new Pixel(62, 62, 62));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void blueComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.visualizeBlueComponent(ih.loadImage(
            "res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(0, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(175, 175, 175));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidBlueComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.visualizeBlueComponent(ih.loadImage(
            "res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(251, 251, 251));
    expected.setPixel(1, 0, new Pixel(49, 47, 47));
    expected.setPixel(0, 1, new Pixel(172, 172, 172));
    expected.setPixel(1, 1, new Pixel(143, 143, 143));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void greenComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.visualizeGreenComponent(ih.loadImage(
            "res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(0, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(255, 255, 255));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidGreenComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.visualizeGreenComponent(ih.loadImage(
            "res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(252, 252, 252));
    expected.setPixel(1, 0, new Pixel(90, 90, 90));
    expected.setPixel(0, 1, new Pixel(211, 201, 201));
    expected.setPixel(1, 1, new Pixel(130, 130, 130));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void valueComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.visualizeValue(ih.loadImage("res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(100, 100, 100));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(255, 255, 255));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidValueComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.visualizeValue(ih.loadImage("res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(252, 252, 252));
    expected.setPixel(1, 0, new Pixel(119, 119, 119));
    expected.setPixel(0, 1, new Pixel(211, 201, 201));
    expected.setPixel(1, 1, new Pixel(143, 143, 143));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void lumaComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.visualizeLuma(ih.loadImage("res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(21, 21, 21));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(195, 195, 195));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidLumaComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.visualizeLuma(ih.loadImage("res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(239, 239, 239));
    expected.setPixel(1, 0, new Pixel(93, 93, 93));
    expected.setPixel(0, 1, new Pixel(194, 184, 184));
    expected.setPixel(1, 1, new Pixel(116, 116, 116));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void intensityComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.visualizeIntensity(ih.loadImage("res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(33, 33, 33));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(143, 143, 143));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidIntensityComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.visualizeIntensity(ih.loadImage("res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(232, 232, 232));
    expected.setPixel(1, 0, new Pixel(85, 85, 85));
    expected.setPixel(0, 1, new Pixel(168, 168, 168));
    expected.setPixel(1, 1, new Pixel(110, 111, 111));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void rgbSplit() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actualr = io.visualizeRedComponent(ih.loadImage(
            "res/PPM/Sample.ppm"));
    ImageInterface actualg = io.visualizeGreenComponent(ih.loadImage(
            "res/PPM/Sample.ppm"));
    ImageInterface actualb = io.visualizeBlueComponent(ih.loadImage(
            "res/PPM/Sample.ppm"));
    ImageCopyInterface expectedr = new ImageCopy(2, 2);
    expectedr.setPixel(0, 0, new Pixel(0, 0, 0));
    expectedr.setPixel(1, 0, new Pixel(100, 100, 100));
    expectedr.setPixel(0, 1, new Pixel(0, 0, 0));
    expectedr.setPixel(1, 1, new Pixel(0, 0, 0));
    assertTrue(expectedr.deepCopyImage().equals(actualr));
    ImageCopyInterface expectedg = new ImageCopy(2, 2);
    expectedg.setPixel(0, 0, new Pixel(0, 0, 0));
    expectedg.setPixel(1, 0, new Pixel(0, 0, 0));
    expectedg.setPixel(0, 1, new Pixel(0, 0, 0));
    expectedg.setPixel(1, 1, new Pixel(255, 255, 255));
    assertTrue(expectedg.deepCopyImage().equals(actualg));
    ImageCopyInterface expectedb = new ImageCopy(2, 2);
    expectedb.setPixel(0, 0, new Pixel(0, 0, 0));
    expectedb.setPixel(1, 0, new Pixel(0, 0, 0));
    expectedb.setPixel(0, 1, new Pixel(0, 0, 0));
    expectedb.setPixel(1, 1, new Pixel(175, 175, 175));
    assertTrue(expectedb.deepCopyImage().equals(actualb));
  }

  @Test
  public void invalidRgbSplit() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actualr = io.visualizeRedComponent(ih.loadImage(
            "res/PPM/Sample.ppm"));
    ImageInterface actualg = io.visualizeGreenComponent(ih.loadImage(
            "res/PPM/Sample.ppm"));
    ImageInterface actualb = io.visualizeBlueComponent(ih.loadImage(
            "res/PPM/Sample.ppm"));
    ImageCopyInterface expectedr = new ImageCopy(2, 2);
    expectedr.setPixel(0, 0, new Pixel(193, 193, 193));
    expectedr.setPixel(1, 0, new Pixel(119, 119, 119));
    expectedr.setPixel(0, 1, new Pixel(131, 131, 131));
    expectedr.setPixel(1, 1, new Pixel(62, 62, 62));
    assertFalse(expectedr.deepCopyImage().equals(actualr));
    ImageCopyInterface expectedg = new ImageCopy(2, 2);
    expectedg.setPixel(0, 0, new Pixel(252, 252, 252));
    expectedg.setPixel(1, 0, new Pixel(90, 90, 90));
    expectedg.setPixel(0, 1, new Pixel(201, 201, 201));
    expectedg.setPixel(1, 1, new Pixel(130, 130, 130));
    assertFalse(expectedg.deepCopyImage().equals(actualg));
    ImageCopyInterface expectedb = new ImageCopy(2, 2);
    expectedb.setPixel(0, 0, new Pixel(251, 251, 251));
    expectedb.setPixel(1, 0, new Pixel(47, 47, 47));
    expectedb.setPixel(0, 1, new Pixel(172, 172, 172));
    expectedb.setPixel(1, 1, new Pixel(143, 143, 143));
    assertFalse(expectedb.deepCopyImage().equals(actualb));
  }

  @Test
  public void RgbCombine() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actualr = io.visualizeRedComponent(ih.loadImage(
            "res/PPM/Sample.ppm"));
    ImageInterface actualg = io.visualizeGreenComponent(ih.loadImage(
            "res/PPM/Sample.ppm"));
    ImageInterface actualb = io.visualizeBlueComponent(ih.loadImage(
            "res/PPM/Sample.ppm"));
    ImageInterface combine = io.combineRGB(actualr, actualg, actualb);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(100, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(combine));
  }

  @Test
  public void invalidRgbCombine() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actualr = io.visualizeRedComponent(ih.loadImage(
            "res/PPM/Sample.ppm"));
    ImageInterface actualg = io.visualizeGreenComponent(ih.loadImage(
            "res/PPM/Sample.ppm"));
    ImageInterface actualb = io.visualizeBlueComponent(ih.loadImage(
            "res/PPM/Sample.ppm"));
    ImageInterface combine = io.combineRGB(actualr, actualg, actualb);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 252, 251));
    expected.setPixel(1, 0, new Pixel(129, 90, 47));
    expected.setPixel(0, 1, new Pixel(121, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertFalse(expected.deepCopyImage().equals(combine));
  }

  @Test
  public void blur() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applyBlur(ih.loadImage("res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(18, 15, 10));
    expected.setPixel(1, 0, new Pixel(56, 47, 32));
    expected.setPixel(0, 1, new Pixel(6, 47, 32));
    expected.setPixel(1, 1, new Pixel(18, 143, 98));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidBlur() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applyBlur(ih.loadImage("res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 252, 251));
    expected.setPixel(1, 0, new Pixel(129, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void sharped() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applySharpen(ih.loadImage("res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(112, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 196));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidSharped() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applySharpen(ih.loadImage("res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 252, 251));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(132, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void sepia() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applySepia(ih.loadImage("res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(39, 34, 27));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(229, 204, 159));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidSepia() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applySepia(ih.loadImage("res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 219));
    expected.setPixel(1, 0, new Pixel(124, 111, 86));
    expected.setPixel(0, 1, new Pixel(238, 212, 165));
    expected.setPixel(1, 1, new Pixel(141, 134, 105));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void compress() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.compressImage(ih.loadImage(
            "res/PPM/Sample.ppm"), 50);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(100, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void wrongCompress1() throws IllegalArgumentException, IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.compressImage(ih.loadImage(
            "res/PPM/Sample.ppm"), 110);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(195, 249, 255));
    expected.setPixel(1, 0, new Pixel(120, 87, 51));
    expected.setPixel(0, 1, new Pixel(132, 204, 168));
    expected.setPixel(1, 1, new Pixel(61, 133, 139));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void wrongCompress2() throws IllegalArgumentException, IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.compressImage(ih.loadImage(
            "res/PPM/Sample.ppm"), -10);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(195, 249, 255));
    expected.setPixel(1, 0, new Pixel(120, 87, 51));
    expected.setPixel(0, 1, new Pixel(132, 204, 168));
    expected.setPixel(1, 1, new Pixel(61, 133, 139));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void wrongCompress3() throws IllegalArgumentException, IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.compressImage(null, 20);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(195, 249, 255));
    expected.setPixel(1, 0, new Pixel(120, 87, 51));
    expected.setPixel(0, 1, new Pixel(132, 204, 168));
    expected.setPixel(1, 1, new Pixel(61, 133, 139));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void histogram() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.createHistogram(ih.loadImage("res/PPM/Sample.ppm"));
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
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.createHistogram(ih.loadImage("res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(192, 249, 255));
    expected.setPixel(1, 0, new Pixel(120, 87, 51));
    expected.setPixel(0, 1, new Pixel(132, 204, 168));
    expected.setPixel(1, 1, new Pixel(61, 133, 139));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidHistogram2() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.createHistogram(null);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(192, 249, 255));
    expected.setPixel(1, 0, new Pixel(120, 87, 51));
    expected.setPixel(0, 1, new Pixel(132, 204, 168));
    expected.setPixel(1, 1, new Pixel(61, 133, 139));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void colourCorrect() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.colorCorrect(ih.loadImage("res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 91, 0));
    expected.setPixel(1, 0, new Pixel(91, 91, 0));
    expected.setPixel(0, 1, new Pixel(0, 91, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 91));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidColour1() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.colorCorrect(ih.loadImage("res/PPM/Sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(197, 228, 255));
    expected.setPixel(1, 0, new Pixel(123, 66, 66));
    expected.setPixel(0, 1, new Pixel(130, 170, 190));
    expected.setPixel(1, 1, new Pixel(130, 170, 190));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test(expected = NullPointerException.class)
  public void invalidColour2() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.colorCorrect(ih.loadImage(null));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(197, 228, 255));
    expected.setPixel(1, 0, new Pixel(123, 66, 66));
    expected.setPixel(0, 1, new Pixel(130, 170, 190));
    expected.setPixel(1, 1, new Pixel(130, 170, 190));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void levelsAdjust() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.levelsAdjust(ih.loadImage(
            "res/PPM/Sample.ppm"), 10, 15, 20);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(255, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 255));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidWhiteLevelsAdjust() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.levelsAdjust(ih.loadImage(
            "res/PPM/Sample.ppm"), 10, 15, -10);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 255));
    expected.setPixel(1, 0, new Pixel(255, 255, 255));
    expected.setPixel(0, 1, new Pixel(255, 255, 255));
    expected.setPixel(1, 1, new Pixel(255, 255, 255));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidBlackLevelsAdjust() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.levelsAdjust(ih.loadImage(
            "res/PPM/Sample.ppm"), -10, 15, 20);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 255));
    expected.setPixel(1, 0, new Pixel(255, 255, 255));
    expected.setPixel(0, 1, new Pixel(255, 255, 255));
    expected.setPixel(1, 1, new Pixel(255, 255, 255));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidMidLevelsAdjust() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.levelsAdjust(ih.loadImage(
            "res/PPM/Sample.ppm"), 10, -15, 20);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 255));
    expected.setPixel(1, 0, new Pixel(255, 255, 255));
    expected.setPixel(0, 1, new Pixel(255, 255, 255));
    expected.setPixel(1, 1, new Pixel(255, 255, 255));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidAllLevelsAdjust1() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.levelsAdjust(ih.loadImage(
            "res/PPM/Sample.ppm"), 20, 10, 5);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 255));
    expected.setPixel(1, 0, new Pixel(255, 255, 255));
    expected.setPixel(0, 1, new Pixel(255, 255, 255));
    expected.setPixel(1, 1, new Pixel(255, 255, 255));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidAllLevelsAdjust2() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.levelsAdjust(ih.loadImage(
            "res/PPM/Sample.ppm"), 10, 20, 5);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 255));
    expected.setPixel(1, 0, new Pixel(255, 255, 255));
    expected.setPixel(0, 1, new Pixel(255, 255, 255));
    expected.setPixel(1, 1, new Pixel(255, 255, 255));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidAllLevelsAdjust3() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.levelsAdjust(ih.loadImage(
            "res/PPM/Sample.ppm"), 10, 50, 5);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 255));
    expected.setPixel(1, 0, new Pixel(255, 255, 255));
    expected.setPixel(0, 1, new Pixel(255, 255, 255));
    expected.setPixel(1, 1, new Pixel(255, 255, 255));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidAllLevelsAdjust4() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.levelsAdjust(null, 10, 50, 100);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 255));
    expected.setPixel(1, 0, new Pixel(255, 255, 255));
    expected.setPixel(0, 1, new Pixel(255, 255, 255));
    expected.setPixel(1, 1, new Pixel(255, 255, 255));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitBlurCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PPM/Sample.ppm"), io::applyBlur);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(100, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitInvalidBlurCheck1() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PPM/Sample.ppm"), io::applyBlur);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(175, 239, 231));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(146, 213, 191));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void splitInvalidBlurCheck2() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.splitViewOperation(250, ih.loadImage(
            "res/PPM/Sample.ppm"), io::applyBlur);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(175, 239, 231));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(146, 213, 191));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void splitInvalidBlurCheck3() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.splitViewOperation(-50, ih.loadImage(
            "res/PPM/Sample.ppm"), io::applyBlur);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(175, 239, 231));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(146, 213, 191));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitSharpenCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PPM/Sample.ppm"), io::applySharpen);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(100, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitInvalidSharpenCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PPM/Sample.ppm"), io::applySharpen);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(200, 255, 255));
    expected.setPixel(1, 0, new Pixel(119, 80, 47));
    expected.setPixel(0, 1, new Pixel(123, 194, 162));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitSepiaCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PPM/Sample.ppm"), io::applySepia);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(100, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitInvalidSepiaCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    String[] tokens = {"sepia", "res/PPM/Sample.ppm", "expected", "split", "50"};
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PPM/Sample.ppm"), io::applySepia);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 245, 219));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(238, 212, 165));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitRedComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PPM/Sample.ppm"), io::visualizeRedComponent);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(100, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitInvalidRedComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PPM/Sample.ppm"), io::visualizeRedComponent);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(190, 190, 190));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 131, 131));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitGreenComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PPM/Sample.ppm"), io::visualizeGreenComponent);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(100, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitInvalidGreenComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PPM/Sample.ppm"), io::visualizeGreenComponent);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(190, 190, 190));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 131, 131));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitBlueComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PPM/Sample.ppm"), io::visualizeBlueComponent);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(100, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitInvalidBlueComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PPM/Sample.ppm"), io::visualizeBlueComponent);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(250, 250, 250));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(172, 172, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitLumaComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PPM/Sample.ppm"), io::visualizeLuma);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(100, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitInvalidLumaComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PPM/Sample.ppm"), io::visualizeLuma);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(239, 239, 239));
    expected.setPixel(1, 0, new Pixel(19, 90, 47));
    expected.setPixel(0, 1, new Pixel(184, 184, 184));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitValueComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PPM/Sample.ppm"), io::visualizeValue);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(100, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitIntensityComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PPM/Sample.ppm"), io::visualizeIntensity);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(100, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitInvalidIntensityComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PPM/Sample.ppm"), io::visualizeIntensity);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(25, 252, 252));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(201, 201, 201));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitInvalidColourCorrectComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PPM/Sample.ppm"), io::colorCorrect);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(252, 252, 252));
    expected.setPixel(1, 0, new Pixel(19, 90, 47));
    expected.setPixel(0, 1, new Pixel(184, 184, 201));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitColourCorrectComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PPM/Sample.ppm"), io::colorCorrect);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(100, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitlevelAdjustComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.splitViewOperation(50, ih.loadImage(
            "res/PPM/Sample.ppm"),  img -> io.levelsAdjust(img, 10, 15, 20));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(100, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(actual));
  }


  @Test
  public void multipleFunction() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applySepia(ih.loadImage("res/PPM/Sample.ppm"));
    ImageInterface afterop = io.applyVerticalFlip(actual);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(229, 204, 159));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(39, 34, 27));
    assertTrue(expected.deepCopyImage().equals(afterop));
  }

  @Test
  public void testEquals1() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface one = ih.loadImage("res/PPM/Sample.ppm");
    assertTrue(one.equals(one));
  }

  @Test
  public void testEquals2() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface one = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface two = ih.loadImage("res/PPM/Sample.ppm");
    assertTrue(one.equals(two));
    assertTrue(two.equals(one));
  }

  @Test
  public void testEquals3() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface one = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface two = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface three = ih.loadImage("res/PPM/Sample.ppm");
    assertTrue(one.equals(two));
    assertTrue(two.equals(three));
    assertTrue(one.equals(three));
  }

  @Test
  public void testEquals4() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    ImageFormatHandler ih2 = new ImageHandler();
    ImageInterface one = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface two = ih2.loadImage("res/JPG/Sample.jpg");
    assertFalse(one.equals(two));
  }

  @Test
  public void testHash1() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface one = ih.loadImage("res/PPM/Sample.ppm");
    assertTrue(one.hashCode() == one.hashCode());
  }

  @Test
  public void testHash2() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    ImageFormatHandler ih2 = new ImageHandler();
    ImageInterface one = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface two = ih2.loadImage("res/JPG/Sample.jpg");
    assertFalse(one.hashCode() == two.hashCode());
  }

  @Test
  public void testHash3() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface one = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface two = ih.loadImage("res/PPM/Sample.ppm");
    assertTrue(one.hashCode() == two.hashCode());
  }

  @Test
  public void multipleFunction2() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applyBlur(ih.loadImage("res/PPM/Sample.ppm"));
    ImageInterface afterop = io.applyHorizontalFlip(actual);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(56, 47, 32));
    expected.setPixel(1, 0, new Pixel(18, 15, 10));
    expected.setPixel(0, 1, new Pixel(18, 143, 98));
    expected.setPixel(1, 1, new Pixel(6, 47, 32));
    assertTrue(expected.deepCopyImage().equals(afterop));
  }

  @Test
  public void multipleFunction3() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applyBlur(ih.loadImage("res/PPM/Sample.ppm"));
    ImageInterface afterop = io.applyVerticalFlip(actual);
    ImageInterface afterop2 = io.applyHorizontalFlip(afterop);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(18, 143, 98));
    expected.setPixel(1, 0, new Pixel(6, 47, 32));
    expected.setPixel(0, 1, new Pixel(56, 47, 32));
    expected.setPixel(1, 1, new Pixel(18, 15, 10));
    assertTrue(expected.deepCopyImage().equals(afterop2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void downsizeInvalid1() throws IOException {
    AdditionalOperations io = new AdditionalImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface temp = io.downscaleImage(ih.loadImage("res/PPM/Sample.ppm"),
            100, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void downsizeInvalid3() throws IOException {
    AdditionalOperations io = new AdditionalImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface temp = io.downscaleImage(ih.loadImage("res/PPM/Sample.ppm"),
            1, 100);
  }

  @Test
  public void downsize() throws IOException {
    AdditionalOperations io = new AdditionalImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.downscaleImage(ih.loadImage("res/PPM/Sample.ppm"),
            1, 1);
    ImageCopyInterface expected = new ImageCopy(1, 1);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  ImageInterface mask;

  @Before
  public void setUp() {
    ImageCopyInterface temp = new ImageCopy(2, 2);
    temp.setPixel(1, 0, new Pixel(0, 0, 0));
    mask = temp.deepCopyImage();
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidBlurMask1() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface transformed = io.applyBlur(image, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidBlurMask2() {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface transformed = io.applyBlur(null, null);
  }

  @Test
  public void blurMask() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface transformed = io.applyBlur(image, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(56, 47, 32));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(transformed));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSharpenMask1() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface transformed = io.applySharpen(image, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSharpenMask2() {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface transformed = io.applySharpen(null, null);
  }

  @Test
  public void sharpenMask() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface transformed = io.applySharpen(image, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(112, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(transformed));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSepiaMask1() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface transformed = io.applySepia(image, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSepiaMask2() {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface transformed = io.applySepia(null, null);
  }

  @Test
  public void sepiaMask() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface transformed = io.applySepia(image, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    System.out.println(transformed.getPixel(1, 0));
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(39, 34, 27));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(transformed));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidLumaMask1() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface transformed = io.visualizeLuma(image, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidLumaMask2() {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface transformed = io.visualizeLuma(null, null);
  }

  @Test
  public void lumaMask() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface transformed = io.visualizeLuma(image, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(21, 21, 21));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(transformed));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidRedMask1() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface transformed = io.visualizeRedComponent(image, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidRedMask2() {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface transformed = io.visualizeRedComponent(null, null);
  }

  @Test
  public void redMask() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface transformed = io.visualizeRedComponent(image, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(100, 100, 100));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(transformed));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidBlueMask1() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface transformed = io.visualizeBlueComponent(image, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidBlueMask2() {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface transformed = io.visualizeBlueComponent(null, null);
  }

  @Test
  public void blueMask() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface transformed = io.visualizeBlueComponent(image, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(0, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(transformed));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidGreenMask1() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface transformed = io.visualizeGreenComponent(image, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidGreenMask2() {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface transformed = io.visualizeGreenComponent(null, null);
  }

  @Test
  public void greenMask() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface transformed = io.visualizeGreenComponent(image, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(0, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(transformed));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidIntensityMask1() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface transformed = io.visualizeIntensity(image, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidIntensityMask2() {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface transformed = io.visualizeIntensity(null, null);
  }

  @Test
  public void intensityMask() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface transformed = io.visualizeIntensity(image, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(33, 33, 33));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(transformed));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidValueMask1() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface transformed = io.visualizeValue(image, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidValueMask2() {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface transformed = io.visualizeValue(null, null);
  }

  @Test
  public void valueMask() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface transformed = io.visualizeValue(image, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(100, 100, 100));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(transformed));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidBrightenMask1() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface transformed = io.applyBrightness(image, 50, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidBrightenMask2() {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface transformed = io.applyBrightness(null, 50, null);
  }

  @Test
  public void brightenMask() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface transformed = io.applyBrightness(image, 50, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(150, 50, 50));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(transformed));
  }

  @Test
  public void darkenMask() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    AdditionalOperations io = new AdditionalImageOperations();
    ImageInterface image = ih.loadImage("res/PPM/Sample.ppm");
    ImageInterface transformed = io.applyBrightness(image, -50, mask);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(50, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(transformed));
  }

  @Test
  public void testScript() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    execute("run res/Scripts/PPM/test.txt");
    ImageInterface actual = ih.loadImage("res/PPM/Sample-save.ppm");
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(100, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(actual));
  }
}
