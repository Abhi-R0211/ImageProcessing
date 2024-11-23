package controllertest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringReader;

import imagecontroller.Controller;
import imagecontroller.TextImageController;
import imagemodel.AdditionalOperations;
import imagemodel.MockOperations;

/**
 * This class tests the working of the TextImage Controller class.
 */
public class ControllerTest {

  private Controller textImageController;
  private AdditionalOperations mock;
  private StringBuilder output;

  @Before
  public void setUp() {
    output = new StringBuilder();
    mock = new MockOperations(output);
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png input\nexit\n"), output);
  }

  @Test
  public void testLevelValid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "levels-adjust 10 20 30 testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Levels adjusted"));
    assertTrue(actual.contains("Levels-adjusted image stored as: output"));
  }

  @Test
  public void testLevelInvalid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "levels-adjust 30 testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid level command"));
  }

  @Test
  public void testColorCorrectValid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "color-correct testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Color corrected"));
    assertTrue(actual.contains("Color corrected"));
  }

  @Test
  public void testColorCorrectInvalid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "color-correct\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid color correct command"));
  }

  @Test
  public void testRGBSplitValid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "rgb-split testImage red green blue\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Visualized red component\n" + "Visualized green component\n"
            + "Visualized blue component"));
    assertTrue(actual.contains("RGB Components split and stored as: red green blue"));
  }

  @Test
  public void testRGBSplitInvalid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "rgb-split red green blue\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid rgb-split command"));
  }

  @Test
  public void testHistogramInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/Sample.png testImage\n"
                    + "histogram testImage output 10\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid Histogram Command"));
  }

  @Test
  public void testHistogramInvalid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "histogram testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Image not found: testImage"));
  }

  @Test
  public void testHistogramValid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/Sample.png testImage\n"
                    + "histogram testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Creating histogram with image"));
    assertTrue(actual.contains("Histogram created and saved as image at: output"));
  }

  @Test
  public void testCompressInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "compress testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid number of arguments"));
  }

  @Test
  public void testCompressInvalid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "compress 90 testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Source image not found."));
  }

  @Test
  public void testCompressValid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/Sample.png testImage\n"
                    + "compress 90 testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Compressing image with percentage: 90%"));
    assertTrue(actual.contains("Image compressed and saved as: output"));
  }

  @Test
  public void testSepiaValid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "sepia testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Applied sepia"));
    assertTrue(actual.contains("Sepia filter added and stored as: output"));
  }

  @Test
  public void testSepiaValid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "sepia testImage output split 25\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Split view operation performed"));
    assertTrue(actual.contains("Sepia filter added and stored as: output"));
  }

  @Test
  public void testSepiaValid3() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "load res/PNG/galaxy.png mask\n"
                    + "sepia testImage mask output \nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Applied sepia with mask"));
    assertTrue(actual.contains("Sepia filter added and stored as: output"));
  }

  @Test
  public void testSepiaInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "sepia testImage output 1 2 3\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid sepia command"));
  }

  @Test
  public void testSharpenValid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "sharpen testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Applied sharpen"));
    assertTrue(actual.contains("Image sharpened and stored as: output"));
  }

  @Test
  public void testSharpenValid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "sharpen testImage output split 25\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Split view operation performed"));
    assertTrue(actual.contains("Image sharpened and stored as: output"));
  }

  @Test
  public void testSharpenValid3() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "load res/PNG/galaxy.png mask\n"
                    + "sharpen testImage mask output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Applied sharpen with mask"));
    assertTrue(actual.contains("Image sharpened and stored as: output"));
  }

  @Test
  public void testSharpenInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "sharpen testImage output 1 2 3\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid sharpen command"));
  }

  @Test
  public void testBlurValid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "blur testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Applied blur"));
    assertTrue(actual.contains("Image blurred and stored as: output"));
  }

  @Test
  public void testBlurValid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "blur testImage output split 25\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Split view operation performed"));
    assertTrue(actual.contains("Image blurred and stored as: output"));
  }

  @Test
  public void testBlurValid3() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "load res/PNG/galaxy.png mask\n"
                    + "blur testImage mask output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Applied blur with mask"));
    assertTrue(actual.contains("Image blurred and stored as: output"));
  }

  @Test
  public void testBlurInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "blur testImage output 1 2 3\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid blur command"));
  }

  @Test
  public void testCombineValid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png red\n"
                    + "load res/PNG/galaxy.png green\nload res/PNG/galaxy.png blue\n"
                    + "rgb-combine testImage red green blue\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Combined RGB"));
    assertTrue(actual.contains("RGB Channels combined and stored as: testImage"));
  }

  @Test
  public void testCombineInvalid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png red\n"
                    + "load res/PNG/galaxy.png green\nload res/PNG/galaxy.png blue\n"
                    + "rgb-combine testImage red green\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid RGB combine command"));
  }

  @Test
  public void testBrightenInvalid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "brighten testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Error executing command"));
  }

  @Test
  public void testBrightenValid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "brighten 50 testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Applied brightness"));
    assertTrue(actual.contains("Image brightened and stored as: output"));
  }

  @Test
  public void testBrightenValid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "load res/PNG/galaxy.png mask\n"
                    + "brighten 50 testImage mask output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Brightened with mask"));
    assertTrue(actual.contains("Image brightened and stored as: output"));
  }

  @Test
  public void testDarkenValid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "brighten -50 testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Applied brightness"));
    assertTrue(actual.contains("Image brightened and stored as: output"));
  }

  @Test
  public void testDarkenValid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "load res/PNG/galaxy.png mask\n"
                    + "brighten -50 testImage mask output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Brightened with mask"));
    assertTrue(actual.contains("Image brightened and stored as: output"));
  }

  @Test
  public void testVerticalFlipValid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "vertical-flip testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Applied vertical flip"));
    assertTrue(actual.contains("Image flipped Vertically and stored as: output"));
  }

  @Test
  public void testVerticalFlipInvalid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "vertical-flip output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid flip command"));
  }

  @Test
  public void testHorizontalFlipValid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "horizontal-flip testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Applied horizontal flip"));
    assertTrue(actual.contains("Image flipped Horizontally and stored as: output"));
  }

  @Test
  public void testHorizontalFlipInvalid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "horizontal-flip output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid horizontal flip command"));
  }

  @Test
  public void testIntensityValid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "intensity-component testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Visualized intensity"));
    assertTrue(actual.contains("Intensity Component Loaded at: output"));
  }

  @Test
  public void testIntensityValid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "intensity-component testImage output split 25\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Split view operation performed"));
    assertTrue(actual.contains("Intensity Component Loaded at: output"));
  }

  @Test
  public void testIntensityValid3() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "load res/PNG/galaxy.png mask\n"
                    + "intensity-component testImage mask output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Visualized intensity with mask"));
    assertTrue(actual.contains("Intensity Component Loaded at: output"));
  }

  @Test
  public void testIntensityInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "intensity-component testImage output 1 2 3\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid intensity-component command"));
  }

  @Test
  public void testLumaValid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "luma-component testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Visualized luma"));
    assertTrue(actual.contains("Luma Component Loaded at: output"));
  }

  @Test
  public void testLumaValid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "luma-component testImage output split 25\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Split view operation performed"));
    assertTrue(actual.contains("Luma Component Loaded at: output"));
  }

  @Test
  public void testLumaValid3() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "load res/PNG/galaxy.png mask\n"
                    + "luma-component testImage mask output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Visualized luma with mask"));
    assertTrue(actual.contains("Luma Component Loaded at: output"));
  }

  @Test
  public void testLumaInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "luma-component testImage output 1 2 3\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid luma-component command"));
  }

  @Test
  public void testValueValid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "value-component testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Visualized value"));
    assertTrue(actual.contains("Value Component Loaded at: output"));
  }

  @Test
  public void testValueValid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "value-component testImage output split 25\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Split view operation performed"));
    assertTrue(actual.contains("Value Component Loaded at: output"));
  }

  @Test
  public void testValueValid3() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "load res/PNG/galaxy.png mask\n"
                    + "value-component testImage mask output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Visualized value with mask"));
    assertTrue(actual.contains("Value Component Loaded at: output"));
  }

  @Test
  public void testValueInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "value-component testImage output 1 2 3\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid value-component command"));
  }

  @Test
  public void testBlueInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "blue-component testImage output 1 2 3\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid blue-component command"));
  }

  @Test
  public void testBlueValid3() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "load res/PNG/galaxy.png mask\n"
                    + "blue-component testImage mask output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Visualized blue with mask"));
    assertTrue(actual.contains("Blue Component Loaded at: output"));
  }

  @Test
  public void testBlueValid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "blue-component testImage output split 25\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Split view operation performed"));
    assertTrue(actual.contains("Blue Component Loaded at: output"));
  }

  @Test
  public void testBlueValid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "blue-component testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Visualized blue component"));
    assertTrue(actual.contains("Blue Component Loaded at: output"));
  }

  @Test
  public void testRedInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "red-component testImage output 1 2 3\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid red-component command"));
  }

  @Test
  public void testRedValid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "red-component testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Visualized red component"));
    assertTrue(actual.contains("Red Component Loaded at: output"));
  }

  @Test
  public void testRedValid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "red-component testImage output split 25\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Split view operation performed"));
    assertTrue(actual.contains("Red Component Loaded at: output"));
  }

  @Test
  public void testRedValid3() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "load res/PNG/galaxy.png mask\n"
                    + "red-component testImage mask output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Visualized red with mask"));
    assertTrue(actual.contains("Red Component Loaded at: output"));
  }

  @Test
  public void testGreenInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "green-component testImage output 1 2 3\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid green-component command"));
  }

  @Test
  public void testGreenValid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "green-component testImage output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Visualized green component"));
    assertTrue(actual.contains("Green Component Loaded at: output"));
  }

  @Test
  public void testGreenValid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "green-component testImage output split 25\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Split view operation performed"));
    assertTrue(actual.contains("Green Component Loaded at: output"));
  }

  @Test
  public void testGreenValid3() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "load res/PNG/galaxy.png mask\n"
                    + "green-component testImage mask output\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Visualized green with mask"));
    assertTrue(actual.contains("Green Component Loaded at: output"));
  }

  @Test
  public void testStartAndExit() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("exit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Available commands:\n"
            + "  load <image-path> <image-name>                                                "
            + "              - Loads an image\n"
            + "  save <image-path> <image-name>                                             "
            + "                 - Saves an image\n"
            + "  red-component <image-name> <dest-image-name>                                 "
            + "               - Gets the Red Component of the Image\n"
            + "  red-component <image-name> <dest-image-name> split p                              "
            + "          - Gets the Red Component of the first p% of the Image while retaining "
            + "the rest\n"
            + "  red-component <image-name> <mask-image-name> <dest-image-name>     "
            + "                         - Applies the red greyscale on the pixels of the "
            + "image with respect to the mask image\n"
            + "  green-component <image-name> <dest-image-name>                 "
            + "                             - Gets the Green Component of the Image\n"
            + "  green-component <image-name> <dest-image-name> split p         "
            + "                             - Gets the Green Component of the first p% of"
            + " the Image while retaining the rest\n"
            + "  green-component <image-name> <mask-image-name> <dest-image-name>     "
            + "                       - Applies the green greyscale on the pixels of the image"
            + " with respect to the mask image\n"
            + "  blue-component <image-name> <dest-image-name>                   "
            + "                            - Gets the Blue Component of the Image\n"
            + "  blue-component <image-name> <dest-image-name> split p           "
            + "                            - Gets the Blue Component of the first p% of the "
            + "Image while retaining the rest\n"
            + "  blue-component <image-name> <mask-image-name> <dest-image-name>    "
            + "                         - Applies the blue greyscale on the pixels of the"
            + " image with respect to the mask image\n"
            + "  value-component <image-name> <dest-image-name>            "
            + "                                  - Gets the Value Component of the Image\n"
            + "  value-component <image-name> <dest-image-name> split p      "
            + "                                - Gets the Value Component of the first p% "
            + "of the Image while retaining the rest\n"
            + "  value-component <image-name> <mask-image-name> <dest-image-name>     "
            + "                       - Applies the value visualization on the pixels of "
            + "the image with respect to the mask image\n"
            + "  luma-component <image-name> <dest-image-name>           "
            + "                                    - Gets the Luma Component of the Image\n"
            + "  luma-component <image-name> <dest-image-name> split p       "
            + "                                - Gets the Luma Component of the first"
            + " p% of the Image while retaining the rest\n"
            + "  luma-component <image-name> <mask-image-name> <dest-image-name>   "
            + "                          - Applies the luma visualization on the pixels"
            + " of the image with respect to the mask image\n"
            + "  intensity-component <image-name> <dest-image-name>        "
            + "                                  - Gets the Intensity Component of the Image\n"
            + "  intensity-component <image-name> <dest-image-name> split p     "
            + "                             - Gets the Intensity Component of the first"
            + " p% of the Image while retaining the rest\n"
            + "  intensity-component <image-name> <mask-image-name> <dest-image-name>  "
            + "                      - Applies the intensity visualization on the pixels "
            + "of the image  with respect to the mask image\n"
            + "  horizontal-flip <image-name> <dest-image-name>   "
            + "                                           - Flips image horizontally\n"
            + "  vertical-flip <image-name> <dest-image-name>        "
            + "                                        - Flips image vertically\n"
            + "  brighten <increment> <image-name> <dest-image-name>       "
            + "                                  - Brightens the image\n"
            + "  brighten <increment> <image-name> <dest-image-name> mask <mask-image-name>  "
            + "                - Brightens the image depending on the mask image\n"
            + "  rgb-split <image-name> <dest-image-name-red> <dest-image-name-green> "
            + "<dest-image-name-blue> - Splits RGB channels\n"
            + "  rgb-combine <dest-image-name> <red-image> <green-image> <blue-image>   "
            + "                     - Combines RGB channels\n"
            + "  blur <image-name> <dest-image-name>             "
            + "                                            - Blurs the image\n"
            + "  blur <image-name> <dest-image-name> split p             "
            + "                                    - Blurs the first p% of the "
            + "Image while retaining the rest\n"
            + "  blur <image-name> <mask-image-name> <dest-image-name>    "
            + "                                   - Blurs the image according to the mask image\n"
            + "  sharpen <image-name> <dest-image-name>        "
            + "                                              - Sharpens the image>\n"
            + "  sharpen <image-name> <dest-image-name> split p         "
            + "                                     - Sharpens the first p% of the "
            + "Image while retaining the rest\n"
            + "  sharpen <image-name> <mask-image-name> <dest-image-name>    "
            + "                                - Sharpens the image according to the mask image\n"
            + "  sepia <image-name> <dest-image-name>          "
            + "                                              - Produces a sepia tone "
            + "of the image>\n"
            + "  sepia <image-name> <dest-image-name> split p    "
            + "                                            - Produces a sepia tone of the first"
            + " p% of the Image while retaining the rest\n"
            + "  sepia <image-name> <mask-image-name> <dest-image-name>        "
            + "                              - Applies the sepia visualization on the pixels "
            + "of the image  with respect to the mask image\n"
            + "  compress percentage <image-name> <dest-image-name>          "
            + "                                - Compresses the image by the given percentage\n"
            + "  histogram <image-name> <dest-image-name>           "
            + "                                         - Generates a histogram of the given "
            + "Image\n" + "  color-correct <image-name> <dest-image-name>    "
            + "                                            - Generates a color"
            + " corrected version of the given Image\n"
            + "  color-correct <image-name> <dest-image-name> split p   "
            + "                                     - Generates a color "
            + "corrected version of the first p% of the given Image\n"
            + "  levels-adjust b m w <image-name> <dest-image-name>   "
            + "                                       - Generates a level "
            + "adjusted version of the given Image as per the given black, mid and white points\n"
            + "  levels-adjust b m w <image-name> <dest-image-name> split p        "
            + "                          - Generates a level adjusted version of the "
            + "first p% of the given Image as per the given black, mid and white points\n"
            + "  downsize <image-name> <dest-image-name> <target-image-width> "
            + "<target-image-height>          - Downscales an image to fit target height "
            + "and width\n"
            + "  run <script-file-path>                                  "
            + "                                    - Run commands from a script file\n"
            + "Type 'exit' to quit."));
    assertTrue(actual.contains("Exiting this application...\n"));
  }

  @Test
  public void testDefaultCase() throws IOException {
    textImageController = new TextImageController(mock,
            new StringReader("loading\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Unknown command: loading"));
  }

  @Test
  public void testDownscalingInvalid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "downsize testImage output 1 2 3 4\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid downsize command"));
  }

  @Test
  public void testDownscalingValid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load res/PNG/galaxy.png testImage\n"
                    + "downsize testImage output 100 100\nexit\n"), output);
    textImageController.start(new String[]{"-text"});
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Downscaling image"));
    assertTrue(actual.contains("Downsized image stored as: output"));
  }
}
