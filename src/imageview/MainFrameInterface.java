package imageview;
import imagecontroller.ControllerGui;
import imagemodel.ImageInterface;

public interface MainFrameInterface {
  void setController(ControllerGui controller);

  void displayImage(ImageInterface image);

  void displayHistogram(ImageInterface histogramImage);

  String loadImage();

  String saveImage();

  //Add showLevelsAdjustDialog
  //  int showCompressionDialog();
  //ArrayList<Integer> showDownsizeDialog()
  void showSplitViewSliderDialog();
}
