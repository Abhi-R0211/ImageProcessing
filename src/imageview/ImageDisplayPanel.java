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
public class ImageDisplayPanel extends JPanel implements ImageDisplayPanelInterface {
  private ImageInterface image;

  /**
   * Sets the image to be displayed in the panel.
   *
   * @param image the interface object representing the image to display.
   */
  @Override
  public void setImage(ImageInterface image) {
    this.image = image;
    repaint();
  }

  /**
   * Paints the component by rendering the image onto the panel.
   * The image is scaled to fit within the panel while maintaining its aspect ratio.
   *
   * @param g the graphics object used for painting the component.
   */
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
      int offsetX = (int) ((panelWidth - (imageWidth * scale)) / 2);
      int offsetY = (int) ((panelHeight - (imageHeight * scale)) / 2);

      Graphics2D g2d = (Graphics2D) g;
      g2d.translate(offsetX, offsetY);
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
