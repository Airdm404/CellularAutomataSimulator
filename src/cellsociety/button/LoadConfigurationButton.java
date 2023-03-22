package cellsociety.button;

import cellsociety.Display;

/**
 * button that enable the user to load a new configuration
 */

public class LoadConfigurationButton extends SimulationButtons {

  public LoadConfigurationButton(String displayText, Display simulationObj){
    super(displayText, simulationObj);
    this.setOnMouseClicked(mouseEvent -> simulationObj.openFileChooser());
  }
}
