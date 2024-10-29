package imagecontroller;

import java.io.IOException;

import imagemodel.Image;

/**
 * ImageFormatHandler is an interface that acts as a universal template for loading and saving
 * images of different types.
 */
public interface ImageFormatHandler {

  /**
   * The loadImage method loads a particular image from the specified path to the memory.
   *
   * @param path of the file to be loaded.
   * @return the loaded image.
   * @throws IOException if invalid width or height is received.
   */
  Image loadImage(String path) throws IOException;

  /**
   * The saveImage method saves a particular image to the specified path.
   *
   * @param image     object to be saved.
   * @param path      where the image has to be saved.
   * @param extension of the image to be saved.
   * @throws IOException if invalid width or height is received.
   */
  void saveImage(Image image, String path, String extension) throws IOException;
}
