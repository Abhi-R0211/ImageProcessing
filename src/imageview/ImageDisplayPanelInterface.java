package imageview;

import imagemodel.ImageInterface;

/**
 * The ImageDisplayPanelInterface is the interface for the ImageDisplayPanel class.
 */
public interface ImageDisplayPanelInterface {

  /**
   * This sets an Image for being displayed on the Panel.
   *
   * @param image is the Image to be displayed.
   */
  void setImage(ImageInterface image);

}
