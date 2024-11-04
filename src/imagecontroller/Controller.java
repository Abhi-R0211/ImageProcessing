package imagecontroller;
import imagemodel.ImageInterface;

/**
 * This is an interface for the TextImageController class which is the mediator between the view
 * and model structures. It has two methods runCommand and getImageFrom map which runs the command
 * that the user inputs and gets the image from the stack of images stored after computation
 * respectively.
 */
public interface Controller {

  /**
   * Runs the command that the user provides by redirecting the code to the necessary method.
   *
   * @param command to be run.
   */
  void runCommand(String command);

  /**
   * Returns the image from the map based on the key value received as input.
   *
   * @param str input which is the reference key of an image.
   * @return image based on the key.
   */
  ImageInterface getImageFromMap(String str);
}
