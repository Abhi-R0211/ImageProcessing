## Assignment 4 - Image Processing

## Overview

This project is a Java-based image processing application that allows users to perform various
operations on images such as vertical-flip, horizontal-flip, brighten, greyscale, sepia and others.
The project uses a Model-View-Controller (MVC) design pattern. The project also supports multiple
file formats like JPG, PNG, JPEG, BMP, PPM (P3 format).

## Project Structure

The project is based on the Model-View-Controller. The code has two interfaces and the Main.java
class.

**1. The Image Controller Interface**

- `TextImageController.java`: The Text Image Controller handles user input and connects the View to
  the model to perform image processing operations.
- `P3PPMHandler.java`: This class is used to load and save images which are of type P3PPM.
- `ImageHandler.java`: This class is an implementation of the ImageFormatHandler Interface where it
  will load and save images based on the file type which is sent as an input.-
  `ImageFormatHandler.java`: This is an interface which acts as an universal template for saving and
  loading images.
- `ImageFormatHandler.java`: This is an interface which acts as a universal template for saving and
  loading images.
- `Controller.java`: This is an interface which is used to run any command or to get an image from
  the Map.

**2. The Image Model Interface**

- `Image.java`: This class defines the main image class and creates an object Image.
- `ImageOperations.java`: The ImageOperations class contains all the operations that will be
  performed on the images.
- `Operations.java`:  The Operations interface contains the function prototype of all the operations
  that can be performed on the image such as horizontal/vertical flipping, brighten/darken,
  component extraction and so on.
- `Pixel.java`: Represents a data structure of type pixel in the image with RGB values.
- `ImageInterface.java`: This interface is used for setting or accessing an Image values like the
  Pixel, Height and the Width.
- `PixelInterface.java`: The pixel interface is used for getting the Pixel values (R, G, B).

**3. Main File**

`Main.java`: The Main class is used to run the project after which the user can input the commands
or run the script file.

**Functions that can be run in the Main File**

- Load an Image - load <image-path> <image-name>
- Save an Image - save <image-path> <image-name>
- Extract Red Component of the Image - red-component <image-name> <dest-image-name>
- Extract Green Component of the Image - green-component <image-name> <dest-image-name>
- Extract Blue Component of the Image - blue-component <image-name> <dest-image-name>
- Extract Blue Component of the Image - value-component <image-name> <dest-image-name>
- Extract Luma Component of the Image - luma-component <image-name> <dest-image-name>
- Extract Intensity Component of the Image - intensity-component <image-name> <dest-image-name>
- Flip the Image Horizontally - horizontal-flip <image-name> <dest-image-name>
- Flip the Image Vertically - vertical-flip <image-name> <dest-image-name>
- Brighten the Image by a given number - brighten <increment> <image-name> <dest-image-name>
- Split the Image's RGB Components - rgb-split <image-name> <dest-image-name-red> <
  dest-image-name-green>
- Combine the RGB Components to give the original Image - rgb-combine <dest-image-name> <
  red-image> <green-image> <blue-image>
- Blur the Image - blur <image-name> <dest-image-name>
- Sharpen the Image - sharpen <image-name> <dest-image-name>
- Apply a Sepia Filter - sepia <image-name> <dest-image-name>
- Run a Script File - run <script-file-path>

**4. Class Diagram**

![class-diagram-src.jpg](class-diagram.jpg)

**5. Images**

This folder contains sample images for testing purposes. Images are available in various formats
like BMP, JPEG, JPG, and PNG:

- BMP: `Sample.bmp`
- JPEG: `Sample.jpeg`
- JPG: `Sample.jpg`
- PNG: `Sample.png`
- PPM: `Sample.ppm`

**6. How to Run**

**List of Commands (Sample) that will be accepted by the Program**

- load src/res/PNG/Sample.png input
- red-component input red
- vertical-flip red vertical
- horizontal-flip vertical horizontal
- save src/res/PNG/Sample-horizontal.png horizontal

The above lines should be typed in the Command Line Interface as an Argument to run the files. The
output would be the red component of the input image flipped vertically and then flipped again
horizontally. The following Script File can be run to get the output as well "
src/res/Scripts/PNG/commands1.txt".

**THE IMAGES USED IN THE PROJECT ARE OWNED BY ABHISHEK RAGHURAMAN AND AYUSH VINEET JAIN AND WE
AUTHORIZE TO USE THE IMAGES**