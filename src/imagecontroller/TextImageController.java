package imagecontroller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import imagemodel.AdditionalOperations;
import imagemodel.ImageInterface;

/**
 * The Controller class behaves as the facilitator between the user and model structures of the MVC
 * design paradigm. Depending on the input received by the user, this class calls and performs the
 * respective functions and gives the required output.
 */
public class TextImageController implements Controller {

  private Map<String, ImageInterface> images;
  private AdditionalOperations imageOperations;
  private final Scanner scanner;
  private final Appendable output;

  /**
   * This is a constructor for the class TextImageOperations which initializes the hashmap that
   * stores the images generated after each operation, the ImageOperations object, the Readable
   * Scanner input and the Appendable output.
   *
   * @param imageOperations class object.
   * @param input           Readable Scanner object.
   * @param output          Appendable object.
   */
  public TextImageController(AdditionalOperations imageOperations, Readable input,
                             Appendable output) {
    this.images = new HashMap<>();
    this.imageOperations = imageOperations;
    this.scanner = new Scanner(input);
    this.output = output;
  }

  /**
   * Private method that stores the list of supported commands.
   *
   * @return commands as a string.
   */
  private String commands() {
    StringBuilder command = new StringBuilder();
    command.append("Available commands:\n");
    command.append("  load <image-path> <image-name>                                           "
            + "                   - Loads an image\n");
    command.append("  save <image-path> <image-name>                                           "
            + "                   - Saves an image\n");
    command.append("  red-component <image-name> <dest-image-name>                             "
            + "                   - Gets the Red Component of the Image\n");
    command.append("  red-component <image-name> <dest-image-name> split p                     "
            + "                   - Gets the Red Component of the first p% of the Image while "
            + "retaining the rest\n");
    command.append("  red-component <image-name> <mask-image-name> <dest-image-name>              "
            + "                - Applies the red greyscale on the pixels of the image with "
            + "respect to the mask image\n");
    command.append("  green-component <image-name> <dest-image-name>                           "
            + "                   - Gets the Green Component of the Image\n");
    command.append("  green-component <image-name> <dest-image-name> split p                   "
            + "                   - Gets the Green Component of the first p% of the Image while "
            + "retaining the rest\n");
    command.append("  green-component <image-name> <mask-image-name> <dest-image-name>         "
            + "                   - Applies the green greyscale on the pixels of the image with "
            + "respect to the mask image\n");
    command.append("  blue-component <image-name> <dest-image-name>                            "
            + "                   - Gets the Blue Component of the Image\n");
    command.append("  blue-component <image-name> <dest-image-name> split p                    "
            + "                   - Gets the Blue Component of the first p% of the Image while "
            + "retaining the rest\n");
    command.append("  blue-component <image-name> <mask-image-name> <dest-image-name>          "
            + "                   - Applies the blue greyscale on the pixels of the image with "
            + "respect to the mask image\n");
    command.append("  value-component <image-name> <dest-image-name>                           "
            + "                   - Gets the Value Component of the Image\n");
    command.append("  value-component <image-name> <dest-image-name> split p                   "
            + "                   - Gets the Value Component of the first p% of the Image while "
            + "retaining the rest\n");
    command.append("  value-component <image-name> <mask-image-name> <dest-image-name>            "
            + "                - Applies the value visualization on the pixels of the image with "
            + "respect to the mask image\n");
    command.append("  luma-component <image-name> <dest-image-name>                            "
            + "                   - Gets the Luma Component of the Image\n");
    command.append("  luma-component <image-name> <dest-image-name> split p                    "
            + "                   - Gets the Luma Component of the first p% of the Image while "
            + "retaining the rest\n");
    command.append("  luma-component <image-name> <mask-image-name> <dest-image-name>          "
            + "                   - Applies the luma visualization on the pixels of the image with "
            + "respect to the mask image\n");
    command.append("  intensity-component <image-name> <dest-image-name>                       "
            + "                   - Gets the Intensity Component of the Image\n");
    command.append("  intensity-component <image-name> <dest-image-name> split p               "
            + "                   - Gets the Intensity Component of the first p% of the Image "
            + "while retaining the rest\n");
    command.append("  intensity-component <image-name> <mask-image-name> <dest-image-name>        "
            + "                - Applies the intensity visualization on the pixels of the image  "
            + "with respect to the mask image\n");
    command.append("  horizontal-flip <image-name> <dest-image-name>                           "
            + "                   - Flips image horizontally\n");
    command.append("  vertical-flip <image-name> <dest-image-name>                             "
            + "                   - Flips image vertically\n");
    command.append("  brighten <increment> <image-name> <dest-image-name>                      "
            + "                   - Brightens the image\n");
    command.append("  brighten <increment> <image-name> <dest-image-name> mask <mask-image-name>  "
            + "                - Brightens the image depending on the mask image\n");
    command.append("  rgb-split <image-name> <dest-image-name-red> <dest-image-name-green> "
            + "<dest-image-name-blue> - Splits RGB channels\n");
    command.append("  rgb-combine <dest-image-name> <red-image> <green-image> <blue-image>     "
            + "                   - Combines RGB channels\n");
    command.append("  blur <image-name> <dest-image-name>                                      "
            + "                   - Blurs the image\n");
    command.append("  blur <image-name> <dest-image-name> split p                              "
            + "                   - Blurs the first p% of the Image while retaining the rest\n");
    command.append("  blur <image-name> <mask-image-name> <dest-image-name>                    "
            + "                   - Blurs the image according to the mask image\n");
    command.append("  sharpen <image-name> <dest-image-name>                                   "
            + "                   - Sharpens the image>\n");
    command.append("  sharpen <image-name> <dest-image-name> split p                           "
            + "                   - Sharpens the first p% of the Image while retaining the rest\n");
    command.append("  sharpen <image-name> <mask-image-name> <dest-image-name>                 "
            + "                   - Sharpens the image according to the mask image\n");
    command.append("  sepia <image-name> <dest-image-name>                                     "
            + "                   - Produces a sepia tone of the image>\n");
    command.append("  sepia <image-name> <dest-image-name> split p                            "
            + "                    - Produces a sepia tone of the first p% of the Image while "
            + "retaining the rest\n");
    command.append("  sepia <image-name> <mask-image-name> <dest-image-name>                    "
            + "                  - Applies the sepia visualization on the pixels of the image  "
            + "with respect to the mask image\n");
    command.append("  compress percentage <image-name> <dest-image-name>                       "
            + "                   - Compresses the image by the given percentage\n");
    command.append("  histogram <image-name> <dest-image-name>                                 "
            + "                   - Generates a histogram of the given Image\n");
    command.append("  color-correct <image-name> <dest-image-name>                             "
            + "                   - Generates a color corrected version of the given Image\n");
    command.append("  color-correct <image-name> <dest-image-name> split p                     "
            + "                   - Generates a color corrected version of the first p% of "
            + "the given Image\n");
    command.append("  levels-adjust b m w <image-name> <dest-image-name>                       "
            + "                   - Generates a level adjusted version of the given Image as "
            + "per the given black, mid and white points\n");
    command.append("  levels-adjust b m w <image-name> <dest-image-name> split p               "
            + "                   - Generates a level adjusted version of the first p% of the "
            + "given Image as per the given black, mid and white points\n");
    command.append("  downsize <image-name> <dest-image-name> <target-image-width> "
            + "<target-image-height>"
            + "          - Downscales an image to fit target height and width\n");
    command.append("  run <script-file-path>                                                   "
            + "                   - Run commands from a script file\n");
    return command.toString();
  }

  /**
   * Starts an interactive session with the user.
   *
   * @throws IOException upon encountering incorrect input/output.
   */
  public void start(String[] args) throws IOException {
    if (args.length == 1) {
      output.append(commands());
      output.append("Type 'exit' to quit.\n");
    } else if (args.length == 2) {
      runScript(args[1]);
      return;
    } else {
      output.append("Invalid number of arguments\n");
      return;
    }
    while (true) {
      System.out.print(" > ");
      String input = scanner.nextLine().trim();
      if (input.equalsIgnoreCase("exit")) {
        output.append("Exiting this application...\n");
        break;
      }
      runCommand(input);
    }
  }

  /**
   * The runCommand method will execute the command given as input by the user.
   *
   * @param command to be executed.
   */
  public void runCommand(String command) throws IOException {
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
        case "downsize":
          handleDownsizeCommand(tokens);
          break;
        case "run":
          runScript(tokens[1]);
          break;
        default:
          output.append("Unknown command: ").append(cmd).append("\n");
      }
    } catch (Exception e) {
      output.append("Error executing command \n");
    }
  }

  /**
   * The runScript method will run the script file provided by the user. This function will in-turn
   * call the runCommand method for each line in the input script file.
   *
   * @param scriptPath which contains the path of the script file to be executed.
   */
  private void runScript(String scriptPath) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(scriptPath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (!line.isEmpty() && !line.startsWith("#") && !line.startsWith("//")) {
          runCommand(line);
        }
      }
    } catch (FileNotFoundException e) {
      output.append("Script file not found: ").append(e.getMessage()).append("\n");
    } catch (IOException e) {
      output.append("Error reading script file: ").append(e.getMessage()).append("\n");
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

  /**
   * Helper method to calculate the histogram.
   *
   * @param tokens command input.
   * @throws IOException upon encountering incorrect input/output.
   */
  private void handleHistogramCommand(String[] tokens) throws IOException {
    String sourceImage = tokens[1];
    String destImage = tokens[2];
    ImageInterface image = images.get(sourceImage);
    if (image != null) {
      if (tokens.length == 3) {
        ImageInterface histogram = imageOperations.createHistogram(image);
        images.put(destImage, histogram);
        output.append("Histogram created and saved as image at: ").append(destImage).append("\n");
      } else {
        output.append("Invalid Histogram Command\n");
      }
    } else {
      output.append("Image not found: ").append(sourceImage).append("\n");
    }
  }

  /**
   * Helper method to calculate the red-component.
   *
   * @param tokens command input.
   * @throws IOException upon encountering incorrect input/output.
   */
  private void handleRedComponentCommand(String[] tokens) throws IOException {
    ImageInterface redImage;
    String dest;
    if (tokens.length == 3) {
      redImage = imageOperations.visualizeRedComponent(images.get(tokens[1]));
      dest = tokens[2];
    } else if (tokens.length == 5 && tokens[3].equals("split")) {
      int percentage = Integer.parseInt(tokens[4]);
      redImage = imageOperations.splitViewOperation(percentage, images.get(tokens[1]),
              imageOperations::visualizeRedComponent);
      dest = tokens[2];
    } else if (tokens.length == 4) {
      redImage = imageOperations.visualizeRedComponent(images.get(tokens[1]),
              images.get(tokens[2]));
      dest = tokens[3];
    } else {
      output.append("Invalid red-component command\n");
      return;
    }
    images.put(dest, redImage);
    output.append("Red Component Loaded at: ").append(dest).append("\n");
  }

  /**
   * Helper method to calculate the green-component.
   *
   * @param tokens command input.
   * @throws IOException upon encountering incorrect input/output.
   */
  private void handleGreenComponentCommand(String[] tokens) throws IOException {
    ImageInterface greenImage;
    String dest;
    if (tokens.length == 3) {
      greenImage = imageOperations.visualizeGreenComponent(images.get(tokens[1]));
      dest = tokens[2];
    } else if (tokens.length == 5 && tokens[3].equals("split")) {
      int percentage = Integer.parseInt(tokens[4]);
      greenImage = imageOperations.splitViewOperation(percentage, images.get(tokens[1]),
              imageOperations::visualizeGreenComponent);
      dest = tokens[2];
    } else if (tokens.length == 4) {
      greenImage = imageOperations.visualizeGreenComponent(images.get(tokens[1]),
              images.get(tokens[2]));
      dest = tokens[3];
    } else {
      output.append("Invalid green-component command\n");
      return;
    }
    images.put(dest, greenImage);
    output.append("Green Component Loaded at: ").append(dest).append("\n");
  }


  /**
   * Helper method to calculate the blue-component.
   *
   * @param tokens command input.
   * @throws IOException upon encountering incorrect input/output.
   */
  private void handleBlueComponentCommand(String[] tokens) throws IOException {
    ImageInterface blueImage;
    String dest;
    if (tokens.length == 3) {
      blueImage = imageOperations.visualizeBlueComponent(images.get(tokens[1]));
      dest = tokens[2];
    } else if (tokens.length == 5 && tokens[3].equals("split")) {
      int percentage = Integer.parseInt(tokens[4]);
      blueImage = imageOperations.splitViewOperation(percentage, images.get(tokens[1]),
              imageOperations::visualizeBlueComponent);
      dest = tokens[2];
    } else if (tokens.length == 4) {
      blueImage = imageOperations.visualizeBlueComponent(images.get(tokens[1]),
              images.get(tokens[2]));
      dest = tokens[3];
    } else {
      output.append("Invalid blue-component command\n");
      return;
    }
    images.put(dest, blueImage);
    output.append("Blue Component Loaded at: ").append(dest).append("\n");
  }

  /**
   * Helper method to calculate the value-component.
   *
   * @param tokens command input.
   * @throws IOException upon encountering incorrect input/output.
   */
  private void handleValueComponentCommand(String[] tokens) throws IOException {
    ImageInterface valueImage;
    String dest;
    if (tokens.length == 3) {
      valueImage = imageOperations.visualizeValue(images.get(tokens[1]));
      dest = tokens[2];
    } else if (tokens.length == 5 && tokens[3].equals("split")) {
      int percentage = Integer.parseInt(tokens[4]);
      valueImage = imageOperations.splitViewOperation(percentage, images.get(tokens[1]),
              imageOperations::visualizeValue);
      dest = tokens[2];
    } else if (tokens.length == 4) {
      valueImage = imageOperations.visualizeValue(images.get(tokens[1]), images.get(tokens[2]));
      dest = tokens[3];
    } else {
      output.append("Invalid value-component command\n");
      return;
    }
    images.put(dest, valueImage);
    output.append("Value Component Loaded at: ").append(dest).append("\n");
  }

  /**
   * Helper method to calculate the luma-component.
   *
   * @param tokens command input.
   * @throws IOException upon encountering incorrect input/output.
   */
  private void handleLumaComponentCommand(String[] tokens) throws IOException {
    ImageInterface lumaImage;
    String dest;
    if (tokens.length == 3) {
      lumaImage = imageOperations.visualizeLuma(images.get(tokens[1]));
      dest = tokens[2];
    } else if (tokens.length == 5 && tokens[3].equals("split")) {
      int percentage = Integer.parseInt(tokens[4]);
      lumaImage = imageOperations.splitViewOperation(percentage, images.get(tokens[1]),
              imageOperations::visualizeLuma);
      dest = tokens[2];
    } else if (tokens.length == 4) {
      lumaImage = imageOperations.visualizeLuma(images.get(tokens[1]), images.get(tokens[2]));
      dest = tokens[3];
    } else {
      output.append("Invalid luma-component command\n");
      return;
    }
    images.put(dest, lumaImage);
    output.append("Luma Component Loaded at: ").append(dest).append("\n");
  }

  /**
   * Helper method to calculate the intensity-component.
   *
   * @param tokens command input.
   * @throws IOException upon encountering incorrect input/output.
   */
  private void handleIntensityComponentCommand(String[] tokens) throws IOException {
    ImageInterface intensityImage;
    String dest;
    if (tokens.length == 3) {
      intensityImage = imageOperations.visualizeIntensity(images.get(tokens[1]));
      dest = tokens[2];
    } else if (tokens.length == 5 && tokens[3].equals("split")) {
      int percentage = Integer.parseInt(tokens[4]);
      intensityImage = imageOperations.splitViewOperation(percentage, images.get(tokens[1]),
              imageOperations::visualizeIntensity);
      dest = tokens[2];
    } else if (tokens.length == 4) {
      intensityImage = imageOperations.visualizeIntensity(images.get(tokens[1]),
              images.get(tokens[2]));
      dest = tokens[3];
    } else {
      output.append("Invalid intensity-component command\n");
      return;
    }
    images.put(dest, intensityImage);
    output.append("Intensity Component Loaded at: ").append(dest).append("\n");
  }

  /**
   * Helper method to perform level adjusting.
   *
   * @param tokens command input.
   * @throws IOException upon encountering incorrect input/output.
   */
  private void handleLevelsAdjustCommand(String[] tokens) throws IOException {
    ImageInterface adjustedImage;
    if (tokens.length == 6) {
      int b = Integer.parseInt(tokens[1]);
      int m = Integer.parseInt(tokens[2]);
      int w = Integer.parseInt(tokens[3]);
      adjustedImage = imageOperations.levelsAdjust(images.get(tokens[4]), b, m, w);
    } else if (tokens.length == 8 && tokens[tokens.length - 2].equals("split")) {
      int percentage = Integer.parseInt(tokens[7]);
      adjustedImage = imageOperations.splitViewOperation(percentage, images.get(tokens[4]),
              img -> {
                int b = Integer.parseInt(tokens[1]);
                int m = Integer.parseInt(tokens[2]);
                int w = Integer.parseInt(tokens[3]);
                return imageOperations.levelsAdjust(img, b, m, w);
              });
    } else {
      output.append("Invalid level command\n");
      return;
    }
    images.put(tokens[5], adjustedImage);
    output.append("Levels-adjusted image stored as: ").append(tokens[5]).append("\n");
  }

  /**
   * Helper method to perform color correction.
   *
   * @param tokens command input.
   * @throws IOException upon encountering incorrect input/output.
   */
  private void handleColorCorrectCommand(String[] tokens) throws IOException {
    ImageInterface corrected;
    if (tokens.length == 3) {
      corrected = imageOperations.colorCorrect(images.get(tokens[1]));
    } else if (tokens.length == 5 && tokens[tokens.length - 2].equals("split")) {
      int percentage = Integer.parseInt(tokens[4]);
      corrected = imageOperations.splitViewOperation(percentage, images.get(tokens[1]),
              imageOperations::colorCorrect);
    } else {
      output.append("Invalid color correct command\n");
      return;
    }
    images.put(tokens[2], corrected);
    output.append("Color-corrected image stored as: ").append(tokens[2]).append("\n");
  }


  /**
   * Helper method to combine red, green and blue channels.
   *
   * @param tokens command input.
   * @throws IOException upon encountering incorrect input/output.
   */
  private void handleRgbCombine(String[] tokens) throws IOException {
    if (tokens.length == 5) {
      ImageInterface combinedImage = imageOperations.combineRGB(
              images.get(tokens[2]), images.get(tokens[3]), images.get(tokens[4]));
      images.put(tokens[1], combinedImage);
      output.append("RGB Channels combined and stored as: ").append(tokens[1]).append("\n");
      return;
    }
    output.append("Invalid RGB combine command").append("\n");
  }

  /**
   * Helper method to split an image into its red, green and blue channels.
   *
   * @param tokens command input.
   * @throws IOException upon encountering incorrect input/output.
   */
  private void handleRgbSplit(String[] tokens) throws IOException {
    if (tokens.length == 5) {
      ImageInterface[] splitImages = {
              imageOperations.visualizeRedComponent(images.get(tokens[1])),
              imageOperations.visualizeGreenComponent(images.get(tokens[1])),
              imageOperations.visualizeBlueComponent(images.get(tokens[1]))
      };
      images.put(tokens[2], splitImages[0]);
      images.put(tokens[3], splitImages[1]);
      images.put(tokens[4], splitImages[2]);
      output.append("RGB Components split and stored as: ").append(tokens[2]).append(" ").append(
              tokens[3]).append(" ").append(tokens[4]).append("\n");
    } else {
      output.append("Invalid rgb-split command\n");
    }
  }

  /**
   * Helper method to brighten or darken an image.
   *
   * @param tokens command input.
   * @throws IOException upon encountering incorrect input/output.
   */
  private void handleBrightenCommand(String[] tokens) throws IOException {
    ImageInterface outputBrightness;
    String dest;
    int increment = Integer.parseInt(tokens[1]);
    ImageInterface brightenedImage = images.get(tokens[2]);
    if (tokens.length == 4) {
      outputBrightness = imageOperations.applyBrightness(brightenedImage, increment);
      dest = tokens[3];
    } else if (tokens.length == 5) {
      outputBrightness = imageOperations.applyBrightness(brightenedImage, increment,
              images.get(tokens[3]));
      dest = tokens[4];
    } else {
      output.append("Invalid brighten command\n");
      return;
    }
    images.put(dest, outputBrightness);
    output.append("Image brightened and stored as: ").append(dest).append("\n");
  }

  /**
   * Helper method to blur an image.
   *
   * @param tokens command input.
   * @throws IOException upon encountering incorrect input/output.
   */
  private void handleBlurCommand(String[] tokens) throws IOException {
    ImageInterface blurImage;
    String dest;
    if (tokens.length == 3) {
      blurImage = imageOperations.applyBlur(images.get(tokens[1]));
      dest = tokens[2];
    } else if (tokens.length == 5 && tokens[3].equals("split")) {
      int percentage = Integer.parseInt(tokens[4]);
      blurImage = imageOperations.splitViewOperation(percentage, images.get(tokens[1]),
              imageOperations::applyBlur);
      dest = tokens[2];
    } else if (tokens.length == 4) {
      blurImage = imageOperations.applyBlur(images.get(tokens[1]), images.get(tokens[2]));
      dest = tokens[3];
    } else {
      output.append("Invalid blur command\n");
      return;
    }
    images.put(dest, blurImage);
    output.append("Image blurred and stored as: ").append(dest).append("\n");
  }

  /**
   * Helper method to sharpen an image.
   *
   * @param tokens command input.
   * @throws IOException upon encountering incorrect input/output.
   */
  private void handleSharpenCommand(String[] tokens) throws IOException {
    ImageInterface sharpenImage;
    String dest;
    if (tokens.length == 3) {
      sharpenImage = imageOperations.applySharpen(images.get(tokens[1]));
      dest = tokens[2];
    } else if (tokens.length == 5 && tokens[3].equals("split")) {
      int percentage = Integer.parseInt(tokens[4]);
      sharpenImage = imageOperations.splitViewOperation(percentage, images.get(tokens[1]),
              imageOperations::applySharpen);
      dest = tokens[2];
    } else if (tokens.length == 4) {
      sharpenImage = imageOperations.applySharpen(images.get(tokens[1]), images.get(tokens[2]));
      dest = tokens[3];
    } else {
      output.append("Invalid sharpen command\n");
      return;
    }
    images.put(dest, sharpenImage);
    output.append("Image sharpened and stored as: ").append(dest).append("\n");
  }

  /**
   * Helper method to produce a sepia tone of an image.
   *
   * @param tokens command input.
   * @throws IOException upon encountering incorrect input/output.
   */
  private void handleSepiaCommand(String[] tokens) throws IOException {
    ImageInterface sepiaImage;
    String dest;
    if (tokens.length == 3) {
      sepiaImage = imageOperations.applySepia(images.get(tokens[1]));
      dest = tokens[2];
    } else if (tokens.length == 5 && tokens[3].equals("split")) {
      int percentage = Integer.parseInt(tokens[4]);
      sepiaImage = imageOperations.splitViewOperation(percentage, images.get(tokens[1]),
              imageOperations::applySepia);
      dest = tokens[2];
    } else if (tokens.length == 4) {
      sepiaImage = imageOperations.applySepia(images.get(tokens[1]), images.get(tokens[2]));
      dest = tokens[3];
    } else {
      output.append("Invalid sepia command\n");
      return;
    }
    images.put(dest, sepiaImage);
    output.append("Sepia filter added and stored as: ").append(dest).append("\n");
  }

  /**
   * Helper method to compress an image.
   *
   * @param tokens command input.
   * @throws IOException upon encountering incorrect input/output.
   */
  private void handleCompressCommand(String[] tokens) throws IllegalArgumentException,
          IOException {
    if (tokens.length != 4) {
      output.append("Invalid number of arguments\n");
      return;
    }
    int percentage = Integer.parseInt(tokens[1]);
    String sourceImage = tokens[2];
    String destImage = tokens[3];
    ImageInterface image = images.get(sourceImage);
    if (image != null) {
      ImageInterface compressedImage = imageOperations.compressImage(image, percentage);
      images.put(destImage, compressedImage);
      output.append("Image compressed and saved as: ").append(destImage).append("\n");
    } else {
      output.append("Source image not found.\n");
    }
  }

  /**
   * Helper method to load an image.
   *
   * @param tokens command input.
   * @throws IOException upon encountering incorrect input/output.
   */
  private void handleLoadCommand(String[] tokens) throws IOException {
    ImageFormatHandler loader = new ImageHandler();
    try {
      ImageInterface image;
      String extension = getFileExtension(tokens[1]);
      if (extension.equals("ppm")) {
        ImageFormatHandler ppm = new P3PPMHandler();
        image = ppm.loadImage(tokens[1]);
      } else {
        image = loader.loadImage(tokens[1]);
      }
      images.put(tokens[2], image);
    } catch (IOException e) {
      output.append("Error loading image: ").append(e.getMessage()).append("\n");
      return;
    }
    output.append("Image Loaded at: ").append(tokens[2]).append("\n");
  }

  /**
   * Helper method to save an image.
   *
   * @param tokens command input.
   * @throws IOException upon encountering incorrect input/output.
   */
  private void handleSaveCommand(String[] tokens) throws IOException {
    String extension = getFileExtension(tokens[1]);
    ImageFormatHandler saver = new ImageHandler();
    ImageInterface image = images.get(tokens[2]);
    try {
      if (extension.equals("ppm")) {
        ImageFormatHandler ppm = new P3PPMHandler();
        ppm.saveImage(image, tokens[1], "ppm");
      } else {
        saver.saveImage(image, tokens[1], extension);
      }
    } catch (IOException e) {
      output.append("Error saving image: ").append(e.getMessage()).append("\n");
      return;
    }
    output.append("Image saved at: ").append(tokens[1]).append("\n");
  }

  /**
   * Helper method to horizontally flip an image.
   *
   * @param tokens command input.
   * @throws IOException upon encountering incorrect input/output.
   */
  private void handleHorizontalFlip(String[] tokens) throws IOException {
    if (tokens.length == 3) {
      ImageInterface flippedHorizontal =
              imageOperations.applyHorizontalFlip(images.get(tokens[1]));
      images.put(tokens[2], flippedHorizontal);
      output.append("Image flipped Horizontally and stored as: ").append(tokens[2]).append("\n");
    } else {
      output.append("Invalid horizontal flip command\n");
    }
  }

  /**
   * Helper method to vertically flip an image.
   *
   * @param tokens command input.
   * @throws IOException upon encountering incorrect input/output.
   */
  private void handleVerticalFlip(String[] tokens) throws IOException {
    if (tokens.length == 3) {
      ImageInterface flippedVertical = imageOperations.applyVerticalFlip(images.get(tokens[1]));
      images.put(tokens[2], flippedVertical);
      output.append("Image flipped Vertically and stored as: ").append(tokens[2]).append("\n");
    } else {
      output.append("Invalid flip command\n");
    }
  }

  /**
   * This is function that calls the model to DownSize an Image.
   *
   * @param tokens command input.
   * @throws IOException upon entering incorrect input/output.
   */
  private void handleDownsizeCommand(String[] tokens) throws IOException {
    ImageInterface downsizedImage;
    if (tokens.length == 5) {
      downsizedImage = imageOperations.downscaleImage(images.get(tokens[1]),
              Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
    } else {
      output.append("Invalid downsize command\n");
      return;
    }
    images.put(tokens[2], downsizedImage);
    output.append("Downsized image stored as: ").append(tokens[2]).append("\n");
  }
}