package imagestest;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import imagecontroller.ImageFormatHandler;
import imagecontroller.ImageHandler;
import imagecontroller.P3PPMHandler;
import imagemodel.Image;
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

  @Test(expected = IOException.class)
  public void inputTestInvalid() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = ih.loadImage("src/res/PPM/Sample.png");
  }

  @Test
  public void inputTest() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = ih.loadImage("src/res/PPM/sample.ppm");
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(100, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void outputTest() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = ih.loadImage("src/res/PPM/sample.ppm");
    ih.saveImage(actual, "src/res/PPM/Sample1.ppm", "ppm");
    assertTrue(new File("src/res/PPM/Sample1.ppm").exists());
  }

  @Test
  public void testHorizontalFlip() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applyHorizontalFlip(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(1, 0, new Pixel(0, 0, 0));
    expected.setPixel(0, 0, new Pixel(100, 0, 0));
    expected.setPixel(1, 1, new Pixel(0, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 255, 175));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

//  @Test
//  public void invalidTestHorizontalFlip() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageFormatHandler ih = new P3PPMHandler();
//    ImageInterface actual = io.applyHorizontalFlip(ih.loadImage("src/res/PPM/sample.ppm"));
//    ImageCopyInterface expected = new ImageCopy(2, 2);
//    expected.setPixel(1, 0, new Pixel(193, 252, 251));
//    expected.setPixel(0, 0, new Pixel(119, 90, 47));
//    expected.setPixel(1, 1, new Pixel(131, 201, 172));
//    expected.setPixel(0, 1, new Pixel(62, 130, 103));
//    assertTrue(expected.deepCopyImage().equals(actual));
//  }

  @Test
  public void testVerticalFlip() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applyVerticalFlip(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(1, 0, new Pixel(0, 255, 175));
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(100, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

//  @Test
//  public void invalidTestVerticalFlip() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageFormatHandler ih = new P3PPMHandler();
//    ImageInterface actual = io.applyVerticalFlip(ih.loadImage("src/res/PPM/sample.ppm"));
//    ImageCopyInterface expected = new ImageCopy(2, 2);;
//    expected.setPixel(1, 0, new Pixel(62, 130, 143));
//    expected.setPixel(0, 0, new Pixel(131, 201, 172));
//    expected.setPixel(1, 1, new Pixel(119, 12, 47));
//    expected.setPixel(0, 1, new Pixel(193, 252, 251));
//    assertTrue(expected.deepCopyImage().equals(actual));
//  }
//
//  @Test
//  public void brightness() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageFormatHandler ih = new P3PPMHandler();
//    ImageInterface actual = io.applyBrightness(ih.loadImage("src/res/PPM/sample.ppm"), 10);
//    ImageCopyInterface expected = new ImageCopy(2, 2);;
//    expected.setPixel(1, 0, new Pixel(110, 10, 10));
//    expected.setPixel(0, 0, new Pixel(10, 10, 10));
//    expected.setPixel(1, 1, new Pixel(10, 255, 185));
//    expected.setPixel(0, 1, new Pixel(10, 10, 10));
//    assertTrue(expected.equals(actual));
//  }

  @Test
  public void invalidBrightness() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applyBrightness(ih.loadImage("src/res/PPM/sample.ppm"), 10);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    ;
    expected.setPixel(1, 0, new Pixel(129, 100, 57));
    expected.setPixel(0, 0, new Pixel(203, 255, 255));
    expected.setPixel(1, 1, new Pixel(132, 140, 153));
    expected.setPixel(0, 1, new Pixel(141, 211, 182));
    assertFalse(expected.equals(actual));
  }

//  @Test
//  public void darken() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageFormatHandler ih = new P3PPMHandler();
//    ImageInterface actual = io.applyBrightness(ih.loadImage("src/res/PPM/sample.ppm"), -10);
//    ImageCopyInterface expected = new ImageCopy(2, 2);;
//    expected.setPixel(1, 0, new Pixel(90, 0, 0));
//    expected.setPixel(0, 0, new Pixel(0, 0, 0));
//    expected.setPixel(1, 1, new Pixel(0, 245, 165));
//    expected.setPixel(0, 1, new Pixel(0, 0, 0));
//    assertTrue(expected.equals(actual));
//  }

  @Test
  public void invalidDarken() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applyBrightness(ih.loadImage("src/res/PPM/sample.ppm"), -10);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    ;
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
    ImageInterface actual = io.visualizeRedComponent(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    ;
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
    ImageInterface actual = io.visualizeRedComponent(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    ;
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
    ImageInterface actual = io.visualizeBlueComponent(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    ;
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(0, 0, 0));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(175, 175, 175));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

//  @Test
//  public void invalidBlueComponent() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageFormatHandler ih = new P3PPMHandler();
//    ImageInterface actual = io.visualizeBlueComponent(ih.loadImage("src/res/PPM/sample.ppm"));
//    ImageCopyInterface expected = new ImageCopy(2, 2);;
//    expected.setPixel(0, 0, new Pixel(251, 251, 251));
//    expected.setPixel(1, 0, new Pixel(49, 47, 47));
//    expected.setPixel(0, 1, new Pixel(172, 172, 172));
//    expected.setPixel(1, 1, new Pixel(143, 143, 143));
//    assertTrue(expected.deepCopyImage().equals(actual));
//  }

  @Test
  public void greenComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.visualizeGreenComponent(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    ;
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
    ImageInterface actual = io.visualizeGreenComponent(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    ;
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
    ImageInterface actual = io.visualizeValue(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    ;
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
    ImageInterface actual = io.visualizeValue(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    ;
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
    ImageInterface actual = io.visualizeLuma(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    ;
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
    ImageInterface actual = io.visualizeLuma(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    ;
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
    ImageInterface actual = io.visualizeIntensity(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    ;
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
    ImageInterface actual = io.visualizeIntensity(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    ;
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
    ImageInterface actualr = io.visualizeRedComponent(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageInterface actualg = io.visualizeGreenComponent(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageInterface actualb = io.visualizeBlueComponent(ih.loadImage("src/res/PPM/sample.ppm"));
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
    ImageInterface actualr = io.visualizeRedComponent(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageInterface actualg = io.visualizeGreenComponent(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageInterface actualb = io.visualizeBlueComponent(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageCopyInterface expectedr = new ImageCopy(2, 2);
    expectedr.setPixel(0, 0, new Pixel(153, 193, 193));
    expectedr.setPixel(1, 0, new Pixel(12, 119, 119));
    expectedr.setPixel(0, 1, new Pixel(121, 131, 131));
    expectedr.setPixel(1, 1, new Pixel(62, 62, 62));
    assertFalse(expectedr.deepCopyImage().equals(actualr));
    ImageCopyInterface expectedg = new ImageCopy(2, 2);
    expectedg.setPixel(0, 0, new Pixel(242, 252, 252));
    expectedg.setPixel(1, 0, new Pixel(92, 90, 90));
    expectedg.setPixel(0, 1, new Pixel(251, 201, 201));
    expectedg.setPixel(1, 1, new Pixel(130, 130, 130));
    assertFalse(expectedg.deepCopyImage().equals(actualg));
    ImageCopyInterface expectedb = new ImageCopy(2, 2);
    expectedb.setPixel(0, 0, new Pixel(251, 251, 251));
    expectedb.setPixel(1, 0, new Pixel(41, 47, 47));
    expectedb.setPixel(0, 1, new Pixel(162, 172, 172));
    expectedb.setPixel(1, 1, new Pixel(113, 143, 143));
    assertFalse(expectedb.deepCopyImage().equals(actualb));
  }

  @Test
  public void RgbCombine() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actualr = io.visualizeRedComponent(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageInterface actualg = io.visualizeGreenComponent(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageInterface actualb = io.visualizeBlueComponent(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageInterface combine = io.combineRGB(actualr, actualg, actualb);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    ;
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
    ImageInterface actualr = io.visualizeRedComponent(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageInterface actualg = io.visualizeGreenComponent(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageInterface actualb = io.visualizeBlueComponent(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageInterface combine = io.combineRGB(actualr, actualg, actualb);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    ;
    expected.setPixel(0, 0, new Pixel(193, 252, 251));
    expected.setPixel(1, 0, new Pixel(129, 90, 47));
    expected.setPixel(0, 1, new Pixel(121, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertFalse(expected.deepCopyImage().equals(combine));
  }

//  @Test
//  public void blur() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageFormatHandler ih = new P3PPMHandler();
//    ImageInterface actual = io.applyBlur(ih.loadImage("src/res/PPM/sample.ppm"));
//    ImageCopyInterface expected = new ImageCopy(2, 2);
//    expected.setPixel(0, 0, new Pixel(0, 0, 0));
//    expected.setPixel(1, 0, new Pixel(100, 0, 0));
//    expected.setPixel(0, 1, new Pixel(0, 0, 0));
//    expected.setPixel(1, 1, new Pixel(0, 255, 175));
//    assertTrue(expected.deepCopyImage().equals(actual));
//  }

  @Test
  public void invalidBlur() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applyBlur(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    ;
    expected.setPixel(0, 0, new Pixel(193, 252, 251));
    expected.setPixel(1, 0, new Pixel(129, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

//  @Test
//  public void sharped() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageFormatHandler ih = new P3PPMHandler();
//    ImageInterface actual = io.applySharpen(ih.loadImage("src/res/PPM/sample.ppm"));
//    ImageCopyInterface expected = new ImageCopy(2, 2);;
//    expected.setPixel(0, 0, new Pixel(0, 0, 0));
//    expected.setPixel(1, 0, new Pixel(100, 0, 0));
//    expected.setPixel(0, 1, new Pixel(0, 0, 0));
//    expected.setPixel(1, 1, new Pixel(0, 255, 175));
//    assertTrue(expected.deepCopyImage().equals(actual));
//  }

  @Test
  public void invalidSharped() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applySharpen(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    ;
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
    ImageInterface actual = io.applySepia(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    ;
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
    ImageInterface actual = io.applySepia(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    ;
    expected.setPixel(0, 0, new Pixel(255, 255, 219));
    expected.setPixel(1, 0, new Pixel(124, 111, 86));
    expected.setPixel(0, 1, new Pixel(238, 212, 165));
    expected.setPixel(1, 1, new Pixel(141, 134, 105));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void multipleFunction() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface actual = io.applySepia(ih.loadImage("src/res/PPM/sample.ppm"));
    ImageInterface afterop = io.applyVerticalFlip(actual);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    ;
    expected.setPixel(0, 0, new Pixel(0, 0, 0));
    expected.setPixel(1, 0, new Pixel(229, 204, 159));
    expected.setPixel(0, 1, new Pixel(0, 0, 0));
    expected.setPixel(1, 1, new Pixel(39, 34, 27));
    assertTrue(expected.deepCopyImage().equals(afterop));
  }

//  @Test
//  public void multipleFunction2() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageFormatHandler ih = new P3PPMHandler();
//    ImageInterface actual = io.applyBlur(ih.loadImage("src/res/PPM/sample.ppm"));
//    ImageInterface afterop = io.applyHorizontalFlip(actual);
//    ImageCopyInterface expected = new ImageCopy(2, 2);;
//    expected.setPixel(0, 0, new Pixel(100, 0, 0));
//    expected.setPixel(1, 0, new Pixel(0, 0, 0));
//    expected.setPixel(0, 1, new Pixel(0, 255, 175));
//    expected.setPixel(1, 1, new Pixel(0, 0, 0));
//    assertTrue(expected.deepCopyImage().equals(afterop));
//  }
//
//  @Test
//  public void multipleFunction3() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageFormatHandler ih = new P3PPMHandler();
//    ImageInterface actual = io.applyBlur(ih.loadImage("src/res/PPM/sample.ppm"));
//    ImageInterface afterop = io.applyVerticalFlip(actual);
//    ImageInterface afterop2 = io.applyHorizontalFlip(afterop);
//    ImageCopyInterface expected = new ImageCopy(2, 2);;
//    expected.setPixel(0, 0, new Pixel(0, 255, 175));
//    expected.setPixel(1, 0, new Pixel(0, 0, 0));
//    expected.setPixel(0, 1, new Pixel(100, 0, 0));
//    expected.setPixel(1, 1, new Pixel(0, 0, 0));
//    assertTrue(expected.deepCopyImage().equals(afterop2));
//  }

  @Test
  public void testEquals1() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface one = ih.loadImage("src/res/PPM/sample.ppm");
    assertTrue(one.equals(one));
  }

  @Test
  public void testEquals2() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface one = ih.loadImage("src/res/PPM/sample.ppm");
    ImageInterface two = ih.loadImage("src/res/PPM/sample.ppm");
    assertTrue(one.equals(two));
    assertTrue(two.equals(one));
  }

  @Test
  public void testEquals3() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface one = ih.loadImage("src/res/PPM/sample.ppm");
    ImageInterface two = ih.loadImage("src/res/PPM/sample.ppm");
    ImageInterface three = ih.loadImage("src/res/PPM/sample.ppm");
    assertTrue(one.equals(two));
    assertTrue(two.equals(three));
    assertTrue(one.equals(three));
  }

  @Test
  public void testEquals4() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    ImageHandler ih2 = new ImageHandler();
    ImageInterface one = ih.loadImage("src/res/PPM/sample.ppm");
    ImageInterface two = ih2.loadImage("src/res/JPG/Sample.jpg");
    assertFalse(one.equals(two));
  }

  @Test
  public void testHash1() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface one = ih.loadImage("src/res/PPM/sample.ppm");
    assertTrue(one.hashCode() == one.hashCode());
  }

  @Test
  public void testHash2() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    ImageHandler ih2 = new ImageHandler();
    ImageInterface one = ih.loadImage("src/res/PPM/sample.ppm");
    ImageInterface two = ih2.loadImage("src/res/JPG/Sample.jpg");
    assertFalse(one.hashCode() == two.hashCode());
  }

  @Test
  public void testHash3() throws IOException {
    ImageFormatHandler ih = new P3PPMHandler();
    ImageInterface one = ih.loadImage("src/res/PPM/sample.ppm");
    ImageInterface two = ih.loadImage("src/res/PPM/sample.ppm");
    assertTrue(one.hashCode() == two.hashCode());
  }
}
