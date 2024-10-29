package imagecontroller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

import imagemodel.Image;
import imagemodel.Pixel;

/**
 * P3PPMHandler is the class that performs the loading and saving operations of a PPM image.
 */
public class P3PPMHandler implements ImageFormatHandler {

  /**
   * The loadImage method loads a PPM image from the specified path to the memory.
   *
   * @param path of the file to be loaded.
   * @return the loaded image.
   * @throws IOException if invalid width or height is received.
   */
  public Image loadImage(String path) throws IOException {
    Scanner scan = new Scanner(new File(path));
    String format = scan.next();
    if (!format.equals("P3")) {
      throw new IOException("Invalid PPM format");
    }

    int width = scan.nextInt();
    int height = scan.nextInt();
    scan.nextInt();

    Image image = new Image(width, height);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int red = scan.nextInt();
        int green = scan.nextInt();
        int blue = scan.nextInt();
        image.setPixel(x, y, new Pixel(red, green, blue));
      }
    }
    scan.close();
    return image;
  }

  /**
   * The saveImage method saves a PPM image to the specified path.
   *
   * @param image object to be saved.
   * @param path  where the image has to be saved.
   * @throws IOException if invalid width or height is received.
   */
  public void saveImage(Image image, String path, String extension) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(path));
    writer.write("P3\n");
    writer.write(image.getWidth() + " " + image.getHeight() + "\n");
    writer.write("255\n");

    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        Pixel pixel = image.getPixel(x, y);
        writer.write(pixel.getRed() + " " + pixel.getGreen() + " " + pixel.getBlue() + "\t");
      }
      writer.write("\n");
    }
    writer.close();
  }
}