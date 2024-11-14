import java.io.IOException;
import java.io.InputStreamReader;

import imagemodel.AdditionalImageOperations;
import imagemodel.AdditionalOperations;
import imagecontroller.Controller;
import imagecontroller.TextImageController;
import imageview.MainFrame;

/**
 * This driver code will communicate with the user. The Main Class will send the inputs to
 * the TextImageController or launch the GUI (MainFrame) depending on the user's choice.
 */
public class Main {

  /**
   * This is the driver function for the Main Class.
   *
   * @param args takes input of the function that the user wants to perform as a string.
   */
  public static void main(String[] args) throws IOException {
    // Initialize operations
    AdditionalOperations operations = new AdditionalImageOperations();

//    if (args.length > 0 && args[0].equalsIgnoreCase("gui")) {
    // If "gui" argument is passed, start the GUI (MainFrame)
    MainFrame mainFrame = new MainFrame(args, operations);
    mainFrame.setVisible(true);

//    } else {
//      // Otherwise, start the CLI (TextImageController)
//      Controller controller = new TextImageController(operations,   new InputStreamReader(System.in), System.out);
//      controller.start(args);
//    }
  }
}
