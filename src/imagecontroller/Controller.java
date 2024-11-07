package imagecontroller;

import java.io.IOException;

/**
 * This is an interface for the TextImageController class which is the mediator between the user
 * and model. It has two methods runCommand and start which runs the command
 * that the user inputs and starts an interactive session between the system and the user.
 */
public interface Controller {

  /**
   * Runs the command that the user provides by redirecting the code to the necessary method.
   *
   * @param command to be run.
   * @throws IOException on incorrect Input/Output.
   */
  void runCommand(String command) throws IOException;

  /**
   * Starts an interactive session between the system and user.
   *
   * @param args input from the user.
   * @throws IOException on incorrect Input/Output.
   */
  void start(String[] args) throws IOException;
}
