package imagecontroller;

import java.io.IOException;
import java.util.ArrayList;

import imagemodel.AdditionalOperations;
import imagemodel.ImageInterface;
import imageview.MainFrameInterface;

/**
 * Controller class for managing GUI-based image processing operations.
 * It interacts with the model and then updates the view accordingly.
 */
public class GUIController implements ControllerGui {
  private final AdditionalOperations operations;
  private ImageInterface currentImage;
  private ImageInterface displayImage;
  private ImageInterface previousImage;
  private ImageInterface histogram;
  private ImageInterface previousHistogram;
  private final MainFrameInterface mainFrame;
  int percentage = 0;

  /**
   * Constructs a GUIController instance with the specified operations and view.
   *
   * @param operations the image operations model.
   * @param mainFrame  the main frame for displaying images and user interaction.
   */
  public GUIController(AdditionalOperations operations, MainFrameInterface mainFrame) {
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
    previousImage = currentImage;
    if (percentage == 0) {
      currentImage = operations.applySharpen(currentImage);
      mainFrame.displayImage(currentImage);
    } else {
      displayImage = operations.splitViewOperation(percentage, currentImage,
              operations::applySharpen);
      mainFrame.displayImage(displayImage);
      currentImage = operations.applySharpen(currentImage);
    }
  }

  /**
   * Calls the applySepia command from the Model to apply the Sepia filter.
   */
  public void handleSepiaCommand() {
    previousImage = currentImage;
    if (percentage == 0) {
      currentImage = operations.applySepia(currentImage);
      mainFrame.displayImage(currentImage);
    } else {
      displayImage = operations.splitViewOperation(percentage, currentImage,
              operations::applySepia);
      mainFrame.displayImage(displayImage);
      currentImage = operations.applySepia(currentImage);
    }
  }

  /**
   * Calls the Red Component command from the Model to extract the Red Component.
   */
  public void handleRedComponent() {
    previousImage = currentImage;
    if (percentage == 0) {
      currentImage = operations.visualizeRedComponent(currentImage);
      mainFrame.displayImage(currentImage);
    } else {
      displayImage = operations.splitViewOperation(percentage, currentImage,
              operations::visualizeRedComponent);
      mainFrame.displayImage(displayImage);
      currentImage = operations.visualizeRedComponent(currentImage);
    }
  }

  /**
   * Calls the Green Component command from the Model to extract the Green Component.
   */
  public void handleGreenComponent() {
    previousImage = currentImage;
    if (percentage == 0) {
      currentImage = operations.visualizeGreenComponent(currentImage);
      mainFrame.displayImage(currentImage);
    } else {
      displayImage = operations.splitViewOperation(percentage, currentImage,
              operations::visualizeGreenComponent);
      mainFrame.displayImage(displayImage);
      currentImage = operations.visualizeGreenComponent(currentImage);
    }
  }

  /**
   * Calls the Blue Component command from the Model to extract the Blue Component.
   */
  public void handleBlueComponent() {
    previousImage = currentImage;
    if (percentage == 0) {
      currentImage = operations.visualizeBlueComponent(currentImage);
      mainFrame.displayImage(currentImage);
    } else {
      displayImage = operations.splitViewOperation(percentage, currentImage,
              operations::visualizeBlueComponent);
      mainFrame.displayImage(displayImage);
      currentImage = operations.visualizeBlueComponent(currentImage);
    }

  }

  /**
   * Calls the Intensity Component command from the Model to extract the Intensity Component.
   */
  public void handleIntensityComponent() {
    previousImage = currentImage;
    if (percentage == 0) {
      currentImage = operations.visualizeIntensity(currentImage);
      mainFrame.displayImage(currentImage);
    } else {
      displayImage = operations.splitViewOperation(percentage, currentImage,
              operations::visualizeIntensity);
      mainFrame.displayImage(displayImage);
      currentImage = operations.visualizeIntensity(currentImage);
    }

  }

  /**
   * Calls the Value Component command from the Model to extract the Value Component.
   */
  public void handleValueComponent() {
    previousImage = currentImage;
    if (percentage == 0) {
      currentImage = operations.visualizeValue(currentImage);
      mainFrame.displayImage(currentImage);
    } else {
      displayImage = operations.splitViewOperation(percentage, currentImage,
              operations::visualizeValue);
      mainFrame.displayImage(displayImage);
      currentImage = operations.visualizeValue(currentImage);
    }
  }

  /**
   * Calls the Flip Vertical command from the Model to flip the image vertically.
   */
  public void flipVertical() {
    previousImage = currentImage;
    currentImage = operations.applyVerticalFlip(currentImage);
    mainFrame.displayImage(currentImage);
  }

  /**
   * Calls the Flip Horizontal command from the Model to flip the image Horizontally.
   */
  public void flipHorizontal() {
    previousImage = currentImage;
    currentImage = operations.applyHorizontalFlip(currentImage);
    mainFrame.displayImage(currentImage);
  }

  /**
   * Calls the Luma Component command from the Model to extract the Luma Component.
   */
  public void handleLumaComponent() {
    previousImage = currentImage;
    if (percentage == 0) {
      currentImage = operations.visualizeLuma(currentImage);
      mainFrame.displayImage(currentImage);
    } else {
      displayImage = operations.splitViewOperation(percentage, currentImage,
              operations::visualizeLuma);
      mainFrame.displayImage(displayImage);
      currentImage = operations.visualizeLuma(currentImage);
    }
  }

  /**
   * Calls the Blur Component command from the Model to extract the Blur Component.
   */
  public void handleBlurCommand() {
    previousImage = currentImage;
    if (percentage == 0) {
      currentImage = operations.applyBlur(currentImage);
      mainFrame.displayImage(currentImage);
    } else {
      displayImage = operations.splitViewOperation(percentage, currentImage,
              operations::applyBlur);
      mainFrame.displayImage(displayImage);
      currentImage = operations.applyBlur(currentImage);
    }
  }

  /**
   * Call the createHistogram command to create a Histogram and then displays on the view.
   */
  public void createHistogram() {
    previousHistogram = histogram;
    histogram = operations.createHistogram(currentImage);
    mainFrame.displayHistogram(histogram);
  }

  /**
   * Calls the levels adjust command and then displays it on the view.
   */
  public void handleLevelsAdjustCommand() {
    try {
      ArrayList<Integer> value = mainFrame.showLevelsAdjustDialog();
      if (value == null) {
        System.out.println("User canceled the dialog.");
        return; // User canceled the dialog
      }
      previousImage = currentImage;
      if (percentage == 0) {
        currentImage = operations.levelsAdjust(currentImage, value.get(0),
                value.get(1), value.get(2));
        mainFrame.displayImage(currentImage);
      } else {
        displayImage = operations.splitViewOperation(percentage, currentImage,
          img -> operations.levelsAdjust(img, value.get(0), value.get(1), value.get(2)));
        mainFrame.displayImage(displayImage);
        currentImage = operations.levelsAdjust(currentImage,
                value.get(0), value.get(1), value.get(2));
      }
    } catch (IllegalArgumentException e) {
      mainFrame.showErrorDialog("Error: " + e.getMessage());
    }
  }


  /**
   * This will be used to get the percentage of the Split View.
   */
  public void handleSplitView() {
    if (currentImage == null) {
      mainFrame.showErrorDialog("Error: Image is null!");
    } else {
      percentage = mainFrame.showSplitDialog();
    }
  }

  /**
   * Applies color correction to the current image and displays the result.
   */
  public void handleColorCorrectCommand() {
    previousImage = currentImage;
    if (percentage == 0) {
      currentImage = operations.colorCorrect(currentImage);
      mainFrame.displayImage(currentImage);
    } else {
      displayImage = operations.splitViewOperation(percentage, currentImage,
              operations::colorCorrect);
      mainFrame.displayImage(displayImage);
      currentImage = operations.colorCorrect(currentImage);
    }
  }

  /**
   * Compresses the current image by the specified percentage and displays the result.
   */
  public void applyCompression() {
    int percentage = mainFrame.showCompressionDialog();
    currentImage = operations.compressImage(currentImage, percentage);
    mainFrame.displayImage(currentImage);
  }

  /**
   * Downsizes the current image to the specified width and height, and displays the result.
   */
  public void downsizeImage() {
    try {
      ArrayList<Integer> dimension = mainFrame.showDownsizeDialog();
      if (dimension == null || dimension.isEmpty()) {
        return; // User canceled the dialog
      }
      ImageInterface downsizedImage = operations.downscaleImage(currentImage,
              dimension.get(0), dimension.get(1));
      mainFrame.displayImage(downsizedImage);
    } catch (IllegalArgumentException e) {
      mainFrame.showErrorDialog("Error: " + e.getMessage());
    }
  }

  /**
   * This is used to toggle between the current Image and the previous Image.
   */
  public void toggleSplitView() {
    boolean toggle = mainFrame.toggleOption();
    if (toggle) {
      currentImage = previousImage;
      mainFrame.displayImage(previousImage);
      mainFrame.displayHistogram(previousHistogram);
    }
  }
}