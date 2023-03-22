package cellsociety.button;

import cellsociety.Display;

/**
 * button that enable the user to end the simulation
 */

public class EndSimulationButton extends SimulationButtons {
    public EndSimulationButton(String displayText, Display simulationObj) {
        super(displayText, simulationObj);
        this.setOnMouseClicked(e -> {
            simulationObj.endSimulation();
        });
    }
}
