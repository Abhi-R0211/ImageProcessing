package imageview;

import java.util.ArrayList;

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
   * Display mock.
   *
   * @param image the image that will be displayed on the UI.
   */
  @Override
  public void displayImage(ImageInterface image) {
    displayImage = true;
  }

  /**
   * Flag for display.
   *
   * @return boolean flag.
   */
  public boolean isDisplayImage() {
    return displayImage;
  }

  /**
   * Histogram mock.
   *
   * @param histogramImage the histogram that will be displayed on the UI.
   */
  @Override
  public void displayHistogram(ImageInterface histogramImage) {
    displayHistogram = true;
  }

  /**
   * Flag for histogram.
   *
   * @return boolean flag.
   */
  public boolean isDisplayHistogram() {
    return displayHistogram;
  }

  /**
   * Loading mock.
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
   * Saving mock.
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
   * setController mock.
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
   * Mock for level adjust.
   *
   * @return ArrayList containing b, m, w values.
   */
  @Override
  public ArrayList<Integer> showLevelsAdjustDialog() {
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
   * Mock for compression.
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
   * Mock for downsize.
   *
   * @return ArrayList containing downsized image dimensions.
   */
  @Override
  public ArrayList<Integer> showDownsizeDialog() {
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
   * Mock for split dialog box.
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
   * Mock for error dialog box.
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
