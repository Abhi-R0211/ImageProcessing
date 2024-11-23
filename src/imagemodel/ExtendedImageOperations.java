package imagemodel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * The ExtendedImageOperations class extends ImageOperations and provides additional
 * functionalities such as compression, color correction, level adjustment, split
 * view operations and creating histograms.
 */
public class ExtendedImageOperations extends ImageOperations implements ExtendedOperations {

  /**
   * Compresses an image by applying Haar wavelet transformation and thresholding.
   *
   * @param image      the original image to be compressed.
   * @param percentage the percentage of detail to compress, between 0 and 100.
   * @return the compressed image.
   * @throws IllegalArgumentException if the percentage is out of range or image is null.
   */
  public ImageInterface compressImage(ImageInterface image, int percentage) {
    if (percentage < 0 || percentage > 100) {
      throw new IllegalArgumentException("Threshold must be between 0 and 100.");
    }
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    int width = image.getWidth();
    int height = image.getHeight();

    double[][] redChannel = new double[height][width];
    double[][] greenChannel = new double[height][width];
    double[][] blueChannel = new double[height][width];


    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        PixelInterface pixel = image.getPixel(col, row);
        redChannel[row][col] = pixel.getRed();
        greenChannel[row][col] = pixel.getGreen();
        blueChannel[row][col] = pixel.getBlue();
      }
    }

    double[][] paddedRedChannel = imagePadding(redChannel);
    double[][] paddedGreenChannel = imagePadding(greenChannel);
    double[][] paddedBlueChannel = imagePadding(blueChannel);

    double[][] transRed = haarTransform2D(paddedRedChannel, paddedRedChannel.length);
    double[][] transGreen = haarTransform2D(paddedGreenChannel, paddedGreenChannel.length);
    double[][] transBlue = haarTransform2D(paddedBlueChannel, paddedBlueChannel.length);

    applyThreshold(transRed, percentage);
    applyThreshold(transGreen, percentage);
    applyThreshold(transBlue, percentage);

    double[][] inverseRed = inverseHaarTransform2D(transRed, transRed.length);
    double[][] inverseGreen = inverseHaarTransform2D(transGreen, transGreen.length);
    double[][] inverseBlue = inverseHaarTransform2D(transBlue, transBlue.length);

    ImageCopyInterface compressedImage = new ImageCopy(width, height);
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int redValue = clamp((int) Math.round(inverseRed[row][col]));
        int greenValue = clamp((int) Math.round(inverseGreen[row][col]));
        int blueValue = clamp((int) Math.round(inverseBlue[row][col]));
        Pixel pixel = new Pixel(redValue, greenValue, blueValue);
        compressedImage.setPixel(col, row, pixel);
      }
    }
    return compressedImage.deepCopyImage();
  }

  /**
   * Calculates the next power of two for a given number.
   *
   * @param x the number to calculate the power of two for.
   * @return the next power of two that is greater than or equal to x.
   */
  private int powerOfTwo(int x) {
    int newWidth = 1;
    while (newWidth < x) {
      newWidth <<= 1;
    }
    return newWidth;
  }

  /**
   * Pads an image's data to the next power-of-two dimensions.
   *
   * @param data the original 2D data array.
   * @return the padded 2D array with power-of-two dimensions.
   */
  private double[][] imagePadding(double[][] data) {
    int originalWidth = data[0].length;
    int originalHeight = data.length;
    int newSize = Math.max(powerOfTwo(originalHeight), powerOfTwo(originalWidth));
    double[][] paddingData = new double[newSize][newSize];
    for (int i = 0; i < originalHeight; i++) {
      System.arraycopy(data[i], 0, paddingData[i], 0, originalWidth);
    }
    return paddingData;
  }

  /**
   * Applies a 2D Haar wavelet transform to a given matrix.
   *
   * @param x the matrix to transform.
   * @param s the size of the matrix.
   * @return the transformed matrix.
   */
  private double[][] haarTransform2D(double[][] x, int s) {
    int c = s;
    while (c > 1) {
      for (int i = 0; i < c; i++) {
        double[] result = haarTransform1D(x[i], c);
        System.arraycopy(result, 0, x[i], 0, c);
      }
      for (int j = 0; j < c; j++) {
        double[] column = new double[c];
        for (int i = 0; i < c; i++) {
          column[i] = x[i][j];
        }
        column = haarTransform1D(column, c);
        for (int i = 0; i < c; i++) {
          x[i][j] = column[i];
        }
      }
      c /= 2;
    }
    return x;
  }

  /**
   * Applies a 2D inverse Haar wavelet transform to a given matrix.
   *
   * @param x the matrix to inverse transform.
   * @param s the size of the matrix.
   * @return the inverse transformed matrix.
   */
  private double[][] inverseHaarTransform2D(double[][] x, int s) {
    int c = 2;
    while (c <= s) {
      for (int j = 0; j < c; j++) {
        double[] column = new double[c];
        for (int i = 0; i < c; i++) {
          column[i] = x[i][j];
        }
        column = inverseHaarTransform1D(column, c);
        for (int i = 0; i < c; i++) {
          x[i][j] = column[i];
        }
      }
      for (int i = 0; i < c; i++) {
        double[] result = inverseHaarTransform1D(x[i], c);
        System.arraycopy(result, 0, x[i], 0, c);
      }
      c *= 2;
    }
    return x;
  }

  /**
   * Applies a 1D Haar wavelet transform to a given array.
   *
   * @param data   the array to transform.
   * @param length the size of the matrix.
   * @return the transformed array.
   */
  private double[] haarTransform1D(double[] data, int length) {
    double[] result = new double[length];
    int h = length / 2;
    for (int i = 0; i < h; i++) {
      double a = data[2 * i];
      double b = data[2 * i + 1];
      result[i] = (a + b) / Math.sqrt(2);
      result[i + h] = (a - b) / Math.sqrt(2);
    }
    return result;
  }

  /**
   * Applies a 1D inverse Haar wavelet transform to a given array.
   *
   * @param data   the array to transform.
   * @param length the size of the matrix.
   * @return the transformed array.
   */
  private double[] inverseHaarTransform1D(double[] data, int length) {
    double[] result = new double[length];
    int half = length / 2;
    for (int i = 0; i < half; i++) {
      double avg = data[i];
      double diff = data[i + half];
      result[2 * i] = (avg + diff) / Math.sqrt(2);
      result[2 * i + 1] = (avg - diff) / Math.sqrt(2);
    }
    return result;
  }

  /**
   * Compresses data by applying thresholding based on compression ratio.
   *
   * @param transformedData  2D matrix of transformed data.
   * @param compressionRatio the compression percentage.
   */
  private void applyThreshold(double[][] transformedData, double compressionRatio) {
    int numRows = transformedData.length;
    int numCols = transformedData[0].length;
    Set<Double> nonZeroValuesSet = new HashSet<>();
    for (double[] transformedDatum : transformedData) {
      for (int col = 0; col < numCols; col++) {
        if (transformedDatum[col] != 0) {
          nonZeroValuesSet.add(transformedDatum[col]);
        }
      }
    }
    List<Double> nonZeroValues = new ArrayList<>(nonZeroValuesSet);
    if (nonZeroValues.isEmpty()) {
      return;
    }
    nonZeroValues.sort(Comparator.comparingDouble(Math::abs));
    int retentionCount = (int) (nonZeroValues.size() * ((compressionRatio) / 100.0));
    double thresholdValue = Math.abs(nonZeroValues.get(retentionCount - 1));
    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {
        if (Math.abs(transformedData[row][col]) < thresholdValue) {
          transformedData[row][col] = 0;
        }
      }
    }
  }

  /**
   * Creates a histogram image from the given image.
   *
   * @param image the input image to generate the histogram from.
   * @return a new ImageInterface object representing the histogram.
   * @throws IllegalArgumentException if the input image is null.
   */
  @Override
  public ImageInterface createHistogram(ImageInterface image) {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    int width = 256;
    int height = 256;
    int[][] histogramData = calculateHistogramData(image);
    int maxFrequency = findMaxFrequency(histogramData);
    ImageCopyInterface histogramImage = new ImageCopy(width, height);
    drawGrid(histogramImage, width, height);
    drawHistogramLine(histogramImage, histogramData[0], maxFrequency, height, width,
            new Pixel(255, 0, 0));
    drawHistogramLine(histogramImage, histogramData[1], maxFrequency, height, width,
            new Pixel(0, 255, 0));
    drawHistogramLine(histogramImage, histogramData[2], maxFrequency, height, width,
            new Pixel(0, 0, 255));

    return histogramImage.deepCopyImage();
  }

  /**
   * Draws a grid on the histogram image to help visualize frequency levels.
   *
   * @param histogramImage the histogram image to draw the grid on.
   * @param width          the width of the histogram image.
   * @param height         the height of the histogram image.
   */
  private void drawGrid(ImageCopyInterface histogramImage, int width, int height) {
    PixelInterface gray = new Pixel(200, 200, 200);
    int verticalInterval = 32;
    int horizontalInterval = 10;

    for (int x = 0; x < width; x += verticalInterval) {
      for (int y = 0; y < height; y++) {
        histogramImage.setPixel(x, y, gray);
      }
    }
    for (int y = 0; y < height; y += horizontalInterval) {
      for (int x = 0; x < width; x++) {
        histogramImage.setPixel(x, y, gray);
      }
    }
  }

  /**
   * Calculates histogram data for each color channel in the image.
   *
   * @param image the input image to analyze
   * @return 2D array containing frequency counts for each intensity level for RGB channels.
   */
  private int[][] calculateHistogramData(ImageInterface image) {
    int[][] histogram = new int[3][256];
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        PixelInterface pixel = image.getPixel(x, y);
        histogram[0][clamp(pixel.getRed())]++;
        histogram[1][clamp(pixel.getGreen())]++;
        histogram[2][clamp(pixel.getBlue())]++;
      }
    }
    return histogram;
  }

  /**
   * Finds the maximum frequency value in the histogram data.
   *
   * @param histogramData the histogram data for each color channel.
   * @return the maximum frequency found across all channels.
   */
  private int findMaxFrequency(int[][] histogramData) {
    int max = 0;
    for (int[] channel : histogramData) {
      for (int frequency : channel) {
        if (frequency > max) {
          max = frequency;
        }
      }
    }
    return max;
  }

  /**
   * Draws a line for a specific color channel's histogram on the histogram image.
   *
   * @param image        the histogram image to draw on.
   * @param histogram    the histogram data for the color channel.
   * @param maxFrequency the maximum frequency for scaling purposes.
   * @param height       the height of the histogram image.
   * @param width        the width of the histogram image.
   * @param color        the color of the line to draw (e.g., red for red channel).
   */
  private void drawHistogramLine(ImageCopyInterface image, int[] histogram,
                                 int maxFrequency, int height, int width, PixelInterface color) {
    int previousX = -1;
    int previousY = -1;

    for (int x = 0; x < histogram.length && x < width; x++) {
      int scaledHeight = (int) ((double) histogram[x] / maxFrequency * (height - 1));
      scaledHeight = clamp(scaledHeight, height - 1);

      if (previousX != -1 && previousY != -1) {
        drawLine(image, previousX, height - previousY - 1, x, height - scaledHeight - 1,
                color, width, height);
      }
      previousX = x;
      previousY = scaledHeight;
    }
  }

  /**
   * Clamps a value to ensure it stays within a specified range.
   *
   * @param value the value to clamp
   * @param max   the maximum allowed value
   * @return the clamped value
   */
  private int clamp(int value, int max) {
    return Math.max(0, Math.min(max, value));
  }

  /**
   * Draws a line between two points on the image.
   *
   * @param image  the image to draw on.
   * @param x0     the starting x-coordinate.
   * @param y0     the starting y-coordinate.
   * @param x1     the ending x-coordinate.
   * @param y1     the ending y-coordinate.
   * @param color  the color of the line.
   * @param width  the width of the image.
   * @param height the height of the image.
   */
  private void drawLine(ImageCopyInterface image, int x0, int y0, int x1, int y1,
                        PixelInterface color,
                        int width, int height) {
    int dx = Math.abs(x1 - x0);
    int dy = Math.abs(y1 - y0);
    int sx = (x0 < x1) ? 1 : -1;
    int sy = (y0 < y1) ? 1 : -1;
    int err = dx - dy;

    while (true) {
      if (x0 >= 0 && x0 < width && y0 >= 0 && y0 < height) {
        image.setPixel(x0, y0, color);
      }
      if (x0 == x1 && y0 == y1) {
        break;
      }
      int err2 = err * 2;
      if (err2 > -dy) {
        err -= dy;
        x0 += sx;
      }
      if (err2 < dx) {
        err += dx;
        y0 += sy;
      }
    }
  }

  /**
   * Adjusts color peaks to achieve a balanced color appearance across the image.
   *
   * @param image the input image to adjust.
   * @return a new ImageInterface object with color correction applied.
   * @throws IllegalArgumentException if the input image is null.
   */
  @Override
  public ImageInterface colorCorrect(ImageInterface image) {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    int[] redHist = calculateHistogramData(image)[0];
    int[] greenHist = calculateHistogramData(image)[1];
    int[] blueHist = calculateHistogramData(image)[2];
    int redPeak = findPeak(redHist);
    int greenPeak = findPeak(greenHist);
    int bluePeak = findPeak(blueHist);
    int avgPeak = (redPeak + greenPeak + bluePeak) / 3;
    return adjustPeaks(image, redPeak, greenPeak, bluePeak, avgPeak);
  }

  /**
   * Finds the intensity level with the highest frequency in a color channel's histogram.
   *
   * @param histogram the histogram data for a color channel.
   * @return the intensity level with the highest frequency.
   */
  private int findPeak(int[] histogram) {
    int peakIndex = 0;
    int peakValue = 0;
    for (int i = 10; i < 245; i++) {
      if (histogram[i] > peakValue) {
        peakValue = histogram[i];
        peakIndex = i;
      }
    }
    return peakIndex;
  }

  /**
   * Adjusts each color channel of the image based on the difference between its peak and average.
   *
   * @param image     the input image to adjust.
   * @param redPeak   the peak intensity for the red channel.
   * @param greenPeak the peak intensity for the green channel.
   * @param bluePeak  the peak intensity for the blue channel.
   * @param avgPeak   the average peak intensity across channels.
   * @return a new ImageInterface object with adjusted color peaks.
   */
  private ImageInterface adjustPeaks(ImageInterface image, int redPeak, int greenPeak,
                                     int bluePeak, int avgPeak) {
    int width = image.getWidth();
    int height = image.getHeight();
    ImageCopyInterface copy = new ImageCopy(width, height);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        PixelInterface pixel = image.getPixel(x, y);
        int newRed = clamp(pixel.getRed() + (avgPeak - redPeak));
        int newGreen = clamp(pixel.getGreen() + (avgPeak - greenPeak));
        int newBlue = clamp(pixel.getBlue() + (avgPeak - bluePeak));
        copy.setPixel(x, y, new Pixel(newRed, newGreen, newBlue));
      }
    }
    return copy.deepCopyImage();
  }

  /**
   * Applies a split-view operation, applying an image transformation to a specified percentage
   * of the image width.
   *
   * @param percentage of image to be split.
   * @param image      the input image to split and transform.
   * @param operation  lambda function that performs the necessary operation.
   * @throws IllegalArgumentException if the input image is null.
   * @throws IllegalArgumentException if the operation type is unsupported.
   */
  @Override
  public ImageInterface splitViewOperation(int percentage, ImageInterface image,
                                           Function<ImageInterface, ImageInterface> operation)
          throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    if (percentage < 0 || percentage > 100) {
      throw new IllegalArgumentException("Percentage must be between 0 and 100.");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    int splitPosition = width * percentage / 100;
    ImageInterface partToProcess = cropImage(image, splitPosition, height);
    ImageInterface processedPart = operation.apply(partToProcess);
    return mergeImages(processedPart, image, splitPosition, width, height);
  }

  /**
   * Merges two images by combining the processed section with the original image starting
   * at the split position.
   *
   * @param processedPart the transformed part of the image.
   * @param originalImage the original image.
   * @param splitPosition the x-coordinate to start merging at.
   * @param width         the width of the final merged image.
   * @param height        the height of the final merged image.
   * @return the final merged ImageInterface object.
   */
  private ImageInterface mergeImages(ImageInterface processedPart, ImageInterface originalImage,
                                     int splitPosition, int width, int height) {
    ImageCopyInterface finalImage = new ImageCopy(width, height);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < splitPosition; x++) {
        finalImage.setPixel(x, y, processedPart.getPixel(x, y));
      }
      for (int x = splitPosition; x < width; x++) {
        finalImage.setPixel(x, y, originalImage.getPixel(x, y));
      }
    }
    return finalImage.deepCopyImage();
  }

  /**
   * Crops the image to the specified width and height dimensions.
   *
   * @param source     the source image to crop.
   * @param cropWidth  the width of the cropped image.
   * @param cropHeight the height of the cropped image.
   * @return the cropped ImageInterface object.
   */
  private ImageInterface cropImage(ImageInterface source, int cropWidth, int cropHeight) {
    ImageCopyInterface croppedImage = new ImageCopy(cropWidth, cropHeight);
    for (int y = 0; y < cropHeight; y++) {
      for (int x = 0; x < cropWidth; x++) {
        croppedImage.setPixel(x, y, source.getPixel(x, y));
      }
    }
    return croppedImage.deepCopyImage();
  }

  /**
   * Adjusts levels in an image using specified black, mid, and white values for color mapping.
   *
   * @param image the input image to adjust.
   * @param black the black point for levels adjustment.
   * @param mid   the mid-tone point for levels adjustment.
   * @param white the white point for levels adjustment.
   * @return a new ImageInterface object with adjusted levels.
   * @throws IllegalArgumentException if black, mid, and white values are not in ascending order.
   * @throws IllegalArgumentException if the input image is null.
   */
  @Override
  public ImageInterface levelsAdjust(ImageInterface image, int black, int mid, int white) {
    if (black < 0 || black >= mid || mid >= white || white > 255) {
      throw new IllegalArgumentException("Black, mid, and white values must be in ascending "
              + "order within [0, 255].");
    }
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    ImageCopyInterface copy = new ImageCopy(width, height);
    double[] coefficients = fitQuadraticCurve(black, mid, white);
    double a = coefficients[0];
    double bCoeff = coefficients[1];
    double c = coefficients[2];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        PixelInterface pixel = image.getPixel(x, y);
        int adjustedRed = applyQuadraticAdjustment(pixel.getRed(), a, bCoeff, c);
        int adjustedGreen = applyQuadraticAdjustment(pixel.getGreen(), a, bCoeff, c);
        int adjustedBlue = applyQuadraticAdjustment(pixel.getBlue(), a, bCoeff, c);
        PixelInterface adjustedPixel = new Pixel(
                clamp(adjustedRed), clamp(adjustedGreen), clamp(adjustedBlue)
        );
        copy.setPixel(x, y, adjustedPixel);
      }
    }
    return copy.deepCopyImage();
  }

  /**
   * Adjusts the intensity of a pixel's color channel using a quadratic equation.
   *
   * @param intensity the original intensity of the pixel.
   * @param a         the quadratic coefficient for the squared term.
   * @param bCoeff    the linear coefficient for the intensity term.
   * @param c         the constant coefficient.
   * @return the adjusted intensity.
   */
  private int applyQuadraticAdjustment(int intensity, double a, double bCoeff, double c) {
    int adjustedIntensity = (int) Math.round(a * Math.pow(intensity, 2) + bCoeff * intensity + c);
    return clamp(adjustedIntensity);
  }

  /**
   * Fits a quadratic curve to three specified points.
   *
   * @param black Black point.
   * @param mid   Midpoint.
   * @param white White point.
   * @return Coefficients for the quadratic curve [a, b, c].
   */
  private double[] fitQuadraticCurve(int black, int mid, int white) {
    double denominator = Math.pow(black, 2) * (mid - white) - black * (Math.pow(mid, 2)
            - Math.pow(white, 2)) + white * Math.pow(mid, 2) - mid * Math.pow(white, 2);
    double aNumerator = -black * (128 - 255) + 128 * white - 255 * mid;
    double bNumerator = Math.pow(black, 2) * (128 - 255) + 255
            * Math.pow(mid, 2) - 128 * Math.pow(white, 2);
    double cNumerator = Math.pow(black, 2) * (255 * mid - 128 * white)
            - black * (255 * Math.pow(mid, 2) - 128 * Math.pow(white, 2));
    double a = aNumerator / denominator;
    double bCoeff = bNumerator / denominator;
    double c = cNumerator / denominator;
    return new double[]{a, bCoeff, c};
  }
}