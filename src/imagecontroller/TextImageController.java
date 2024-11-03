package imagecontroller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import imagemodel.ExtendedImageOperations;
import imagemodel.Image;

/**
 * The Controller class behaves as the facilitator between the view and model structures of the MVC
 * design paradigm. Depending on the input received by the user, this class calls and performs the
 * respective functions and gives the required output.
 */
public class TextImageController implements Controller {

  private Map<String, Image> images;
  private ExtendedImageOperations imageOperations;

  /**
   * This is a constructor for the class TextImageOperations which initializes the hashmap which
   * stores the images generated after each operation, and the ImageOperations object.
   *
   * @param imageOperations class object.
   */
  public TextImageController(ExtendedImageOperations imageOperations) {
    this.images = new HashMap<>();
    this.imageOperations = imageOperations;
  }

  /**
   * The runCommand method will execute the command given as input by the user.
   *
   * @param command to be executed.
   */
  public void runCommand(String command) {
    String[] tokens = command.split(" ");
    String cmd = tokens[0].toLowerCase();

    try {
      switch (cmd) {
        case "load":
          handleLoadCommand(tokens);
          break;
        case "save":
          handleSaveCommand(tokens);
          break;
        case "red-component":
          handleRedComponentCommand(tokens);
          break;
        case "green-component":
          handleGreenComponentCommand(tokens);
          break;
        case "blue-component":
          handleBlueComponentCommand(tokens);
          break;
        case "value-component":
          handleValueComponentCommand(tokens);
          break;
        case "luma-component":
          handleLumaComponentCommand(tokens);
          break;
        case "intensity-component":
          handleIntensityComponentCommand(tokens);
          break;
        case "horizontal-flip":
          handleHorizontalFlip(tokens);
          break;
        case "vertical-flip":
          handleVerticalFlip(tokens);
          break;
        case "brighten":
          handleBrightenCommand(tokens);
          break;
        case "rgb-split":
          handleRgbSplit(tokens);
          break;
        case "rgb-combine":
          handleRgbCombine(tokens);
          break;
        case "blur":
          handleBlurCommand(tokens);
          break;
        case "sharpen":
          handleSharpenCommand(tokens);
          break;
        case "sepia":
          handleSepiaCommand(tokens);
          break;
        case "compress":
          handleCompressCommand(tokens);
          break;
        case "histogram":
          handleHistogramCommand(tokens);
          break;
        case "color-correct":
          handleColorCorrectCommand(tokens);
          break;
        case "levels-adjust":
          handleLevelsAdjustCommand(tokens);
          break;
        case "run":
          runScript(tokens[1]);
          break;
        default:
          System.out.println("Unknown command: " + cmd);
      }
    } catch (Exception e) {
      System.out.println("Error executing command ");
    }
  }

  /**
   * The runScript method will run the script file provided by the user. This function will in-turn
   * call the runCommand method for each line in the input script file.
   *
   * @param scriptPath which contains the path of the script file to be executed.
   */
  private void runScript(String scriptPath) {
    try (BufferedReader reader = new BufferedReader(new FileReader(scriptPath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (!line.isEmpty() && !line.startsWith("#") && !line.startsWith("//")) {
          runCommand(line);
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("Script file not found: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("Error reading script file: " + e.getMessage());
    }
  }

  /**
   * The getFileExtension method will return the extension of the image.
   *
   * @param path of the image whose extension is to be calculated.
   * @return the extension of the input file as a string.
   */
  private String getFileExtension(String path) {
    return path.substring(path.lastIndexOf('.') + 1).toLowerCase();
  }


  private void handleHistogramCommand(String[] tokens) {
    String sourceImage = tokens[1];
    String destImage = tokens[2];
    Image image = images.get(sourceImage);
    if (image != null) {
      Image histogram = imageOperations.createHistogram(image);
      images.put(destImage, histogram);
      System.out.println("Histogram created and saved as image at: " + destImage);
    } else {
      System.out.println("Source image not found.");
    }
  }

  private void handleRedComponentCommand(String[] tokens) {
    Image redImage;
    if (tokens.length == 3) {
      redImage = imageOperations.visualizeRedComponent(images.get(tokens[1]));
    } else if (tokens.length == 5) {
      redImage = imageOperations.splitViewOperation(tokens, images.get(tokens[1]));
    } else {
      throw new IllegalArgumentException("Invalid red-component command");
    }
    images.put(tokens[2], redImage);
    System.out.println("Red Component Loaded at: " + tokens[2]);
  }

  private void handleGreenComponentCommand(String[] tokens) {
    Image greenImage;
    if (tokens.length == 3) {
      greenImage = imageOperations.visualizeGreenComponent(images.get(tokens[1]));
    } else if (tokens.length == 5) {
      greenImage = imageOperations.splitViewOperation(tokens, images.get(tokens[1]));
    } else {
      throw new IllegalArgumentException("Invalid green-component command");
    }
    images.put(tokens[2], greenImage);
    System.out.println("Green Component Loaded at: " + tokens[2]);
  }

  private void handleBlueComponentCommand(String[] tokens) {
    Image blueImage;
    if (tokens.length == 3) {
      blueImage = imageOperations.visualizeBlueComponent(images.get(tokens[1]));
    } else if (tokens.length == 5) {
      blueImage = imageOperations.splitViewOperation(tokens, images.get(tokens[1]));
    } else {
      throw new IllegalArgumentException("Invalid blue-component command");
    }
    images.put(tokens[2], blueImage);
    System.out.println("Blue Component Loaded at: " + tokens[2]);
  }

  private void handleValueComponentCommand(String[] tokens) {
    Image valueImage;
    if (tokens.length == 3) {
      valueImage = imageOperations.visualizeValue(images.get(tokens[1]));
    } else if (tokens.length == 5) {
      valueImage = imageOperations.splitViewOperation(tokens, images.get(tokens[1]));
    } else {
      throw new IllegalArgumentException("Invalid value-component command");
    }
    images.put(tokens[2], valueImage);
    System.out.println("Value Component Loaded at: " + tokens[2]);
  }

  private void handleLumaComponentCommand(String[] tokens) {
    Image lumaImage;
    if (tokens.length == 3) {
      lumaImage = imageOperations.visualizeLuma(images.get(tokens[1]));
    } else if (tokens.length == 5) {
      lumaImage = imageOperations.splitViewOperation(tokens, images.get(tokens[1]));
    } else {
      throw new IllegalArgumentException("Invalid luma-component command");
    }
    images.put(tokens[2], lumaImage);
    System.out.println("Luma Component Loaded at: " + tokens[2]);
  }

  private void handleIntensityComponentCommand(String[] tokens) {
    Image intensityImage;
    if (tokens.length == 3) {
      intensityImage = imageOperations.visualizeIntensity(images.get(tokens[1]));
    } else if (tokens.length == 5) {
      intensityImage = imageOperations.splitViewOperation(tokens, images.get(tokens[1]));
    } else {
      throw new IllegalArgumentException("Invalid intensity-component command");
    }
    images.put(tokens[2], intensityImage);
    System.out.println("Intensity Component Loaded at: " + tokens[2]);
  }

  private void handleLevelsAdjustCommand(String[] tokens) {
    Image adjustedImage;
    if (tokens.length == 6) {
      int b = Integer.parseInt(tokens[1]);
      int m = Integer.parseInt(tokens[2]);
      int w = Integer.parseInt(tokens[3]);
      adjustedImage = imageOperations.levelsAdjust(images.get(tokens[4]), b, m, w);
    } else if (tokens.length == 8) {
      adjustedImage = imageOperations.splitViewOperation(tokens, images.get(tokens[1]));
    } else {
      throw new IllegalArgumentException("Invalid level command");
    }
    images.put(tokens[5], adjustedImage);
    System.out.println("Levels-adjusted image stored at: " + tokens[5]);
  }

  private void handleColorCorrectCommand(String[] tokens) {
    Image corrected;
    if (tokens.length == 3) {
      corrected = imageOperations.colorCorrect(images.get(tokens[1]));
    } else if (tokens.length == 5) {
      corrected = imageOperations.splitViewOperation(tokens, images.get(tokens[1]));
    } else {
      throw new IllegalArgumentException("Invalid color correct command");
    }
    images.put(tokens[2], corrected);
    System.out.println("Color-corrected image stored at: " + tokens[2]);
  }

  private void handleRgbCombine(String[] tokens) {
    Image combinedImage = imageOperations.combineRGB(
            images.get(tokens[2]), images.get(tokens[3]), images.get(tokens[4]));
    images.put(tokens[1], combinedImage);
    System.out.println("RGB Channels combined and stored at :" + tokens[1]);
  }

  private void handleRgbSplit(String[] tokens) {
    Image[] splitImages = {
            imageOperations.visualizeRedComponent(images.get(tokens[1])),
            imageOperations.visualizeGreenComponent(images.get(tokens[1])),
            imageOperations.visualizeBlueComponent(images.get(tokens[1]))
    };
    images.put(tokens[2], splitImages[0]);
    images.put(tokens[3], splitImages[1]);
    images.put(tokens[4], splitImages[2]);
    System.out.println("RGB Components split and stored at "
            + tokens[2] + " " + tokens[3] + " " + tokens[4]);
  }

  private void handleBrightenCommand(String[] tokens) {
    int increment = Integer.parseInt(tokens[1]);
    Image brightenedImage = images.get(tokens[2]);
    Image outputBrightness = imageOperations.applyBrightness(brightenedImage, increment);
    images.put(tokens[3], outputBrightness);
    System.out.println("Image brightened and stored at " + tokens[3]);
  }

  private void handleBlurCommand(String[] tokens) {
    Image blurImage;
    if (tokens.length == 3) {
      blurImage = imageOperations.applyBlur(images.get(tokens[1]));
    } else if (tokens.length == 5) {
      blurImage = imageOperations.splitViewOperation(tokens, images.get(tokens[1]));
    } else {
      throw new IllegalArgumentException("Invalid blur command");
    }
    images.put(tokens[2], blurImage);
    System.out.println("Image blurred and stored at " + tokens[2]);
  }

  private void handleSharpenCommand(String[] tokens) {
    Image sharpenImage;
    if (tokens.length == 3) {
      sharpenImage = imageOperations.applySharpen(images.get(tokens[1]));
    } else if (tokens.length == 5) {
      sharpenImage = imageOperations.splitViewOperation(tokens, images.get(tokens[1]));
    } else {
      throw new IllegalArgumentException("Invalid sharpen command");
    }
    images.put(tokens[2], sharpenImage);
    System.out.println("Image sharpened and stored at " + tokens[2]);
  }

  private void handleSepiaCommand(String[] tokens) {
    Image sepiaImage;
    if (tokens.length == 3) {
      sepiaImage = imageOperations.applySepia(images.get(tokens[1]));
    } else if (tokens.length == 5) {
      sepiaImage = imageOperations.splitViewOperation(tokens, images.get(tokens[1]));
    } else {
      throw new IllegalArgumentException("Invalid sepia command");
    }
    images.put(tokens[2], sepiaImage);
    System.out.println("Sepia filter added and stored at " + tokens[2]);
  }

  private void handleCompressCommand(String[] tokens) throws IllegalArgumentException {
    if (tokens.length != 4) {
      throw new IllegalArgumentException("Invalid number of arguments");
    }
    int percentage = Integer.parseInt(tokens[1]);
    String sourceImage = tokens[2];
    String destImage = tokens[3];
    Image image = images.get(sourceImage);
    if (image != null) {
      double threshold = percentage / 100.0;
      Image compressedImage = imageOperations.compressImage(image, threshold);
      images.put(destImage, compressedImage);
      System.out.println("Image compressed and saved as: " + destImage);
    } else {
      System.out.println("Source image not found.");
    }
  }

  private void handleLoadCommand(String[] tokens) throws IOException {
    ImageHandler loader = new ImageHandler();
    try {
      Image image;
      String extension = getFileExtension(tokens[1]);
      if (extension.equals("ppm")) {
        P3PPMHandler ppm = new P3PPMHandler();
        image = ppm.loadImage(tokens[1]);
      } else {
        image = loader.loadImage(tokens[1]);
      }
      images.put(tokens[2], image);
    } catch (IOException e) {
      throw new IOException("Error loading image: " + e.getMessage());
    }
    System.out.println("Image Loaded at: " + tokens[2]);
  }

  private void handleSaveCommand(String[] tokens) {
    String extension = getFileExtension(tokens[1]);
    ImageHandler saver = new ImageHandler();
    Image image = images.get(tokens[2]);
    try {
      if (extension.equals("ppm")) {
        P3PPMHandler ppm = new P3PPMHandler();
        ppm.saveImage(image, tokens[1], "ppm");
      } else {
        saver.saveImage(image, tokens[1], extension);
      }
    } catch (IOException e) {
      System.out.println("Error saving image: " + e.getMessage());
      return;
    }
    System.out.println("Image saved at: " + tokens[1]);
  }

  private void handleHorizontalFlip(String[] tokens) {
    Image flippedHorizontal = imageOperations.applyHorizontalFlip(images.get(tokens[1]));
    images.put(tokens[2], flippedHorizontal);
    System.out.println("Image flipped Horizontally and stored at " + tokens[2]);
  }

  private void handleVerticalFlip(String[] tokens) {
    Image flippedVertical = imageOperations.applyVerticalFlip(images.get(tokens[1]));
    images.put(tokens[2], flippedVertical);
    System.out.println("Image flipped Vertically and stored at " + tokens[2]);
  }

  @Override
  public Image getImageFromMap(String str) {
    return images.get(str);
  }
}