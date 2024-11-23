package imageview;

import java.util.ArrayList;
import java.util.List;

import imagecontroller.ControllerGui;
import imagemodel.ImageInterface;

/**
 * Mock class for view operations.
 */
public class MainFrameMock implements MainFrameInterface {

  private boolean displayImage;
  private boolean displayHistogram;
  private boolean load;
  private boolean save;
  private boolean level;
  private boolean error;
  private boolean split;
  private boolean downsize;
  private boolean compress;
  private boolean control;
  private boolean blurred;

  /**
   * Class Constructor.
   */
  public MainFrameMock() {
    displayImage = false;
    displayHistogram = false;
    load = false;
    save = false;
    level = false;
    error = false;
    split = false;
    downsize = false;
    compress = false;
    control = false;
    blurred = false;
  }

  /**
   * This is a mock for the displayImage.
   *
   * @param image the image that will be displayed on the UI.
   */
  @Override
  public void displayImage(ImageInterface image) {
    displayImage = true;
  }

  /**
   * Flag for displayImage.
   *
   * @return boolean flag.
   */
  public boolean isDisplayImage() {
    return displayImage;
  }

  /**
   * This is a mock for the displayHistogram.
   *
   * @param histogramImage the histogram that will be displayed on the UI.
   */
  @Override
  public void displayHistogram(ImageInterface histogramImage) {
    displayHistogram = true;
  }

  /**
   * Flag for displayHistogram.
   *
   * @return boolean flag.
   */
  public boolean isDisplayHistogram() {
    return displayHistogram;
  }

  /**
   * This is a mock for loading the image.
   *
   * @return loaded image path.
   */
  @Override
  public String loadImage() {
    load = true;
    return "res/PNG/food.png";
  }

  /**
   * Flag for load.
   *
   * @return boolean flag.
   */
  public boolean isLoad() {
    return load;
  }

  /**
   * This is a mock for saving the Image.
   *
   * @return saved image path.
   */
  @Override
  public String saveImage() {
    save = true;
    return "res/PNG/Save.png";
  }

  /**
   * Flag for save.
   *
   * @return boolean flag.
   */
  public boolean isSave() {
    return save;
  }

  /**
   * This is a mock for setController.
   *
   * @param controller object.
   */
  @Override
  public void setController(ControllerGui controller) {
    addButtons();
    control = true;
  }

  /**
   * Helper method for setController.
   */
  private void addButtons() {
    blurred = true;
    displayHistogram = true;
  }

  /**
   * Flag for setController.
   *
   * @return boolean flag.
   */
  public boolean isSetController() {
    return control;
  }

  /**
   * This is a mock for level adjust.
   *
   * @return ArrayList containing b, m, w values.
   */
  @Override
  public List<Integer> showLevelsAdjustDialog() {
    level = true;
    ArrayList<Integer> temp = new ArrayList<>();
    temp.add(10);
    temp.add(20);
    temp.add(30);
    return temp;
  }

  /**
   * Flag for level adjust.
   *
   * @return boolean flag.
   */
  public boolean isLevelAdjust() {
    return level;
  }

  /**
   * This is a mock for compression.
   *
   * @return dummy value.
   */
  @Override
  public int showCompressionDialog() {
    compress = true;
    return 0;
  }

  /**
   * Flag for compression.
   *
   * @return boolean flag.
   */
  public boolean isCompress() {
    return compress;
  }

  /**
   * This is a mock for downsize.
   *
   * @return ArrayList containing downsized image dimensions.
   */
  @Override
  public List<Integer> showDownsizeDialog() {
    downsize = true;
    ArrayList<Integer> temp = new ArrayList<>();
    temp.add(1);
    temp.add(2);
    return temp;
  }

  /**
   * Flag for toggle.
   *
   * @return boolean flag.
   */
  @Override
  public boolean toggleOption() {
    return true;
  }

  /**
   * This is a mock for split dialog box.
   *
   * @return dummy value.
   */
  @Override
  public int showSplitDialog() {
    split = true;
    return 0;
  }

  /**
   * Flag for downsize.
   *
   * @return boolean flag.
   */
  public boolean isDownsize() {
    return downsize;
  }

  /**
   * Flag for split.
   *
   * @return boolean flag.
   */
  public boolean isSplit() {
    return split;
  }

  /**
   * This is a mock for error dialog box.
   *
   * @param errorMessage is a String of what the error is.
   */
  @Override
  public void showErrorDialog(String errorMessage) {
    error = true;
  }

  /**
   * Flag for error messages.
   *
   * @return boolean flag.
   */
  public boolean isError() {
    return error;
  }
}
