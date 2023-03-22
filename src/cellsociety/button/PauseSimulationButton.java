package cellsociety.button;

import cellsociety.Display;

/**
 * button that enables the user to pause and unpause the simulation
 */

public class PauseSimulationButton extends SimulationButtons {
    public PauseSimulationButton(String displayText, Display simulationObj) {
        super(displayText, simulationObj);
        this.setOnMouseClicked(e -> {
            simulationObj.pauseSimulation();
        });
    }
}
