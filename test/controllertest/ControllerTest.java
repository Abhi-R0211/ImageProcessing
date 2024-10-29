package controllertest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import imagecontroller.ImageHandler;
import imagecontroller.P3PPMHandler;
import imagecontroller.TextImageController;
import imagemodel.Image;
import imagemodel.ImageOperations;

/**
 * This class tests the working of the TextImage Controller class.
 */
public class ControllerTest {

  Map<String, Image> images_temp;
  Image img;
  TextImageController textImageController;
  StringBuilder path;
  ImageHandler ih;
  ImageOperations io;

  @Before
  public void setUp() {
    images_temp = new HashMap<>();
    img = new Image(2, 2);
    textImageController = new TextImageController(new ImageOperations());
    path = new StringBuilder("src/res/PNG/Sample.png");
    ih = new ImageHandler();
    io = new ImageOperations();
  }

  @Test
  public void loadTestNonPPM() throws IOException {
    textImageController.runCommand("load " + path + " input");
    img = ih.loadImage(path.toString());
    images_temp.put("input", img);
    assertTrue(img.equals(textImageController.getImageFromMap("input")));
    images_temp.clear();
  }

  @Test
  public void loadTestPPM() throws IOException {
    String ppmPath = "src/res/PPM/Sample.ppm";
    P3PPMHandler p3ppmHandler = new P3PPMHandler();
    textImageController.runCommand("load " + ppmPath + " input");
    img = p3ppmHandler.loadImage(ppmPath);
    images_temp.put("input", img);
    assertTrue(img.equals(textImageController.getImageFromMap("input")));
    images_temp.clear();
  }

  @Test
  public void saveTestNonPPM() throws IOException {
    textImageController.runCommand("load " + path + " input");
    img = ih.loadImage(path.toString());
    images_temp.put("input", img);
    textImageController.runCommand("save src/res/PNG/Sample1.png input");
    assertTrue(new File("src/res/PNG/Sample1.png").exists());
  }

  @Test
  public void saveTestPPM() throws IOException {
    String ppmPath = "src/res/PPM/Sample.ppm";
    P3PPMHandler p3ppmHandler = new P3PPMHandler();
    textImageController.runCommand("load " + ppmPath + " input");
    img = p3ppmHandler.loadImage(ppmPath);
    images_temp.put("input", img);
    textImageController.runCommand("save src/res/PPM/Sample1.ppm input");
    assertTrue(new File("src/res/PPM/Sample1.ppm").exists());
  }

  @Test
  public void testRedComponent() throws IOException {
    textImageController.runCommand("load " + path + " input");
    textImageController.runCommand("red-component input red");
    Image red_image = io.visualizeRedComponent(ih.loadImage(path.toString()));
    assertTrue(red_image.equals(textImageController.getImageFromMap("red")));
    images_temp.clear();
  }

  @Test
  public void testGreenComponent() throws IOException {
    textImageController.runCommand("load " + path + " input");
    textImageController.runCommand("green-component input green");
    Image green_image = io.visualizeGreenComponent(ih.loadImage(path.toString()));
    assertTrue(green_image.equals(textImageController.getImageFromMap("green")));
    images_temp.clear();
  }

  @Test
  public void testBlueComponent() throws IOException {
    textImageController.runCommand("load " + path + " input");
    textImageController.runCommand("blue-component input blue");
    Image blue_image = io.visualizeBlueComponent(ih.loadImage(path.toString()));
    assertTrue(blue_image.equals(textImageController.getImageFromMap("blue")));
    images_temp.clear();
  }

  @Test
  public void testValueComponent() throws IOException {
    textImageController.runCommand("load " + path + " input");
    textImageController.runCommand("value-component input value");
    Image value_image = io.visualizeValue(ih.loadImage(path.toString()));
    assertTrue(value_image.equals(textImageController.getImageFromMap("value")));
    images_temp.clear();
  }

  @Test
  public void testLumaComponent() throws IOException {
    textImageController.runCommand("load " + path + " input");
    textImageController.runCommand("luma-component input luma");
    Image luma_image = io.visualizeLuma(ih.loadImage(path.toString()));
    assertTrue(luma_image.equals(textImageController.getImageFromMap("luma")));
    images_temp.clear();
  }

  @Test
  public void testIntensityComponent() throws IOException {
    textImageController.runCommand("load " + path.toString() + " input");
    textImageController.runCommand("intensity-component input intensity");
    Image intensity_image = io.visualizeIntensity(ih.loadImage(path.toString()));
    assertTrue(intensity_image.equals(textImageController.getImageFromMap("intensity")));
    images_temp.clear();
  }

  @Test
  public void testHorizontalFlip() throws IOException {
    textImageController.runCommand("load " + path.toString() + " input");
    textImageController.runCommand("horizontal-flip input horizontal");
    Image horizontal_flip = io.applyHorizontalFlip(ih.loadImage(path.toString()));
    assertTrue(horizontal_flip.equals(textImageController.getImageFromMap("horizontal")));
    images_temp.clear();
  }

  @Test
  public void testVerticalFlip() throws IOException {
    textImageController.runCommand("load " + path.toString() + " input");
    textImageController.runCommand("vertical-flip input vertical");
    Image vertical_flip = io.applyVerticalFlip(ih.loadImage(path.toString()));
    assertTrue(vertical_flip.equals(textImageController.getImageFromMap("vertical")));
    images_temp.clear();
  }

  @Test
  public void testBrighten() throws IOException {
    textImageController.runCommand("load " + path.toString() + " input");
    textImageController.runCommand("brighten 100 input bright");
    Image brighten = io.applyBrightness(ih.loadImage(path.toString()), 100);
    assertTrue(brighten.equals(textImageController.getImageFromMap("bright")));
    images_temp.clear();
  }

  @Test
  public void testDarken() throws IOException {
    textImageController.runCommand("load " + path.toString() + " input");
    textImageController.runCommand("brighten -100 input dark");
    Image brighten = io.applyBrightness(ih.loadImage(path.toString()), -100);
    assertTrue(brighten.equals(textImageController.getImageFromMap("dark")));
    images_temp.clear();
  }

  @Test
  public void testSplit() throws IOException {
    textImageController.runCommand("load " + path.toString() + " input");
    textImageController.runCommand("rgb-split input red green blue");
    Image red = io.visualizeRedComponent(ih.loadImage(path.toString()));
    Image green = io.visualizeGreenComponent(ih.loadImage(path.toString()));
    Image blue = io.visualizeBlueComponent(ih.loadImage(path.toString()));
    assertTrue(red.equals(textImageController.getImageFromMap("red")));
    assertTrue(green.equals(textImageController.getImageFromMap("green")));
    assertTrue(blue.equals(textImageController.getImageFromMap("blue")));
    images_temp.clear();
  }

  @Test
  public void testCombine() throws IOException {
    textImageController.runCommand("load " + path.toString() + " input");
    textImageController.runCommand("rgb-split input red green blue");
    textImageController.runCommand("rgb-combine combine red green blue");
    Image red = io.visualizeRedComponent(ih.loadImage(path.toString()));
    Image green = io.visualizeGreenComponent(ih.loadImage(path.toString()));
    Image blue = io.visualizeBlueComponent(ih.loadImage(path.toString()));
    Image combine = io.combineRGB(red, green, blue);
    assertTrue(combine.equals(textImageController.getImageFromMap("combine")));
    images_temp.clear();
  }

  @Test
  public void testBlur() throws IOException {
    textImageController.runCommand("load " + path.toString() + " input");
    textImageController.runCommand("blur input blur");
    Image blur = io.applyBlur(ih.loadImage(path.toString()));
    assertTrue(blur.equals(textImageController.getImageFromMap("blur")));
    images_temp.clear();
  }

  @Test
  public void testSharpen() throws IOException {
    textImageController.runCommand("load " + path.toString() + " input");
    textImageController.runCommand("sharpen input sharpen");
    Image sharpen = io.applyBlur(ih.loadImage(path.toString()));
    assertTrue(sharpen.equals(textImageController.getImageFromMap("sharpen")));
    images_temp.clear();
  }

  @Test
  public void testSepia() throws IOException {
    textImageController.runCommand("load " + path.toString() + " input");
    textImageController.runCommand("sepia input sepia");
    Image sepia = io.applySepia(ih.loadImage(path.toString()));
    assertTrue(sepia.equals(textImageController.getImageFromMap("sepia")));
    images_temp.clear();
  }

  @Test
  public void testScript() throws IOException {
    textImageController.runCommand("run src/res/Scripts/PNG/commands2.txt");
    Image load = ih.loadImage(path.toString());
    Image flip1 = io.applyHorizontalFlip(load);
    Image flip2 = io.applyVerticalFlip(flip1);
    Image brighten = io.applyBrightness(flip2, 100);
    Image darken = io.applyBrightness(brighten, -50);
    Image blur = io.applyBlur(darken);
    Image sharpen = io.applySharpen(blur);
    Image sepia = io.applySepia(sharpen);
    assertTrue(sepia.equals(textImageController.getImageFromMap("sepia")));
    images_temp.clear();
  }
}
