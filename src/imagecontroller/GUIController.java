package imagecontroller;

import java.io.IOException;
import java.util.ArrayList;

import imagemodel.AdditionalOperations;
import imagemodel.ImageInterface;
import imageview.MainFrame;

/**
 * Controller class for managing GUI-based image processing operations.
 * It interacts with the model and then updates the view accordingly.
 */
public class GUIController implements ControllerGui {
  private final AdditionalOperations operations;
  private ImageInterface currentImage;
  private final MainFrame mainFrame;

  /**
   * Constructs a GUIController instance with the specified operations and view.
   *
   * @param operations the image operations model.
   * @param mainFrame  the main frame for displaying images and user interaction.
   */
  public GUIController(AdditionalOperations operations, MainFrame mainFrame) {
    this.operations = operations;
    this.mainFrame = mainFrame;
  }

  /**
   * Extracts the file extension from the given file path.
   *
   * @param path the file path.
   * @return the file extension in lowercase.
   */
  private String getFileExtension(String path) {
    return path.substring(path.lastIndexOf('.') + 1).toLowerCase();
  }

  /**
   * Loads an image from the specified file path and displays it in the view.
   *
   * @throws IOException if an error occurs during loading.
   */
  public void loadImage() throws IOException {
    String filePath = mainFrame.loadImage();
    ImageInterface image;
    ImageFormatHandler loader = new ImageHandler();

    String extension = getFileExtension(filePath);
    if (extension.equals("ppm")) {
      ImageFormatHandler ppm = new P3PPMHandler();
      image = ppm.loadImage(filePath);
    } else {
      image = loader.loadImage(filePath);
    }
    currentImage = image;
    mainFrame.displayImage(currentImage);
  }

  /**
   * Saves the currently loaded image to the specified file path and format.
   *
   * @throws IOException if an error occurs during saving.
   */
  public void saveImage() throws IOException {
    String filePath = mainFrame.saveImage();
    String extension = getFileExtension(filePath);
    ImageFormatHandler saver = new ImageHandler();
    saver.saveImage(currentImage, filePath, extension);
  }


  /**
   * Calls the applySharpen command from the Model to apply the sharpen filter.
   */
  public void handleSharpenCommand() {
    currentImage = operations.applySharpen(currentImage);
    mainFrame.displayImage(currentImage);
  }

  /**
   * Calls the applySepia command from the Model to apply the Sepia filter.
   */
  public void handleSepiaCommand() {
    currentImage = operations.applySepia(currentImage);
    mainFrame.displayImage(currentImage);
  }

  /**
   * Calls the Red Component command from the Model to extract the Red Component.
   */
  public void handleRedComponent() {
    currentImage = operations.visualizeRedComponent(currentImage);
    mainFrame.displayImage(currentImage);
  }

  /**
   * Calls the Green Component command from the Model to extract the Green Component.
   */
  public void handleGreenComponent() {
    currentImage = operations.visualizeGreenComponent(currentImage);
    mainFrame.displayImage(currentImage);
  }

  /**
   * Calls the Blue Component command from the Model to extract the Blue Component.
   */
  public void handleBlueComponent() {
    currentImage = operations.visualizeBlueComponent(currentImage);
    mainFrame.displayImage(currentImage);
  }

  /**
   * Calls the Intensity Component command from the Model to extract the Intensity Component.
   */
  public void handleIntensityComponent() {
    currentImage = operations.visualizeIntensity(currentImage);
    mainFrame.displayImage(currentImage);
  }

  /**
   * Calls the Value Component command from the Model to extract the Value Component.
   */
  public void handleValueComponent() {
    currentImage = operations.visualizeValue(currentImage);
    mainFrame.displayImage(currentImage);
  }

  /**
   * Calls the Flip Vertical command from the Model to flip the image vertically.
   */
  public void flipVertical() {
    currentImage = operations.applyVerticalFlip(currentImage);
    mainFrame.displayImage(currentImage);
  }

  /**
   * Calls the Flip Horizontal command from the Model to flip the image Horizontally.
   */
  public void flipHorizontal() {
    currentImage = operations.applyHorizontalFlip(currentImage);
    mainFrame.displayImage(currentImage);
  }

  /**
   * Calls the Luma Component command from the Model to extract the Luma Component.
   */
  public void handleLumaComponent() {
    currentImage = operations.visualizeLuma(currentImage);
    mainFrame.displayImage(currentImage);
  }

  /**
   * Calls the Luma Component command from the Model to extract the Luma Component.
   */
  public void handleBlurCommand() {
    currentImage = operations.applyBlur(currentImage);
    mainFrame.displayImage(currentImage);
  }

  /**
   * Call the createHistogram command to create a Histogram and then displays on the view.
   */
  public void createHistogram() {
    ImageInterface histogram = operations.createHistogram(currentImage);
    mainFrame.displayHistogram(histogram);
  }

  /**
   * Calls the levels adjust command and then displays it on the view.
   */
  public void handleLevelsAdjustCommand() {
    ArrayList<Integer> value = MainFrame.showLevelsAdjustDialog();
    if (value == null) {
      System.out.println("User canceled the dialog.");
      return;
    }
    currentImage = operations.levelsAdjust(currentImage, value.get(0), value.get(1), value.get(2));
    mainFrame.displayImage(currentImage);
  }

  /**
   * Applies color correction to the current image and displays the result.
   */
  public void handleColorCorrectCommand() {
    currentImage = operations.colorCorrect(currentImage);
    mainFrame.displayImage(currentImage);
  }

  /**
   * Compresses the current image by the specified percentage and displays the result.
   */
  public void applyCompression() {
    int percentage = MainFrame.showCompressionDialog();
    currentImage = operations.compressImage(currentImage, percentage);
    mainFrame.displayImage(currentImage);
  }

  /**
   * Downsizes the current image to the specified width and height, and displays the result.
   */
  public void downsizeImage() {
    ArrayList<Integer> dimension = MainFrame.showDownsizeDialog();
    ImageInterface downsizedImage = operations.downscaleImage(currentImage,
            dimension.get(0), dimension.get(1));
    mainFrame.displayImage(downsizedImage);
  }

}