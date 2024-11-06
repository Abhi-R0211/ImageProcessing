package controllertest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringReader;

import imagecontroller.Controller;
import imagecontroller.TextImageController;
import imagemodel.ExtendedOperations;
import imagemodel.MockOperations;

/**
 * This class tests the working of the TextImage Controller class.
 */
public class ControllerTest {

  private Controller textImageController;
  private ExtendedOperations mock;
  private StringBuilder output;

  @Before
  public void setUp() {
    output = new StringBuilder();
    mock = new MockOperations(output);
    textImageController = new TextImageController(mock, new StringReader(
            "load src/res/PNG/galaxy.png input\nexit\n"), output);
  }

  @Test
  public void testLevelValid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nlevels-adjust 10 20 30 testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Levels adjusted"));
    assertTrue(actual.contains("Levels-adjusted image stored at: output"));
  }

  @Test
  public void testLevelInvalid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nlevels-adjust 30 testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid level command"));
  }

  @Test
  public void testColorCorrectValid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\ncolor-correct testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Color corrected"));
    assertTrue(actual.contains("Color corrected"));
  }

  @Test
  public void testColorCorrectInvalid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\ncolor-correct\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid color correct command"));
  }

  @Test
  public void testRGBSplitValid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load src/res/PNG/galaxy.png testImage\n" +
                    "rgb-split testImage red green blue\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Visualized red component\n" + "Visualized green component\n" +
            "Visualized blue component"));
    assertTrue(actual.contains("RGB Components split and stored at red green blue"));
  }

  @Test
  public void testRGBSplitInvalid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader(
            "load src/res/PNG/galaxy.png testImage\n" +
                    "rgb-split red green blue\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid rgb-split command"));
  }

  @Test
  public void testHistogramInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nhistogram testImage output 10\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid Histogram Command"));
  }

  @Test
  public void testHistogramInvalid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("histogram testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Image not found: testImage"));
  }

  @Test
  public void testHistogramValid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nhistogram testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Creating histogram with image"));
    assertTrue(actual.contains("Histogram created and saved as image at: output"));
  }

  @Test
  public void testCompressInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\ncompress testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid number of arguments"));
  }

  @Test
  public void testCompressInvalid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("compress 90 testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Source image not found."));
  }

  @Test
  public void testCompressValid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\ncompress 90 testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Compressing image with percentage: 90%"));
    assertTrue(actual.contains("Image compressed and saved as: output"));
  }

  @Test
  public void testSepiaValid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nsepia testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Applied sepia"));
    assertTrue(actual.contains("Sepia filter added and stored at output"));
  }

  @Test
  public void testSepiaValid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nsepia testImage output split 25\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Split view operation performed"));
    assertTrue(actual.contains("Sepia filter added and stored at output"));
  }

  @Test
  public void testSepiaInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nsepia testImage output 1 2 3\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid sepia command"));
  }

  @Test
  public void testSharpenValid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nsharpen testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Applied sharpen"));
    assertTrue(actual.contains("Image sharpened and stored at output"));
  }

  @Test
  public void testSharpenValid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nsharpen testImage output split 25\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Split view operation performed"));
    assertTrue(actual.contains("Image sharpened and stored at output"));
  }

  @Test
  public void testSharpenInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nsharpen testImage output 1 2 3\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid sharpen command"));
  }

  @Test
  public void testBlurValid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nblur testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Applied blur"));
    assertTrue(actual.contains("Image blurred and stored at output"));
  }

  @Test
  public void testBlurValid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nblur testImage output split 25\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Split view operation performed"));
    assertTrue(actual.contains("Image blurred and stored at output"));
  }

  @Test
  public void testBlurInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nblur testImage output 1 2 3\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid blur command"));
  }

  @Test
  public void testCombineValid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png red\nload src/res/PNG/galaxy.png green\nload src/res/PNG/galaxy.png blue\nrgb-combine testImage red green blue\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Combined RGB"));
    assertTrue(actual.contains("RGB Channels combined and stored at :testImage"));
  }

  @Test
  public void testCombineInvalid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png red\nload src/res/PNG/galaxy.png green\nload src/res/PNG/galaxy.png blue\nrgb-combine testImage red green\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid RGB combine command"));
  }

  @Test
  public void testBrightenInvalid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nbrighten testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid brighten command"));
  }

  @Test
  public void testBrightenValid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nbrighten 50 testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Applied brightness"));
    assertTrue(actual.contains("Image brightened and stored at output"));
  }

  @Test
  public void testDarkenValid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nbrighten -50 testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Applied brightness"));
    assertTrue(actual.contains("Image brightened and stored at output"));
  }

  @Test
  public void testVerticalFlipValid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nvertical-flip testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Applied vertical flip"));
    assertTrue(actual.contains("Image flipped Vertically and stored at output"));
  }

  @Test
  public void testVerticalFlipInvalid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nvertical-flip output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid flip command"));
  }

  @Test
  public void testHorizontalFlipValid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nhorizontal-flip testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Applied horizontal flip"));
    assertTrue(actual.contains("Image flipped Horizontally and stored at output"));
  }

  @Test
  public void testHorizontalFlipInvalid() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nhorizontal-flip output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid horizontal flip command"));
  }

  @Test
  public void testIntensityValid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nintensity-component testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Visualized intensity"));
    assertTrue(actual.contains("Intensity Component Loaded at: output"));
  }

  @Test
  public void testIntensityValid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nintensity-component testImage output split 25\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Split view operation performed"));
    assertTrue(actual.contains("Intensity Component Loaded at: output"));
  }

  @Test
  public void testIntensityInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nintensity-component testImage output 1 2 3\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid intensity-component command"));
  }

  @Test
  public void testLumaValid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nluma-component testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Visualized luma"));
    assertTrue(actual.contains("Luma Component Loaded at: output"));
  }

  @Test
  public void testLumaValid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nluma-component testImage output split 25\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Split view operation performed"));
    assertTrue(actual.contains("Luma Component Loaded at: output"));
  }

  @Test
  public void testLumaInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nluma-component testImage output 1 2 3\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid luma-component command"));
  }

  @Test
  public void testValueValid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nvalue-component testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Visualized value"));
    assertTrue(actual.contains("Value Component Loaded at: output"));
  }

  @Test
  public void testValueValid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nvalue-component testImage output split 25\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Split view operation performed"));
    assertTrue(actual.contains("Value Component Loaded at: output"));
  }

  @Test
  public void testValueInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nvalue-component testImage output 1 2 3\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid value-component command"));
  }

  @Test
  public void testBlueInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nblue-component testImage output 1 2 3\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid blue-component command"));
  }

  @Test
  public void testBlueValid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nblue-component testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Visualized blue component"));
    assertTrue(actual.contains("Blue Component Loaded at: output"));
  }

  @Test
  public void testBlueValid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nblue-component testImage output split 25\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Split view operation performed"));
    assertTrue(actual.contains("Blue Component Loaded at: output"));
  }

  @Test
  public void testRedInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nred-component testImage output 1 2 3\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid red-component command"));
  }

  @Test
  public void testRedValid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nred-component testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Visualized red component"));
    assertTrue(actual.contains("Red Component Loaded at: output"));
  }

  @Test
  public void testRedValid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\nred-component testImage output split 25\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Split view operation performed"));
    assertTrue(actual.contains("Red Component Loaded at: output"));
  }

  @Test
  public void testGreenInvalid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\ngreen-component testImage output 1 2 3\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Invalid green-component command"));
  }

  @Test
  public void testGreenValid1() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\ngreen-component testImage output\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Visualized green component"));
    assertTrue(actual.contains("Green Component Loaded at: output"));
  }

  @Test
  public void testGreenValid2() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("load src/res/PNG/galaxy.png testImage\ngreen-component testImage output split 25\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Split view operation performed"));
    assertTrue(actual.contains("Green Component Loaded at: output"));
  }

  @Test
  public void testStartAndExit() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("exit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("  load <image-path> <image-name>                                                              - Loads an image\n" +
            "  save <image-path> <image-name>                                                  " +
            "            - Saves an image\n" +
            "  red-component <image-name> <dest-image-name>                                    " +
            "            - Gets the Red Component of the Image\n" +
            "  red-component <image-name> <dest-image-name> split p                            " +
            "            - Gets the Red Component of the first p% of the Image while " +
            "retaining the rest\n" +
            "  green-component <image-name> <dest-image-name>                                  " +
            "            - Gets the Green Component of the Image\n" +
            "  green-component <image-name> <dest-image-name> split p                          " +
            "            - Gets the Green Component of the first p% of the Image while " +
            "retaining the rest\n" +
            "  blue-component <image-name> <dest-image-name>                                   " +
            "            - Gets the Blue Component of the Image\n" +
            "  blue-component <image-name> <dest-image-name> split p                           " +
            "            - Gets the Blue Component of the first p% of the Image while " +
            "retaining the rest\n" +
            "  value-component <image-name> <dest-image-name>                                  " +
            "            - Gets the Value Component of the Image\n" +
            "  value-component <image-name> <dest-image-name> split p                          " +
            "            - Gets the Value Component of the first p% of the Image while " +
            "retaining the rest\n" +
            "  luma-component <image-name> <dest-image-name>                                   " +
            "            - Gets the Luma Component of the Image\n" +
            "  luma-component <image-name> <dest-image-name> split p                           " +
            "            - Gets the Luma Component of the first p% of the Image while " +
            "retaining the rest\n" +
            "  intensity-component <image-name> <dest-image-name>                              " +
            "            - Gets the Intensity Component of the Image\n" +
            "  intensity-component <image-name> <dest-image-name> split p                      " +
            "            - Gets the Intensity Component of the first p% of the Image while " +
            "retaining the rest\n" +
            "  horizontal-flip <image-name> <dest-image-name>                                  " +
            "            - Flips image horizontally\n" +
            "  vertical-flip <image-name> <dest-image-name>                                    " +
            "            - Flips image vertically\n" +
            "  brighten <increment> <image-name> <dest-image-name>                             " +
            "            - Brightens the image\n" +
            "  rgb-split <image-name> <dest-image-name-red> <dest-image-name-green> " +
            "<dest-image-name-blue> - Splits RGB channels\n" +
            "  rgb-combine <dest-image-name> <red-image> <green-image> <blue-image>            " +
            "            - Combines RGB channels\n" +
            "  blur <image-name> <dest-image-name>                                             " +
            "            - Blurs the image\n" +
            "  blur <image-name> <dest-image-name> split p                                     " +
            "            - Blurs the first p% of the Image while retaining the rest\n" +
            "  sharpen <image-name> <dest-image-name>                                          " +
            "            - Sharpens the image>\n" +
            "  sharpen <image-name> <dest-image-name> split p                                  " +
            "            - Sharpens the first p% of the Image while retaining the rest\n" +
            "  sepia <image-name> <dest-image-name>                                            " +
            "            - Produces a sepia tone of the image>\n" +
            "  sepia <image-name> <dest-image-name> split p                                    " +
            "            - Produces a sepia tone of the first p% of the Image while " +
            "retaining the rest\n" +
            "  compress percentage <image-name> <dest-image-name>                              " +
            "            - Compresses the image by the given percentage\n" +
            "  histogram <image-name> <dest-image-name>                                        " +
            "            - Generates a histogram of the given Image\n" +
            "  color-correct <image-name> <dest-image-name>                                    " +
            "            - Generates a color corrected version of the given Image\n" +
            "  color-correct <image-name> <dest-image-name> split p                            " +
            "            - Generates a color corrected version of the first p% of " +
            "the given Image\n" +
            "  levels-adjust b m w <image-name> <dest-image-name>                              " +
            "            - Generates a level adjusted version of the given Image as " +
            "per the given black, mid and white points\n" +
            "  levels-adjust b m w <image-name> <dest-image-name> split p                      " +
            "            - Generates a level adjusted version of the first p% of the given " +
            "Image as per the given black, mid and white points\n" +
            "  run <script-file-path>                                                          " +
            "            - Run commands from a script file\n" +
            "Type 'exit' to quit."));
    assertTrue(actual.contains("Exiting this application...\n"));
  }

  @Test
  public void testDefaultCase() throws IOException {
    textImageController = new TextImageController(mock, new StringReader("loading\nexit\n"), output);
    textImageController.start();
    String actual = output.toString().replace(System.lineSeparator(), "\n");
    assertTrue(actual.contains("Unknown command: loading"));
  }
}
