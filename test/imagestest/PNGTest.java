package imagestest;

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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the image operations on a PNG image file.
 */
public class PNGTest extends AbstractTest {

  @Test
  public void inputTest() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = ih.loadImage("src/res/PNG/Sample.png");
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 252, 251));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test(expected = IOException.class)
  public void inputTestInvalid() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = ih.loadImage("src/res/PG/Sample.png");
  }

  @Test
  public void outputTest() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = ih.loadImage("src/res/PNG/Sample.png");
    ih.saveImage(actual, "src/res/PNG/Sample1.png", "png");
    assertEquals(true, new File("src/res/PNG/Sample1.png").exists());
  }

  @Test
  public void testHorizontalFlip() throws IOException {
    ImageOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyHorizontalFlip(ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(1, 0, new Pixel(193, 252, 251));
    expected.setPixel(0, 0, new Pixel(119, 90, 47));
    expected.setPixel(1, 1, new Pixel(131, 201, 172));
    expected.setPixel(0, 1, new Pixel(62, 130, 143));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidTestHorizontalFlip() throws IOException {
    ImageOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyHorizontalFlip(ih.loadImage(
            "src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyVerticalFlip(ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(1, 0, new Pixel(62, 130, 143));
    expected.setPixel(0, 0, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(193, 252, 251));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidTestVerticalFlip() throws IOException {
    ImageOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyVerticalFlip(ih.loadImage(
            "src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyBrightness(ih.loadImage(
            "src/res/PNG/Sample.png"), 10);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(1, 0, new Pixel(129, 100, 57));
    expected.setPixel(0, 0, new Pixel(203, 255, 255));
    expected.setPixel(1, 1, new Pixel(72, 140, 153));
    expected.setPixel(0, 1, new Pixel(141, 211, 182));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidBrightness() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyBrightness(ih.loadImage(
            "src/res/PNG/Sample.png"), 10);
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyBrightness(ih.loadImage(
            "src/res/PNG/Sample.png"), -10);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(1, 0, new Pixel(109, 80, 37));
    expected.setPixel(0, 0, new Pixel(183, 242, 241));
    expected.setPixel(1, 1, new Pixel(52, 120, 133));
    expected.setPixel(0, 1, new Pixel(121, 191, 162));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidDarken() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyBrightness(ih.loadImage(
            "src/res/PNG/Sample.png"), -10);
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeRedComponent(ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 193, 193));
    expected.setPixel(1, 0, new Pixel(119, 119, 119));
    expected.setPixel(0, 1, new Pixel(131, 131, 131));
    expected.setPixel(1, 1, new Pixel(62, 62, 62));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidRedComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeRedComponent(ih.loadImage(
            "src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeBlueComponent(ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(251, 251, 251));
    expected.setPixel(1, 0, new Pixel(47, 47, 47));
    expected.setPixel(0, 1, new Pixel(172, 172, 172));
    expected.setPixel(1, 1, new Pixel(143, 143, 143));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidBlueComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeBlueComponent(ih.loadImage(
            "src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeGreenComponent(ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(252, 252, 252));
    expected.setPixel(1, 0, new Pixel(90, 90, 90));
    expected.setPixel(0, 1, new Pixel(201, 201, 201));
    expected.setPixel(1, 1, new Pixel(130, 130, 130));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidGreenComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeGreenComponent(ih.loadImage(
            "src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeValue(ih.loadImage("src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(252, 252, 252));
    expected.setPixel(1, 0, new Pixel(119, 119, 119));
    expected.setPixel(0, 1, new Pixel(201, 201, 201));
    expected.setPixel(1, 1, new Pixel(143, 143, 143));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidValueComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeValue(ih.loadImage("src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeLuma(ih.loadImage("src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(239, 239, 239));
    expected.setPixel(1, 0, new Pixel(93, 93, 93));
    expected.setPixel(0, 1, new Pixel(184, 184, 184));
    expected.setPixel(1, 1, new Pixel(116, 116, 116));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidLumaComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeLuma(ih.loadImage("src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeIntensity(ih.loadImage("src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(232, 232, 232));
    expected.setPixel(1, 0, new Pixel(85, 85, 85));
    expected.setPixel(0, 1, new Pixel(168, 168, 168));
    expected.setPixel(1, 1, new Pixel(111, 111, 111));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidIntensityComponent() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.visualizeIntensity(ih.loadImage("src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actualr = io.visualizeRedComponent(ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageInterface actualg = io.visualizeGreenComponent(ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageInterface actualb = io.visualizeBlueComponent(ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageCopyInterface expectedr = new ImageCopy(2, 2);
    expectedr.setPixel(0, 0, new Pixel(193, 193, 193));
    expectedr.setPixel(1, 0, new Pixel(119, 119, 119));
    expectedr.setPixel(0, 1, new Pixel(131, 131, 131));
    expectedr.setPixel(1, 1, new Pixel(62, 62, 62));
    assertTrue(expectedr.deepCopyImage().equals(actualr));
    ImageCopyInterface expectedg = new ImageCopy(2, 2);
    expectedg.setPixel(0, 0, new Pixel(252, 252, 252));
    expectedg.setPixel(1, 0, new Pixel(90, 90, 90));
    expectedg.setPixel(0, 1, new Pixel(201, 201, 201));
    expectedg.setPixel(1, 1, new Pixel(130, 130, 130));
    assertTrue(expectedg.deepCopyImage().equals(actualg));
    ImageCopyInterface expectedb = new ImageCopy(2, 2);
    expectedb.setPixel(0, 0, new Pixel(251, 251, 251));
    expectedb.setPixel(1, 0, new Pixel(47, 47, 47));
    expectedb.setPixel(0, 1, new Pixel(172, 172, 172));
    expectedb.setPixel(1, 1, new Pixel(143, 143, 143));
    assertTrue(expectedb.deepCopyImage().equals(actualb));
  }

  @Test
  public void invalidRgbSplit() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actualr = io.visualizeRedComponent(ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageInterface actualg = io.visualizeGreenComponent(ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageInterface actualb = io.visualizeBlueComponent(ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageCopyInterface expectedr = new ImageCopy(2, 2);
    expectedr.setPixel(0, 0, new Pixel(193, 193, 193));
    expectedr.setPixel(1, 0, new Pixel(119, 119, 119));
    expectedr.setPixel(0, 1, new Pixel(131, 131, 131));
    expectedr.setPixel(1, 1, new Pixel(62, 62, 62));
    assertTrue(expectedr.deepCopyImage().equals(actualr));
    ImageCopyInterface expectedg = new ImageCopy(2, 2);
    expectedg.setPixel(0, 0, new Pixel(252, 252, 252));
    expectedg.setPixel(1, 0, new Pixel(90, 90, 90));
    expectedg.setPixel(0, 1, new Pixel(201, 201, 201));
    expectedg.setPixel(1, 1, new Pixel(130, 130, 130));
    assertTrue(expectedg.deepCopyImage().equals(actualg));
    ImageCopyInterface expectedb = new ImageCopy(2, 2);
    expectedb.setPixel(0, 0, new Pixel(251, 251, 251));
    expectedb.setPixel(1, 0, new Pixel(47, 47, 47));
    expectedb.setPixel(0, 1, new Pixel(172, 172, 172));
    expectedb.setPixel(1, 1, new Pixel(143, 143, 143));
    assertTrue(expectedb.deepCopyImage().equals(actualb));
  }

  @Test
  public void RgbCombine() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actualr = io.visualizeRedComponent(ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageInterface actualg = io.visualizeGreenComponent(ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageInterface actualb = io.visualizeBlueComponent(ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageInterface combine = io.combineRGB(actualr, actualg, actualb);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 252, 251));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 201, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertTrue(expected.deepCopyImage().equals(combine));
  }

  @Test
  public void invalidRgbCombine() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actualr = io.visualizeRedComponent(ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageInterface actualg = io.visualizeGreenComponent(ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageInterface actualb = io.visualizeBlueComponent(ih.loadImage(
            "src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyBlur(ih.loadImage("src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(159, 204, 191));
    expected.setPixel(1, 0, new Pixel(122, 134, 111));
    expected.setPixel(0, 1, new Pixel(128, 190, 173));
    expected.setPixel(1, 1, new Pixel(93, 143, 137));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidBlur() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyBlur(ih.loadImage("src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applySharpen(ih.loadImage("src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(209, 255, 255));
    expected.setPixel(1, 0, new Pixel(117, 76, 31));
    expected.setPixel(0, 1, new Pixel(132, 214, 187));
    expected.setPixel(1, 1, new Pixel(45, 114, 129));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidSharped() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applySharpen(ih.loadImage("src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applySepia(ih.loadImage("src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 219));
    expected.setPixel(1, 0, new Pixel(124, 111, 86));
    expected.setPixel(0, 1, new Pixel(238, 212, 165));
    expected.setPixel(1, 1, new Pixel(151, 134, 105));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidSepia() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applySepia(ih.loadImage("src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.compressImage(ih.loadImage(
            "src/res/PNG/Sample.png"), 50);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(192, 249, 255));
    expected.setPixel(1, 0, new Pixel(120, 87, 51));
    expected.setPixel(0, 1, new Pixel(132, 204, 168));
    expected.setPixel(1, 1, new Pixel(61, 133, 139));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void wrongCompress1() throws IllegalArgumentException, IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.compressImage(ih.loadImage(
            "src/res/PNG/Sample.png"), 110);
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.compressImage(ih.loadImage(
            "src/res/PNG/Sample.png"), -10);
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
    ImageFormatHandler ih = new ImageHandler();
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.createHistogram(ih.loadImage("src/res/PNG/Sample.png"));
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
    ImageInterface actual = io.createHistogram(ih.loadImage("src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.colorCorrect(ih.loadImage("src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(197, 228, 255));
    expected.setPixel(1, 0, new Pixel(123, 66, 66));
    expected.setPixel(0, 1, new Pixel(135, 177, 191));
    expected.setPixel(1, 1, new Pixel(66, 106, 162));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void invalidColour1() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.colorCorrect(ih.loadImage("src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.levelsAdjust(ih.loadImage(
            "src/res/PNG/Sample.png"), 10, 15, 20);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 255));
    expected.setPixel(1, 0, new Pixel(255, 255, 255));
    expected.setPixel(0, 1, new Pixel(255, 255, 255));
    expected.setPixel(1, 1, new Pixel(255, 255, 255));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidWhiteLevelsAdjust() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.levelsAdjust(ih.loadImage(
            "src/res/PNG/Sample.png"), 10, 15, -10);
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.levelsAdjust(ih.loadImage(
            "src/res/PNG/Sample.png"), -10, 15, 20);
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.levelsAdjust(ih.loadImage(
            "src/res/PNG/Sample.png"), 10, -15, 20);
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.levelsAdjust(ih.loadImage(
            "src/res/PNG/Sample.png"), 20, 10, 5);
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.levelsAdjust(ih.loadImage(
            "src/res/PNG/Sample.png"), 10, 20, 5);
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
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.levelsAdjust(ih.loadImage(
            "src/res/PNG/Sample.png"), 10, 50, 5);
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
    ImageFormatHandler ih = new ImageHandler();
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
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"blur", "test", "expected", "split", "50"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(177, 239, 231));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(146, 213, 191));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitInvalidBlurCheck1() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"blur", "src/res/PNG/Sample.png", "expected", "split", "50"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"blur", "src/res/PNG/Sample.png", "expected", "split", "250"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"blur", "src/res/PNG/Sample.png", "expected", "split", "-50"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(175, 239, 231));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(146, 213, 191));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertFalse(expected.deepCopyImage().equals(actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void splitInvalidBlurCheck4() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"blur", "src/res/PNG/Sample.png", "expected", "split", "-50"};
    ImageInterface actual = io.splitViewOperation(tokens, null);
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
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"sharpen", "src/res/PNG/Sample.png", "expected", "split", "50"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(200, 255, 255));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(123, 194, 162));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitInvalidSharpenCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"sharpen", "src/res/PNG/Sample.png", "expected", "split", "50"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"sepia", "src/res/PNG/Sample.png", "expected", "split", "50"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 219));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(238, 212, 165));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitInvalidSepiaCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"sepia", "src/res/PNG/Sample.png", "expected", "split", "50"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"red-component", "src/res/PNG/Sample.png", "expected", "split", "50"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(193, 193, 193));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(131, 131, 131));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitInvalidRedComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"red-component", "src/res/PNG/Sample.png", "expected", "split", "50"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"green-component", "src/res/PNG/Sample.png", "expected", "split", "50"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(252, 252, 252));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(201, 201, 201));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitInvalidGreenComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"green-component", "src/res/PNG/Sample.png", "expected", "split", "50"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"blue-component", "src/res/PNG/Sample.png", "expected", "split", "50"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(251, 251, 251));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(172, 172, 172));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitInvalidBlueComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"blue-component", "src/res/PNG/Sample.png", "expected", "split", "50"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"luma-component", "src/res/PNG/Sample.png", "expected", "split", "50"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(239, 239, 239));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(184, 184, 184));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitInvalidLumaComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"luma-component", "src/res/PNG/Sample.png", "expected", "split", "50"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"value-component", "src/res/PNG/Sample.png", "expected", "split", "50"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(252, 252, 252));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(201, 201, 201));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitIntensityComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"value-component", "src/res/PNG/Sample.png", "expected", "split", "50"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(252, 252, 252));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(201, 201, 201));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitInvalidIntensityComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"value-component", "src/res/PNG/Sample.png", "expected", "split", "50"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"color-correct", "src/res/PNG/Sample.png", "expected", "split", "50"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
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
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"color-correct", "src/res/PNG/Sample.png", "expected", "split", "50"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(230, 219, 247));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(168, 168, 168));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertTrue(expected.deepCopyImage().equals(actual));
  }

  @Test
  public void splitlevelAdjustComponentCheck() throws IOException {
    ExtendedOperations io = new ExtendedImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    String[] tokens = {"levels-adjust", "10", "15", "20", "test", "expected", "split", "50"};
    ImageInterface actual = io.splitViewOperation(tokens, ih.loadImage(
            "src/res/PNG/Sample.png"));
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(255, 255, 255));
    expected.setPixel(1, 0, new Pixel(119, 90, 47));
    expected.setPixel(0, 1, new Pixel(255, 255, 255));
    expected.setPixel(1, 1, new Pixel(62, 130, 143));
    assertTrue(expected.deepCopyImage().equals(actual));
  }


  @Test
  public void multipleFunction() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applySepia(ih.loadImage("src/res/PNG/Sample.png"));
    ImageInterface afterop = io.applyVerticalFlip(actual);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(238, 212, 165));
    expected.setPixel(1, 0, new Pixel(151, 134, 105));
    expected.setPixel(0, 1, new Pixel(255, 255, 219));
    expected.setPixel(1, 1, new Pixel(124, 111, 86));
    assertTrue(expected.deepCopyImage().equals(afterop));
  }

  @Test
  public void testEquals1() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface one = ih.loadImage("src/res/PNG/Sample.png");
    assertTrue(one.equals(one));
  }

  @Test
  public void testEquals2() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface one = ih.loadImage("src/res/PNG/Sample.png");
    ImageInterface two = ih.loadImage("src/res/PNG/Sample.png");
    assertTrue(one.equals(two));
    assertTrue(two.equals(one));
  }

  @Test
  public void testEquals3() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface one = ih.loadImage("src/res/PNG/Sample.png");
    ImageInterface two = ih.loadImage("src/res/PNG/Sample.png");
    ImageInterface three = ih.loadImage("src/res/PNG/Sample.png");
    assertTrue(one.equals(two));
    assertTrue(two.equals(three));
    assertTrue(one.equals(three));
  }

  @Test
  public void testEquals4() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface one = ih.loadImage("src/res/PNG/Sample.png");
    ImageInterface two = ih.loadImage("src/res/JPG/Sample.jpg");
    assertFalse(one.equals(two));
  }

  @Test
  public void testHash1() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface one = ih.loadImage("src/res/PNG/Sample.png");
    assertTrue(one.hashCode() == one.hashCode());
  }

  @Test
  public void testHash2() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface one = ih.loadImage("src/res/PNG/Sample.png");
    ImageInterface two = ih.loadImage("src/res/JPG/Sample.jpg");
    assertFalse(one.hashCode() == two.hashCode());
  }

  @Test
  public void testHash3() throws IOException {
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface one = ih.loadImage("src/res/PNG/Sample.png");
    ImageInterface two = ih.loadImage("src/res/PNG/Sample.png");
    assertTrue(one.hashCode() == two.hashCode());
  }

  @Test
  public void multipleFunction2() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyBlur(ih.loadImage("src/res/PNG/Sample.png"));
    ImageInterface afterop = io.applyHorizontalFlip(actual);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(122, 134, 111));
    expected.setPixel(1, 0, new Pixel(159, 204, 191));
    expected.setPixel(0, 1, new Pixel(93, 143, 137));
    expected.setPixel(1, 1, new Pixel(128, 190, 173));
    assertTrue(expected.deepCopyImage().equals(afterop));
  }

  @Test
  public void multipleFunction3() throws IOException {
    ImageOperations io = new ImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface actual = io.applyBlur(ih.loadImage("src/res/PNG/Sample.png"));
    ImageInterface afterop = io.applyVerticalFlip(actual);
    ImageInterface afterop2 = io.applyHorizontalFlip(afterop);
    ImageCopyInterface expected = new ImageCopy(2, 2);
    expected.setPixel(0, 0, new Pixel(93, 143, 137));
    expected.setPixel(1, 0, new Pixel(128, 190, 173));
    expected.setPixel(0, 1, new Pixel(122, 134, 111));
    expected.setPixel(1, 1, new Pixel(159, 204, 191));
    assertTrue(expected.deepCopyImage().equals(afterop2));
  }

  @Test
  public void demoDOWNSIZE() throws IOException {
    AdditionalOperations io = new AdditionalImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface input = ih.loadImage("src/res/PNG/galaxy.png");
    ImageInterface actual = io.downscaleImage(input, 100, 100);
    assertEquals(100, actual.getHeight());
    assertEquals(100, actual.getWidth());
    ih.saveImage(actual, "src/res/PNG/test.png", "png");
  }

  @Test
  public void demoPARTIAL() throws IOException {
    AdditionalOperations io = new AdditionalImageOperations();
    ImageFormatHandler ih = new ImageHandler();
    ImageInterface input = ih.loadImage("src/res/PNG/manhattan-small.png");
    ImageCopyInterface expected = new ImageCopy(input.getWidth(), input.getHeight());

    for (int i = 0; i < input.getHeight() / 2; i++) {
      for (int j = 0; j < input.getWidth() / 2; j++) {
        expected.setPixel(j, i, new Pixel(0, 0, 0));
      }
    }

    ImageInterface trial = io.visualizeLuma(input, expected.deepCopyImage());
    ih.saveImage(trial, "src/res/PNG/test.png", "png");
  }


}


