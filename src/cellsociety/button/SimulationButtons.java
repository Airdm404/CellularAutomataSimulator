package cellsociety.button;


import cellsociety.Display;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * abstract class for all the buttons
 */

public abstract class SimulationButtons extends Button {
    protected Display simulationObj;

    public SimulationButtons (String displayText, Display simulationObj) {
        super(displayText);
        this.simulationObj = simulationObj;
        this.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));

    }

}
