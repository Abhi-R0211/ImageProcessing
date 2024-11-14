package imageview;

import javax.swing.*;

import imagemodel.AdditionalOperations;
import imagecontroller.GUIController;
import imagemodel.ImageInterface;

import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {
  private final JTextArea outputArea;
  private final GUIController controller;
  private final ImageDisplayPanel imageDisplayPanel;
  private final JScrollPane scrollPane;
  private final JPanel buttonPanel;

  public MainFrame(String[] args, AdditionalOperations operations) {
    setTitle("Image Processor");
    setSize(800, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    outputArea = new JTextArea(10, 50);
    add(new JScrollPane(outputArea), BorderLayout.SOUTH);

    imageDisplayPanel = new ImageDisplayPanel();
    scrollPane = new JScrollPane(imageDisplayPanel);
    add(scrollPane, BorderLayout.CENTER);

    controller = new GUIController(operations, outputArea, this);

    buttonPanel = new JPanel();
    JButton loadButton = new JButton("Load Image");
    loadButton.addActionListener(e -> {
      try {
        controller.runCommand("load");
      } catch (IOException ex) {
        outputArea.append("Error: " + ex.getMessage() + "\n");
      }
    });
    buttonPanel.add(loadButton);
    add(buttonPanel, BorderLayout.NORTH);
  }

  public void displayImage(ImageInterface image) {
    imageDisplayPanel.setImage(image);

    imageDisplayPanel.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
    scrollPane.revalidate();

    addAdditionalButtons();
  }

  private void addAdditionalButtons() {
    buttonPanel.removeAll();
    buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
    buttonPanel.setPreferredSize(new Dimension(600, 200));

    JButton loadButton = new JButton("Load Image");
    loadButton.addActionListener(e -> {
      try {
        controller.runCommand("load");
      } catch (IOException ex) {
        outputArea.append("Error: " + ex.getMessage() + "\n");
      }
    });
    buttonPanel.add(loadButton);

    JButton saveButton = new JButton("Save Image");
    saveButton.addActionListener(e -> {
      try {
        controller.runCommand("save");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(saveButton);


    JButton sepiaImage = new JButton("Sepia");
    sepiaImage.addActionListener(e -> {
      try {
        controller.runCommand("sepia");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(sepiaImage);

    JButton redComponent = new JButton("Red Component");
    redComponent.addActionListener(e -> {
      try {
        controller.runCommand("red-component");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(redComponent);

    JButton greenComponent = new JButton("Green Component");
    greenComponent.addActionListener(e -> {
      try {
        controller.runCommand("green-component");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(greenComponent);

    JButton blueComponent = new JButton("Blue Component");
    blueComponent.addActionListener(e -> {
      try {
        controller.runCommand("blue-component");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(blueComponent);

    JButton blurImage = new JButton("Blur");
    blurImage.addActionListener(e -> {
      try {
        controller.runCommand("blur");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(blurImage);

    JButton sharpenImage = new JButton("Sharpen");
    sharpenImage.addActionListener(e -> {
      try {
        controller.runCommand("sharpen");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(sharpenImage);

    JButton lumaComponent = new JButton("Luma Component");
    lumaComponent.addActionListener(e -> {
      try {
        controller.runCommand("luma-component");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(lumaComponent);

    JButton valueComponent = new JButton("Value Component");
    valueComponent.addActionListener(e -> {
      try {
        controller.runCommand("value-component");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(valueComponent);

    JButton intensityComponent = new JButton("Intensity Component");
    intensityComponent.addActionListener(e -> {
      try {
        controller.runCommand("intensity-component");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(intensityComponent);

    JButton flipHorizontal = new JButton("Horizontal Flip");
    flipHorizontal.addActionListener(e -> {
      try {
        System.out.println("Button Pressed");
        controller.runCommand("horizontal-flip");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(flipHorizontal);

    JButton flipVertical = new JButton("Vertical Flip");
    flipVertical.addActionListener(e -> {
      try {
        controller.runCommand("vertical-flip");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    buttonPanel.add(flipVertical);

    buttonPanel.revalidate();
    buttonPanel.repaint();
  }
}
