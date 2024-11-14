package imagecontroller;

import java.io.File;
import java.io.IOException;

import javax.swing.*;

import imagemodel.AdditionalOperations;
import imagemodel.ImageInterface;
import imageview.MainFrame;

import java.util.HashMap;
import java.util.Map;

public class GUIController implements controllerGUI {

  private Map<String, ImageInterface> images;
  private final AdditionalOperations operations;
  private ImageInterface currentImage;
  private final JTextArea output;
  private final MainFrame mainFrame;  // Added reference to MainFrame

  public GUIController(AdditionalOperations operations, JTextArea output, MainFrame mainFrame) {
    this.operations = operations;
    this.images = new HashMap<>();
    this.output = output;
    this.mainFrame = mainFrame;  // Initialize the reference
  }

  public void runCommand(String command) throws IOException {
    String[] tokens = command.split(" ");
    String cmd = tokens[0].toLowerCase();

    try {
      switch (cmd) {
        case "save":
          handleSaveCommand();
          break;
        case "load":
          handleLoadCommand();
          break;
        case "sharpen":
          handleSharpenCommand();
          break;
        case "sepia":
          handleSepiaCommand();
          break;
        case "red-component":
          handleRedComponent();
          break;
        case "green-component":
          handleGreenComponent();
          break;
        case "blue-component":
          handleBlueComponent();
          break;
        case "blur":
          handleBlurCommand();
          break;
        case "luma-component":
          handleLumaComponent();
          break;
        case "value-component":
          handleValueComponent();
          break;
        case "intensity-component":
          handleIntensityComponent();
          break;
        case "horizontal-flip":
          flipHorizontal();
          break;
        case "vertical-flip":
          flipVertical();
          break;
        default:
          output.append("Unknown command: ");
          output.append(command);
          output.append("\n");
          break;
      }
    } catch (Exception e) {
      output.append("Error executing command\n" + e.getMessage());
    }
  }

  private String getFileExtension(String path) {
    return path.substring(path.lastIndexOf('.') + 1).toLowerCase();
  }

  @Override
  public void handleLoadCommand() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Select an Image File");

    int result = fileChooser.showOpenDialog(null);
    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      String fileName = selectedFile.getName();
      ImageInterface image;
      ImageFormatHandler loader = new ImageHandler();

      try {
        String extension = getFileExtension(fileName);
        if (extension.equals("ppm")) {
          ImageFormatHandler ppm = new P3PPMHandler();
          image = ppm.loadImage(String.valueOf(selectedFile)); // Load as PPM
        } else {
          image = loader.loadImage(String.valueOf(selectedFile)); // Load as generic format
        }

        // Set current image and add it to the images map
        currentImage = image;
        String imageIdentifier = fileName.substring(0, fileName.lastIndexOf('.'));
        images.put("currentImage", currentImage); // Store in map with "currentImage" as key
        images.put(imageIdentifier, image); // Also store with the filename identifier

        // Update the MainFrame to display the loaded image
        mainFrame.displayImage(currentImage);

        // Notify user of successful load
        output.append("Image loaded successfully: ");
        output.append(imageIdentifier);
        output.append("\n");
      } catch (IOException e) {
        output.append("Error loading image: ");
        output.append(e.getMessage());
        output.append("\n");
      }
    } else {
      output.append("Image loading canceled.\n");
    }
  }


  private void handleSaveCommand() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save Image");
    fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PPM Image", "ppm"));
    fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PNG Image", "png"));
    fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("JPEG Image", "jpg", "jpeg"));

    int result = fileChooser.showSaveDialog(null);

    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      String selectedFilePath = selectedFile.getAbsolutePath();
      String extension = getFileExtension(selectedFilePath);

      // If no extension provided, default to .png
      if (extension.isEmpty()) {
        selectedFilePath += ".png";
        extension = "png";
      }

      // Verify that the path is valid and does not contain invalid sequences
      if (selectedFilePath.contains("::{")) {
        output.append("Invalid path selected for saving. Please choose a standard directory.\n");
        return;
      }

      ImageInterface image = images.get("currentImage");
      ImageFormatHandler saver = new ImageHandler();
      try {
        saver.saveImage(image, selectedFilePath, extension);
        output.append("Image saved successfully at: " + selectedFilePath + "\n");
      } catch (IOException e) {
        output.append("Error saving image: " + e.getMessage() + "\n");
      }
    } else {
      output.append("Save operation was canceled.\n");
    }
  }


  private void handleSharpenCommand() {
    if (currentImage == null) {
      output.append("No current image loaded to apply sharpen filter.\n");
      return;
    }

    try {
      currentImage = operations.applySharpen(currentImage);
      images.put("currentImage", currentImage);

      output.append("Sharpen filter applied to current image.\n");
      mainFrame.displayImage(currentImage);
    } catch (Exception e) {
      output.append("Error applying sharpen filter: " + e.getMessage() + "\n");
    }
  }

  private void handleSepiaCommand() {
    if (currentImage == null) {
      output.append("No current image loaded to apply sepia filter.\n");
      return;
    }

    // Apply sepia effect
    try {
      currentImage = operations.applySepia(currentImage);
      images.put("currentImage", currentImage);

      output.append("Sepia filter applied to current image.\n");
      mainFrame.displayImage(currentImage);
    } catch (Exception e) {
      output.append("Error applying sepia filter: " + e.getMessage() + "\n");
    }
  }

  private void handleRedComponent() {

  }

  private void handleGreenComponent() {

  }

  private void handleBlueComponent() {

  }

  private void handleIntensityComponent() {
  }

  private void handleValueComponent() {
  }


  private void flipVertical() {
    if (currentImage == null) {
      output.append("No current image loaded to flip vertically.\n");
      return;
    }

    // Apply vertical flip
    try {
      currentImage = operations.applyVerticalFlip(currentImage);
      images.put("currentImage", currentImage);

      output.append("Image flipped vertically.\n");
      mainFrame.displayImage(currentImage);
    } catch (Exception e) {
      output.append("Error flipping image vertically: " + e.getMessage() + "\n");
    }
  }

  private void flipHorizontal() {
    if (currentImage == null) {
      System.out.println("1");
      output.append("No current image loaded to flip horizontally.\n");
      return;
    }

    System.out.println("2");
    // Apply horizontal flip
    try {
      System.out.println("3");
      currentImage = operations.applyHorizontalFlip(currentImage);
      System.out.println("4");
      images.put("currentImage", currentImage);
      System.out.println("5");
      output.append("Image flipped horizontally.\n");
      System.out.println("6");
      mainFrame.displayImage(currentImage);
    } catch (Exception e) {
      System.out.println("7");
      output.append("Error flipping image horizontally: " + e.getMessage() + "\n");
    }
  }

  private void handleLumaComponent() {
  }

  private void handleBlurCommand() {
    if (currentImage == null) {
      output.append("No current image loaded to apply blur filter.\n");
      return;
    }

    try {
      currentImage = operations.applyBlur(currentImage);
      images.put("currentImage", currentImage);

      output.append("Blur filter applied to current image.\n");
      mainFrame.displayImage(currentImage);
    } catch (Exception e) {
      output.append("Error applying blur filter: " + e.getMessage() + "\n");
    }
  }
}
