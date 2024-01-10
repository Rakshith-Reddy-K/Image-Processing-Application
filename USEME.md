## Table of Contents

- [Supported commands](#supported-commands)
- [Usage](#usage)

## Supported commands

1. `load image-path image-name`: Load an image from the specified path and refer it to henceforth in
   the program by the given image name.
2. `save image-path image-name`: Save the image with the given name to the specified path which
   should include the name of the file.
3. `red-component image-name dest-image-name`: Create an image with the red-component of the image
   with the given name, and refer to it henceforth in the program by the given destination name.
4. `green-component image-name dest-image-name`: Create an image with the red-component of the image
   with the given name, and refer to it henceforth in the program by the given destination name.
5. `blue-component image-name dest-image-name`: Create an image with the red-component of the image
   with the given name, and refer to it henceforth in the program by the given destination name.
6. `value-component image-name dest-image-name`: Create an image with the red-component of the image
   with the given name, and refer to it henceforth in the program by the given destination name.
   This will be a greyscale image.
7. `luma-component image-name dest-image-name`: Create an image with the red-component of the image
   with the given name, and refer to it henceforth in the program by the given destination name.
   This will be a greyscale image.
8. `intensity-component image-name dest-image-name`: Create an image with the red-component of the
   image with the given name, and refer to it henceforth in the program by the given destination
   name. This will be a greyscale image.
9. `horizontal-flip image-name dest-image-name`: Flip an image horizontally to create a new image,
   referred to henceforth by the given destination name.
10. `vertical-flip image-name dest-image-name`: Flip an image vertically to create a new image,
    referred to henceforth by the given destination name.
11. `brighten increment image-name dest-image-name`: brighten the image by the given increment to
    create a new image, referred to henceforth by the given destination name. The increment may be
    positive (brightening) or negative (darkening). The increment value should be a number.
12. `rgb-split image-name dest-image-name-red dest-image-name-green dest-image-name-blue`: split the
    given image into three images containing its red, green and blue components respectively. These
    would be the same images that would be individually produced with the red-component,
    green-component and blue-component commands.
13. `rgb-combine image-name red-image green-image blue-image`: Combine the three greyscale images
    into a single image that gets its red, green and blue components from the three images
    respectively.
14. `blur image-name dest-image-name`: blur the given image and store the result in another image
    with the given name.
15. `sharpen image-name dest-image-name`: sharpen the given image and store the result in another
    image with the given name.
16. `sepia image-name dest-image-name split p`: produce a sepia-toned version of the given image and
    store
    the result in another image with the given name.
17. `compress percentage image-name dest-image-name`: compressed the given image by the given
    percentage. The percentage should be a number and between 0 and 100.
18. `histogram image-name dest-image-name`: gets the histogram of the image and stores the result
    with the given dest-name.
19. `color-correct image-name dest-image-name`: color corrects the image and stores the result with
    the given dest-name.
20. `levels-adjust b m w image-name dest-image-name`: Adjusts the levels of the given image with the
    given values of b,m,w where b, m and w are the three relevant black, mid and white values
    respectively. These values should be ascending in that order, and should be within 0 and 255 for
    this command to work correctly.
21. The commands 6,7,8,14,15,16,19,20 above support the split operation by just appending the same
    command with `split p` which splits the image with the given p(percentage) of width and performs
    the operation only on that part of the image.
    Example: `blur image-name dest-image-name split p`
22. `run script-file`: Load and run the script commands in the specified file. The file should be a
    txt file.

## Usage

1. Run the jar file provided in res/ folder.

2. To do this Open a command-prompt/terminal and navigate to that folder.

   With command-line arguments (file) - Type `java -jar CS5010_A4.jar -file name-of-script.txt` and press
   ENTER.

   With command-line arguments (text-based environment) - Type `java -jar Program.jar -text ` and press 
   ENTER.

   Without command-line arguments - Type `java -jar CS5010_A4.jar` and press ENTER.
   Once the program starts a GUI pops up where there are many options with dropdowns and buttons.
   You can load an image and perform operations as you wish and save them to your desired folder. 

   ![GUI Screenshot.png](res%2FGUI%20Screenshot.png)

    Here's the screenshot of the UI. On the left of the image we can see the histogram and
    below there are options for various operations on the image.
    The button Open a file helps to load a file to the screen from any folder.
    The button Save a file saves the image to a particular folder.
    Split View is the preview mode of supported operations like blur, sharpen, sepia,
    luma-component and color-correct. You can enter any values between 0-100 for the spit view to 
    work properly in the textBox to the left of split view toggle button. 
    Sample images can be found in images/ folder.

3. There is one sample script which loads the image and performs all possible operations and stores
   the result in res/ folder. Type `run res/testScript.txt` to run this sample script when the program
   starts. You can type any of the commands mentioned above and test them. Make sure to load the
   image first and use the save command to save the results in the target folder.