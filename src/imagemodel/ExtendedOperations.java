package imagemodel;

public interface ExtendedOperations extends Operations {

  ImageInterface compressImage(ImageInterface image, int percentage);

  ImageInterface createHistogram(ImageInterface image);

  ImageInterface colorCorrect(ImageInterface image);

  ImageInterface levelsAdjust(ImageInterface image, int b, int m, int w);

  ImageInterface splitViewOperation(String[] tokens, ImageInterface image);
}
