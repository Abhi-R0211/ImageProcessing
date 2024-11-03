import java.util.Scanner;

import imagecontroller.TextImageController;
import imagemodel.ExtendedImageOperations;

/**
 * This driver code in the ImageView Package will communicate with the user. The Main Class will
 * send the inputs to the TextImageController.
 */
public class Main {

  /**
   * This is the driver function for the Main Class.
   *
   * @param args takes input of the function that the user wants to perform as a string.
   */
  public static void main(String[] args) {
    ExtendedImageOperations operations = new ExtendedImageOperations();
    TextImageController controller = new TextImageController(operations);

    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to the Image Processing Application!\n");
    System.out.println("Available commands:\n");
    System.out.println("  load <image-path> <image-name>                                    "
            + "                          - Load an image");
    System.out.println("  save <image-path> <image-name>                                    "
            + "                          - Save an image");
    System.out.println("  red-component <image-name> <dest-image-name>                      "
            + "                          - Get the Red Component of the Image");
    System.out.println("  green-component <image-name> <dest-image-name>                    "
            + "                          - Get the Green Component of the Image");
    System.out.println("  blue-component <image-name> <dest-image-name>                     "
            + "                          - Get the Blue Component of the Image");
    System.out.println("  value-component <image-name> <dest-image-name>                    "
            + "                          - Get the Value Component of the Image");
    System.out.println("  luma-component <image-name> <dest-image-name>                     "
            + "                          - Get the Luma Component of the Image");
    System.out.println("  intensity-component <image-name> <dest-image-name>                "
            + "                          - Get the intensity Component of the Image");
    System.out.println("  horizontal-flip <image-name> <dest-image-name>                    "
            + "                          - Flip image horizontally");
    System.out.println("  vertical-flip <image-name> <dest-image-name>                      "
            + "                          - Flip image vertically");
    System.out.println("  brighten <increment> <image-name> <dest-image-name>               "
            + "                          - Brighten the image");
    System.out.println("  rgb-split <image-name> <dest-image-name-red> <dest-image-name-green> "
            + "<dest-image-name-blue> - Split RGB channels");
    System.out.println("  rgb-combine <dest-image-name> <red-image> <green-image> <blue-image>   "
            + "                          - Combine RGB channels");
    System.out.println("  blur <image-name> <dest-image-name>                               "
            + "                          - Blur the image");
    System.out.println("  sharpen <image-name> <dest-image-name>                            "
            + "                          - Sharpen the image>");
    System.out.println("  sepia <image-name> <dest-image-name>                              "
            + "                          - Produce a sepia tone of the image>");
    System.out.println("  run <script-file-path>                                            "
            + "                          - Run commands from a script file");
    System.out.println("\nType 'exit' to quit.");

    while (true) {
      System.out.print(" > ");
      String input = scanner.nextLine().trim();
      if (input.equalsIgnoreCase("exit")) {
        break;
      }
      controller.runCommand(input);
    }

    scanner.close();
    System.out.println("Exiting the application!");
  }
}