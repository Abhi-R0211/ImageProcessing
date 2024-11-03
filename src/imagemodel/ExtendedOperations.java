package imagemodel;

public interface ExtendedOperations extends Operations {

  Image compressImage(Image image, double threshold);

  Image createHistogram(Image image);

  Image colorCorrect(Image image);

  Image levelsAdjust(Image image, int b, int m, int w);

  Image splitViewOperation(String[] tokens, Image image);
}
