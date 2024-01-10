package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ImageGUI;
import controller.Result;
import model.Image;


/**
 * This class represents GUI of image processing application.
 */
public class ImageGraphicalView extends JFrame implements ActionListener, View {
  private ImagePanel histogramPanel;
  private ImagePanel imagePanel;
  private JToggleButton splitButton;

  private ArrayList<String> splitCommands;
  private boolean hasImage = false;
  private ImageGUI imageGUIController;
  private JTextField splitPercentage;

  private String command;

  /**
   * Instantiates a new Image graphical view.
   */
  public ImageGraphicalView() {
    super();
    setTitle("Images");
    this.setSize(856, 856);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());


    imagePanel = new ImagePanel();
    imagePanel.setLayout(new BorderLayout());
    imagePanel.setPreferredSize(new Dimension(600, 600));

    histogramPanel = new ImagePanel();
    histogramPanel.setLayout(new BorderLayout());
    histogramPanel.setPreferredSize(new Dimension(256, 256));

    JScrollPane jsp = new JScrollPane(imagePanel);
    this.add(histogramPanel, BorderLayout.WEST);
    this.add(jsp, BorderLayout.CENTER);

    //selection list panel
    JPanel commandPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    commandPanel.setBorder(BorderFactory.createTitledBorder("Command options"));
    commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.PAGE_AXIS));
    add(commandPanel, BorderLayout.SOUTH);

    JLabel comboboxDisplay = new JLabel("Choose action");
    commandPanel.add(comboboxDisplay);
    String[] options = {"red-component", "green-component", "blue-component", "sharpen", "luma"
            + "-component", "blur", "sepia",
        "vertical-flip", "horizontal-flip", "color-correct", "compress", "level-adjustment"};
    splitCommands = new ArrayList<>();
    splitCommands.add("sharpen");
    splitCommands.add("color-correct");
    splitCommands.add("blur");
    splitCommands.add("luma-component");
    splitCommands.add("sepia");
    JComboBox<String> option = new JComboBox<String>();
    //the event listener when an option is selected
    option.setActionCommand("Command");
    option.addActionListener(this);
    for (int i = 0; i < options.length; i++) {
      option.addItem(options[i]);
    }
    command = "red-component";
    JButton confirmButton;
    confirmButton = new JButton("Confirm");
    confirmButton.addActionListener(this);

    commandPanel.add(option);
    commandPanel.add(confirmButton);

    //Control panels
    JPanel controlPanel = new JPanel();
    controlPanel.setLayout(new FlowLayout());
    add(controlPanel, BorderLayout.NORTH);

    //file open
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);
    controlPanel.add(fileOpenButton);

    //file save
    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this);
    controlPanel.add(fileSaveButton);

    //split control
    splitPercentage = new JTextField(3);
    splitPercentage.setText("0");
    splitButton = new JToggleButton("Split View : OFF");
    splitButton.setActionCommand("Split");
    splitButton.addActionListener(this);
    controlPanel.add(splitPercentage, BorderLayout.SOUTH);
    controlPanel.add(splitButton);
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    switch (event.getActionCommand()) {
      case "Command":
        JComboBox<String> box = (JComboBox<String>) event.getSource();
        command = (String) box.getSelectedItem();
        break;
      case "Split":
        if (splitButton.isSelected()) {
          splitButton.setText("Split View : ON");
          if (splitCommands.contains(command)) {
            imageGUIController.executeUserCommandInput("split", command, splitPercentage.getText());
          }
        } else {
          splitButton.setText("Split View : OFF");
          if (splitCommands.contains(command)) {
            imageGUIController.executeUserCommandInput("split", command, null);
          }
        }
        break;
      case "Confirm":
        if (!hasImage) {
          displayMessage(new Result<>(null, "Load an image first\n", true));
          break;
        }
        String argument = "";
        switch (command) {
          case "compress":
            argument = JOptionPane.showInputDialog("Compress percentage");
            break;
          case "level-adjustment":
            argument = JOptionPane.showInputDialog("Black") + " ";
            argument += JOptionPane.showInputDialog("Mid") + " ";
            argument += JOptionPane.showInputDialog("White");
            break;
          default:
            break;
        }
        imageGUIController.executeUserCommandInput(command, argument, "100");
        break;
      case "Open file": {
        hasImage = true;
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & JPEG & & PNG & ppm Images", "jpg", "jpeg", "ppm", "png");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(ImageGraphicalView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          imageGUIController.executeUserCommandInput("Load", f.getAbsolutePath(), null);
        }
      }
      break;
      case "Save file": {
        if (!hasImage) {
          displayMessage(new Result<>(null, "Load an image first\n", true));
          break;
        }
        final JFileChooser fchooser = new JFileChooser("");
        int retvalue = fchooser.showSaveDialog(ImageGraphicalView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          System.out.println(f.getAbsolutePath());
          imageGUIController.executeUserCommandInput("Save", f.getAbsolutePath(), null);
        }
      }
      break;
      default:
        break;
    }
  }


  @Override
  public <T> void displayResult(Result<T> result) {
    if (!result.isError() && result.getData() != null) {
      ArrayList<Image> images = (ArrayList<Image>) result.getData();
      imagePanel.setPic(images.get(0));
      histogramPanel.setPic(images.get(1));
    }
  }

  @Override
  public <T> void displayMessage(Result<T> result) {
    JOptionPane.showMessageDialog(ImageGraphicalView.this, result.getMessage(), "Message",
            JOptionPane.PLAIN_MESSAGE);
  }

  @Override
  public void addController(ImageGUI imageGUIController) {
    this.imageGUIController = imageGUIController;
  }
}
