package imagecontroller;

import java.io.IOException;

/**
 * Interface defining the contract for GUI-based image processing controllers.
 * Provides methods for loading, saving, and applying various operations to images.
 */
public interface ControllerGui {

  /**
   * Loads an image from the specified file path.
   *
   * @throws IOException if an error occurs during image loading.
   */
  void loadImage() throws IOException;

  /**
   * Saves the current image to the specified file path and format.
   *
   * @throws IOException if an error occurs during image saving.
   */
  void saveImage() throws IOException;

  /**
   * Applies a sharpen filter to the current image.
   */
  void handleSharpenCommand();

  /**
   * Applies a sepia tone filter to the current image.
   */
  void handleSepiaCommand();

  /**
   * Visualizes the red component of the current image.
   */
  void handleRedComponent();

  /**
   * Visualizes the green component of the current image.
   */
  void handleGreenComponent();

  /**
   * Visualizes the blue component of the current image.
   */
  void handleBlueComponent();

  /**
   * Visualizes the intensity component of the current image.
   */
  void handleIntensityComponent();

  /**
   * Visualizes the value component of the current image.
   */
  void handleValueComponent();

  /**
   * Flips the current image vertically.
   */
  void flipVertical();

  /**
   * Flips the current image horizontally.
   */
  void flipHorizontal();

  /**
   * Visualizes the luma component of the current image.
   */
  void handleLumaComponent();

  /**
   * Applies a blur filter to the current image.
   */
  void handleBlurCommand();

  /**
   * Creates a histogram of the current image.
   */
  void createHistogram();

  /**
   * Adjusts the levels of the current image using specified black, mid, and white levels.
   */
  void handleLevelsAdjustCommand();

  /**
   * Applies color correction to the current image.
   */
  void handleColorCorrectCommand();

  /**
   * Compresses the current image by the specified percentage.
   */
  void applyCompression();

  /**
   * Downsizes the current image to the specified width and height.
   */
  void downsizeImage();

  /**
   * This will handle the Split View.
   */
  void handleSplitView();

  /**
   * This function is to for the toggle functionality.
   */
  void toggleSplitView();
}
