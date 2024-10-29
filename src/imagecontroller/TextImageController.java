package imagecontroller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import imagemodel.ImageOperations;
import imagemodel.Image;

/**
 * The Controller class behaves as the facilitator between the view and model structures of the MVC
 * design paradigm. Depending on the input received by the user, this class calls and performs the
 * respective functions and gives the required output.
 */
public class TextImageController implements Controller {

  private Map<String, Image> images;
  private ImageOperations imageOperations;

  /**
   * This is a constructor for the class TextImageOperations which initializes the hashmap which
   * stores the images generated after each operation, and the ImageOperations object.
   *
   * @param imageOperations class object.
   */
  public TextImageController(ImageOperations imageOperations) {
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
          break;

        case "save":
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
          break;

        case "red-component":
          Image redImage = imageOperations.visualizeRedComponent(images.get(tokens[1]));
          images.put(tokens[2], redImage);
          System.out.println("Red Component Loaded at: " + tokens[2]);
          break;

        case "green-component":
          Image greenImage = imageOperations.visualizeGreenComponent(images.get(tokens[1]));
          images.put(tokens[2], greenImage);
          System.out.println("Green Component Loaded at: " + tokens[2]);
          break;

        case "blue-component":
          Image blueImage = imageOperations.visualizeBlueComponent(images.get(tokens[1]));
          images.put(tokens[2], blueImage);
          System.out.println("Blue Component Loaded at: " + tokens[2]);
          break;

        case "value-component":
          Image valueImage = imageOperations.visualizeValue(images.get(tokens[1]));
          images.put(tokens[2], valueImage);
          System.out.println("Value Component Loaded at: " + tokens[2]);
          break;

        case "luma-component":
          Image lumaImage = imageOperations.visualizeLuma(images.get(tokens[1]));
          images.put(tokens[2], lumaImage);
          System.out.println("Luma Component Loaded at: " + tokens[2]);
          break;

        case "intensity-component":
          Image intensityImage = imageOperations.visualizeIntensity(images.get(tokens[1]));
          images.put(tokens[2], intensityImage);
          System.out.println("Intensity Component Loaded at: " + tokens[2]);
          break;

        case "horizontal-flip":
          Image flippedHorizontal = imageOperations.applyHorizontalFlip(images.get(tokens[1]));
          images.put(tokens[2], flippedHorizontal);
          System.out.println("Image flipped Horizontally and stored at " + tokens[2]);
          break;

        case "vertical-flip":
          Image flippedVertically = imageOperations.applyVerticalFlip(images.get(tokens[1]));
          images.put(tokens[2], flippedVertically);
          System.out.println("Image flipped Vertically and stored at " + tokens[2]);
          break;

        case "brighten":
          int increment = Integer.parseInt(tokens[1]);
          Image brightenedImage = images.get(tokens[2]);
          Image outputBrightness = imageOperations.applyBrightness(brightenedImage, increment);
          images.put(tokens[3], outputBrightness);
          System.out.println("Image brightened and stored at " + tokens[3]);
          break;

        case "rgb-split":
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
          break;

        case "rgb-combine":
          Image combinedImage = imageOperations.combineRGB(
                  images.get(tokens[2]), images.get(tokens[3]), images.get(tokens[4]));
          images.put(tokens[1], combinedImage);
          System.out.println("RGB Channels combined and stored at :" + tokens[1]);
          break;

        case "blur":
          Image blurredImage = images.get(tokens[1]);
          Image outputBlur = imageOperations.applyBlur(blurredImage);
          images.put(tokens[2], outputBlur);
          System.out.println("Image made blur and stored at " + tokens[2]);
          break;

        case "sharpen":
          Image sharpenedImage = images.get(tokens[1]);
          Image outputSharpen = imageOperations.applySharpen(sharpenedImage);
          images.put(tokens[2], outputSharpen);
          System.out.println("Image sharpened and stored at " + tokens[2]);
          break;

        case "sepia":
          Image sepiaImage = images.get(tokens[1]);
          Image outputSepia = imageOperations.applySepia(sepiaImage);
          images.put(tokens[2], outputSepia);
          System.out.println("Sepia filter added and stored at " + tokens[2]);
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

  @Override
  public Image getImageFromMap(String str) {
    return images.get(str);
  }
}