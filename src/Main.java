import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import imagecontroller.ControllerGui;
import imagecontroller.GUIController;
import imagemodel.AdditionalImageOperations;
import imagemodel.AdditionalOperations;
import imagecontroller.Controller;
import imagecontroller.TextImageController;
import imageview.MainFrame;

/**
 * The entry point of the image processing application.
 */
public class Main {

  /**
   * Main method to start the application.
   *
   * @param args Command-line arguments to specify the mode of operation.
   * @throws IOException if an I/O error occurs during file reading or processing.
   */
  public static void main(String[] args) throws IOException {
    AdditionalOperations operations = new AdditionalImageOperations();

    if (args.length == 2 && args[0].equalsIgnoreCase("-file")) {
      String filePath = args[1];
      try (FileReader fileReader = new FileReader(filePath)) {
        Controller fileController = new TextImageController(operations, fileReader, System.out);
        fileController.start(args);
      } catch (IOException e) {
        System.err.println("Error reading from file: " + filePath);
      }
    } else if (args.length == 1 && args[0].equalsIgnoreCase("-text")) {
      Controller textController = new TextImageController(operations,
              new InputStreamReader(System.in), System.out);
      textController.start(args);
    } else if (args.length == 0) {
      MainFrame mainFrame = new MainFrame();
      ControllerGui guiController = new GUIController(operations, mainFrame);
      mainFrame.setController(guiController);
      mainFrame.setVisible(true);
    } else {
      System.out.println("Invalid arguments");
    }
  }
}
