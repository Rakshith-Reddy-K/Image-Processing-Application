package controller;

import view.View;

/**
 * The interface Image GUI for the controller.
 */
public interface ImageGUI {

  /**
   * Set the view.
   *
   * @param view the view.
   */
  void setView(View view);

  /**
   * Execute user command input.
   *
   * @param userInputCommand the user input command.
   * @param args             the extra arguments for the command.
   * @param percentage       the split percentage value.
   */
  void executeUserCommandInput(String userInputCommand, String args, String percentage);
}
