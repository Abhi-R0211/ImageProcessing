package imagemodel;

public interface ImageCopyInterface {

  void setPixel(int x, int y, PixelInterface pixel);

  ImageInterface deepCopyImage();
}
