package imagemodel;

/**
 * Interface that supports operations such as downsizing of an image to a target height and width,
 * and extends the blur, sharpen, sepia, luma, red, blue,green, intensity, value and brightness
 * operations to work on images with a masking option.
 */
public interface AdditionalOperations extends ExtendedOperations {

  /**
   * Downsizes an image to a target height and width.
   *
   * @param original     image.
   * @param targetWidth  of the downsized image.
   * @param targetHeight of the downsized image.
   * @return downsized image.
   */
  ImageInterface downscaleImage(ImageInterface original, int targetWidth, int targetHeight);

  /**
   * The applyBlur method will blur the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after blurring.
   */
  ImageInterface applyBlur(ImageInterface image, ImageInterface maskImage);

  /**
   * The applySharpen method will sharpen the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after applying the operation.
   */
  ImageInterface applySharpen(ImageInterface image, ImageInterface maskImage);

  /**
   * The applySepia method will apply a Sepia tone to the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after applying a sepia tone.
   */
  ImageInterface applySepia(ImageInterface image, ImageInterface maskImage);

  /**
   * This method will return an image with the luma component of the input image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after applying the operation.
   */
  ImageInterface visualizeLuma(ImageInterface image, ImageInterface maskImage);

  /**
   * This method will extract the red component out of the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after extracting the red component.
   */
  ImageInterface visualizeRedComponent(ImageInterface image, ImageInterface maskImage);

  /**
   * This method will extract the green component out of the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after extracting the green component.
   */
  ImageInterface visualizeGreenComponent(ImageInterface image, ImageInterface maskImage);

  /**
   * This method will extract the blue component out of the image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after extracting the blue component.
   */
  ImageInterface visualizeBlueComponent(ImageInterface image, ImageInterface maskImage);

  /**
   * This method will return an image with the intensity component of the input image.
   *
   * @param image on which the operation is to be applied.
   * @return an object of type Image after applying the operation
   */
  ImageInterface visualizeIntensity(ImageInterface image, ImageInterface maskImage);

  /**
   * This method will return an image with the value component of the input image.
   *
   * @param image is the image on which the operation is to be applied.
   * @return this will return an object of type Image after applying the operation.
   */
  ImageInterface visualizeValue(ImageInterface image, ImageInterface maskImage);

  /**
   * The applyBrightness method will brighten or darken the image depending on the increment
   * parameter received.
   *
   * @param image     whose brightness needs to be changed.
   * @param increment factor to be applied on the image.
   * @return object of type Image after brightening/darkening.
   */
  ImageInterface applyBrightness(ImageInterface image, int increment, ImageInterface maskImage);
}
