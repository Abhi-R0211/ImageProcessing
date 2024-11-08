import java.io.IOException;
import java.io.InputStreamReader;

import imagecontroller.Controller;
import imagecontroller.TextImageController;
import imagemodel.ExtendedImageOperations;
import imagemodel.ExtendedOperations;

/**
 * This driver code will communicate with the user. The Main Class will send the inputs to
 * the TextImageController.
 */
public class Main {

  /**
   * This is the driver function for the Main Class.
   *
   * @param args takes input of the function that the user wants to perform as a string.
   */
  public static void main(String[] args) throws IOException {
    ExtendedOperations operations = new ExtendedImageOperations();
    Controller controller = new TextImageController(operations,
            new InputStreamReader(System.in), System.out);
    System.out.println("\nWelcome to the Image Processor Application!\n");
    controller.start(args);
  }
}