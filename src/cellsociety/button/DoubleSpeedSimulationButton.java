package cellsociety.button;

import cellsociety.Display;
import cellsociety.button.SimulationButtons;

/**
 * button that enable the user to speed up the simulation
 */

public class DoubleSpeedSimulationButton extends SimulationButtons {
    public DoubleSpeedSimulationButton(String displayText, Display simulationObj) {
        super(displayText, simulationObj);
        this.setOnMouseClicked(e -> {
            simulationObj.doubleSimulationSpeed();
        });
    }
}
