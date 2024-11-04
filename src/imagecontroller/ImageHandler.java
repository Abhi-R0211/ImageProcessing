package imagecontroller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import imagemodel.ImageCopy;
import imagemodel.ImageCopyInterface;
import imagemodel.ImageInterface;
import imagemodel.Pixel;
import imagemodel.PixelInterface;

/**
 * ImageHandler is the class that performs the loading and saving operations of various file types
 * such as JPG/JPEG, BMP and PNG. If a PPM image is received, it will be redirected to its
 * respective class.
 */
public class ImageHandler implements ImageFormatHandler {

  /**
   * The loadImage method loads a particular image from the specified path to the memory.
   *
   * @param path of the file to be loaded.
   * @return the loaded image.
   * @throws IOException if invalid width or height is received.
   */
  @Override
  public ImageInterface loadImage(String path) throws IOException {

    BufferedImage bufferedImage = ImageIO.read(new File(path));
    int width = bufferedImage.getWidth();
    int height = bufferedImage.getHeight();
    ImageCopyInterface image = new ImageCopy(width, height);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int rgb = bufferedImage.getRGB(x, y);
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;
        image.setPixel(x, y, new Pixel(red, green, blue));
      }
    }
    return image.deepCopyImage();
  }

  /**
   * The saveImage method saves a particular image to the specified path.
   *
   * @param image     object to be saved.
   * @param path      where the image has to be saved.
   * @param extension of the image to be saved.
   * @throws IOException if invalid width or height is received.
   */
  public void saveImage(ImageInterface image, String path, String extension) throws IOException {
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        PixelInterface pixel = image.getPixel(x, y);
        int rgb = (pixel.getRed() << 16) | (pixel.getGreen() << 8) | pixel.getBlue();
        bufferedImage.setRGB(x, y, rgb);
      }
    }
    ImageIO.write(bufferedImage, extension, new File(path));
  }
}
