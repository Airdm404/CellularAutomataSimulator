package cellsociety.button;

import cellsociety.Display;
import cellsociety.button.SimulationButtons;
import cellsociety.exceptions.ClassOrMethodNotFoundException;

/**
 * button that enables the user to step to the next iteration of the simulation
 */

public class StepThroughSimulationButton extends SimulationButtons {

    public StepThroughSimulationButton(String displayText, Display simulationObj) {
        super(displayText, simulationObj);
        this.setOnMouseClicked(e -> {
            try {
                simulationObj.stepThroughSimulation();
            } catch (ClassOrMethodNotFoundException classOrMethodNotFoundException) {
                //TODO: handling ClassOrMethodNotFoundException
            }
        });
    }
}
