package imageview;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import imagecontroller.ControllerGui;
import imagemodel.ImageInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is the MainFrame which displays the Graphical User Interface (GUI) of the program.
 * This has three panels the Panel where the Image is displayed, the panel where the Histogram is
 * displayed and the panel where the buttons are pr
 */
public class MainFrame extends JFrame implements MainFrameInterface {
  private final ImageDisplayPanel imageDisplayPanel;
  private final ImageDisplayPanel histogramDisplayPanel;
  private final JScrollPane scrollPane;
  private final JPanel buttonPanel;
  private final JSplitPane splitPane;
  private int count;

  /**
   * Constructs the main frame for the Image Processor.
   *
   * @param args Command-line arguments.
   */
  public MainFrame(String[] args) {
    setTitle("Image Processor");
    setSize(900, 600);
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        if (count == 1) {
          int option = JOptionPane.showConfirmDialog(
                  MainFrame.this,
                  "There are unsaved changes. Do you want to exit without saving?",
                  "Unsaved Changes",
                  JOptionPane.YES_NO_OPTION
          );
          if (option == JOptionPane.NO_OPTION) {
            return;
          }
        }
        dispose();
      }
    });

    imageDisplayPanel = new ImageDisplayPanel();
    scrollPane = new JScrollPane(imageDisplayPanel);
    JPanel histogramPanel = new JPanel();
    histogramDisplayPanel = new ImageDisplayPanel();
    histogramPanel.setPreferredSize(new Dimension(200, 0));
    histogramPanel.setLayout(new BorderLayout());
    histogramPanel.add(histogramDisplayPanel, BorderLayout.CENTER);
    JLabel histogramLabel = new JLabel("Histogram", SwingConstants.CENTER);
    histogramPanel.add(histogramLabel, BorderLayout.NORTH);
    splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, histogramPanel);
    splitPane.setDividerLocation(600);
    add(splitPane, BorderLayout.CENTER);
    buttonPanel = new JPanel();
    add(buttonPanel, BorderLayout.NORTH);
  }


  public void setController(ControllerGui controller) {
    addAdditionalButtons(controller);
  }

  /**
   * Displays the provided image in the image panel.
   *
   * @param image The image to display.
   */
  public void displayImage(ImageInterface image) {
    imageDisplayPanel.setImage(image);
    imageDisplayPanel.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
    scrollPane.revalidate();
    splitPane.revalidate();
    count = 1;
  }

  /**
   * Displays the histogram of the currently loaded image.
   *
   * @param histogramImage The image representing the histogram.
   */
  public void displayHistogram(ImageInterface histogramImage) {
    histogramDisplayPanel.setImage(histogramImage);
    histogramDisplayPanel.revalidate();
    histogramDisplayPanel.repaint();
  }

  /**
   * Prompts the user to load an image and updates the display by calling the load function
   * from the controller.
   */
  public String loadImage() {
    File selectedFile = null;
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Select an Image File");

    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      selectedFile = fileChooser.getSelectedFile();
    }
    return selectedFile.getAbsolutePath();
  }

  /**
   * Prompts the user to save the current image to a file and calls the save function
   * from the controller.
   */
  public String saveImage() {
    JFileChooser fileChooser = new JFileChooser();
    String selectedFilePath = null;
    String extension = null;
    fileChooser.setDialogTitle("Save Image");

    int result = fileChooser.showSaveDialog(this);

    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      selectedFilePath = selectedFile.getAbsolutePath();
    }
    return selectedFilePath;
  }

  /**
   * Opens a dialog for levels adjustment (black, mid, and white levels)
   * and calls the levels adjust from the controller.
   */
  public static ArrayList<Integer> showLevelsAdjustDialog() {
    JSlider blackSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 128);
    JSlider midSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 128);
    JSlider whiteSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 128);

    blackSlider.setMajorTickSpacing(50);
    blackSlider.setMinorTickSpacing(5);
    blackSlider.setPaintTicks(true);
    blackSlider.setPaintLabels(true);

    midSlider.setMajorTickSpacing(50);
    midSlider.setMinorTickSpacing(5);
    midSlider.setPaintTicks(true);
    midSlider.setPaintLabels(true);

    whiteSlider.setMajorTickSpacing(50);
    whiteSlider.setMinorTickSpacing(5);
    whiteSlider.setPaintTicks(true);
    whiteSlider.setPaintLabels(true);

    JPanel inputPanel = new JPanel(new GridLayout(3, 2));
    inputPanel.add(new JLabel("Black Level:"));
    inputPanel.add(blackSlider);
    inputPanel.add(new JLabel("Mid Level:"));
    inputPanel.add(midSlider);
    inputPanel.add(new JLabel("White Level:"));
    inputPanel.add(whiteSlider);

    int result = JOptionPane.showConfirmDialog(
            null,
            inputPanel,
            "Levels Adjustment",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
    );

    if (result != JOptionPane.OK_OPTION) {
      return null;
    }

    int blackLevel = blackSlider.getValue();
    int midLevel = midSlider.getValue();
    int whiteLevel = whiteSlider.getValue();

    ArrayList<Integer> value = new ArrayList<>();
    value.add(blackLevel);
    value.add(midLevel);
    value.add(whiteLevel);

    return value;
  }

  /**
   * Opens a dialog for image compression with adjustable compression percentage and calls the
   * compression function from the controller.
   */
  public static int showCompressionDialog() {
    int percentage = 0;
    JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
    slider.setMajorTickSpacing(10);
    slider.setMinorTickSpacing(1);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);

    int option = JOptionPane.showConfirmDialog(
            null,
            slider,
            "Select Compression Percentage",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
    );

    if (option == JOptionPane.OK_OPTION) {
      percentage = slider.getValue();
    }
    return percentage;
  }

  /**
   * Opens a dialog for downsizing the image by specifying new dimensions
   * and calls the downsize function in the controller.
   */
  public static ArrayList<Integer> showDownsizeDialog() {
    JPanel inputPanel = new JPanel(new GridLayout(2, 2));
    int newWidth = 0;
    int newHeight = 0;

    JTextField widthField = new JTextField();
    JTextField heightField = new JTextField();

    inputPanel.add(new JLabel("Enter new width:"));
    inputPanel.add(widthField);
    inputPanel.add(new JLabel("Enter new height:"));
    inputPanel.add(heightField);

    int result = JOptionPane.showConfirmDialog(
            null,
            inputPanel,
            "Downsize Image",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
    );

    if (result == JOptionPane.OK_OPTION) {
      newWidth = Integer.parseInt(widthField.getText());
      newHeight = Integer.parseInt(heightField.getText());
    }
    ArrayList<Integer> dimension = new ArrayList<>();
    dimension.add(newWidth);
    dimension.add(newHeight);

    return dimension;
  }

  /**
   * Opens a slider dialog to adjust the split view divider.
   */
  public void showSplitViewSliderDialog() {
    JSlider splitViewSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
    splitViewSlider.setMajorTickSpacing(25);
    splitViewSlider.setMinorTickSpacing(5);
    splitViewSlider.setPaintTicks(true);
    splitViewSlider.setPaintLabels(true);

    JPanel sliderPanel = new JPanel(new BorderLayout());
    sliderPanel.add(new JLabel("Adjust Split View", JLabel.CENTER), BorderLayout.NORTH);
    sliderPanel.add(splitViewSlider, BorderLayout.CENTER);

    int result = JOptionPane.showConfirmDialog(
            this,
            sliderPanel,
            "Split View Adjustment",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
    );

    if (result == JOptionPane.OK_OPTION) {
      int value = splitViewSlider.getValue();
      adjustSplitView(value);
    }
  }

  /**
   * Adds additional buttons for various image operations.
   */
  private void addAdditionalButtons(ControllerGui controller) {
    buttonPanel.removeAll();
    buttonPanel.setLayout(new GridLayout(4, 4, 10, 5));
    buttonPanel.setPreferredSize(new Dimension(600, 150));

    addButton("Load Image", e -> {
      try {
        controller.loadImage();
        controller.createHistogram();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    addButton("Save Image", e -> {
      try {
        controller.saveImage();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    addButton("Sepia", e -> {
      controller.handleSepiaCommand();
      controller.createHistogram();
    });
    addButton("Red Component", e -> {
      controller.handleRedComponent();
      controller.createHistogram();
    });
    addButton("Green Component", e -> {
      controller.handleGreenComponent();
      controller.createHistogram();
    });
    addButton("Blue Component", e -> {
      controller.handleBlueComponent();
      controller.createHistogram();
    });
    addButton("Blur", e -> {
      controller.handleBlurCommand();
      controller.createHistogram();
    });
    addButton("Sharpen", e -> {
      controller.handleSharpenCommand();
      controller.createHistogram();
    });
    addButton("Luma", e -> {
      controller.handleLumaComponent();
      controller.createHistogram();
    });
    addButton("Value", e -> {
      controller.handleValueComponent();
      controller.createHistogram();
    });
    addButton("Intensity", e -> {
      controller.handleIntensityComponent();
      controller.createHistogram();
    });
    addButton("Horizontal Flip", e -> {
      controller.flipHorizontal();
      controller.createHistogram();
    });
    addButton("Vertical Flip", e -> {
      controller.flipVertical();
      controller.createHistogram();
    });
    addButton("Color Correct", e -> {
      controller.handleColorCorrectCommand();
      controller.createHistogram();
    });
    addButton("Downscale", e -> {
      controller.downsizeImage();
      controller.createHistogram();
    });
    addButton("Compress", e -> {
      controller.applyCompression();
      controller.createHistogram();
    });
    addButton("Levels Adjust", e -> {
      controller.handleLevelsAdjustCommand();
      controller.createHistogram();
    });
    addButton("Split View", e -> {
      showSplitViewSliderDialog();
      controller.createHistogram();
    });

    buttonPanel.revalidate();
    buttonPanel.repaint();
  }

  /**
   * Adds a button to the button panel with a specified label and action.
   *
   * @param label  The label of the button.
   * @param action The action listener for the button.
   */
  private void addButton(String label, ActionListener action) {
    JButton button = new JButton(label);
    button.addActionListener(action);
    buttonPanel.add(button);
  }

  /**
   * Adjusts the split view divider based on a specified value from the split slider.
   *
   * @param value The new position of the divider.
   */
  private void adjustSplitView(int value) {

  }
}
