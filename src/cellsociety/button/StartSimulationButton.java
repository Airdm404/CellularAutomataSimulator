package cellsociety.button;

import cellsociety.Display;
import cellsociety.button.SimulationButtons;
import cellsociety.exceptions.InvalidCellStateGivenException;

import java.io.IOException;

/**
 * button that enables the user to start a simulation
 */

public class StartSimulationButton extends SimulationButtons {
    public StartSimulationButton(String displayText, int translateX, int translateY, Display simulationObj) {
        super(displayText, simulationObj);
        this.setTranslateX(translateX);
        this.setTranslateY(translateY);

        this.setOnMouseClicked(e -> {
            try {
                simulationObj.startSimulation();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (InvalidCellStateGivenException invalidCellStateGivenException) {
                invalidCellStateGivenException.printStackTrace();
            }
        });
    }






}
