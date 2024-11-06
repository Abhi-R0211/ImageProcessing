package imagemodel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class ExtendedImageOperations extends ImageOperations implements ExtendedOperations {

  private final Map<String, Function<ImageInterface, ImageInterface>> operations;

  public ExtendedImageOperations() {
    operations = initializeOperations();
  }

  private Map<String, Function<ImageInterface, ImageInterface>> initializeOperations() {
    Map<String, Function<ImageInterface, ImageInterface>> map = new HashMap<>();
    map.put("blur", this::applyBlur);
    map.put("sharpen", this::applySharpen);
    map.put("sepia", this::applySepia);
    map.put("red-component", this::visualizeRedComponent);
    map.put("blue-component", this::visualizeBlueComponent);
    map.put("green-component", this::visualizeGreenComponent);
    map.put("value-component", this::visualizeValue);
    map.put("luma-component", this::visualizeLuma);
    map.put("intensity-component", this::visualizeIntensity);
    map.put("color-correct", this::colorCorrect);
    return map;
  }

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

  private int powerOfTwo(int x) {
    int newWidth = 1;
    while (newWidth < x) {
      newWidth <<= 1;
    }
    return newWidth;
  }

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

  private double[][] haarTransform2D(double[][] X, int s) {
    int c = s;
    while (c > 1) {
      for (int i = 0; i < c; i++) {
        double[] result = haarTransform1D(X[i], c);
        System.arraycopy(result, 0, X[i], 0, c);
      }
      for (int j = 0; j < c; j++) {
        double[] column = new double[c];
        for (int i = 0; i < c; i++) {
          column[i] = X[i][j];
        }
        column = haarTransform1D(column, c);
        for (int i = 0; i < c; i++) {
          X[i][j] = column[i];
        }
      }
      c /= 2;
    }
    return X;
  }

  private double[][] inverseHaarTransform2D(double[][] X, int s) {
    int c = 2;
    while (c <= s) {
      for (int j = 0; j < c; j++) {
        double[] column = new double[c];
        for (int i = 0; i < c; i++) {
          column[i] = X[i][j];
        }
        column = inverseHaarTransform1D(column, c);
        for (int i = 0; i < c; i++) {
          X[i][j] = column[i];
        }
      }
      for (int i = 0; i < c; i++) {
        double[] result = inverseHaarTransform1D(X[i], c);
        System.arraycopy(result, 0, X[i], 0, c);
      }
      c *= 2;
    }
    return X;
  }

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

  @Override
  public ImageInterface createHistogram(ImageInterface image) {
    if (image == null) {
      throw new NullPointerException("Image cannot be null.");
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

  private int findMaxFrequency(int[][] histogramData) {
    int max = 0;
    for (int[] channel : histogramData) {
      for (int frequency : channel) {
        if (frequency > max) max = frequency;
      }
    }
    return max;
  }

  private void drawHistogramLine(ImageCopyInterface image, int[] histogram, int maxFrequency, int height,
                                 int width, PixelInterface color) {
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

  private int clamp(int value, int max) {
    return Math.max(0, Math.min(max, value));
  }

  private void drawLine(ImageCopyInterface image, int x0, int y0, int x1, int y1, PixelInterface color,
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
      if (x0 == x1 && y0 == y1) break;
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

  @Override
  public ImageInterface colorCorrect(ImageInterface image) {
    if (image == null) {
      throw new NullPointerException("Image cannot be null.");
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

  private ImageInterface parseLevelsAdjust(String[] tokens, ImageInterface partToProcess) {
    if (tokens.length < 4) {
      throw new IllegalArgumentException("Levels adjust requires black, mid, and white values.");
    }
    int black = Integer.parseInt(tokens[1]);
    int mid = Integer.parseInt(tokens[2]);
    int white = Integer.parseInt(tokens[3]);
    return levelsAdjust(partToProcess, black, mid, white);
  }

  @Override
  public ImageInterface levelsAdjust(ImageInterface image, int black, int mid, int white) {
    if (black < 0 || black >= mid || mid >= white || white > 255) {
      throw new IllegalArgumentException("Black, mid, and white values must be in ascending order within [0, 255].");
    }
    if (image == null) {
      throw new NullPointerException("Image cannot be null.");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    ImageCopyInterface copy = new ImageCopy(width, height);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        PixelInterface pixel = image.getPixel(x, y);
        int adjustedRed = adjustPixelValue(pixel.getRed(), black, mid, white);
        int adjustedGreen = adjustPixelValue(pixel.getGreen(), black, mid, white);
        int adjustedBlue = adjustPixelValue(pixel.getBlue(), black, mid, white);
        copy.setPixel(x, y, new Pixel(clamp(adjustedRed), clamp(adjustedGreen),
                clamp(adjustedBlue)));
      }
    }
    return copy.deepCopyImage();
  }

  private int adjustPixelValue(int value, int black, int mid, int white) {
    if (value < black) {
      return 0;
    } else if (value > white) {
      return 255;
    } else if (value <= mid) {
      return (int) Math.round((value - black) * (128.0 / (mid - black)));
    } else {
      return (int) Math.round(128 + (value - mid) * (127.0 / (white - mid)));
    }
  }

  @Override
  public ImageInterface splitViewOperation(String[] tokens, ImageInterface image) {
    if (image == null) {
      throw new NullPointerException("Image cannot be null.");
    }
    int width = image.getWidth();
    int height = image.getHeight();
    int splitPosition = getSplitPosition(tokens, width);
    ImageInterface partToProcess = cropImage(image, splitPosition, height);
    String operationType = tokens[0];
    if (!operations.containsKey(operationType)) {
      throw new IllegalArgumentException("Unsupported operation: " + operationType);
    }
    ImageInterface processedPart;
    if (operationType.equals("levels-adjust")) {
      processedPart = parseLevelsAdjust(tokens, partToProcess);
    } else {
      processedPart = operations.get(operationType).apply(partToProcess);
    }
    return mergeImages(processedPart, image, splitPosition, width, height);
  }

  private int getSplitPosition(String[] tokens, int width) {
    int percentage = Integer.parseInt(tokens[tokens.length - 1]);
    if (percentage > 100 || percentage < 0) {
      throw new IllegalArgumentException("Percentage must be between 0 and 100.");
    }
    return width * percentage / 100;
  }

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

  private ImageInterface cropImage(ImageInterface source, int cropWidth, int cropHeight) {
    ImageCopyInterface croppedImage = new ImageCopy(cropWidth, cropHeight);
    for (int y = 0; y < cropHeight; y++) {
      for (int x = 0; x < cropWidth; x++) {
        croppedImage.setPixel(x, y, source.getPixel(x, y));
      }
    }
    return croppedImage.deepCopyImage();
  }
}