package imageview;

import imagemodel.ImageInterface;

import javax.swing.*;

import java.awt.*;

public class ImageDisplayPanel extends JPanel {
  private ImageInterface image;

  public void setImage(ImageInterface image) {
    this.image = image;
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (image != null) {
      for (int i = 0; i < image.getHeight(); i++) {
        for (int j = 0; j < image.getWidth(); j++) {
          Color color = new Color(image.getPixel(j, i).getRed(), image.getPixel(j, i).getGreen(),
                  image.getPixel(j, i).getBlue());
          g.setColor(color);
          g.fillRect(j, i, 1, 1);
        }
      }
    }
  }
}
