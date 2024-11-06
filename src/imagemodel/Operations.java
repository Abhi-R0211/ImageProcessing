package imagemodel;

/**
 * The Operations interface contains the function prototype of all the operations that can be
 * performed on the image such as horizontal/vertical flipping, brighten/darken, component
 * extraction and so on.
 */
public interface Operations {

  /**
   * The applyHorizontalFlip method will flip the image horizontally.
   *
   * @param image that needs to be flipped.
   * @return object of type Image, after flipping it horizontally.
   */
  ImageInterface applyHorizontalFlip(ImageInterface image);

  /**
   * The applyVerticalFlip method will flip the image vertically.
   *
   * @param image that needs to be flipped.
   * @return object of type Image, after flipping it vertically.
   */
  ImageInterface applyVerticalFlip(ImageInterface image);

  /**
   * The applyBrightness method will brighten or darken the image depending on the increment
   * parameter received.
   *
   * @param image     whose brightness needs to be changed.
   * @param increment factor to be applied on the image.
   * @return object of type Image after brightening/darkening.
   */
  ImageInterface applyBrightness(ImageInterface image, int increment);

  /**
   * The applySepia method will apply a Sepia tone to the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after applying a sepia tone.
   */
  ImageInterface applySepia(ImageInterface image);

  /**
   * The applyBlur method will blur the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after blurring.
   */
  ImageInterface applyBlur(ImageInterface image);

  /**
   * The applySharpen method will sharpen the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after applying the operation.
   */
  ImageInterface applySharpen(ImageInterface image);

  /**
   * This method will extract the red component out of the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after extracting the red component.
   */
  ImageInterface visualizeRedComponent(ImageInterface image);

  /**
   * This method will extract the green component out of the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after extracting the green component.
   */
  ImageInterface visualizeGreenComponent(ImageInterface image);

  /**
   * This method will extract the blue component out of the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after extracting the blue component.
   */
  ImageInterface visualizeBlueComponent(ImageInterface image);

  /**
   * This method will return an image with the value component of the input image.
   *
   * @param image is the image on which the operation is to be applied.
   * @return this will return an object of type Image after applying the operation.
   */
  ImageInterface visualizeValue(ImageInterface image);

  /**
   * This method will return an image with the intensity component of the input image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after applying the operation
   */
  ImageInterface visualizeIntensity(ImageInterface image);

  /**
   * This method will return an image with the luma component of the input image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after applying the operation.
   */
  ImageInterface visualizeLuma(ImageInterface image);

  /**
   * The combineRGB method will combine the Red, Green, and Blue channels, and convert it into
   * one single image.
   *
   * @param redImage   that is used for the red-pixels of the image.
   * @param greenImage that is used for the green-pixels of the image.
   * @param blueImage  that is used for the blue-pixels of the image.
   * @return an image after combining all the three RGB Channels.
   */
  ImageInterface combineRGB(ImageInterface redImage, ImageInterface greenImage,
                            ImageInterface blueImage);
}
