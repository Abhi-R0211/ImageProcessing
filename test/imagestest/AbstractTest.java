package imagestest;

import org.junit.Test;

import java.io.File;

import imagecontroller.TextImageController;
import imagemodel.ImageOperations;

import static org.junit.Assert.assertFalse;

/**
 * This is an abstract class that tests the common functionalities across multiple file formats..
 */
public abstract class AbstractTest {

  protected void execute(String command) {
    TextImageController obj = new TextImageController(new ImageOperations());
    obj.runCommand(command);
  }

  @Test
  public void invalidLoadFile() {
    execute("load random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidSaveFile() {
    execute("save random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidRedComponentFile() {
    execute("red-component random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidGreenComponentFile() {
    execute("green-component random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidBlueComponentFile() {
    execute("blue-component random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidValueComponentFile() {
    execute("value-component random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidLumaComponentFile() {
    execute("luma-component random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidIntensityComponentFile() {
    execute("intensity-component random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidHorizontalFlipFile() {
    execute("horizontal-flip random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidVerticalFlipFile() {
    execute("vertical-flip random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidBrightenFile() {
    execute("brighten 10 random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidDarkenFile() {
    execute("brighten -10 random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidRGBSplitFile() {
    execute("rgb-split random red green blue");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidRGBCombineFile() {
    execute("rgb-combine random red green blue");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidBlurFile() {
    execute("blur random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidSharpenFile() {
    execute("sharpen random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidSepiaFileFile() {
    execute("sepia random noFile");
    assertFalse(new File("random/noFile").exists());
  }

  @Test
  public void invalidRunFile() {
    execute("run random.txt");
    assertFalse(new File("random.txt").exists());
  }

  @Test
  public void invalidCommandFile() {
    execute("lod random/ noFile");
    assertFalse(new File("random/noFile").exists());
  }
}