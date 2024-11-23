package imageview;

import java.util.List;

import imagecontroller.ControllerGui;
import imagemodel.ImageInterface;

/**
 * This is an interface for the MainFrame.
 */
public interface MainFrameInterface {

  /**
   * This will display an Image on the GUI.
   *
   * @param image the image that will be displayed on the UI.
   */
  void displayImage(ImageInterface image);

  /**
   * This will display the Histogram on the Panel.
   *
   * @param histogramImage The histogram Image to be displayed.
   */
  void displayHistogram(ImageInterface histogramImage);

  /**
   * This function is called to load an Image to the GUI.
   *
   * @return a string which is the location of the file.
   */
  String loadImage();

  /**
   * This will save the final Image to the GUI.
   *
   * @return the string of the position where the file is stored.
   */
  String saveImage();

  /**
   * This sets the controller for use.
   *
   * @param controller is the object of the Interface ControllerGUI.
   */
  void setController(ControllerGui controller);

  /**
   * This will show the Dialog which has the sliders for the values of the Levels Adjust function.
   *
   * @return It will return an Array List which has the values of black, mid and white.
   */
  List<Integer> showLevelsAdjustDialog();

  /**
   * This will show a dialog which takes input of the compression percentage from the user.
   *
   * @return the percentage value.
   */
  int showCompressionDialog();

  /**
   * This will show a dialog which takes input of the new Height and Width for the Compression.
   *
   * @return an Array List which has the new Height and the Width.
   */
  List<Integer> showDownsizeDialog();

  /**
   * This is to toggle between the current and the previous image.
   *
   * @return a boolean if the toggle is accepted or not
   */
  boolean toggleOption();

  /**
   * This will show a slider which takes input of the Split Percentage from the user.
   *
   * @return This will return the split percentage.
   */
  int showSplitDialog();

  /**
   * This will pop a dialog Box when an error is caught by the program.
   *
   * @param errorMessage is a String of what the error is.
   */
  void showErrorDialog(String errorMessage);
}