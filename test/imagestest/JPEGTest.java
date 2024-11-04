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
 * This class tests the image operations on a JPEG file.
 */
public class JPEGTest extends AbstractTest {

//  @Test
//  public void inputTest() throws IOException {
//    ImageHandler ih = new ImageHandler();
//    Image actual = ih.loadImage("src/res/JPEG/Sample.jpeg");
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(74, 75, 5));
//    expected.setPixel(1, 0, new Pixel(175, 176, 106));
//    expected.setPixel(0, 1, new Pixel(55, 56, 0));
//    expected.setPixel(1, 1, new Pixel(215, 216, 146));
//    assertTrue(expected.equals(actual));
//  }
//
//  @Test(expected = IOException.class)
//  public void inputTestInvalid() throws IOException {
//    ImageHandler ih = new ImageHandler();
//    Image actual = ih.loadImage("src/res/JPEG/Sample.png");
//  }
//
//  @Test
//  public void outputTest() throws IOException {
//    ImageHandler ih = new ImageHandler();
//    Image actual = ih.loadImage("src/res/JPEG/Sample.jpeg");
//    ih.saveImage(actual, "src/res/JPEG/Sample1.jpeg", "jpeg");
//    assertEquals(true, new File("src/res/JPEG/Sample1.jpeg").exists());
//  }
//
//  @Test
//  public void testHorizontalFlip() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.applyHorizontalFlip(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(1, 0, new Pixel(74, 75, 5));
//    expected.setPixel(0, 0, new Pixel(175, 176, 106));
//    expected.setPixel(1, 1, new Pixel(55, 56, 0));
//    expected.setPixel(0, 1, new Pixel(215, 216, 146));
//    assertTrue(expected.equals(actual));
//  }
//
//  @Test
//  public void invalidTestHorizontalFlip() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.applyHorizontalFlip(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(1, 0, new Pixel(193, 252, 251));
//    expected.setPixel(0, 0, new Pixel(119, 90, 47));
//    expected.setPixel(1, 1, new Pixel(131, 201, 172));
//    expected.setPixel(0, 1, new Pixel(62, 130, 103));
//    assertFalse(expected.equals(actual));
//  }
//
//  @Test
//  public void testVerticalFlip() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.applyVerticalFlip(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(1, 0, new Pixel(215, 216, 146));
//    expected.setPixel(0, 0, new Pixel(55, 56, 0));
//    expected.setPixel(1, 1, new Pixel(175, 176, 106));
//    expected.setPixel(0, 1, new Pixel(74, 75, 5));
//    assertTrue(expected.equals(actual));
//  }
//
//  @Test
//  public void invalidTestVerticalFlip() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.applyVerticalFlip(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(1, 0, new Pixel(62, 130, 143));
//    expected.setPixel(0, 0, new Pixel(131, 201, 172));
//    expected.setPixel(1, 1, new Pixel(119, 12, 47));
//    expected.setPixel(0, 1, new Pixel(193, 252, 251));
//    assertFalse(expected.equals(actual));
//  }
//
//  @Test
//  public void brightness() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.applyBrightness(ih.loadImage("src/res/JPEG/Sample.jpeg"), 10);
//    Image expected = new Image(2, 2);
//    expected.setPixel(1, 0, new Pixel(185, 186, 116));
//    expected.setPixel(0, 0, new Pixel(84, 85, 15));
//    expected.setPixel(1, 1, new Pixel(225, 226, 156));
//    expected.setPixel(0, 1, new Pixel(65, 66, 10));
//    assertTrue(expected.equals(actual));
//  }
//
//  @Test
//  public void invalidBrightness() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.applyBrightness(ih.loadImage("src/res/JPEG/Sample.jpeg"), 10);
//    Image expected = new Image(2, 2);
//    expected.setPixel(1, 0, new Pixel(129, 100, 57));
//    expected.setPixel(0, 0, new Pixel(203, 255, 255));
//    expected.setPixel(1, 1, new Pixel(132, 140, 153));
//    expected.setPixel(0, 1, new Pixel(141, 211, 182));
//    assertFalse(expected.equals(actual));
//  }
//
//  @Test
//  public void darken() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.applyBrightness(ih.loadImage("src/res/JPEG/Sample.jpeg"), -10);
//    Image expected = new Image(2, 2);
//    expected.setPixel(1, 0, new Pixel(165, 166, 96));
//    expected.setPixel(0, 0, new Pixel(64, 65, 0));
//    expected.setPixel(1, 1, new Pixel(205, 206, 136));
//    expected.setPixel(0, 1, new Pixel(45, 46, 0));
//    assertTrue(expected.equals(actual));
//  }
//
//  @Test
//  public void invalidDarken() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.applyBrightness(ih.loadImage("src/res/JPEG/Sample.jpeg"), -10);
//    Image expected = new Image(2, 2);
//    expected.setPixel(1, 0, new Pixel(129, 100, 57));
//    expected.setPixel(0, 0, new Pixel(203, 255, 255));
//    expected.setPixel(1, 1, new Pixel(13, 140, 153));
//    expected.setPixel(0, 1, new Pixel(141, 211, 182));
//    assertFalse(expected.equals(actual));
//  }
//
//  @Test
//  public void redComponent() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.visualizeRedComponent(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(74, 74, 74));
//    expected.setPixel(1, 0, new Pixel(175, 175, 175));
//    expected.setPixel(0, 1, new Pixel(55, 55, 55));
//    expected.setPixel(1, 1, new Pixel(215, 215, 215));
//    assertTrue(expected.equals(actual));
//  }
//
//  @Test
//  public void invalidRedComponent() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.visualizeRedComponent(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(193, 193, 193));
//    expected.setPixel(1, 0, new Pixel(157, 145, 132));
//    expected.setPixel(0, 1, new Pixel(131, 131, 131));
//    expected.setPixel(1, 1, new Pixel(62, 62, 62));
//    assertFalse(expected.equals(actual));
//  }
//
//  @Test
//  public void blueComponent() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.visualizeBlueComponent(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(5, 5, 5));
//    expected.setPixel(1, 0, new Pixel(106, 106, 106));
//    expected.setPixel(0, 1, new Pixel(0, 0, 0));
//    expected.setPixel(1, 1, new Pixel(146, 146, 146));
//    assertTrue(expected.equals(actual));
//  }
//
//  @Test
//  public void invalidBlueComponent() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.visualizeBlueComponent(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(251, 251, 251));
//    expected.setPixel(1, 0, new Pixel(49, 47, 47));
//    expected.setPixel(0, 1, new Pixel(172, 172, 172));
//    expected.setPixel(1, 1, new Pixel(143, 143, 143));
//    assertFalse(expected.equals(actual));
//  }
//
//  @Test
//  public void greenComponent() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.visualizeGreenComponent(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(75, 75, 75));
//    expected.setPixel(1, 0, new Pixel(176, 176, 176));
//    expected.setPixel(0, 1, new Pixel(56, 56, 56));
//    expected.setPixel(1, 1, new Pixel(216, 216, 216));
//    assertTrue(expected.equals(actual));
//  }
//
//  @Test
//  public void invalidGreenComponent() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.visualizeGreenComponent(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(252, 252, 252));
//    expected.setPixel(1, 0, new Pixel(90, 90, 90));
//    expected.setPixel(0, 1, new Pixel(211, 201, 201));
//    expected.setPixel(1, 1, new Pixel(130, 130, 130));
//    assertFalse(expected.equals(actual));
//  }
//
//  @Test
//  public void valueComponent() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.visualizeValue(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(75, 75, 75));
//    expected.setPixel(1, 0, new Pixel(176, 176, 176));
//    expected.setPixel(0, 1, new Pixel(56, 56, 56));
//    expected.setPixel(1, 1, new Pixel(216, 216, 216));
//    assertTrue(expected.equals(actual));
//  }
//
//  @Test
//  public void invalidValueComponent() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.visualizeValue(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(252, 252, 252));
//    expected.setPixel(1, 0, new Pixel(119, 119, 119));
//    expected.setPixel(0, 1, new Pixel(211, 201, 201));
//    expected.setPixel(1, 1, new Pixel(143, 143, 143));
//    assertFalse(expected.equals(actual));
//  }
//
//  @Test
//  public void lumaComponent() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.visualizeLuma(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(69, 69, 69));
//    expected.setPixel(1, 0, new Pixel(170, 170, 170));
//    expected.setPixel(0, 1, new Pixel(51, 51, 51));
//    expected.setPixel(1, 1, new Pixel(210, 210, 210));
//    assertTrue(expected.equals(actual));
//  }
//
//  @Test
//  public void invalidLumaComponent() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.visualizeLuma(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(239, 239, 239));
//    expected.setPixel(1, 0, new Pixel(93, 93, 93));
//    expected.setPixel(0, 1, new Pixel(194, 184, 184));
//    expected.setPixel(1, 1, new Pixel(116, 116, 116));
//    assertFalse(expected.equals(actual));
//  }
//
//  @Test
//  public void intensityComponent() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.visualizeIntensity(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(51, 51, 51));
//    expected.setPixel(1, 0, new Pixel(152, 152, 152));
//    expected.setPixel(0, 1, new Pixel(37, 37, 37));
//    expected.setPixel(1, 1, new Pixel(192, 192, 192));
//    assertTrue(expected.equals(actual));
//  }
//
//  @Test
//  public void invalidIntensityComponent() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.visualizeIntensity(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(232, 232, 232));
//    expected.setPixel(1, 0, new Pixel(85, 85, 85));
//    expected.setPixel(0, 1, new Pixel(168, 168, 168));
//    expected.setPixel(1, 1, new Pixel(110, 111, 111));
//    assertFalse(expected.equals(actual));
//  }
//
//  @Test
//  public void rgbSplit() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actualr = io.visualizeRedComponent(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image actualg = io.visualizeGreenComponent(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image actualb = io.visualizeBlueComponent(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expectedr = new Image(2, 2);
//    expectedr.setPixel(0, 0, new Pixel(74, 74, 74));
//    expectedr.setPixel(1, 0, new Pixel(175, 175, 175));
//    expectedr.setPixel(0, 1, new Pixel(55, 55, 55));
//    expectedr.setPixel(1, 1, new Pixel(215, 215, 215));
//    assertTrue(expectedr.equals(actualr));
//    Image expectedg = new Image(2, 2);
//    expectedg.setPixel(0, 0, new Pixel(75, 75, 75));
//    expectedg.setPixel(1, 0, new Pixel(176, 176, 176));
//    expectedg.setPixel(0, 1, new Pixel(56, 56, 56));
//    expectedg.setPixel(1, 1, new Pixel(216, 216, 216));
//    assertTrue(expectedg.equals(actualg));
//    Image expectedb = new Image(2, 2);
//    expectedb.setPixel(0, 0, new Pixel(5, 5, 5));
//    expectedb.setPixel(1, 0, new Pixel(106, 106, 106));
//    expectedb.setPixel(0, 1, new Pixel(0, 0, 0));
//    expectedb.setPixel(1, 1, new Pixel(146, 146, 146));
//    assertTrue(expectedb.equals(actualb));
//  }
//
//  @Test
//  public void invalidRgbSplit() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actualr = io.visualizeRedComponent(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image actualg = io.visualizeGreenComponent(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image actualb = io.visualizeBlueComponent(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expectedr = new Image(2, 2);
//    expectedr.setPixel(0, 0, new Pixel(153, 193, 193));
//    expectedr.setPixel(1, 0, new Pixel(12, 119, 119));
//    expectedr.setPixel(0, 1, new Pixel(121, 131, 131));
//    expectedr.setPixel(1, 1, new Pixel(62, 62, 62));
//    assertFalse(expectedr.equals(actualr));
//    Image expectedg = new Image(2, 2);
//    expectedg.setPixel(0, 0, new Pixel(242, 252, 252));
//    expectedg.setPixel(1, 0, new Pixel(92, 90, 90));
//    expectedg.setPixel(0, 1, new Pixel(251, 201, 201));
//    expectedg.setPixel(1, 1, new Pixel(130, 130, 130));
//    assertFalse(expectedg.equals(actualg));
//    Image expectedb = new Image(2, 2);
//    expectedb.setPixel(0, 0, new Pixel(251, 251, 251));
//    expectedb.setPixel(1, 0, new Pixel(41, 47, 47));
//    expectedb.setPixel(0, 1, new Pixel(162, 172, 172));
//    expectedb.setPixel(1, 1, new Pixel(113, 143, 143));
//    assertFalse(expectedb.equals(actualb));
//  }
//
//  @Test
//  public void RgbCombine() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actualr = io.visualizeRedComponent(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image actualg = io.visualizeGreenComponent(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image actualb = io.visualizeBlueComponent(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image combine = io.combineRGB(actualr, actualg, actualb);
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(74, 75, 5));
//    expected.setPixel(1, 0, new Pixel(175, 176, 106));
//    expected.setPixel(0, 1, new Pixel(55, 56, 0));
//    expected.setPixel(1, 1, new Pixel(215, 216, 146));
//    assertTrue(expected.equals(combine));
//  }
//
//  @Test
//  public void invalidRgbCombine() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actualr = io.visualizeRedComponent(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image actualg = io.visualizeGreenComponent(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image actualb = io.visualizeBlueComponent(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image combine = io.combineRGB(actualr, actualg, actualb);
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(193, 252, 251));
//    expected.setPixel(1, 0, new Pixel(129, 90, 47));
//    expected.setPixel(0, 1, new Pixel(121, 201, 172));
//    expected.setPixel(1, 1, new Pixel(62, 130, 143));
//    assertFalse(expected.equals(combine));
//  }
//
//  @Test
//  public void blur() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.applyBlur(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(74, 75, 5));
//    expected.setPixel(1, 0, new Pixel(175, 176, 106));
//    expected.setPixel(0, 1, new Pixel(55, 56, 0));
//    expected.setPixel(1, 1, new Pixel(215, 216, 146));
//    assertTrue(expected.equals(actual));
//  }
//
//  @Test
//  public void invalidBlur() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.applyBlur(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(193, 252, 251));
//    expected.setPixel(1, 0, new Pixel(129, 90, 47));
//    expected.setPixel(0, 1, new Pixel(131, 201, 172));
//    expected.setPixel(1, 1, new Pixel(62, 130, 143));
//    assertFalse(expected.equals(actual));
//  }
//
//  @Test
//  public void sharped() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.applySharpen(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(74, 75, 5));
//    expected.setPixel(1, 0, new Pixel(175, 176, 106));
//    expected.setPixel(0, 1, new Pixel(55, 56, 0));
//    expected.setPixel(1, 1, new Pixel(215, 216, 146));
//    assertTrue(expected.equals(actual));
//  }
//
//  @Test
//  public void invalidSharped() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.applySharpen(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(193, 252, 251));
//    expected.setPixel(1, 0, new Pixel(119, 90, 47));
//    expected.setPixel(0, 1, new Pixel(132, 201, 172));
//    expected.setPixel(1, 1, new Pixel(62, 130, 143));
//    assertFalse(expected.equals(actual));
//  }
//
//  @Test
//  public void sepia() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.applySepia(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(87, 78, 60));
//    expected.setPixel(1, 0, new Pixel(224, 199, 155));
//    expected.setPixel(0, 1, new Pixel(64, 57, 44));
//    expected.setPixel(1, 1, new Pixel(255, 247, 192));
//    assertTrue(expected.equals(actual));
//  }
//
//  @Test
//  public void invalidSepia() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.applySepia(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(255, 255, 219));
//    expected.setPixel(1, 0, new Pixel(124, 111, 86));
//    expected.setPixel(0, 1, new Pixel(238, 212, 165));
//    expected.setPixel(1, 1, new Pixel(141, 134, 105));
//    assertFalse(expected.equals(actual));
//  }
//
//  @Test
//  public void multipleFunction() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.applySepia(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image afterop = io.applyVerticalFlip(actual);
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(64, 57, 44));
//    expected.setPixel(1, 0, new Pixel(255, 247, 192));
//    expected.setPixel(0, 1, new Pixel(87, 78, 60));
//    expected.setPixel(1, 1, new Pixel(224, 199, 155));
//    assertTrue(expected.equals(afterop));
//  }
//
//  @Test
//  public void multipleFunction2() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.applyBlur(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image afterop = io.applyHorizontalFlip(actual);
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(175, 176, 106));
//    expected.setPixel(1, 0, new Pixel(74, 75, 5));
//    expected.setPixel(0, 1, new Pixel(215, 216, 146));
//    expected.setPixel(1, 1, new Pixel(55, 56, 0));
//    assertTrue(expected.equals(afterop));
//  }
//
//  @Test
//  public void multipleFunction3() throws IOException {
//    ImageOperations io = new ImageOperations();
//    ImageHandler ih = new ImageHandler();
//    Image actual = io.applyBlur(ih.loadImage("src/res/JPEG/Sample.jpeg"));
//    Image afterop = io.applyVerticalFlip(actual);
//    Image afterop2 = io.applyHorizontalFlip(afterop);
//    Image expected = new Image(2, 2);
//    expected.setPixel(0, 0, new Pixel(215, 216, 146));
//    expected.setPixel(1, 0, new Pixel(55, 56, 0));
//    expected.setPixel(0, 1, new Pixel(175, 176, 106));
//    expected.setPixel(1, 1, new Pixel(74, 75, 5));
//    assertTrue(expected.equals(afterop2));
//  }
//
//  @Test
//  public void testEquals1() throws IOException {
//    ImageHandler ih = new ImageHandler();
//    Image one = ih.loadImage("src/res/JPEG/Sample.jpeg");
//    assertTrue(one.equals(one));
//  }
//
//  @Test
//  public void testEquals2() throws IOException {
//    ImageHandler ih = new ImageHandler();
//    Image one = ih.loadImage("src/res/JPEG/Sample.jpeg");
//    Image two = ih.loadImage("src/res/JPEG/Sample.jpeg");
//    assertTrue(one.equals(two));
//    assertTrue(two.equals(one));
//  }
//
//  @Test
//  public void testEquals3() throws IOException {
//    ImageHandler ih = new ImageHandler();
//    Image one = ih.loadImage("src/res/JPEG/Sample.jpeg");
//    Image two = ih.loadImage("src/res/JPEG/Sample.jpeg");
//    Image three = ih.loadImage("src/res/JPEG/Sample.jpeg");
//    assertTrue(one.equals(two));
//    assertTrue(two.equals(three));
//    assertTrue(one.equals(three));
//  }
//
//  @Test
//  public void testEquals4() throws IOException {
//    ImageHandler ih = new ImageHandler();
//    Image one = ih.loadImage("src/res/PNG/Sample.png");
//    Image two = ih.loadImage("src/res/JPEG/Sample.jpeg");
//    assertFalse(one.equals(two));
//  }
//
//  @Test
//  public void testHash1() throws IOException {
//    ImageHandler ih = new ImageHandler();
//    Image one = ih.loadImage("src/res/JPEG/Sample.jpeg");
//    assertTrue(one.hashCode() == one.hashCode());
//  }
//
//  @Test
//  public void testHash2() throws IOException {
//    ImageHandler ih = new ImageHandler();
//    Image one = ih.loadImage("src/res/PNG/Sample.png");
//    Image two = ih.loadImage("src/res/JPEG/Sample.jpeg");
//    assertFalse(one.hashCode() == two.hashCode());
//  }
//
//  @Test
//  public void testHash3() throws IOException {
//    ImageHandler ih = new ImageHandler();
//    Image one = ih.loadImage("src/res/JPEG/Sample.jpeg");
//    Image two = ih.loadImage("src/res/JPEG/Sample.jpeg");
//    assertTrue(one.hashCode() == two.hashCode());
//  }
}