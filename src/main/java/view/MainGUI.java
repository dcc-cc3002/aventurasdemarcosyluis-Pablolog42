package view;

import aventurasdemarcoyluis.controller.GameController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


// https://docs.oracle.com/javafx/2/ui_controls/text-field.htm

public class MainGUI extends Application {

    private Stage window;
    private Scene playerEnteringNameScene;
    private Scene gameScene;

    private GameController controller;
    private Button submitNameButton;

    private TextArea textOutputBox;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        controller = new GameController();

        window = primaryStage;
        window.setTitle("Las Flipantes Aventuras de Marco y Luis");

        /*
        First stage: Get player's name
         */

        submitNameButton = new Button("Set Name");
        GridPane layout = new GridPane();
        // Setea el encuadre de la app
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(5);
        layout.setHgap(5);
        //Defining the Name text field
        final TextField nameInputField = new TextField();
        nameInputField.setPromptText("Enter your first name.");
        nameInputField.setPrefColumnCount(10);
        //Defining the label
        final Label welcomeMessage = new Label("Bienvenido! Por favor, indica tu nombre:");
        GridPane.setConstraints(welcomeMessage,0,0);
        layout.getChildren().add(welcomeMessage);

        // Se agrega el field que setea el nombre
        GridPane.setConstraints(nameInputField, 0, 1);
        layout.getChildren().add(nameInputField);
        // Se agrega boton que setea nombre
        GridPane.setConstraints(submitNameButton, 1, 1);
        layout.getChildren().add(submitNameButton);
        //Setting an action for the Submit button
        // We handle the button event directly in an anonimus handler class.
        submitNameButton.setOnAction(e -> {
            controller.setPlayer(nameInputField.getText());
            window.setScene(gameScene);
        });
        playerEnteringNameScene = new Scene(layout,350,70);


        /*
        Second stage: The actual Game
         */

        // Setup ////////////
        GridPane gameSceneLayout = new GridPane();
        // Setea el encuadre de la app
        gameSceneLayout.setPadding(new Insets(10, 10, 10, 10));
        gameSceneLayout.setVgap(5);
        gameSceneLayout.setHgap(5);
        //////////////


        //Phase Label
        final Label currentPhaseLabel = new Label("La fase actual de juego es: " + controller.getCurrentPhase().toString());
        GridPane.setConstraints(currentPhaseLabel,2,0);
        gameSceneLayout.getChildren().add(currentPhaseLabel);

        // Player name Label
        final Label playerNameLabel = new Label("Hola!, " + controller.getPlayer().getPlayerName());
        GridPane.setConstraints(playerNameLabel,0,0);
        gameSceneLayout.getChildren().add(playerNameLabel);


        gameScene =  new Scene(gameSceneLayout, 1000,600);






        /*
        Startpoint for the UI
         */
        window.setScene(playerEnteringNameScene);
        window.show();

    }
}
