package cellsociety;


import cellsociety.button.EndSimulationButton;
import cellsociety.button.LoadConfigurationButton;
import cellsociety.button.PauseSimulationButton;
import cellsociety.button.RunThroughSimulationButton;
import cellsociety.button.SimulationButtons;
import cellsociety.button.StartSimulationButton;
import cellsociety.button.StepThroughSimulationButton;
import cellsociety.exceptions.CSVDimensionsException;
import cellsociety.exceptions.ClassOrMethodNotFoundException;


import java.io.File;

import java.io.IOException;

import java.util.ResourceBundle;
import cellsociety.exceptions.InvalidCellStateGivenException;
import cellsociety.button.DoubleSpeedSimulationButton;
import cellsociety.button.HalfSpeedSimulationButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class is the main class of the frontend of the program
 * and handles the display of the simulation
 *
 * @author Edem Ahorlu
 * @author Yixuan Li
 * @author Christian Welch
 */






public class Display extends Application {

  public static final int SCREEN_SIZE = 600;
  public static final Paint BACKGROUND = Color.AZURE;
  public static final int FRAMES_PER_SECOND = 20;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public String RESOURCES_BUNDLE_FILE_NAME = "English";
  public static final int START_BUTTON_XPOSITION = SCREEN_SIZE / 4;
  public static final int START_BUTTON_YPOSITION = SCREEN_SIZE / 3 ;
  public String CSS_FILENAME = "Light.css";
  private boolean PAUSED = false;
  private ResourceBundle buttonNames = ResourceBundle.getBundle(RESOURCES_BUNDLE_FILE_NAME);
  private final String TITLE = buttonNames.getString("GameTitle");



  private Stage stage;
  private Scene welcomeScreenScene;
  private Scene simulationScene;
  private KeyFrame frame;
  private Timeline animation;
  private Group simulationRoot;
  private Group welcomeSceneRoot;
  private SimulationController mySimulationController;
  private SimulationButtons loadConfigurationButton;

  private ComboBox selectLanguage;
  private ComboBox selectAppearance;
  private ComboBox selectSimulationType;
  private final String[] languageArray = {"English", "French", "German", "Spanish"};
  private final String[] appearanceArray = {"Light", "Dark", "Duke"};
  private final String[] simulationArray = {"GameOfLife", "Percolation", "RockPaperScissor",
                                      "SpreadingOfFire", "ModelOfSegregation", "WaTorWorld"};
  private String simulationType = "";


  private void createLanguageSelectionBox() {
    selectLanguage = new ComboBox(FXCollections.observableArrayList(languageArray));
    selectLanguage.getStyleClass().add("welcome-button");
    selectLanguage.setValue("Language ");
    selectLanguage.setTranslateX(START_BUTTON_XPOSITION);
    selectLanguage.setTranslateY(SCREEN_SIZE/2);
    selectLanguage.setOnAction(resetLanguage());
    welcomeSceneRoot.getChildren().add(selectLanguage);
  }



  /**
   * changes the properties file when a language is selected from the language dropdown menu
   * on the program hence changing the language of the program
   * languages available: French, German, English, Spanish
   *
   * @return event which sets the selected language
   */

  public EventHandler<ActionEvent> resetLanguage() {
    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e)
      {
        buttonNames = ResourceBundle.getBundle((String) selectLanguage.getValue());
      }
    };

    return event;
  }


  private void createAppearanceBox() {
    selectAppearance = new ComboBox(FXCollections.observableArrayList(appearanceArray));
    selectAppearance.getStyleClass().add("welcome-button");
    selectAppearance.setValue("Mode");
    selectAppearance.setTranslateX(START_BUTTON_XPOSITION);
    selectAppearance.setTranslateY(SCREEN_SIZE/1.5);
    selectAppearance.setOnAction(changeAppearance());
    welcomeSceneRoot.getChildren().add(selectAppearance);
  }



  /**
   * changes the css file when an appearance mode is selected from the mode dropdown menu
   * on the program hence changing the appearance of the simulation
   * appearances available: Light, Dark, Duke
   *
   * @return event which sets the selected appearance
   */

  public EventHandler<ActionEvent> changeAppearance() {
    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e)
      {
        CSS_FILENAME = (String) selectAppearance.getValue() + ".css";
      }
    };
    return event;
  }

  private void createSimulationSelectionBox() {
    selectSimulationType = new ComboBox(FXCollections.observableArrayList(simulationArray));
    selectSimulationType.getStyleClass().add("welcome-button");
    selectSimulationType.setValue("Select Simulation");
    selectSimulationType.setTranslateX(START_BUTTON_XPOSITION);
    selectSimulationType.setTranslateY(SCREEN_SIZE/6);
    selectSimulationType.setOnAction(changeSimulation());
    welcomeSceneRoot.getChildren().add(selectSimulationType);
  }




  /**
   * sets the simulation type to the simulation selected
   * from the Select Simulation dropdown menu
   *
   * simulations available: GameOfLife, Percolation, RockPaperScissor,
   * SpreadingOfFire, ModelOfSegregation, WaTorWorld
   *
   * @return event which sets the selected simulation
   */

  public EventHandler<ActionEvent> changeSimulation() {
    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e)
      {
        simulationType = (String) selectSimulationType.getValue();
      }
    };
    return event;
  }


  /**
   * method which loads the selected simulation and its properties when
   * the Start Simulation button is selected
   *
   * @throws IOException
   * @throws InvalidCellStateGivenException
   */




//Options:
  //simulationType: GameOfLife/Percolation/RockPaperScissor/SpreadingOfFire/ModelOfSegregation/WaTorWorld
  //neighborType: AllFirstLayerNeighbor/CardinalFistLayerNeighbor/CornerFirstLayerNeighbor
  //initialPattern: Default/Test1/Test2/Test3(for game of life, it's Default/Beacon/Blinker/Toad)
  //edgeType: Finite/Toroidal/Unbounded
  //shapeType: Square/Triangle/Hexagon

  public void startSimulation() throws IOException, InvalidCellStateGivenException {
    simulationScene = initializeSimulationScene(SCREEN_SIZE, SCREEN_SIZE, BACKGROUND);
    this.mySimulationController = new SimulationController(simulationType, "AllFirstLayerNeighbor",
            "Default", "Unbounded", "Square", simulationRoot);
    stage.setScene(simulationScene);
  }


  /**
   * main: launches simulation
   * @param args
   */

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * builds the simulation stage and animation
   *
   * @param stage
   * @throws CSVDimensionsException
   * @throws IOException
   * @throws ClassOrMethodNotFoundException
   * @throws InvalidCellStateGivenException
   */

  @Override

  public void start(Stage stage)
          throws CSVDimensionsException, IOException, ClassOrMethodNotFoundException, InvalidCellStateGivenException {

    this.stage = stage;
    welcomeScreenScene = initializeWelcomeScene(SCREEN_SIZE, SCREEN_SIZE, BACKGROUND);

    stage.setScene(welcomeScreenScene);
    stage.setTitle(TITLE);
    stage.show();
    frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> {
      try {
        step(SECOND_DELAY);
      } catch (ClassOrMethodNotFoundException classOrMethodNotFoundException) {
        //TODO: handling ClassOrMethodNotFoundException
      }
    });
    animation = new Timeline();
    animation.getKeyFrames().add(frame);
    animation.setCycleCount(Timeline.INDEFINITE);
  }


  /**
   * initializes the scene responsible for the welcome screen
   *
   * @param width width of the welcome scene
   * @param height height of the welcome scene
   * @param background background color of the welcome scene
   * @return welcome scene
   */



  Scene initializeWelcomeScene(int width, int height, Paint background) {
    welcomeSceneRoot = new Group();
    Scene scene = new Scene(welcomeSceneRoot, width, height, background);
    scene.getStylesheets().add(CSS_FILENAME);
    createStartSimulationButton();
    createLanguageSelectionBox();
    createAppearanceBox();
    createSimulationSelectionBox();
    return scene;
  }



  /**
   * initializes the scene responsible for the simulation screen
   *
   * @param width width of the simulation scene
   * @param height height of the simulation scene
   * @param background background color of the simulation scene
   * @return simulation scene
   */


  Scene initializeSimulationScene(int width, int height, Paint background) {
    simulationRoot = new Group();
    BorderPane sceneRoot = new BorderPane();
    sceneRoot.setTop(makeUpSideSimulationButtons());
    sceneRoot.setCenter(this.simulationRoot);
    sceneRoot.setLeft(makeLeftSideSimulationButtons());
    sceneRoot.setId("backgroundColor");
    Scene scene = new Scene(sceneRoot, width, height, background);
    scene.getStylesheets().add(CSS_FILENAME);
    return scene;
  }

  /**
   *step function that handles the simulation after each step.
   * Updates the state and movement of the cells
   *
   * @param elapsedTime
   * @throws ClassOrMethodNotFoundException
   */


  void step(double elapsedTime) throws ClassOrMethodNotFoundException {
    mySimulationController.updateBothCellAndCellBlocks();
  }

  /**
   * method that pauses simulation
   */


  public void pauseSimulation() {
    if (!PAUSED) {
      animation.pause();
    } else {
      animation.play();
    }
    PAUSED = !PAUSED;
  }


  /**
   * method that ends simulation
   */

  public void endSimulation() {
    animation.stop();
    stage.close();
  }


  double getSimulationSpeed() {
    double speed = animation.getRate();
    return speed;
  }


  /**
   * method to double simulation speed
   */


  public void doubleSimulationSpeed() {
    animation.setRate(getSimulationSpeed() * 2);
  }


  /**
   * method to half simulation speed
   */

  public void halfSimulationSpeed() {
    animation.setRate(getSimulationSpeed() * 0.5);
  }

  /**
   * method to let the simulation animate
   */


  public void runThroughSimulation() {
    animation.play();
  }


  /**
   * method to step through the simulation by
   * hand without animation
   */


  public void stepThroughSimulation() throws ClassOrMethodNotFoundException {
    animation.stop();
    mySimulationController.updateBothCellAndCellBlocks();
  }


  private Node makeUpSideSimulationButtons() {
    HBox topSimulationButtons = new HBox();
    //topSimulationButtons.setSpacing(TOP_BUTTONS_SPACING);
    topSimulationButtons.getStyleClass().add("simulation-button");

    SimulationButtons pauseButton = new PauseSimulationButton(buttonNames.getString("PauseText"), this);
    System.out.println(buttonNames);
    topSimulationButtons.getChildren().add(pauseButton);
    SimulationButtons endButton = new EndSimulationButton(buttonNames.getString("EndText"), this);
    topSimulationButtons.getChildren().add(endButton);
    SimulationButtons stepButton = new StepThroughSimulationButton(buttonNames.getString("StepText"), this);
    topSimulationButtons.getChildren().add(stepButton);
    SimulationButtons playButton = new RunThroughSimulationButton(buttonNames.getString("PlayText"), this);
    topSimulationButtons.getChildren().add(playButton);
    loadConfigurationButton = new LoadConfigurationButton("Load New Configuration", this);
    topSimulationButtons.getChildren().add(loadConfigurationButton);

    return topSimulationButtons;
  }


  private Node makeLeftSideSimulationButtons() {
    VBox rightSideSimulationButtons = new VBox();
    rightSideSimulationButtons.setSpacing(10);
    rightSideSimulationButtons.getStyleClass().add("simulation-button");

    SimulationButtons doubleSpeedButton = new DoubleSpeedSimulationButton(buttonNames.getString("DoubleSpeedText"), this);
    rightSideSimulationButtons.getChildren().add(doubleSpeedButton);
    SimulationButtons halfSpeedButton = new HalfSpeedSimulationButton(buttonNames.getString("HalfSpeedText"), this);
    rightSideSimulationButtons.getChildren().add(halfSpeedButton);


    return rightSideSimulationButtons;
  }


  private void createStartSimulationButton() {
    SimulationButtons startSimulationButton = new StartSimulationButton(
        buttonNames.getString("StartText"), START_BUTTON_XPOSITION, START_BUTTON_YPOSITION, this);
    startSimulationButton.getStyleClass().add("welcome-button");
    welcomeSceneRoot.getChildren().add(startSimulationButton);
  }


  /**
   * method that creates the file chooser to enable the user load
   * different configuration files
   */



  public void openFileChooser() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialDirectory(new File("data/"));
    fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("CSV File", "*.csv")
    );
    fileChooser.setTitle("Load Configuration File");
    fileChooser.showOpenDialog(stage);
  }



}
