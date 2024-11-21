package imageview;

import imagemodel.ImageInterface;
import imagemodel.Pixel;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;


/**
 * A custom JPanel for displaying an image represented by an ImageInterface.
 */
public class ImageDisplayPanel extends JPanel {
  private ImageInterface image;

  /**
   * Sets the image to be displayed in the panel.
   *
   * @param image the {@link ImageInterface} object representing the image to display.
   */
  public void setImage(ImageInterface image) {
    this.image = image;
    repaint();
  }

  /**
   * Paints the component by rendering the image onto the panel.
   * The image is scaled to fit within the panel while maintaining its aspect ratio.
   *
   * @param g the {@link Graphics} object used for painting the component.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (image != null) {
      double panelWidth = getWidth();
      double panelHeight = getHeight();
      double imageWidth = image.getWidth();
      double imageHeight = image.getHeight();

      double scaleX = panelWidth / imageWidth;
      double scaleY = panelHeight / imageHeight;
      double scale = Math.min(scaleX, scaleY);

      Graphics2D g2d = (Graphics2D) g;
      g2d.scale(scale, scale);

      for (int i = 0; i < image.getHeight(); i++) {
        for (int j = 0; j < image.getWidth(); j++) {
          Pixel pixel = (Pixel) image.getPixel(j, i);
          Color color = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
          g2d.setColor(color);
          g2d.fillRect(j, i, 1, 1);
        }
      }
    }
  }
}
