package cellsociety.button;

import cellsociety.Display;
import cellsociety.button.SimulationButtons;

/**
 * button that enables the user to slow down the simulation
 */

public class HalfSpeedSimulationButton extends SimulationButtons {
    public HalfSpeedSimulationButton(String displayText, Display simulationObj) {
        super(displayText, simulationObj);
        this.setOnMouseClicked(e -> {
            simulationObj.halfSimulationSpeed();
        });
    }
}
