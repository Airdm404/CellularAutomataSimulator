package cellsociety.button;

import cellsociety.Display;

/**
 * button that enables the user to run through the simulation
 */

public class RunThroughSimulationButton extends SimulationButtons {
    public RunThroughSimulationButton(String displayText, Display simulationObj) {
        super(displayText, simulationObj);
        this.setOnMouseClicked(e -> {
            simulationObj.runThroughSimulation();
        });
    }

}
