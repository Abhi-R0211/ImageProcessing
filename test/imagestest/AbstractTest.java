package imagestest;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import imagecontroller.Controller;
import imagecontroller.TextImageController;
import imagemodel.AdditionalImageOperations;

import static org.junit.Assert.assertFalse;

/**
 * This is an abstract class that tests the common functionalities across multiple file formats.
 */
public abstract class AbstractTest {

  protected void execute(String command) throws IOException {
    Controller obj = new TextImageController(new AdditionalImageOperations(),
            new InputStreamReader(System.in), System.out);
    obj.runCommand(command);
  }

  @Test
  public void invalidLoadFile() throws IOException {
    execute("load random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidSaveFile() throws IOException {
    execute("save random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidRedComponentFile() throws IOException {
    execute("red-component random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidGreenComponentFile() throws IOException {
    execute("green-component random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidBlueComponentFile() throws IOException {
    execute("blue-component random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidValueComponentFile() throws IOException {
    execute("value-component random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidLumaComponentFile() throws IOException {
    execute("luma-component random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidIntensityComponentFile() throws IOException {
    execute("intensity-component random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidHorizontalFlipFile() throws IOException {
    execute("horizontal-flip random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidVerticalFlipFile() throws IOException {
    execute("vertical-flip random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidBrightenFile() throws IOException {
    execute("brighten 10 random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidDarkenFile() throws IOException {
    execute("brighten -10 random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidRGBSplitFile() throws IOException {
    execute("rgb-split random red green blue");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidRGBCombineFile() throws IOException {
    execute("rgb-combine random red green blue");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidBlurFile() throws IOException {
    execute("blur random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidSharpenFile() throws IOException {
    execute("sharpen random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidSepiaFileFile() throws IOException {
    execute("sepia random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidRunFile() throws IOException {
    execute("run random.txt");
    assertFalse(new File("random.txt").exists());
  }

  @Test
  public void invalidCommandFile() throws IOException {
    execute("lod random/ noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidCompress() throws IOException {
    execute(("compress 10293 noFile"));
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidHistogram() throws IOException {
    execute("histogram random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidColourCorrect() throws IOException {
    execute("colour-correct random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidLevelAdjust() throws IOException {
    execute("level-adjust random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidColorAdjust() throws IOException {
    execute("color-adjust random noFile");
    assertFalse(new File("random/noFile").exists());
  }
}