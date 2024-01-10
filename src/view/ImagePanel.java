package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import model.Image;

/**
 * This class represents the Image panel which extends JPanel.
 */
public class ImagePanel extends JPanel {

  private BufferedImage bufferedImageimage;

  /**
   * Instantiates a new Image panel.
   */
  public ImagePanel() {
    super();
  }

  /**
   * Sets the image in the UI.
   *
   * @param image the image to be set in the UI.
   */
  protected void setPic(Image image) {
    int width = image.getWidth();
    int height = image.getHeight();
    int[][][] imageArray = image.getImage();
    BufferedImage pic = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color color = new Color(imageArray[i][j][0], imageArray[i][j][1], imageArray[i][j][2]);
        pic.setRGB(j, i, color.getRGB());
      }
    }
    this.bufferedImageimage = pic;
    repaint();
  }

  @Override
  public Dimension getPreferredSize() {
    if (bufferedImageimage != null) {
      return new Dimension(bufferedImageimage.getWidth(), bufferedImageimage.getHeight());
    }
    return super.getPreferredSize();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(bufferedImageimage, 0, 0, this);
  }

}
