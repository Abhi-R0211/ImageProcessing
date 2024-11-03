package imagemodel;

import java.util.Arrays;

public class ExtendedImageOperations extends ImageOperations implements ExtendedOperations {

  @Override
  public Image compressImage(Image image, double threshold) {
    int width = image.getWidth();
    int height = image.getHeight();
    int maxSize = Math.max(width, height);
    int[][] redChannel = padToSquare(extractChannel(image, "red"), maxSize);
    int[][] greenChannel = padToSquare(extractChannel(image, "green"), maxSize);
    int[][] blueChannel = padToSquare(extractChannel(image, "blue"), maxSize);

    haarTransform2D(redChannel, threshold);
    haarTransform2D(greenChannel, threshold);
    haarTransform2D(blueChannel, threshold);

    inverseHaarTransform2D(redChannel);
    inverseHaarTransform2D(greenChannel);
    inverseHaarTransform2D(blueChannel);

    return mergeChannels(resizeToOriginal(redChannel, width, height),
            resizeToOriginal(greenChannel, width, height),
            resizeToOriginal(blueChannel, width, height));
  }

  private int[][] padToSquare(int[][] channel, int maxSize) {
    int[][] padded = new int[maxSize][maxSize];
    for (int y = 0; y < channel.length; y++) {
      System.arraycopy(channel[y], 0, padded[y], 0, channel[y].length);
    }
    return padded;
  }

  private int[][] resizeToOriginal(int[][] paddedChannel, int width, int height) {
    int[][] resized = new int[height][width];
    for (int y = 0; y < height; y++) {
      System.arraycopy(paddedChannel[y], 0, resized[y], 0, width);
    }
    return resized;
  }

  private void haarTransform2D(int[][] channel, double threshold) {
    int size = channel.length;
    while (size > 1) {
      for (int i = 0; i < size; i++) {
        haarTransform1D(channel[i], threshold);
      }
      for (int j = 0; j < size; j++) {
        int[] col = new int[size];
        for (int i = 0; i < size; i++) {
          col[i] = channel[i][j];
        }
        haarTransform1D(col, threshold);
        for (int i = 0; i < size; i++) {
          channel[i][j] = col[i];
        }
      }
      size /= 2;
    }
  }

  private void haarTransform1D(int[] array, double threshold) {
    int length = array.length;
    int[] temp = new int[length];

    while (length > 1) {
      int half = length / 2;
      for (int i = 0; i < half; i++) {
        int avg = (int) ((array[2 * i] + array[2 * i + 1]) / Math.sqrt(2));
        int diff = (int) ((array[2 * i] - array[2 * i + 1]) / Math.sqrt(2));
        temp[i] = avg;
        temp[half + i] = (Math.abs(diff) < threshold) ? 0 : diff;
      }
      System.arraycopy(temp, 0, array, 0, length);
      length = half;
    }
  }

  private void inverseHaarTransform2D(int[][] channel) {
    int size = 2;
    while (size <= channel.length) {
      for (int j = 0; j < size; j++) {
        int[] col = new int[size];
        for (int i = 0; i < size; i++) {
          col[i] = channel[i][j];
        }
        inverseHaarTransform1D(col);
        for (int i = 0; i < size; i++) {
          channel[i][j] = col[i];
        }
      }
      for (int i = 0; i < size; i++) {
        inverseHaarTransform1D(channel[i]);
      }
      size *= 2;
    }
  }

  private void inverseHaarTransform1D(int[] array) {
    int length = 2;
    int[] temp = new int[array.length];

    while (length <= array.length) {
      int half = length / 2;
      for (int i = 0; i < half; i++) {
        int avg = array[i];
        int diff = array[half + i];
        temp[2 * i] = (int) ((avg + diff) / Math.sqrt(2));
        temp[2 * i + 1] = (int) ((avg - diff) / Math.sqrt(2));
      }
      System.arraycopy(temp, 0, array, 0, length);
      length *= 2;
    }
  }

  private Image mergeChannels(int[][] red, int[][] green, int[][] blue) {
    int width = red[0].length;
    int height = red.length;
    Image mergedImage = new Image(width, height);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int clampedRed = clamp(red[y][x]);
        int clampedGreen = clamp(green[y][x]);
        int clampedBlue = clamp(blue[y][x]);
        mergedImage.setPixel(x, y, new Pixel(clampedRed, clampedGreen, clampedBlue));
      }
    }
    return mergedImage;
  }

  private int[][] extractChannel(Image image, String channel) {
    int width = image.getWidth();
    int height = image.getHeight();
    int[][] channelData = new int[height][width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel pixel = image.getPixel(x, y);
        switch (channel.toLowerCase()) {
          case "red":
            channelData[y][x] = pixel.getRed();
            break;
          case "green":
            channelData[y][x] = pixel.getGreen();
            break;
          case "blue":
            channelData[y][x] = pixel.getBlue();
            break;
        }
      }
    }
    return channelData;
  }

  @Override
  public Image createHistogram(Image image) {
    int width = 256;
    int height = 256;
    int[][] histogramData = calculateHistogramData(image);
    int maxFrequency = findMaxFrequency(histogramData);
    Image histogramImage = new Image(width, height);
    drawGrid(histogramImage);
    drawHistogramLine(histogramImage, histogramData[0], maxFrequency, height,
            new Pixel(255, 0, 0));
    drawHistogramLine(histogramImage, histogramData[1], maxFrequency, height,
            new Pixel(0, 255, 0));
    drawHistogramLine(histogramImage, histogramData[2], maxFrequency, height,
            new Pixel(0, 0, 255));
    return histogramImage;
  }

  private void drawGrid(Image histogramImage) {
    int width = histogramImage.getWidth();
    int height = histogramImage.getHeight();
    Pixel gray = new Pixel(200, 200, 200);
    for (int x = 0; x < width; x += 32) {
      for (int y = 0; y < height; y++) {
        histogramImage.setPixel(x, y, gray);
      }
    }
    for (int y = 0; y < height; y += 10) {
      for (int x = 0; x < width; x++) {
        histogramImage.setPixel(x, y, gray);
      }
    }
  }

  private int[][] calculateHistogramData(Image image) {
    int[][] histogram = new int[3][256];
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        Pixel pixel = image.getPixel(x, y);
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

  private void drawHistogramLine(Image image, int[] histogram, int maxFrequency,
                                 int height, Pixel color) {
    int width = image.getWidth();
    int previousX = -1;
    int previousY = -1;
    for (int x = 0; x < histogram.length && x < width; x++) {
      int scaledHeight = (int) ((double) histogram[x] / maxFrequency * height);
      scaledHeight = clamp(scaledHeight, height - 1);
      if (previousX != -1 && previousY != -1) {
        drawLine(image, previousX, height - previousY, x, height - scaledHeight, color);
      }
      previousX = x;
      previousY = scaledHeight;
    }
  }

  private int clamp(int value, int max) {
    return Math.max(0, Math.min(max, value));
  }

  private void drawLine(Image image, int x0, int y0, int x1, int y1, Pixel color) {
    int dx = Math.abs(x1 - x0);
    int dy = Math.abs(y1 - y0);
    int sx = (x0 < x1) ? 1 : -1;
    int sy = (y0 < y1) ? 1 : -1;
    int err = dx - dy;
    while (true) {
      if (x0 >= 0 && x0 < image.getWidth() && y0 >= 0 && y0 < image.getHeight()) {
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
  public Image colorCorrect(Image image) {
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

  private Image adjustPeaks(Image image, int redPeak, int greenPeak, int bluePeak, int avgPeak) {
    Image adjustedImage = deepCopyImage(image);
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        Pixel pixel = image.getPixel(x, y);
        int newRed = clamp(pixel.getRed() + (avgPeak - redPeak));
        int newGreen = clamp(pixel.getGreen() + (avgPeak - greenPeak));
        int newBlue = clamp(pixel.getBlue() + (avgPeak - bluePeak));
        adjustedImage.setPixel(x, y, new Pixel(newRed, newGreen, newBlue));
      }
    }
    return adjustedImage;
  }

  @Override
  public Image levelsAdjust(Image image, int black, int mid, int white) {
    int width = image.getWidth();
    int height = image.getHeight();
    Image adjustedImage = new Image(width, height);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel pixel = image.getPixel(x, y);
        int adjustedRed = adjustPixelValue(pixel.getRed(), black, mid, white);
        int adjustedGreen = adjustPixelValue(pixel.getGreen(), black, mid, white);
        int adjustedBlue = adjustPixelValue(pixel.getBlue(), black, mid, white);
        adjustedImage.setPixel(x, y, new Pixel(clamp(adjustedRed), clamp(adjustedGreen),
                clamp(adjustedBlue)));
      }
    }
    return adjustedImage;
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
  public Image splitViewOperation(String[] tokens, Image image) {
    int width = image.getWidth();
    int height = image.getHeight();
    int splitPosition = width * Integer.parseInt(tokens[tokens.length - 1]) / 100;
    Image outputImage = deepCopyImage(image);
    Image partToProcess = cropImage(image, splitPosition, height);
    Image processedPart;
    switch (tokens[0]) {
      case "blur":
        processedPart = applyBlur(partToProcess);
        break;
      case "sharpen":
        processedPart = applySharpen(partToProcess);
        break;
      case "sepia":
        processedPart = applySepia(partToProcess);
        break;
      case "red-component":
        processedPart = visualizeRedComponent(partToProcess);
        break;
      case "blue-component":
        processedPart = visualizeBlueComponent(partToProcess);
        break;
      case "green-component":
        processedPart = visualizeGreenComponent(partToProcess);
        break;
      case "value-component":
        processedPart = visualizeValue(partToProcess);
        break;
      case "luma-component":
        processedPart = visualizeLuma(partToProcess);
        break;
      case "intensity-component":
        processedPart = visualizeIntensity(partToProcess);
        break;
      case "color_correct":
        processedPart = colorCorrect(partToProcess);
        break;
      case "levels_adjust":
        processedPart = levelsAdjust(partToProcess, Integer.parseInt(tokens[1]),
                Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
        break;
      default:
        throw new IllegalArgumentException("Unsupported operation: " + Arrays.toString(tokens));
    }

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < splitPosition; x++) {
        outputImage.setPixel(x, y, processedPart.getPixel(x, y));
      }
    }
    return outputImage;
  }

  private Image cropImage(Image source, int cropWidth, int cropHeight) {
    Image croppedImage = new Image(cropWidth, cropHeight);
    for (int y = 0; y < cropHeight; y++) {
      for (int x = 0; x < cropWidth; x++) {
        Pixel pixel = source.getPixel(x, y);
        croppedImage.setPixel(x, y, pixel);
      }
    }
    return croppedImage;
  }
}