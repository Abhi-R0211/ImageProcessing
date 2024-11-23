package controllertest;


import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

import imagecontroller.ControllerGui;
import imagecontroller.GUIController;
import imagemodel.Image;
import imagemodel.ImageInterface;
import imagemodel.MockOperations;
import imageview.MainFrameMock;

/**
 * Test class for validating the link between controller, model and view.
 */
public class GUIControllerTest {

  private MockOperations ops;
  private ControllerGui gui;
  private MainFrameMock mock;
  private ImageInterface image;

  @Before
  public void setUp() {
    ops = new MockOperations();
    mock = new MainFrameMock();
    gui = new GUIController(ops, mock);
    image = new Image(2, 2);
  }

  @Test
  public void testDownsize() {
    gui.downsizeImage();
    assertTrue(mock.isDownsize());
    assertTrue(ops.isDownscale());
    assertTrue(mock.isDisplayImage());
  }

  @Test
  public void testBlur() throws IOException {
    gui.loadImage();
    gui.handleBlurCommand();
    mock.setController(gui);
    assertTrue(mock.isSetController());
    assertTrue(ops.isBlurred());
    assertTrue(mock.isDisplayImage());
    assertTrue(mock.isDisplayHistogram());
  }

  @Test
  public void testSharpen() throws IOException {
    gui.loadImage();
    gui.handleSharpenCommand();
    mock.setController(gui);
    assertTrue(mock.isSetController());
    assertTrue(ops.isSharpen());
    assertTrue(mock.isDisplayImage());
    assertTrue(mock.isDisplayHistogram());
  }

  @Test
  public void testRed() throws IOException {
    gui.loadImage();
    gui.handleRedComponent();
    mock.setController(gui);
    assertTrue(mock.isSetController());
    assertTrue(ops.isRed());
    assertTrue(mock.isDisplayImage());
    assertTrue(mock.isDisplayHistogram());
  }

  @Test
  public void testBlue() throws IOException {
    gui.loadImage();
    gui.handleBlueComponent();
    mock.setController(gui);
    assertTrue(mock.isSetController());
    assertTrue(ops.isBlue());
    assertTrue(mock.isDisplayImage());
    assertTrue(mock.isDisplayHistogram());
  }

  @Test
  public void testGreen() throws IOException {
    gui.loadImage();
    gui.handleGreenComponent();
    mock.setController(gui);
    assertTrue(mock.isSetController());
    assertTrue(ops.isGreen());
    assertTrue(mock.isDisplayImage());
    assertTrue(mock.isDisplayHistogram());
  }

  @Test
  public void testSepia() throws IOException {
    gui.loadImage();
    gui.handleSepiaCommand();
    mock.setController(gui);
    assertTrue(mock.isSetController());
    assertTrue(ops.isSepia());
    assertTrue(mock.isDisplayImage());
    assertTrue(mock.isDisplayHistogram());
  }

  @Test
  public void testLuma() throws IOException {
    gui.loadImage();
    gui.handleLumaComponent();
    mock.setController(gui);
    assertTrue(mock.isSetController());
    assertTrue(ops.isVisualizeLuma());
    assertTrue(mock.isDisplayImage());
    assertTrue(mock.isDisplayHistogram());
  }

  @Test
  public void testIntensity() throws IOException {
    gui.loadImage();
    gui.handleIntensityComponent();
    mock.setController(gui);
    assertTrue(mock.isSetController());
    assertTrue(ops.isIntensity());
    assertTrue(mock.isDisplayImage());
    assertTrue(mock.isDisplayHistogram());
  }

  @Test
  public void testValue() throws IOException {
    gui.loadImage();
    gui.handleValueComponent();
    mock.setController(gui);
    assertTrue(mock.isSetController());
    assertTrue(ops.isValue());
    assertTrue(mock.isDisplayImage());
    assertTrue(mock.isDisplayHistogram());
  }

  @Test
  public void testHorizontal() throws IOException {
    gui.loadImage();
    gui.flipHorizontal();
    mock.setController(gui);
    assertTrue(mock.isSetController());
    assertTrue(ops.isHorizontalFlip());
    assertTrue(mock.isDisplayImage());
    assertTrue(mock.isDisplayHistogram());
  }

  @Test
  public void testVertical() throws IOException {
    gui.loadImage();
    gui.flipVertical();
    mock.setController(gui);
    assertTrue(mock.isSetController());
    assertTrue(ops.isVerticalFlip());
    assertTrue(mock.isDisplayImage());
    assertTrue(mock.isDisplayHistogram());
  }

  @Test
  public void testColorCorrect() throws IOException {
    gui.loadImage();
    gui.handleColorCorrectCommand();
    mock.setController(gui);
    assertTrue(mock.isSetController());
    assertTrue(ops.isCorrect());
    assertTrue(mock.isDisplayImage());
    assertTrue(mock.isDisplayHistogram());
  }

  @Test
  public void testCompress() throws IOException {
    gui.loadImage();
    gui.applyCompression();
    mock.setController(gui);
    assertTrue(mock.isSetController());
    assertTrue(ops.isCompress());
    assertTrue(mock.isDisplayImage());
    assertTrue(mock.isDisplayHistogram());
  }

  @Test
  public void testLevelAdjust() throws IOException {
    gui.loadImage();
    gui.handleLevelsAdjustCommand();
    mock.setController(gui);
    assertTrue(mock.isSetController());
    assertTrue(ops.isAdjust());
    assertTrue(mock.isDisplayImage());
    assertTrue(mock.isDisplayHistogram());
  }

  @Test
  public void testLevelAdjustInvalid() throws IOException {
    gui.loadImage();
    ops.levelsAdjust(image, 50, 20, 0);
    gui.handleLevelsAdjustCommand();
    mock.setController(gui);
    assertTrue(mock.isSetController());
    assertTrue(mock.isLevelAdjust());
    assertTrue(ops.isAdjust());
    assertTrue(mock.isError());
  }

  @Test
  public void testDownsizeInvalid() throws IOException {
    gui.loadImage();
    ops.downscaleImage(image, 1000, 1000);
    gui.downsizeImage();
    mock.setController(gui);
    assertTrue(mock.isSetController());
    assertTrue(mock.isDownsize());
    assertTrue(ops.isDownscale());
    assertTrue(mock.isError());
  }
}